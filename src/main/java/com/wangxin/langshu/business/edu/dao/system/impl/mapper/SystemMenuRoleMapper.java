package com.wangxin.langshu.business.edu.dao.system.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuRole;
import com.wangxin.langshu.business.edu.entity.system.old.SystemMenuRoleExample;

@Mapper
public interface SystemMenuRoleMapper {
    int countByExample(SystemMenuRoleExample example);

    int deleteByExample(SystemMenuRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SystemMenuRole record);

    int insertSelective(SystemMenuRole record);

    List<SystemMenuRole> selectByExample(SystemMenuRoleExample example);

    SystemMenuRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SystemMenuRole record, @Param("example") SystemMenuRoleExample example);

    int updateByExample(@Param("record") SystemMenuRole record, @Param("example") SystemMenuRoleExample example);

    int updateByPrimaryKeySelective(SystemMenuRole record);

    int updateByPrimaryKey(SystemMenuRole record);
}