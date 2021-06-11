package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AdvertListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "广告信息集合")
	private List<AdvertDTO> advertList =  new ArrayList<>();
}
