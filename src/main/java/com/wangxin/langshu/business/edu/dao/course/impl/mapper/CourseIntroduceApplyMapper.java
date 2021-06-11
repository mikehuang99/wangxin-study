package com.wangxin.langshu.business.edu.dao.course.impl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApplyExample;

@Mapper
public interface CourseIntroduceApplyMapper {
    int countByExample(CourseIntroduceApplyExample example);

    int deleteByExample(CourseIntroduceApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(CourseIntroduceApply record);

    int insertSelective(CourseIntroduceApply record);

    List<CourseIntroduceApply> selectByExampleWithBLOBs(CourseIntroduceApplyExample example);

    List<CourseIntroduceApply> selectByExample(CourseIntroduceApplyExample example);

    CourseIntroduceApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CourseIntroduceApply record, @Param("example") CourseIntroduceApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") CourseIntroduceApply record, @Param("example") CourseIntroduceApplyExample example);

    int updateByExample(@Param("record") CourseIntroduceApply record, @Param("example") CourseIntroduceApplyExample example);

    int updateByPrimaryKeySelective(CourseIntroduceApply record);

    int updateByPrimaryKeyWithBLOBs(CourseIntroduceApply record);

    int updateByPrimaryKey(CourseIntroduceApply record);
}