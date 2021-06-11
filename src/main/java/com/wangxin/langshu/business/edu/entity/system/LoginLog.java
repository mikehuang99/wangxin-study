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
@TableName(value ="login_log")
public class LoginLog extends Model<LoginLog> implements Serializable{


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
	
	@TableField(value = "login_user_no")
	private String loginUserNo;//登录的用户编号	
	@TableField(value = "client_id")
	private String clientId;//客户端ID	
	@TableField(value = "login_status")
	private int loginStatus;//登录状态(1成功，0失败)
	@TableField(value = "login_ip")
	private String loginIp;//登录IP
	@TableField(value = "login_platform")
	private int loginPlatform;//登录平台(1PC端,2移动端,3后台管理)
	
	
	

}

