package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherApplyPageREQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3782271321596205874L;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 教师名称
	 */
	@ApiModelProperty(value = "教师名称", required = false)
	private String teacherName;
	/**
	 * 教师手机
	 */
	@ApiModelProperty(value = "教师手机", required = false)
	private String teacherMobile;
	/**
	 * 审核状态(0:待审核,1:审核通过,2:审核不通过)
	 */
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = false)
	private Integer applyStatus;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;

}
