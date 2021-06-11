package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubjectExample;

@Mapper
public interface CourseSubjectMapper {
    int countByExample(CourseSubjectExample example);

    int deleteByExample(CourseSubjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseSubject record);

    int insertSelective(CourseSubject record);

    List<CourseSubject> selectByExample(CourseSubjectExample example);

    CourseSubject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseSubject record, @Param("example") CourseSubjectExample example);

    int updateByExample(@Param("record") CourseSubject record, @Param("example") CourseSubjectExample example);

    int updateByPrimaryKeySelective(CourseSubject record);

    int updateByPrimaryKey(CourseSubject record);
}