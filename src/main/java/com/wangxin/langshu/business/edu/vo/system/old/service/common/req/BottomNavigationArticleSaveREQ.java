package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

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
public class BottomNavigationArticleSaveREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6658582608450440738L;
	/**
	 * 导航ID
	 */
	@ApiModelProperty(value = "导航ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private String navigationId;
	/**
	 * 文章标题
	 */
	@ApiModelProperty(value = "文章标题", required = true)
	private String articleTitle;
	/**
	 * 文章描述
	 */
	@ApiModelProperty(value = "文章描述", required = true)
	private String articleContent;
}
