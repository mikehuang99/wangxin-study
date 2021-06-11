package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApplyExample;

@Mapper
public interface LessonApplyMapper {
    int countByExample(LessonApplyExample example);

    int deleteByExample(LessonApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(LessonApply record);

    int insertSelective(LessonApply record);

    List<LessonApply> selectByExample(LessonApplyExample example);

    LessonApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LessonApply record, @Param("example") LessonApplyExample example);

    int updateByExample(@Param("record") LessonApply record, @Param("example") LessonApplyExample example);

    int updateByPrimaryKeySelective(LessonApply record);

    int updateByPrimaryKey(LessonApply record);
}