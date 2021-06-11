package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 教师信息查看
 *
 *  
 */
@Data
@Accessors(chain = true)
public class TeacherViewREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4920578506506154606L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private String id;
	/**
	 * 教师编号
	 */
	@ApiModelProperty(value = "教师编号")
	private String teacherUserNo;
}
