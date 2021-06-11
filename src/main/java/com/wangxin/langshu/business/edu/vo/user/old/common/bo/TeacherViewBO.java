/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.user.old.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 教师信息
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class TeacherViewBO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4858617559355364052L;

	/**
	 * 教师编号
	 */
	@ApiModelProperty(value = "教师用户编号", required = true)
	private String teacherUserNo;
	
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNo;

}
