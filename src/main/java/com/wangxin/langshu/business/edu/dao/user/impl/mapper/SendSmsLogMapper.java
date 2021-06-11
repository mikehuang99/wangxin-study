package com.wangxin.langshu.business.edu.dao.user.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLog;
import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLogExample;

@Mapper
public interface SendSmsLogMapper {
    int countByExample(SendSmsLogExample example);

    int deleteByExample(SendSmsLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SendSmsLog record);

    int insertSelective(SendSmsLog record);

    List<SendSmsLog> selectByExample(SendSmsLogExample example);

    SendSmsLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SendSmsLog record, @Param("example") SendSmsLogExample example);

    int updateByExample(@Param("record") SendSmsLog record, @Param("example") SendSmsLogExample example);

    int updateByPrimaryKeySelective(SendSmsLog record);

    int updateByPrimaryKey(SendSmsLog record);
}