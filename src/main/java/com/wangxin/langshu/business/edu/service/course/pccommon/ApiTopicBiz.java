package com.wangxin.langshu.business.edu.service.course.pccommon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.TopicBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TopicCourseDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.TopicDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.Topic;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.entity.course.old.TopicExample;
import com.wangxin.langshu.business.edu.entity.course.old.TopicExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.TopicCourseDao;
import com.wangxin.langshu.business.edu.dao.course.TopicDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiTopicBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;

@Service
public class ApiTopicBiz implements IApiTopicBiz{

	@Autowired
	private TopicDao topicDao;
	@Autowired
	private TopicCourseDao topicCourseDao;
	@Autowired
	private CourseDao courseDao;

	/**
	 * 专区课程分页列表接口
	 *
	 * @param topicBO
	 * 
	 */
	public Result<Page<TopicDTO>> listForPage(TopicBO topicBO) {
		if (StringUtils.isEmpty(topicBO.getTopicLocation())) {
			return Result.error("topicLocation不能为空");
		}
		TopicExample example = new TopicExample();
		Criteria c = example.createCriteria();
		c.andValidValueEqualTo(ValidValueEnum.YES.getCode());
		if (!StringUtils.isEmpty(topicBO.getId())) {
			c.andIdEqualTo(topicBO.getId());
		}
		if (!StringUtils.isEmpty(topicBO.getTopicLocation())) {
			c.andTopicLocationEqualTo(topicBO.getTopicLocation());
		}
		example.setOrderByClause("sort desc, id desc");
		Page<Topic> topicPage = topicDao.listForPage(topicBO.getPageCurrent(), topicBO.getPageSize(), example);
		if (StringUtils.isEmpty(topicPage)) {
			return Result.error("找不到信息");
		}
		Page<TopicDTO> page = PageUtil.transform(topicPage, TopicDTO.class);
		for (TopicDTO topic : page.getList()) {
			List<TopicCourse> topicCourseList = topicCourseDao.listByTopicIdAndValidValue(topic.getId(), ValidValueEnum.YES.getCode());
			List<TopicCourseDTO> topicCourseListDTO = new ArrayList<>();
			for (TopicCourse topicCourse : topicCourseList) {
				Course course = courseDao.getById(topicCourse.getCourseId());
				topicCourseListDTO.add(BeanUtil.copyProperties(course, TopicCourseDTO.class));
			}
			topic.setTopicCourseList(topicCourseListDTO);
		}
		return Result.success(page);
	}

}
