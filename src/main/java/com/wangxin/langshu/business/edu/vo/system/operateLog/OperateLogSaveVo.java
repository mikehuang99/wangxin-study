package com.wangxin.langshu.business.edu.vo.system.operateLog;

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
public class OperateLogSaveVo  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6240809693195311622L;
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
	
	@ApiModelProperty(value = "POST,GET", required = false)
	private String operateMethod;//POST,GET
	@ApiModelProperty(value = "操作参数", required = false)
	private String operateParameter;//操作参数
	@ApiModelProperty(value = "操作URI", required = false)
	private String operateUri;//操作URI	
	@ApiModelProperty(value = "操作IP地址", required = false)
	private String operateIp;//操作IP地址	
	@ApiModelProperty(value = "操作用户userNo", required = false)
	private String operateUserNo;//操作用户userNo
	@ApiModelProperty(value = "操作平台(1PC端,2移动端,3后台管理)", required = false)
	private int operatePlatform;//操作平台(1PC端,2移动端,3后台管理)
	
}
