package com.wangxin.langshu.business.edu.service.user.pccommon;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aliyuncs.exceptions.ClientException;
import com.wangxin.langshu.business.edu.dao.user.SendSmsLogDao;
import com.wangxin.langshu.business.edu.dao.user.StudentDao;
import com.wangxin.langshu.business.edu.dao.user.UserDao;
import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLog;
import com.wangxin.langshu.business.edu.entity.user.old.Student;
import com.wangxin.langshu.business.edu.entity.user.old.User;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebDotService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointService;
import com.wangxin.langshu.business.edu.iservice.system.pcboss.IPcBossLoginLogService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IApiUserInfoBiz;
import com.wangxin.langshu.business.edu.vo.market.dot.DotSaveVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointSaveVo;
import com.wangxin.langshu.business.edu.vo.system.loginLog.LoginLogSaveVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentVO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserLoginCodeBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserLoginPasswordBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserRegisterBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserSendCodeBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.UserUpdateBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO;
import com.wangxin.langshu.business.util.GlobalVar;
import com.wangxin.langshu.business.util.PlatformUtil;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.DotGainRuleEnum;
import com.wangxin.langshu.business.util.enums.IsSuccessEnum;
import com.wangxin.langshu.business.util.enums.LoginStatusEnum;
import com.wangxin.langshu.business.util.enums.OperatePlatEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.UserTypeEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.Constants;
import com.wangxin.langshu.business.util.tools.JWTUtil;
import com.wangxin.langshu.business.util.tools.NOUtil;
import com.wangxin.langshu.business.util.tools.StrUtil;
import com.xiaoleilu.hutool.crypto.digest.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

/**
 * 用户基本信息
 *
 *  
 */
@Service
public class ApiUserInfoBiz extends BaseBiz implements IApiUserInfoBiz{





	@Autowired
	private UserDao userDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private SendSmsLogDao sendSmsLogDao;
	@Autowired
	private ISystemConfigService systemConfigService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private IPcWebPointService pcWebPointService;
	
	@Autowired
	private IPcWebDotService pcWebDotService;
	
	@Autowired
	private IPcBossLoginLogService pcBossLoginLogService;
	


