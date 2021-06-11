package com.wangxin.langshu.business.edu.vo.system.old.service.common.resq;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 头部导航
 *
 */
@Data
@Accessors(chain = true)
public class NavigationBarViewRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3349455767019218670L;
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
     * 导航标题
     */
    @ApiModelProperty(value = "导航标题")
    private String navigationTitle;
    /**
     * 跳转方式
     */
    @ApiModelProperty(value = "跳转方式")
    private String target;
}
