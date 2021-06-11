package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommend;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommendExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommendExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseRecommendDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseRecommendMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class CourseRecommendDaoImpl implements CourseRecommendDao {
	@Autowired
	private CourseRecommendMapper courseRecommendMapper;

	public int save(CourseRecommend record) {
		record.setId(IdWorker.getId());
		return this.courseRecommendMapper.insertSelective(record);
	}

	public int deleteById(String id) {
		return this.courseRecommendMapper.deleteByPrimaryKey(id);
	}

	public int updateById(CourseRecommend record) {
		return this.courseRecommendMapper.updateByPrimaryKeySelective(record);
	}

	public CourseRecommend getById(String id) {
		return this.courseRecommendMapper.selectByPrimaryKey(id);
	}

	public Page<CourseRecommend> listForPage(int pageCurrent, int pageSize, CourseRecommendExample example) {
		int count = this.courseRecommendMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseRecommend>(count, totalPage, pageCurrent, pageSize, this.courseRecommendMapper.selectByExample(example));
	}

	@Override
	public List<CourseRecommend> listBySubjectIdAndValidValue(String subjectId, Integer ValidValue) {
		CourseRecommendExample example = new CourseRecommendExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubjectIdEqualTo(subjectId);
		criteria.andValidValueEqualTo(ValidValue);
		return this.courseRecommendMapper.selectByExample(example);
	}

	@Override
	public CourseRecommend getBySubjectIdAndCourseId(String subjectId, String courseId) {
		CourseRecommendExample example = new CourseRecommendExample();
		Criteria criteria = example.createCriteria();
		criteria.andSubjectIdEqualTo(subjectId);
		criteria.andCourseIdEqualTo(courseId);
		List<CourseRecommend> list = this.courseRecommendMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}