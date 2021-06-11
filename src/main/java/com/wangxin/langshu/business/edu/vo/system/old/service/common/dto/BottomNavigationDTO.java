package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
public class BottomNavigationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1有效, 0无效)
	 */
	@ApiModelProperty(value = "状态(1有效, 0无效)")
	private Integer validValue;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 父ID
	 */
	@ApiModelProperty(value = "父ID")
	private String parentId;
	/**
	 * 导航名称
	 */
	@ApiModelProperty(value = "导航名称")
	private String navigationName;

	/**
	 * 站点导航集合
	 */
	@ApiModelProperty(value = "站点导航集合")
	private List<BottomNavigationDTO> bottomNavigationList = new ArrayList<>();
}
