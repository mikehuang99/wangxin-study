package com.wangxin.langshu.business.edu.service.course.pcboss;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseGetREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CoursePageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.ChapterViewRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseGetRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CoursePageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseViewRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.LessonViewRESQ;
import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample.Criteria;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.dao.course.ChapterDao;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.dao.course.TopicCourseDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseBiz;
import com.wangxin.langshu.business.edu.service.user.pccommon.BossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息
 */
@Service
public class PcBossCourseBiz implements IPcBossCourseBiz{


	@Autowired
	private BossTeacherBiz bossTeacherBiz;

	@Autowired
	private TopicCourseDao topicCourseDao;
	@Autowired
	private CourseDao dao;
	@Autowired
	private CourseApplyDao courseApplyDao;
	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	@Autowired
	private CourseSubjectDao courseSubjectDao;
	@Autowired
	private LessonDao lessonDao;

	/**
	 * 分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CoursePageRESQ>> list(CoursePageREQ req) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		if (req.getSubjectId1() != null) {
			c.andSubjectId1EqualTo(req.getSubjectId1());
		}
		if (!StringUtils.isEmpty(req.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(req.getCourseName()));
		}
		if (req.getValidValue() != null) {
			c.andValidValueEqualTo(req.getValidValue());
		}
		if (req.getIsFree() != null) {
			c.andIsFreeEqualTo(req.getIsFree());
		}
		if (req.getIsPutaway() != null) {
			c.andIsPutawayEqualTo(req.getIsPutaway());
		}
		example.setOrderByClause(" valid_value desc, is_putaway desc, course_sort desc, id desc ");
		Page<Course> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CoursePageRESQ> listForPage = PageUtil.transform(page, CoursePageRESQ.class);
		// 获取分类名称
		for (CoursePageRESQ resq : listForPage.getList()) {
			if (req.getTopicId() != null) {
				// 校验专区是否存在课程
				TopicCourse topicCourse = topicCourseDao.getTopicIdAndCourseId(resq.getId(), req.getTopicId());
				if (ObjectUtil.isNull(topicCourse)) {
					// 不存在
					resq.setIsAddTopicCourse(0);
				} else {
					// 存在
					resq.setIsAddTopicCourse(1);
				}
			}
			TeacherVO teacher = bossTeacherBiz.getByTeacherUserNo(resq.getTeacherUserNo());
			if (ObjectUtil.isNotNull(teacher)) {
				resq.setTeacherName(teacher.getTeacherName());
			}
			if (resq.getSubjectId1() != null && !"0".equals(resq.getSubjectId1())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId1());
				if (!StringUtils.isEmpty(courseSubject)) {
					resq.setSubjectName1(courseSubject.getSubjectName());
				}
			}
			if (resq.getSubjectId2() != null && !"0".equals(resq.getSubjectId2())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId2());
				if (!StringUtils.isEmpty(courseSubject)) {
					resq.setSubjectName2(courseSubject.getSubjectName());
				}
			}
			if (resq.getSubjectId3() != null && !"0".equals(resq.getSubjectId3())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId3());
				if (!StringUtils.isEmpty(courseSubject)) {
					resq.setSubjectName3(courseSubject.getSubjectName());
				}
			}
		}
		return Result.success(listForPage);
	}

	@Transactional
	public Result<Integer> update(CourseUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Course course = dao.getById(req.getId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息,或者该课程还没有进行过审核");
		}
		if (IsFreeEnum.FREE.getCode().equals(req.getIsFree())) {
			req.setCourseOriginal(BigDecimal.ZERO);
			req.setCourseDiscount(BigDecimal.ZERO);
		}
		Course record = BeanUtil.copyProperties(req, Course.class);
		int result = dao.updateById(record);
		if (result > 0) {
			// 同步更新审核表
			CourseApply courseApply = BeanUtil.copyProperties(req, CourseApply.class);
			courseApply.setCreateTime(null);
			courseApply.setModifiedTime(null);
			int recordAudit = courseApplyDao.updateById(courseApply);
			if (recordAudit < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}

		if (StringUtils.hasText(req.getIntroduce())) {
			// 更新课程介绍
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(course.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduce)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduce.setId(course.getIntroduceId());
			courseIntroduce.setIntroduce(req.getIntroduce());
			int results = courseIntroduceDao.updateById(courseIntroduce);
			if (results < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
			// 同步更新审核表
			CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(course.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceApply)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduceApply.setCreateTime(null);
			courseIntroduceApply.setModifiedTime(null);
			courseIntroduceApply.setId(course.getIntroduceId());
			courseIntroduceApply.setIntroduce(req.getIntroduce());
			courseIntroduceApplyDao.updateById(courseIntroduceApply);
		}
		return Result.success(result);
	}

	/**
	 * 查看(课程修改使用)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseGetRESQ> get(CourseGetREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		// 根据id查找课程信息
		Course record = dao.getById(req.getId());
		CourseGetRESQ resq = BeanUtil.copyProperties(record, CourseGetRESQ.class);
		if (ObjectUtil.isNotNull(resq)) {
			// 获取分类名称
			if (resq.getSubjectId1() != null && !"0".equals(resq.getSubjectId1())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId1());
				resq.setSubjectName1(courseSubject.getSubjectName());
			}
			if (resq.getSubjectId2() != null && !"0".equals(resq.getSubjectId2())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId2());
				resq.setSubjectName2(courseSubject.getSubjectName());
			}
			if (resq.getSubjectId3() != null && !"0".equals(resq.getSubjectId3())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId3());
				resq.setSubjectName3(courseSubject.getSubjectName());
			}
			// 根据id查找课程简介信息
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(record.getIntroduceId());
			// 把课程简介带回课程信息
			if (ObjectUtil.isNotNull(courseIntroduce)) {
				resq.setIntroduce(courseIntroduce.getIntroduce());
			}
		}
		return Result.success(resq);
	}

	/**
	 * 查看详情
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseViewRESQ> view(CourseViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Course record = dao.getById(req.getId());
		CourseViewRESQ resq = BeanUtil.copyProperties(record, CourseViewRESQ.class);
		// 获取分类名称
		if (resq.getSubjectId1() != null && !"0".equals(resq.getSubjectId1())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId1());
			resq.setSubjectName1(courseSubject.getSubjectName());
		}
		if (resq.getSubjectId2() != null && !"0".equals(resq.getSubjectId2())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId2());
			resq.setSubjectName2(courseSubject.getSubjectName());
		}
		if (resq.getSubjectId3() != null && !"0".equals(resq.getSubjectId3())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId3());
			resq.setSubjectName3(courseSubject.getSubjectName());
		}
		// 章节
		List<Chapter> ChapterList = chapterDao.listByCourseIdAndValidValue(resq.getId(),
				ValidValueEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<ChapterViewRESQ> chapterVOList = PageUtil.copyList(ChapterList,
					ChapterViewRESQ.class);
			for (ChapterViewRESQ chapter : chapterVOList) {
				// 课时
				List<Lesson> lessonList = lessonDao
						.listByChapterIdAndValidValue(chapter.getId(), ValidValueEnum.YES.getCode());
				chapter
						.setLessonList(PageUtil.copyList(lessonList, LessonViewRESQ.class));
			}
			resq.setChapterList(chapterVOList);
		}
		return Result.success(resq);
	}

}
