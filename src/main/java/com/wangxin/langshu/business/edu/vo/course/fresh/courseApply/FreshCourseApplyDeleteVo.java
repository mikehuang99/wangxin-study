package com.wangxin.langshu.business.edu.vo.course.fresh.courseApply;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshCourseApplyDeleteVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550254859012964105L;
	@ApiModelProperty(value = "ID数组", required = true)
	private String[] ids;

	@ApiModelProperty(value = "ID", required = true)
	private String id;
	

}
