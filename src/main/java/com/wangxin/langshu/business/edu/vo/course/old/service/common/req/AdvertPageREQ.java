package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告信息-分页列出
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AdvertPageREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 广告标题
	 */
	@ApiModelProperty(value = "广告标题", required = false)
	private String advertTitle;
	/**
	 * 位置(0电脑端，1微信端,2移动端)
	 */
	@ApiModelProperty(value = "位置(0电脑端，1微信端,2移动网页端)", required = false)
	private Integer platShow;
	
	
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
