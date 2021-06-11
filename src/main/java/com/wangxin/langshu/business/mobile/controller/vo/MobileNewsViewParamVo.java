package com.wangxin.langshu.business.mobile.controller.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MobileNewsViewParamVo implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -6948871189866423851L;
	
	@ApiModelProperty(value = "ID", required = true)
	private String id;

}
