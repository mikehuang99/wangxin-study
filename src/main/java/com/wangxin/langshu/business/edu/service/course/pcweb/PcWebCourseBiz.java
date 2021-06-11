package com.wangxin.langshu.business.edu.service.course.pcweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseSignBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.ChapterDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseIntroduceDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.LessonDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseSignDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseViewDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthTeacherDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.dao.course.ChapterDao;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IBossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.config.SystemUtil;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.IsPayEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息
 *
 *  
 */
@Service
public class PcWebCourseBiz extends BaseBiz implements IPcWebCourseBiz{

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private LessonDao lessonDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;

	
	@Autowired
	private IBossTeacherBiz bossTeacherBiz;
	
	
	
	@Autowired
	private ISystemConfigService systemConfigService;



	public Result<AuthCourseSignDTO> sign(AuthCourseSignBO authCourseSignBO) {
		if (ObjectUtil.isNull(authCourseSignBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		if (ObjectUtil.isNull(authCourseSignBO.getLessonId())) {
			return Result.error("lessonId不能为空");
		}


		// 课时信息
		Lesson lesson = lessonDao.getById(authCourseSignBO.getLessonId());
		if (StringUtils.isEmpty(lesson)) {
			return Result.error("找不到课时信息");
		}
		// 章节信息
		Chapter chapter = chapterDao.getById(lesson.getChapterId());
		if (ObjectUtil.isNull(chapter)) {
			return Result.error("找不到章节信息");
		}
		// 课程信息
		Course course = courseDao.getById(lesson.getCourseId());
		if (StringUtils.isEmpty(course)) {
			return Result.error("找不到课程信息");
		}

		// 免费：课时免费，章节免费，课程免费
		if (IsFreeEnum.FREE.getCode().equals(lesson.getIsFree()) || IsFreeEnum.FREE.getCode().equals(chapter.getIsFree()) || IsFreeEnum.FREE.getCode().equals(course.getIsFree())) {
			//AuthCourseSignDTO authCourseSignDTO = getSgin(authCourseSignBO);//原本是获取保利威视签名的
			AuthCourseSignDTO authCourseSignDTO = new AuthCourseSignDTO();
			return Result.success(authCourseSignDTO);
		}

		// 收费：订单是否存在并且判断订单是否支付成功
		//。。。。。。。

		// 成功
		//AuthCourseSignDTO authCourseSignDTO = getSgin(authCourseSignBO);
		AuthCourseSignDTO authCourseSignDTO = new AuthCourseSignDTO();

		return Result.success(authCourseSignDTO);
	}

	public Result<AuthCourseViewDTO> view(AuthCourseViewBO authCourseViewBO) {
		if (StringUtils.isEmpty(authCourseViewBO.getCourseId())) {
			return Result.error("courseId不能为空");
		}
		if (StringUtils.isEmpty(authCourseViewBO.getUserNo())) {
			return Result.error("userNo不能为空");
		}

		// 查询课程信息
		Course course = courseDao.getById(authCourseViewBO.getCourseId());
		if (course == null) {
			return Result.error("找不到该课程");
		}
		AuthCourseViewDTO dto = BeanUtil.copyProperties(course, AuthCourseViewDTO.class);

		// 查询课程介绍
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(course.getIntroduceId());
		dto.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());

		// 先假设为收费课程且用户未付款
		dto.setIsPay(IsPayEnum.NO.getCode());
		// 查询订单号，查看用户是否购买了课程，是否存在订单号
		//。。。。。。。
		
		// 如果课程为免费课程则设置为已付费
		if (IsFreeEnum.FREE.getCode().equals(course.getIsFree())) {
			dto.setIsPay(IsPayEnum.YES.getCode());
		}

		// 此处用于测试支付课程，无论怎样都是未购买
		if (SystemUtil.TEST_COURSE.equals(course.getId().toString())) {
			dto.setIsPay(IsPayEnum.NO.getCode());
		}

		// 查询教师信息
		TeacherVO teacherVO = bossTeacherBiz.getByTeacherUserNo(dto.getTeacherUserNo());
		dto.setTeacher(BeanUtil.copyProperties(teacherVO, AuthTeacherDTO.class));

		// 查询章节信息
		List<Chapter> chapterList = chapterDao.listByCourseIdAndValidValue(authCourseViewBO.getCourseId(), ValidValueEnum.YES.getCode());
		// 如果为空就直接返回
		if (chapterList.isEmpty()) {
			return Result.success(dto);
		}
		dto.setChapterList(PageUtil.copyList(chapterList, ChapterDTO.class));

		// 课时信息
		for (ChapterDTO chapterDTO : dto.getChapterList()) {
			List<Lesson> lessonList = lessonDao.listByChapterId(chapterDTO.getId());
			chapterDTO.setLessonList(PageUtil.copyList(lessonList, LessonDTO.class));
		}

		return Result.success(dto);
	}
	
	public AuthCourseViewDTO viewByCourseId(String courseId ) {
		if (StringUtils.isEmpty(courseId)) {
			throw new BaseException("courseId不能为空");
		}
		

		// 查询课程信息
		Course course = courseDao.getById(courseId);
		if (course == null) {
			throw new BaseException("找不到该课程");
		}
		AuthCourseViewDTO dto = BeanUtil.copyProperties(course, AuthCourseViewDTO.class);

		// 查询课程介绍
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(course.getIntroduceId());
		dto.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());

		/*
		// 先假设为收费课程且用户未付款
		dto.setIsPay(IsPayEnum.NO.getCode());
		// 查询订单号，查看用户是否购买了课程，是否存在订单号
		OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(authCourseViewBO.getUserNo(), authCourseViewBO.getCourseId());
		if (ObjectUtil.isNull(orderInfo)) {
			// 未购买或者没支付情况
			dto.setIsPay(IsPayEnum.NO.getCode());
		} else if (OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
			// 订单状态为已支付
			dto.setIsPay(IsPayEnum.YES.getCode());
		}
		// 如果课程为免费课程则设置为已付费
		if (IsFreeEnum.FREE.getCode().equals(course.getIsFree())) {
			dto.setIsPay(IsPayEnum.YES.getCode());
		}

		// 此处用于测试支付课程，无论怎样都是未购买
		if (SystemUtil.TEST_COURSE.equals(course.getId().toString())) {
			dto.setIsPay(IsPayEnum.NO.getCode());
		}
		*/

		// 查询教师信息
		TeacherVO teacherVO = bossTeacherBiz.getByTeacherUserNo(dto.getTeacherUserNo());
		dto.setTeacher(BeanUtil.copyProperties(teacherVO, AuthTeacherDTO.class));

		// 查询章节信息
		List<Chapter> chapterList = chapterDao.listByCourseIdAndValidValue(courseId, ValidValueEnum.YES.getCode());
		// 如果为空就直接返回
		if (chapterList.isEmpty()) {
			return dto;
		}
		dto.setChapterList(PageUtil.copyList(chapterList, ChapterDTO.class));

		// 课时信息
		for (ChapterDTO chapterDTO : dto.getChapterList()) {
			List<Lesson> lessonList = lessonDao.listByChapterId(chapterDTO.getId());
			chapterDTO.setLessonList(PageUtil.copyList(lessonList, LessonDTO.class));
		}

		return dto;
	}

