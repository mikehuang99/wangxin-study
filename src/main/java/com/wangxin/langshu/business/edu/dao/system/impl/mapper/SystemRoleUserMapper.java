package com.wangxin.langshu.business.edu.dao.system.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleUser;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleUserExample;

@Mapper
public interface SystemRoleUserMapper {
    int countByExample(SystemRoleUserExample example);

    int deleteByExample(SystemRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SystemRoleUser record);

    int insertSelective(SystemRoleUser record);

    List<SystemRoleUser> selectByExample(SystemRoleUserExample example);

    SystemRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SystemRoleUser record, @Param("example") SystemRoleUserExample example);

    int updateByExample(@Param("record") SystemRoleUser record, @Param("example") SystemRoleUserExample example);

    int updateByPrimaryKeySelective(SystemRoleUser record);

    int updateByPrimaryKey(SystemRoleUser record);
}