package com.wangxin.langshu.business.edu.dao.course;

import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceExample;
import com.wangxin.langshu.business.util.base.Page;

public interface CourseIntroduceDao {
    int save(CourseIntroduce record);

    int deleteById(String id);

    int updateById(CourseIntroduce record);

    CourseIntroduce getById(String id);

    Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example);
}