package com.wangxin.langshu.business.edu.vo.system.loginLog;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class LoginLogSaveVo  implements Serializable {

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 8312374081836885774L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "登录的用户编号", required = false)
	private String loginUserNo;//登录的用户编号	
	@ApiModelProperty(value = "客户端ID", required = false)
	private String clientId;//客户端ID	
	@ApiModelProperty(value = "登录状态(1成功，0失败)", required = false)
	private int loginStatus;//登录状态(1成功，0失败)
	@ApiModelProperty(value = "登录IP", required = false)
	private String loginIp;//登录IP
	@ApiModelProperty(value = "登录平台(1PC端,2移动端,3后台管理)", required = false)
	private int loginPlatform;//登录平台(1PC端,2移动端,3后台管理)
	
	
}
