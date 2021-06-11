package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MobileSubjectViewParamVo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 770745249860445144L;
	@ApiModelProperty(value = "ID", required = true)
	private String id;

}
