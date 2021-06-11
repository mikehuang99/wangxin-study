package com.wangxin.langshu.business.edu.vo.course.courseUser;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CourseUserDeleteVo  implements Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8203179972714511298L;

	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;
	
	@ApiModelProperty(value = "ID", required = true)
	private String id;
	@ApiModelProperty(value = "userNo", required = true)
	private String userNo;

}
