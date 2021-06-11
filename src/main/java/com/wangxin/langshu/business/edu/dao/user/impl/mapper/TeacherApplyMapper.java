package com.wangxin.langshu.business.edu.dao.user.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.user.old.TeacherApply;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherApplyExample;

@Mapper
public interface TeacherApplyMapper {
    int countByExample(TeacherApplyExample example);

    int deleteByExample(TeacherApplyExample example);

    int deleteByPrimaryKey(String id);

    int insert(TeacherApply record);

    int insertSelective(TeacherApply record);

    List<TeacherApply> selectByExample(TeacherApplyExample example);

    TeacherApply selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TeacherApply record, @Param("example") TeacherApplyExample example);

    int updateByExample(@Param("record") TeacherApply record, @Param("example") TeacherApplyExample example);

    int updateByPrimaryKeySelective(TeacherApply record);

    int updateByPrimaryKey(TeacherApply record);
}