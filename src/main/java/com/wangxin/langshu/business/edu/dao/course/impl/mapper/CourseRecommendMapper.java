package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommend;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommendExample;

@Mapper
public interface CourseRecommendMapper {
    int countByExample(CourseRecommendExample example);

    int deleteByExample(CourseRecommendExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseRecommend record);

    int insertSelective(CourseRecommend record);

    List<CourseRecommend> selectByExample(CourseRecommendExample example);

    CourseRecommend selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseRecommend record, @Param("example") CourseRecommendExample example);

    int updateByExample(@Param("record") CourseRecommend record, @Param("example") CourseRecommendExample example);

    int updateByPrimaryKeySelective(CourseRecommend record);

    int updateByPrimaryKey(CourseRecommend record);
}