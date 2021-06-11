package com.wangxin.langshu.business.edu.dao.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApplyExample;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.CourseIntroduceApplyMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class CourseIntroduceApplyDaoImpl implements CourseIntroduceApplyDao {
    @Autowired
    private CourseIntroduceApplyMapper courseIntroduceApplyMapper;

    public int save(CourseIntroduceApply record) {
        record.setId(IdWorker.getId());
        return this.courseIntroduceApplyMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.courseIntroduceApplyMapper.deleteByPrimaryKey(id);
    }

    public int updateById(CourseIntroduceApply record) {
    	record.setCreateTime(null);
    	record.setModifiedTime(null);
        return this.courseIntroduceApplyMapper.updateByPrimaryKeySelective(record);
    }

    public CourseIntroduceApply getById(String id) {
        return this.courseIntroduceApplyMapper.selectByPrimaryKey(id);
    }

    public Page<CourseIntroduceApply> listForPage(int pageCurrent, int pageSize, CourseIntroduceApplyExample example) {
        int count = this.courseIntroduceApplyMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseIntroduceApply>(count, totalPage, pageCurrent, pageSize, this.courseIntroduceApplyMapper.selectByExample(example));
    }
}