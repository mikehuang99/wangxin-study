package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthTeacherApplyViewBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3983882473518791772L;
	/**
	 * 教师用户编号
	 */
	@ApiModelProperty(value = "教师用户编号", required = true)
	private String teacherUserNo;
}
