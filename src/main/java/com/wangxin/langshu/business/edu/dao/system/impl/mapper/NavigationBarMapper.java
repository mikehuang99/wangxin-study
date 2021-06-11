package com.wangxin.langshu.business.edu.dao.system.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.system.old.NavigationBar;
import com.wangxin.langshu.business.edu.entity.system.old.NavigationBarExample;

@Mapper
public interface NavigationBarMapper {
    int countByExample(NavigationBarExample example);

    int deleteByExample(NavigationBarExample example);

    int deleteByPrimaryKey(String id);

    int insert(NavigationBar record);

    int insertSelective(NavigationBar record);

    List<NavigationBar> selectByExample(NavigationBarExample example);

    NavigationBar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NavigationBar record, @Param("example") NavigationBarExample example);

    int updateByExample(@Param("record") NavigationBar record, @Param("example") NavigationBarExample example);

    int updateByPrimaryKeySelective(NavigationBar record);

    int updateByPrimaryKey(NavigationBar record);
}