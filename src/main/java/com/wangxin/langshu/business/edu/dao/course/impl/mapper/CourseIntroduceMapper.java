package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceExample;

@Mapper
public interface CourseIntroduceMapper {
    int countByExample(CourseIntroduceExample example);

    int deleteByExample(CourseIntroduceExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseIntroduce record);

    int insertSelective(CourseIntroduce record);

    List<CourseIntroduce> selectByExampleWithBLOBs(CourseIntroduceExample example);

    List<CourseIntroduce> selectByExample(CourseIntroduceExample example);

    CourseIntroduce selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseIntroduce record, @Param("example") CourseIntroduceExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseIntroduce record, @Param("example") CourseIntroduceExample example);

    int updateByExample(@Param("record") CourseIntroduce record, @Param("example") CourseIntroduceExample example);

    int updateByPrimaryKeySelective(CourseIntroduce record);

    int updateByPrimaryKeyWithBLOBs(CourseIntroduce record);

    int updateByPrimaryKey(CourseIntroduce record);
}