package com.wangxin.langshu.business.edu.dao.course;

import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApplyExample;
import com.wangxin.langshu.business.util.base.Page;

public interface CourseIntroduceApplyDao {
    int save(CourseIntroduceApply record);

    int deleteById(String id);

    int updateById(CourseIntroduceApply record);

    CourseIntroduceApply getById(String id);

    Page<CourseIntroduceApply> listForPage(int pageCurrent, int pageSize, CourseIntroduceApplyExample example);
}