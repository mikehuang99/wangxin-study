package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample;

@Mapper
public interface CourseApplyMapper {
    int countByExample(CourseApplyExample example);

    int deleteByExample(CourseApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseApply record);

    int insertSelective(CourseApply record);

    List<CourseApply> selectByExample(CourseApplyExample example);

    CourseApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseApply record, @Param("example") CourseApplyExample example);

    int updateByExample(@Param("record") CourseApply record, @Param("example") CourseApplyExample example);

    int updateByPrimaryKeySelective(CourseApply record);

    int updateByPrimaryKey(CourseApply record);
}