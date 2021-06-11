package com.wangxin.langshu.business.edu.vo.system.old.service.common.resq;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航文章-查看
 *
 */
@Data
@Accessors(chain = true)
public class BottomNavigationArticleViewRESQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6468548721264847034L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 导航ID
	 */
	@ApiModelProperty(value = "导航ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private String navigationId;
	/**
	 * 文章标题
	 */
	@ApiModelProperty(value = "文章标题")
	private String articleTitle;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	 private Integer sort;
	
	/**
	 * 文章描述
	 */
	@ApiModelProperty(value = "文章描述")
	private String articleContent;
}
