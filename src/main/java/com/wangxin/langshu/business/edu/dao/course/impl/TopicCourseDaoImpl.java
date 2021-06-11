package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourseExample;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourseExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.TopicCourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.TopicCourseMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class TopicCourseDaoImpl implements TopicCourseDao {
	@Autowired
	private TopicCourseMapper topicCourseMapper;

	@Override
	public int save(TopicCourse record) {
		record.setId(IdWorker.getId());
		return this.topicCourseMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.topicCourseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(TopicCourse record) {
		return this.topicCourseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TopicCourse getById(String id) {
		return this.topicCourseMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<TopicCourse> listForPage(int pageCurrent, int pageSize, TopicCourseExample example) {
		int count = this.topicCourseMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<TopicCourse>(count, totalPage, pageCurrent, pageSize, this.topicCourseMapper.selectByExample(example));
	}

	@Override
	public List<TopicCourse> listByTopicIdAndValidValue(String topicId, Integer validValue) {
		TopicCourseExample example = new TopicCourseExample();
		Criteria c = example.createCriteria();
		c.andTopicIdEqualTo(topicId);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause("sort desc, id desc");
		return this.topicCourseMapper.selectByExample(example);
	}

	@Override
	public List<TopicCourse> listByTopicId(String topicId) {
		TopicCourseExample example = new TopicCourseExample();
		Criteria c = example.createCriteria();
		c.andTopicIdEqualTo(topicId);
		c.andValidValueEqualTo(ValidValueEnum.YES.getCode());
		return this.topicCourseMapper.selectByExample(example);
	}

	@Override
	public TopicCourse getTopicIdAndCourseId(String courseId, String topicId) {
		TopicCourseExample example = new TopicCourseExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		c.andTopicIdEqualTo(topicId);
		List<TopicCourse> list = this.topicCourseMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}