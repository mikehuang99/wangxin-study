package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
public class AdvertDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键id")
	private String id;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 广告标题
	 */
	@ApiModelProperty(value = "广告标题")
	private String advertTitle;
	/**
	 * 广告图片
	 */
	@ApiModelProperty(value = "广告图片")
	private String advertImg;
	/**
	 * 广告链接
	 */
	@ApiModelProperty(value = "广告链接")
	private String advertUrl;
	/**
	 * 广告跳转方式
	 */
	@ApiModelProperty(value = "广告跳转方式")
	private String advertTarget;
	/**
	 * 广告位置(1首页轮播)
	 */
	@ApiModelProperty(value = "广告位置(1首页轮播)")
	private Integer advertLocation;
	
	/**
	 * 广告类型(1为外部链接，2为内部内容，当值为2时，将显示advert_content的内容)
	 */
	@ApiModelProperty(value = "广告类型(1为外部链接，2为内部内容，当值为2时，将显示advert_content的内容)", required = false)
	private Integer advertType;
	
	/**
	 * 当advert_type为2的时候，使用advert_content的值
	 */
	@ApiModelProperty(value = "当advert_type为2的时候，使用advert_content的值", required = false)
	private String advertContent;
	
}
