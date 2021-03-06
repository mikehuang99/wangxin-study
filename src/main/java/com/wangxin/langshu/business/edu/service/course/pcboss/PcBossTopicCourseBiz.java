package com.wangxin.langshu.business.edu.service.course.pcboss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCoursePageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.TopicCourseViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicCoursePageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.TopicCourseViewRESQ;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.Topic;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourseExample;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourseExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.dao.course.TopicCourseDao;
import com.wangxin.langshu.business.edu.dao.course.TopicDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossTopicCourseBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 
 * ?????????????????????
 *
 */
@Service
public class PcBossTopicCourseBiz implements IPcBossTopicCourseBiz{
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private TopicCourseDao dao;
	@Autowired
	private CourseSubjectDao courseSubjectDao;
	@Autowired
	private CourseDao courseDao;

	/**
	 * ????????????
	 * 
	 * @param topicCoursePageREQ
	 * @return
	 */
	public Result<Page<TopicCoursePageRESQ>> listForPage(TopicCoursePageREQ req) {
		if (StringUtils.isEmpty(req.getTopicId())) {
			return Result.error("??????ID????????????");
		}
		TopicCourseExample example = new TopicCourseExample();
		Criteria c = example.createCriteria();
		c.andTopicIdEqualTo(req.getTopicId());
		if (!StringUtils.isEmpty(req.getCourseName())) {
			Course course = courseDao.getByCourseName(req.getCourseName());
			if (ObjectUtil.isNull(course)) {
				c.andCourseIdEqualTo("0");
			} else {
				c.andCourseIdEqualTo(course.getId());
			}
		}
		example.setOrderByClause("valid_value desc, sort desc, id desc");
		Page<TopicCourse> listForPage = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<TopicCoursePageRESQ> page = PageUtil.transform(listForPage, TopicCoursePageRESQ.class);
		// ????????????ID??????????????????
		for (TopicCoursePageRESQ resq : page.getList()) {
			List<Course> course = courseDao.listBySubjectId(resq.getCourseId());
			for (Course courseinfo : course) {
				// ????????????????????????
				if (courseinfo.getSubjectId1() != null && !"0".equals(courseinfo.getSubjectId1())) {
					CourseSubject courseSubject = courseSubjectDao.getById(courseinfo.getSubjectId1());
					if (courseSubject != null) {
						resq.setSubjectName1(courseSubject.getSubjectName());
					}
				}
				if (courseinfo.getSubjectId2() != null && !"0".equals(courseinfo.getSubjectId2())) {
					CourseSubject courseSubject = courseSubjectDao.getById(courseinfo.getSubjectId2());
					if (courseSubject != null) {
						resq.setSubjectName2(courseSubject.getSubjectName());
					}
				}
				if (courseinfo.getSubjectId3() != null && !"0".equals(courseinfo.getSubjectId3())) {
					CourseSubject courseSubject = courseSubjectDao.getById(courseinfo.getSubjectId3());
					if (courseSubject != null) {
						resq.setSubjectName3(courseSubject.getSubjectName());
					}
				}
				resq.setCourseName(courseinfo.getCourseName());
			}

		}
		return Result.success(page);
	}

	/**
	 * ??????????????????
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(TopicCourseSaveREQ req) {
		if (ObjectUtil.isNull(req.getCourseId())) {
			return Result.error("??????ID????????????");
		}
		if (ObjectUtil.isNull(req.getTopicId())) {
			return Result.error("??????ID????????????");
		}

		Topic topic = topicDao.getById(req.getTopicId());
		if (ObjectUtil.isNull(topic) && !ValidValueEnum.YES.getCode().equals(topic.getValidValue())) {
			return Result.error("?????????????????????");
		}

		// ????????????????????????????????????
		Course course = courseDao.getByCourseIdAndValidValue(req.getCourseId(), ValidValueEnum.YES.getCode());
		
		if (ObjectUtil.isNull(course) || !ValidValueEnum.YES.getCode().equals(course.getValidValue())) {
			return Result.error("?????????????????????");
		}

		TopicCourse topicCourse = dao.getTopicIdAndCourseId(req.getCourseId(), req.getTopicId());
		if (ObjectUtil.isNotNull(topicCourse)) {
			return Result.error("?????????????????????");
		}

		// ??????????????????????????????
		TopicCourse result = new TopicCourse();
		result.setCourseId(course.getId());
		result.setTopicId(req.getTopicId());
		result.setSort(1);
		int results = dao.save(result);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * ??????????????????
	 * 
	 * @param topicCourseDeleteREQ
	 * @return
	 */
	public Result<Integer> delete(TopicCourseDeleteREQ topicCourseDeleteREQ) {
		if (StringUtils.isEmpty(topicCourseDeleteREQ.getId())) {
			return Result.error("ID????????????");
		}
		int results = dao.deleteById(topicCourseDeleteREQ.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * ??????????????????
	 * 
	 * @param topicCourseUpdateREQ
	 * @return
	 */
	public Result<Integer> update(TopicCourseUpdateREQ topicCourseUpdateREQ) {
		if (StringUtils.isEmpty(topicCourseUpdateREQ.getId())) {
			return Result.error("ID????????????");
		}
		TopicCourse record = BeanUtil.copyProperties(topicCourseUpdateREQ, TopicCourse.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * ??????????????????
	 * 
	 * @param topicCourseViewREQ
	 * @return
	 */
	public Result<TopicCourseViewRESQ> view(TopicCourseViewREQ topicCourseViewREQ) {
		if (StringUtils.isEmpty(topicCourseViewREQ.getId())) {
			return Result.error("ID????????????");
		}
		TopicCourse record = dao.getById(topicCourseViewREQ.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("?????????????????????");
		}
		return Result.success(BeanUtil.copyProperties(record, TopicCourseViewRESQ.class));
	}

}
