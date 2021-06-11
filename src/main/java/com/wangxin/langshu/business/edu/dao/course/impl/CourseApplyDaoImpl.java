package com.wangxin.langshu.business.edu.dao.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseApplyMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class CourseApplyDaoImpl implements CourseApplyDao {
    @Autowired
    private CourseApplyMapper courseApplyMapper;

    public int save(CourseApply record) {
        record.setId(IdWorker.getId());
        return this.courseApplyMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.courseApplyMapper.deleteByPrimaryKey(id);
    }

    public int updateById(CourseApply record) {
    	record.setCreateTime(null);
		record.setModifiedTime(null);
        return this.courseApplyMapper.updateByPrimaryKeySelective(record);
    }

    public CourseApply getById(String id) {
        return this.courseApplyMapper.selectByPrimaryKey(id);
    }

    public Page<CourseApply> listForPage(int pageCurrent, int pageSize, CourseApplyExample example) {
        int count = this.courseApplyMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseApply>(count, totalPage, pageCurrent, pageSize, this.courseApplyMapper.selectByExample(example));
    }

	@Override
	public int updateApplyStatusBycourseId(Integer applyStatus, String courseId) {
		CourseApplyExample example = new CourseApplyExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(courseId);
		CourseApply record = new CourseApply();
		record.setId(courseId);
		record.setApplyStatus(applyStatus);
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.courseApplyMapper.updateByExampleSelective(record, example);
	}
}