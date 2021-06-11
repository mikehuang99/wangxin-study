package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.TopicCourse;
import com.wangxin.langshu.business.edu.entity.course.old.TopicCourseExample;

@Mapper
public interface TopicCourseMapper {
    int countByExample(TopicCourseExample example);

    int deleteByExample(TopicCourseExample example);

    int deleteByPrimaryKey(String id);

    int insert(TopicCourse record);

    int insertSelective(TopicCourse record);

    List<TopicCourse> selectByExample(TopicCourseExample example);

    TopicCourse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TopicCourse record, @Param("example") TopicCourseExample example);

    int updateByExample(@Param("record") TopicCourse record, @Param("example") TopicCourseExample example);

    int updateByPrimaryKeySelective(TopicCourse record);

    int updateByPrimaryKey(TopicCourse record);
}