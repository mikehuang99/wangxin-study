package com.wangxin.langshu.business.edu.entity.system;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="operate_log")
public class OperateLog extends Model<OperateLog> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1080071456243236514L;
	//@TableId(type = IdType.UUID)
	@TableField(value ="id")
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	
	@TableField(value = "operate_method")
	private String operateMethod;//POST,GET
	@TableField(value = "operate_parameter")
	private String operateParameter;//操作参数
	@TableField(value = "operate_uri")
	private String operateUri;//操作URI	
	@TableField(value = "operate_ip")
	private String operateIp;//操作IP地址	
	@TableField(value = "operate_user_no")
	private String operateUserNo;//操作用户userNo
	
	@TableField(value = "operate_platform")
	private int operatePlatform;//登录平台(1PC端,2移动端,3后台管理)
	

}

