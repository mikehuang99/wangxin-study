package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航
 *
 * 
 */
@Data
@Accessors(chain = true)
public class BottomNavigationListDTO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 89790137447343044L;
	/**
	 * 站点导航集合
	 */
	@ApiModelProperty(value = "站点导航集合")
	private List<BottomNavigationDTO> bottomNavigationList = new ArrayList<>();
}
