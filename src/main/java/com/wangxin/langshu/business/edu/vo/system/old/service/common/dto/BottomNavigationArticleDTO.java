package com.wangxin.langshu.business.edu.vo.system.old.service.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航文章
 *
 * 
 */
@Data
@Accessors(chain = true)
public class BottomNavigationArticleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)", required = true)
    private Integer validValue;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;
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
