package com.wangxin.langshu.business.edu.dao.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceExample;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseIntroduceMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;

@Repository
public class CourseIntroduceDaoImpl implements CourseIntroduceDao {
    @Autowired
    private CourseIntroduceMapper courseIntroduceMapper;

    public int save(CourseIntroduce record) {
        return this.courseIntroduceMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.courseIntroduceMapper.deleteByPrimaryKey(id);
    }

    public int updateById(CourseIntroduce record) {
        return this.courseIntroduceMapper.updateByPrimaryKeySelective(record);
    }

    public CourseIntroduce getById(String id) {
        return this.courseIntroduceMapper.selectByPrimaryKey(id);
    }

    public Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example) {
        int count = this.courseIntroduceMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseIntroduce>(count, totalPage, pageCurrent, pageSize, this.courseIntroduceMapper.selectByExample(example));
    }
}