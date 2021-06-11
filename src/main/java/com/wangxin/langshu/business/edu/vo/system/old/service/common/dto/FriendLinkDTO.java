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
 * 站点友情链接
 *
 * 
 */
@Data
@Accessors(chain = true)
public class FriendLinkDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3728227989472097787L;
	/**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifiedTime;
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
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String linkName;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    @ApiModelProperty(value = "跳转方式(_blank，_self)")
    private String linkTarget;
}
