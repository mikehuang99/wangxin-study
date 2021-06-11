package com.wangxin.langshu.business.mobile.controller.user;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IUserStudyLogService;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogQ;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogSaveVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogVo;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.ObjectUtil;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi/user")
public class MobileApiCourseController extends BaseController {
	public static final String TOKEN = "token";

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private IPcWebFreshLessonService pcWebFreshLessonService;
	
	@Autowired
	private IUserStudyLogService userStudyLogService;
	
	/**
	 * 用户学习记录保存接口
	 * 
	 * @param 
	 */
	/*
	@ApiOperation(value = "用户学习记录保存接口", notes = "用户学习记录保存接口")
	@RequestMapping(value = "/userStudyLog/save", method = RequestMethod.POST)
	public Result<Integer> view(@RequestBody UserStudyLogSaveVo u) {
		if(1==1) {
			return Result.success(1);
		}
		if(StringUtils.isEmpty(u.getUserNo())) {
			return Result.error("缺少用户userNo");
		}
		if(StringUtils.isEmpty(u.getLessonId())) {
			return Result.error("缺少lessonId");
		}
		
		if(StringUtils.isEmpty(u.getStudyProgessDetails())) {
			return Result.error("缺少进度详情");
		}
		String[] studyProgessDetailsArray = u.getStudyProgessDetails().split(",");
		if(studyProgessDetailsArray.length!=101) {
			return Result.error("进度详情错误");
		}
		for(int i=0;i<studyProgessDetailsArray.length;i++) {
			if(StringUtils.isNotEmpty(studyProgessDetailsArray[i])&&String.valueOf(i-1).equals(studyProgessDetailsArray[i])) {
				return Result.error("进度详情数据错误");
			}
		}
		
		FreshLessonViewVo freshLessonViewVo = new FreshLessonViewVo();
		freshLessonViewVo.setId(u.getLessonId());
		freshLessonViewVo = pcWebFreshLessonService.viewLesson(freshLessonViewVo);
		if(ObjectUtil.isNull(freshLessonViewVo)) {
			return Result.error("根据lessonId找不到对象");
		}
		if(freshLessonViewVo.getLessonType()!=0) {
			return Result.error("只算点播的，其余的学习记录不会计算");
		}
		
		u.setCourseId(freshLessonViewVo.getCourseId());
		u.setChapterId(freshLessonViewVo.getChapterId());
		
		u.setStudyUserNo(u.getUserNo());
		
		String versionNum = UUID.randomUUID().toString();
		String key = RedisKey.USER_STUDY_LOG_PREFIX_+versionNum;
		
		u.setModifiedTime(new Date());//更新时间
		String uStr = JSON.toJSONString(u);

		redisTemplate.opsForValue().set(key, uStr);
		
		return Result.success(1);
	}
	*/
	/**
	 * 用户学习记录保存接口
	 * 
	 * @param 
	 */
	@ApiOperation(value = "用户学习记录保存接口", notes = "用户学习记录保存接口")
	@RequestMapping(value = "/userStudyLog/save", method = RequestMethod.POST)
	public Result<Integer> saveUserStudyLog(@RequestBody UserStudyLogSaveVo u) {
		if(StringUtils.isEmpty(u.getUserNo())) {
			return Result.error("缺少用户userNo");
		}
		if(StringUtils.isEmpty(u.getLessonId())) {
			return Result.error("缺少lessonId");
		}
		
		if(StringUtils.isEmpty(u.getStudyProgessDetails())) {
			return Result.error("缺少进度详情");
		}
		String studyProgessDetails=u.getStudyProgessDetails();
		if(studyProgessDetails.endsWith(",")) {
			studyProgessDetails = studyProgessDetails + "100";
		}
		String[] studyProgessDetailsArray = studyProgessDetails.split(",");
		if(studyProgessDetailsArray.length!=101) {
			return Result.error("进度详情错误");
		}
		for(int i=0;i<studyProgessDetailsArray.length;i++) {
			if(StringUtils.isNotEmpty(studyProgessDetailsArray[i])&&String.valueOf(i-1).equals(studyProgessDetailsArray[i])) {
				return Result.error("进度详情数据错误");
			}
		}
		
		FreshLessonViewVo freshLessonViewVo = new FreshLessonViewVo();
		freshLessonViewVo.setId(u.getLessonId());
		freshLessonViewVo = pcWebFreshLessonService.viewLesson(freshLessonViewVo);
		if(ObjectUtil.isNull(freshLessonViewVo)) {
			return Result.error("根据lessonId找不到对象");
		}
		if(freshLessonViewVo.getLessonType()!=0) {
			return Result.error("只算点播的，其余的学习记录不会计算");
		}
		
		u.setCourseId(freshLessonViewVo.getCourseId());
		u.setChapterId(freshLessonViewVo.getChapterId());
		
		u.setStudyUserNo(u.getUserNo());
		
		String versionNum = UUID.randomUUID().toString();
		String key = RedisKey.USER_STUDY_LOG_PREFIX_+versionNum;
		
		u.setModifiedTime(new Date());//更新时间
		String uStr = JSON.toJSONString(u);

		redisTemplate.opsForValue().set(key, uStr);
		
		return Result.success(1);
	}
	
	
	/**
	 * 学生查询用户学习记录列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "学生查询用户学习记录列表分页接口", notes = "学生查询用户学习记录列表分页接口")
	@RequestMapping(value = "/listByStudentUserNo", method = RequestMethod.POST)
	public Result<Page<UserStudyLogVo>> listByStudentUserNo(@RequestBody UserStudyLogQ q) {
		//置空，禁止学生根据真实姓名和手机号码查询
		q.setRealName("");
		q.setMobile("");
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setStudyUserNo(q.getUserNo());
		return Result.success(userStudyLogService.queryUserStudyLogs(q));
	}
	
	
}

