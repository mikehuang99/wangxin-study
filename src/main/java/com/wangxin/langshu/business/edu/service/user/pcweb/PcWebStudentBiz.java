package com.wangxin.langshu.business.edu.service.user.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.dao.user.StudentDao;
import com.wangxin.langshu.business.edu.entity.user.old.Student;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudentBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentViewBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.auth.AuthStudentDTO;
import com.wangxin.langshu.business.util.PlatformUtil;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 用户教育信息
 *
 *  
 */
@Service
public class PcWebStudentBiz extends BaseBiz implements IPcWebStudentBiz{

	@Autowired
	private StudentDao studentDao;


	//@Autowired
	//private BossSysBiz bossSysBiz;

	@Autowired
	private ISystemConfigService systemConfigService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	/**
	 * 用户信息查看接口
	 * 
	 * @param userNo
	 * 
	 */
	public Result<AuthStudentDTO> view(AuthStudentViewBO authStudentViewBO) {
		if (ObjectUtil.isNull(authStudentViewBO)) {
			return Result.error("authStudentUserNoBO不能为空");
		}
		if (null == authStudentViewBO.getUserNo()) {
			return Result.error("userNo不能为空");
		}
		Student student = studentDao.getByUserNo(authStudentViewBO.getUserNo());
		if (student == null) {
			return Result.error("找不到该用户信息");
		}
		
		// 查缓存
		/*
		String s = redisTemplate.opsForValue().get(PlatformUtil.id + student.getMobile());
		if(StringUtils.isEmpty(s)) {
			return Result.error("该用户未登录");
		}*/
		return Result.success(BeanUtil.copyProperties(student, AuthStudentDTO.class));
	}

	/**
	 * 用户信息更新接口
	 * 
	 * @param authStudentBO
	 * 
	 */
	public Result<AuthStudentDTO> update(AuthStudentBO authStudentBO) {
		if (authStudentBO.getId() == null) {
			return Result.error("Id不能为空");
		}
		if (authStudentBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		// 根据传入userNo查找到对应的用户信息
		Student student = studentDao.getByUserNo(authStudentBO.getUserNo());
		if (ObjectUtil.isNull(student)) {
			return Result.error("没找到对应的用户信息");
		}
		if(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty(student.getRealName())) {
			if(!student.getRealName().equals(authStudentBO.getRealName())) {
				return Result.error("修改失败。真实姓名不能修改！如确需修改请联系管理员。");
			}
		}
		if(com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotEmpty(student.getIdentityCardNo())) {
			if(!student.getIdentityCardNo().equals(authStudentBO.getIdentityCardNo())) {
				return Result.error("修改失败。身份证号码不能修改！如确需修改请联系管理员。");
			}
		}
		// 如果修改图片删除阿里云oss上的原图片
		if (!StringUtils.isEmpty(authStudentBO.getHeadImgUrl())) {
			if (!StringUtils.isEmpty(student.getHeadImgUrl()) && !authStudentBO.getHeadImgUrl().equals(student.getHeadImgUrl())) {
				AliyunUtil.delete(student.getHeadImgUrl(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
			}
		}
		student = BeanUtil.copyProperties(authStudentBO, Student.class);
		int resultNum = studentDao.updateByUserNo(student);
		if (resultNum > 0) {
			// 修改成功返回用户信息
			Student education = studentDao.getByUserNo(authStudentBO.getUserNo());
			AuthStudentDTO dto = BeanUtil.copyProperties(education, AuthStudentDTO.class);
			return Result.success(dto);
		}
		return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());

	}

	@Override
	public Result<Integer> logout(AuthStudentViewBO authStudentViewBO) {
		if (null == authStudentViewBO.getUserNo()) {
			return Result.success(1);
			//return Result.error("userNo不能为空");
		}
		if (ObjectUtil.isNull(authStudentViewBO)) {
			return Result.error("authStudentUserNoBO不能为空");
		}
		
		
		if (StringUtils.isEmpty(authStudentViewBO.getPlatform())) {
			return Result.error("platform不能为空");
		}
		Student student = studentDao.getByUserNo(authStudentViewBO.getUserNo());
		if (student == null) {
			return Result.error("找不到该用户信息");
		}
		// 清空缓存
		redisTemplate.delete(PlatformUtil.id + student.getMobile());
		
		redisTemplate.delete(authStudentViewBO.getUserNo()+"_"+authStudentViewBO.getPlatform());
		
		return Result.success(1);
	}

}
