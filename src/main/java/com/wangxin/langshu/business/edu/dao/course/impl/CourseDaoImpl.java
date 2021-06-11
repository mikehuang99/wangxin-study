package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    public int save(Course record) {
        return this.courseMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.courseMapper.deleteByPrimaryKey(id);
    }

    public int updateById(Course record) {
    	record.setCreateTime(null);
		record.setModifiedTime(null);
        return this.courseMapper.updateByPrimaryKeySelective(record);
    }

    public Course getById(String id) {
        return this.courseMapper.selectByPrimaryKey(id);
    }

    public Page<Course> listForPage(int pageCurrent, int pageSize, CourseExample example) {
        int count = this.courseMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Course>(count, totalPage, pageCurrent, pageSize, this.courseMapper.selectByExample(example));
    }

	@Override
	public List<Course> listBySubjectId(String courseId) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(courseId);
		List<Course> list = this.courseMapper.selectByExample(example);
		return list;
	}

	@Override
	public Course getByCourseIdAndValidValue(String courseId, Integer ValidValue) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(courseId);
		c.andValidValueEqualTo(ValidValue);
		List<Course> list = this.courseMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Course getByCourseName(String courseName) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andCourseNameLike(PageUtil.rightLike(courseName));
		List<Course> courseList =  this.courseMapper.selectByExample(example);
		if (courseList.isEmpty()) {
			return null;
		}
		return courseList.get(0);
	}

	@Override
	public List<Course> listBysubjectId2AndValidValue(String subjectId2, Integer validValue) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andSubjectId2EqualTo(subjectId2);
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause("sort desc,id desc");
		return this.courseMapper.selectByExample(example);
	}
}