	@Transactional
	public void save(List<UserRegisterBO> userRegisterBOs) {
		for(UserRegisterBO userRegisterBO: userRegisterBOs){
			if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
				throw new BaseException("手机号不能为空");
			}
			if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
				throw new BaseException("密码不能为空");
			}
			// 手机号重复校验
			User user = userDao.getByMobile(userRegisterBO.getMobile());
			if (null == user) {
				// 用户注册
				user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword(), userRegisterBO.getRealName(),PlatformUtil.id);
			}
		}
	}
	
	@Transactional
	public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) {
		if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
			return Result.error("密码不能为空");
		}
		

		// 密码校验
		if (!userRegisterBO.getPassword().equals(userRegisterBO.getRepassword())) {
			return Result.error("两次密码不一致");
		}

		

		// 验证码校验
		String redisSmsCode = redisTemplate.opsForValue().get(RedisKey.SMS_PREFIX_+ userRegisterBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("请输入验证码");
		}
		if (!redisSmsCode.equals(userRegisterBO.getCode())) {
			return Result.error("验证码不正确，请重新输入");
		}

		// 手机号重复校验
		User user = userDao.getByMobile(userRegisterBO.getMobile());
		if (null != user) {
			return Result.error("该手机号已经注册，请更换手机号");
		}

		// 用户注册
		user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword(),userRegisterBO.getRealName(), PlatformUtil.id);

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
		return Result.success(dto);
	}

	public Result<UserLoginDTO> loginPassword(UserLoginPasswordBO userLoginPasswordBO) {
		/*
		if (StringUtils.isEmpty(userLoginPasswordBO.getClientId())) {
			return Result.error("clientId不能为空");
		}*/
		if (StringUtils.isEmpty(userLoginPasswordBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(userLoginPasswordBO.getPassword())) {
			return Result.error("密码不能为空");
		}
		/*
		if (!PlatformUtil.id.equals(userLoginPasswordBO.getClientId())) {
			return Result.error("该平台不存在");
		}*/
		

		// 密码错误次数校验
		String loginFailCountString = redisTemplate.opsForValue().get(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile());
		if(!StringUtils.isEmpty(loginFailCountString)){
			int loginFailCount = Integer.parseInt(loginFailCountString);
			if(loginFailCount>GlobalVar.LOGIN_FAIL_COUNT){
				return Result.error("账号或者密码不正确已经超过"+GlobalVar.LOGIN_FAIL_COUNT+"次，请"+GlobalVar.LOGIN_FAIL_FROZEN_MINUTES+"分钟之后再试");
			}
		}

		// 用户校验
		User user = userDao.getByMobile(userLoginPasswordBO.getMobile());
		if (null == user) {
			return Result.error("账号不存在");
		}
		// 密码校验
		if (!DigestUtil.sha1Hex(user.getPswSalt() + userLoginPasswordBO.getPassword()).equals(user.getUserPsw())) {
			loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.FAIL, userLoginPasswordBO.getIp(),userLoginPasswordBO.getLoginPlatform());
			// 放入缓存，错误次数+1
			loginFailCountString = redisTemplate.opsForValue().get(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile());
			if(!StringUtils.isEmpty(loginFailCountString)){
				int loginFailCount = Integer.parseInt(loginFailCountString);
				loginFailCount = loginFailCount + 1;
				redisTemplate.opsForValue().set(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile(), loginFailCount+"", GlobalVar.LOGIN_FAIL_FROZEN_MINUTES, TimeUnit.MINUTES);
			}else{
				redisTemplate.opsForValue().set(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile(), "1", GlobalVar.LOGIN_FAIL_FROZEN_MINUTES, TimeUnit.MINUTES);
			}
			return Result.error("账号或者密码不正确");
		}
		
		Student student = studentDao.getByMobile(userLoginPasswordBO.getMobile());
		if(student.getValidValue()==0){
			return Result.error("账号被禁用，无法登陆");
		}
		

		// 登录日志
		loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginPasswordBO.getIp(),userLoginPasswordBO.getLoginPlatform());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
		dto.setRealName(student.getRealName()==null?student.getNickname():student.getRealName());

		// 登录成功，存入缓存，单点登录使用
		String operatePlatform = "";
		long minites = 1;
		if(userLoginPasswordBO.getLoginPlatform()==OperatePlatEnum.PCWEB.getCode()) {
			operatePlatform = OperatePlatEnum.PCWEB.getCodeName();
			minites = 1*60L;
		}
		if(userLoginPasswordBO.getLoginPlatform()==OperatePlatEnum.MOBILE.getCode()) {
			operatePlatform = OperatePlatEnum.MOBILE.getCodeName();
			minites = 60*24*60L;
		}
		if(userLoginPasswordBO.getLoginPlatform()==OperatePlatEnum.PCBOSS.getCode()) {
			operatePlatform = OperatePlatEnum.PCBOSS.getCodeName();
			minites = 1*60L;
		}
		redisTemplate.opsForValue().set(dto.getUserNo().toString()+"_"+operatePlatform, dto.getToken(),
				minites, TimeUnit.MINUTES);
		
		//登陆成功，增加点券
		pcWebDotService.dotGainRule(DotGainRuleEnum.LOGIN, user.getUserNo());

		return Result.success(dto);
	}

	public Result<UserLoginDTO> loginCode(UserLoginCodeBO userLoginCodeBO) {
		if (StringUtils.isEmpty(userLoginCodeBO.getClientId())) {
			return Result.error("clientId不能为空");
		}
		if (StringUtils.isEmpty(userLoginCodeBO.getMobile())) {
			return Result.error("手机号码不能为空");
		}
		if (!PlatformUtil.id.equals(userLoginCodeBO.getClientId())) {
			return Result.error("该平台不存在");
		}
		

		// 登录错误次数的校验

		// 验证码校验
		String redisSmsCode = redisTemplate.opsForValue().get(PlatformUtil.id + userLoginCodeBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("验证码已经过期，请重新获取");
		}

		User user = userDao.getByMobile(userLoginCodeBO.getMobile());
		if (null == user) {
			return Result.error("该用户不存在");
		}

		if (!redisSmsCode.equals(userLoginCodeBO.getCode())) {
			loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.FAIL, userLoginCodeBO.getIp(),userLoginCodeBO.getLoginPlatform());
			// 缓存控制错误次数
			return Result.error("验证码不正确,重新输入");
		}

		// 清空缓存
		redisTemplate.delete(PlatformUtil.id + userLoginCodeBO.getMobile());

		// 登录日志
		loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginCodeBO.getIp(),userLoginCodeBO.getLoginPlatform());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		// 登录成功，存入缓存，单点登录使用
		// redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
		// 1, TimeUnit.DAYS);
		return Result.success(dto);
	}

	public Result<String> sendCode(UserSendCodeBO userSendCodeBO) {
		if (StringUtils.isEmpty(userSendCodeBO.getClientId())) {
			return Result.error("clientId不能为空");
		}
		if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(userSendCodeBO.getMobile()).matches()) {
			return Result.error("手机号码格式不正确");
		}

		/*
		if (!PlatformUtil.id.equals(userSendCodeBO.getClientId())) {
			return Result.error("该平台不存在");
		}
		*/
		

		//SysVO sys = bossSysBiz.getSys();
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
		
		if (ObjectUtil.isNull(systemConfigViewVo)) {
			return Result.error("找不到系统配置信息");
		}
		// 创建日志实例
		SendSmsLog sendSmsLog = new SendSmsLog();
		sendSmsLog.setMobile(userSendCodeBO.getMobile());
		sendSmsLog.setTemplate(systemConfigViewVo.getSmsCode());
		// 随机生成验证码
		sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
		try {
			// 发送验证码
			boolean result = AliyunUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class));
			// 发送成功，验证码存入缓存：5分钟有效
			if (result) {
				/*
				redisTemplate.opsForValue().set(userSendCodeBO.getClientId() + userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);*/
				redisTemplate.opsForValue().set(RedisKey.SMS_PREFIX_+ userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);

				sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
				sendSmsLogDao.save(sendSmsLog);
				return Result.success("发送成功");
			}
			// 发送失败
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			throw new BaseException("发送失败");
		} catch (ClientException e) {
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			logger.error("发送失败，原因={}", e.getErrMsg());
			return Result.error("发送失败");
		}
	}

	private User register(String mobile, String password, String realName, String clientId) {
		// 用户基本信息
		User user = new User();
		user.setUserNo(NOUtil.getUserNo());
		user.setMobile(mobile);
		user.setPswSalt(StrUtil.get32UUID());
		user.setUserPsw(DigestUtil.sha1Hex(user.getPswSalt() + password));
		userDao.save(user);

		// 用户其他信息
		Student student = new Student();
		student.setUserNo(user.getUserNo());
		student.setUserType(UserTypeEnum.USER.getCode());
		student.setMobile(user.getMobile());
		if(!StringUtils.isEmpty(realName)){
			student.setRealName(realName);
		}
		studentDao.save(student);
		
		/**初始化数据
		1、初始化点券
		2、初始化积分
		*/
		PointSaveVo pointSaveVo = new PointSaveVo();
		pointSaveVo.setBindUserNo(user.getUserNo());
		pointSaveVo.setTotalPoint(0);
		pcWebPointService.savePoint(pointSaveVo);
		
		DotSaveVo dotSaveVo = new DotSaveVo();
		dotSaveVo.setBindUserNo(user.getUserNo());
		dotSaveVo.setTotalDot(0);
		pcWebDotService.saveDot(dotSaveVo);

		return user;
	}

	private void loginLog(String userNo, String clientId, LoginStatusEnum status, String ip, Integer loginPlatform) {
		LoginLogSaveVo record = new LoginLogSaveVo();
		record.setLoginUserNo(userNo);
		record.setClientId(clientId);
		record.setLoginStatus(status.getCode());
		record.setLoginIp(ip);
		record.setLoginPlatform(loginPlatform);
		pcBossLoginLogService.saveEntity(record);
	}

	public Result<Integer> updatePassword(UserUpdateBO userUpdateBO) {
		if (StringUtils.isEmpty(userUpdateBO.getMobile())) {
			return Result.error("手机号为空,请重试");
		}
		if (StringUtils.isEmpty(userUpdateBO.getCode())) {
			return Result.error("验证码不能为空");
		}
		if (StringUtils.isEmpty(userUpdateBO.getClientId())) {
			return Result.error("clientId不能为空");
		}

		/*
		if (!PlatformUtil.id.equals(userUpdateBO.getClientId())) {
			return Result.error("该平台不存在");
		}*/
		
		/*
		String redisCode = redisTemplate.opsForValue().get(PlatformUtil.id + userUpdateBO.getMobile());
		*/
		String redisCode = redisTemplate.opsForValue().get(RedisKey.SMS_PREFIX_ + userUpdateBO.getMobile());

		if (StringUtils.isEmpty(redisCode)) {
			return Result.error("请输入验证码");
		}
		if (!userUpdateBO.getCode().equals(redisCode)) {
			return Result.error("验证码匹配不正确");
		}
		// 手机号去空处理
		String mobile = userUpdateBO.getMobile().replaceAll(" +", "");

		if (StringUtils.isEmpty(userUpdateBO.getConfirmPassword())) {
			return Result.error("新登录密码为空,请重试");
		}
		if (!userUpdateBO.getNewPassword().equals(userUpdateBO.getConfirmPassword())) {
			return Result.error("密码输入不一致，请重试");
		}

		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("没找到用户信息,请重试");
		}
		if (DigestUtil.sha1Hex(user.getPswSalt() + userUpdateBO.getNewPassword()).equals(user.getUserPsw())) {
			return Result.error("输入的密码与原密码一致,请重试");
		}

		// 更新密码
		User bean = new User();
		bean.setId(user.getId());
		bean.setPswSalt(StrUtil.get32UUID());
		bean.setUserPsw(DigestUtil.sha1Hex(bean.getPswSalt() + userUpdateBO.getNewPassword()));
		int result = userDao.updateById(bean);
		return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

	@Override
	public StudentVO findStudentByMobile(String mobile) {
		StudentVO studentVO = new StudentVO();
		Student student =  studentDao.getByMobile(mobile);
		studentVO.setAge(student.getAge());
		studentVO.setCreateTime(student.getCreateTime());
		studentVO.setModifiedTime(student.getModifiedTime());
		studentVO.setHeadImgUrl(student.getHeadImgUrl());
		studentVO.setId(student.getId());
		studentVO.setMobile(student.getMobile());
		studentVO.setNickname(student.getNickname());
		studentVO.setRemark(student.getRemark());
		studentVO.setSex(student.getSex());
		studentVO.setValidValue(student.getValidValue());
		studentVO.setUserNo(student.getUserNo());
		studentVO.setUserType(student.getUserType());
		studentVO.setRealName(student.getRealName());
		
		return studentVO;
	}

	@Override
	public Result<Integer> bossResetPassword(UserUpdateBO userUpdateBO) {
		if (StringUtils.isEmpty(userUpdateBO.getMobile())) {
			return Result.error("手机号为空,请重试");
		}
		// 手机号去空处理
		String mobile = userUpdateBO.getMobile().replaceAll(" +", "");
		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("没找到用户信息,请重试");
		}

		// 更新密码
		User bean = new User();
		bean.setId(user.getId());
		bean.setPswSalt(StrUtil.get32UUID());
		bean.setUserPsw(DigestUtil.sha1Hex(bean.getPswSalt() + userUpdateBO.getNewPassword()));
		int result = userDao.updateById(bean);
		return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

}
