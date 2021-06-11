package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MobileStudentViewParamVo implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8394049728281070675L;
	@ApiModelProperty(value = "ID", required = true)
	private String id;
	
	@ApiModelProperty(value = "mobile", required = true)
	private String mobile;
	

}
