package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * 广告信息- 分页列出
 *
 */
@Data
@Accessors(chain = true)
public class AdvertViewRESQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
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
	 * 跳转方式（_blank：新窗口打开；_self：同窗口打开）
	 */
	@ApiModelProperty(value = "跳转方式（_blank：新窗口打开；_self：同窗口打开）")
	private String advertTarget;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
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
