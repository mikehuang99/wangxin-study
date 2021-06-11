package com.wangxin.langshu.business.edu.dao.system.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigation;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationExample;

@Mapper
public interface BottomNavigationMapper {
    int countByExample(BottomNavigationExample example);

    int deleteByExample(BottomNavigationExample example);

    int deleteByPrimaryKey(String id);

    int insert(BottomNavigation record);

    int insertSelective(BottomNavigation record);

    List<BottomNavigation> selectByExample(BottomNavigationExample example);

    BottomNavigation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BottomNavigation record, @Param("example") BottomNavigationExample example);

    int updateByExample(@Param("record") BottomNavigation record, @Param("example") BottomNavigationExample example);

    int updateByPrimaryKeySelective(BottomNavigation record);

    int updateByPrimaryKey(BottomNavigation record);
}