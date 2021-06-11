package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApplyExample;

@Mapper
public interface ChapterApplyMapper {
    int countByExample(ChapterApplyExample example);

    int deleteByExample(ChapterApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(ChapterApply record);

    int insertSelective(ChapterApply record);

    List<ChapterApply> selectByExample(ChapterApplyExample example);

    ChapterApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ChapterApply record, @Param("example") ChapterApplyExample example);

    int updateByExample(@Param("record") ChapterApply record, @Param("example") ChapterApplyExample example);

    int updateByPrimaryKeySelective(ChapterApply record);

    int updateByPrimaryKey(ChapterApply record);
}