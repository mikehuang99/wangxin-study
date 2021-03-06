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
 * ??????????????????
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
				throw new BaseException("?????????????????????");
			}
			if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
				throw new BaseException("??????????????????");
			}
			// ?????????????????????
			User user = userDao.getByMobile(userRegisterBO.getMobile());
			if (null == user) {
				// ????????????
				user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword(), userRegisterBO.getRealName(),PlatformUtil.id);
			}
		}
	}
	
	@Transactional
	public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) {
		if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
			return Result.error("?????????????????????");
		}
		if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
			return Result.error("??????????????????");
		}
		

		// ????????????
		if (!userRegisterBO.getPassword().equals(userRegisterBO.getRepassword())) {
			return Result.error("?????????????????????");
		}

		

		// ???????????????
		String redisSmsCode = redisTemplate.opsForValue().get(RedisKey.SMS_PREFIX_+ userRegisterBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("??????????????????");
		}
		if (!redisSmsCode.equals(userRegisterBO.getCode())) {
			return Result.error("????????????????????????????????????");
		}

		// ?????????????????????
		User user = userDao.getByMobile(userRegisterBO.getMobile());
		if (null != user) {
			return Result.error("?????????????????????????????????????????????");
		}

		// ????????????
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
			return Result.error("clientId????????????");
		}*/
		if (StringUtils.isEmpty(userLoginPasswordBO.getMobile())) {
			return Result.error("?????????????????????");
		}
		if (StringUtils.isEmpty(userLoginPasswordBO.getPassword())) {
			return Result.error("??????????????????");
		}
		/*
		if (!PlatformUtil.id.equals(userLoginPasswordBO.getClientId())) {
			return Result.error("??????????????????");
		}*/
		

		// ????????????????????????
		String loginFailCountString = redisTemplate.opsForValue().get(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile());
		if(!StringUtils.isEmpty(loginFailCountString)){
			int loginFailCount = Integer.parseInt(loginFailCountString);
			if(loginFailCount>GlobalVar.LOGIN_FAIL_COUNT){
				return Result.error("???????????????????????????????????????"+GlobalVar.LOGIN_FAIL_COUNT+"?????????"+GlobalVar.LOGIN_FAIL_FROZEN_MINUTES+"??????????????????");
			}
		}

		// ????????????
		User user = userDao.getByMobile(userLoginPasswordBO.getMobile());
		if (null == user) {
			return Result.error("???????????????");
		}
		// ????????????
		if (!DigestUtil.sha1Hex(user.getPswSalt() + userLoginPasswordBO.getPassword()).equals(user.getUserPsw())) {
			loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.FAIL, userLoginPasswordBO.getIp(),userLoginPasswordBO.getLoginPlatform());
			// ???????????????????????????+1
			loginFailCountString = redisTemplate.opsForValue().get(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile());
			if(!StringUtils.isEmpty(loginFailCountString)){
				int loginFailCount = Integer.parseInt(loginFailCountString);
				loginFailCount = loginFailCount + 1;
				redisTemplate.opsForValue().set(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile(), loginFailCount+"", GlobalVar.LOGIN_FAIL_FROZEN_MINUTES, TimeUnit.MINUTES);
			}else{
				redisTemplate.opsForValue().set(RedisKey.LOGIN_FAIL_COUNT_PREFIX_+ userLoginPasswordBO.getMobile(), "1", GlobalVar.LOGIN_FAIL_FROZEN_MINUTES, TimeUnit.MINUTES);
			}
			return Result.error("???????????????????????????");
		}
		
		Student student = studentDao.getByMobile(userLoginPasswordBO.getMobile());
		if(student.getValidValue()==0){
			return Result.error("??????????????????????????????");
		}
		

		// ????????????
		loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginPasswordBO.getIp(),userLoginPasswordBO.getLoginPlatform());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
		dto.setRealName(student.getRealName()==null?student.getNickname():student.getRealName());

		// ????????????????????????????????????????????????
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
		
		//???????????????????????????
		pcWebDotService.dotGainRule(DotGainRuleEnum.LOGIN, user.getUserNo());

		return Result.success(dto);
	}

	public Result<UserLoginDTO> loginCode(UserLoginCodeBO userLoginCodeBO) {
		if (StringUtils.isEmpty(userLoginCodeBO.getClientId())) {
			return Result.error("clientId????????????");
		}
		if (StringUtils.isEmpty(userLoginCodeBO.getMobile())) {
			return Result.error("????????????????????????");
		}
		if (!PlatformUtil.id.equals(userLoginCodeBO.getClientId())) {
			return Result.error("??????????????????");
		}
		

		// ???????????????????????????

		// ???????????????
		String redisSmsCode = redisTemplate.opsForValue().get(PlatformUtil.id + userLoginCodeBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("???????????????????????????????????????");
		}

		User user = userDao.getByMobile(userLoginCodeBO.getMobile());
		if (null == user) {
			return Result.error("??????????????????");
		}

		if (!redisSmsCode.equals(userLoginCodeBO.getCode())) {
			loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.FAIL, userLoginCodeBO.getIp(),userLoginCodeBO.getLoginPlatform());
			// ????????????????????????
			return Result.error("??????????????????,????????????");
		}

		// ????????????
		redisTemplate.delete(PlatformUtil.id + userLoginCodeBO.getMobile());

		// ????????????
		loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginCodeBO.getIp(),userLoginCodeBO.getLoginPlatform());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		// ????????????????????????????????????????????????
		// redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
		// 1, TimeUnit.DAYS);
		return Result.success(dto);
	}

	public Result<String> sendCode(UserSendCodeBO userSendCodeBO) {
		if (StringUtils.isEmpty(userSendCodeBO.getClientId())) {
			return Result.error("clientId????????????");
		}
		if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(userSendCodeBO.getMobile()).matches()) {
			return Result.error("???????????????????????????");
		}

		/*
		if (!PlatformUtil.id.equals(userSendCodeBO.getClientId())) {
			return Result.error("??????????????????");
		}
		*/
		

		//SysVO sys = bossSysBiz.getSys();
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
		
		if (ObjectUtil.isNull(systemConfigViewVo)) {
			return Result.error("???????????????????????????");
		}
		// ??????????????????
		SendSmsLog sendSmsLog = new SendSmsLog();
		sendSmsLog.setMobile(userSendCodeBO.getMobile());
		sendSmsLog.setTemplate(systemConfigViewVo.getSmsCode());
		// ?????????????????????
		sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
		try {
			// ???????????????
			boolean result = AliyunUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(systemConfigViewVo, Aliyun.class));
			// ???????????????????????????????????????5????????????
			if (result) {
				/*
				redisTemplate.opsForValue().set(userSendCodeBO.getClientId() + userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);*/
				redisTemplate.opsForValue().set(RedisKey.SMS_PREFIX_+ userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);

				sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
				sendSmsLogDao.save(sendSmsLog);
				return Result.success("????????????");
			}
			// ????????????
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			throw new BaseException("????????????");
		} catch (ClientException e) {
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			logger.error("?????????????????????={}", e.getErrMsg());
			return Result.error("????????????");
		}
	}

	private User register(String mobile, String password, String realName, String clientId) {
		// ??????????????????
		User user = new User();
		user.setUserNo(NOUtil.getUserNo());
		user.setMobile(mobile);
		user.setPswSalt(StrUtil.get32UUID());
		user.setUserPsw(DigestUtil.sha1Hex(user.getPswSalt() + password));
		userDao.save(user);

		// ??????????????????
		Student student = new Student();
		student.setUserNo(user.getUserNo());
		student.setUserType(UserTypeEnum.USER.getCode());
		student.setMobile(user.getMobile());
		if(!StringUtils.isEmpty(realName)){
			student.setRealName(realName);
		}
		studentDao.save(student);
		
		/**???????????????
		1??????????????????
		2??????????????????
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
			return Result.error("???????????????,?????????");
		}
		if (StringUtils.isEmpty(userUpdateBO.getCode())) {
			return Result.error("?????????????????????");
		}
		if (StringUtils.isEmpty(userUpdateBO.getClientId())) {
			return Result.error("clientId????????????");
		}

		/*
		if (!PlatformUtil.id.equals(userUpdateBO.getClientId())) {
			return Result.error("??????????????????");
		}*/
		
		/*
		String redisCode = redisTemplate.opsForValue().get(PlatformUtil.id + userUpdateBO.getMobile());
		*/
		String redisCode = redisTemplate.opsForValue().get(RedisKey.SMS_PREFIX_ + userUpdateBO.getMobile());

		if (StringUtils.isEmpty(redisCode)) {
			return Result.error("??????????????????");
		}
		if (!userUpdateBO.getCode().equals(redisCode)) {
			return Result.error("????????????????????????");
		}
		// ?????????????????????
		String mobile = userUpdateBO.getMobile().replaceAll(" +", "");

		if (StringUtils.isEmpty(userUpdateBO.getConfirmPassword())) {
			return Result.error("?????????????????????,?????????");
		}
		if (!userUpdateBO.getNewPassword().equals(userUpdateBO.getConfirmPassword())) {
			return Result.error("?????????????????????????????????");
		}

		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("?????????????????????,?????????");
		}
		if (DigestUtil.sha1Hex(user.getPswSalt() + userUpdateBO.getNewPassword()).equals(user.getUserPsw())) {
			return Result.error("?????????????????????????????????,?????????");
		}

		// ????????????
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
			return Result.error("???????????????,?????????");
		}
		// ?????????????????????
		String mobile = userUpdateBO.getMobile().replaceAll(" +", "");
		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("?????????????????????,?????????");
		}

		// ????????????
		User bean = new User();
		bean.setId(user.getId());
		bean.setPswSalt(StrUtil.get32UUID());
		bean.setUserPsw(DigestUtil.sha1Hex(bean.getPswSalt() + userUpdateBO.getNewPassword()));
		int result = userDao.updateById(bean);
		return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

}