	/**
	 * 获取播放sign值
	 * 
	 * @param bo
	 * @return
	 */
	/*
	private AuthCourseSignDTO getSgin(AuthCourseSignBO authCourseSignBO) {
		//SysVO sys = bossSysBiz.getSys();
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();

		if (ObjectUtil.isNull(systemConfigViewVo)) {
			try {
				throw new Exception("找不到系统配置信息");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isEmpty("sys.getPolyvUseid()") || StringUtils.isEmpty("sys.getPolyvSecretkey()")) {
			try {
				throw new Exception("useid或secretkey未配置");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PolyvSign polyvSign = new PolyvSign();
		polyvSign.setIp(authCourseSignBO.getIp());
		polyvSign.setUserNo(authCourseSignBO.getUserNo());
		PolyvSignResult signResult = PolyvUtil.getSignForH5(polyvSign, "sys.getPolyvUseid()", "sys.getPolyvSecretkey()");
		AuthCourseSignDTO dto = BeanUtil.copyProperties(signResult, AuthCourseSignDTO.class);
		PolyvCode polyvCode = new PolyvCode();
		polyvCode.setLessonNo(authCourseSignBO.getLessonId());
		polyvCode.setUserNo(authCourseSignBO.getUserNo());
		dto.setCode(PolyvUtil.getPolyvCode(polyvCode));
		return dto;
	}*/


}
