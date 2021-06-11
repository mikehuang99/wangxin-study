package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告信息-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AdvertSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 位置(0电脑端，1微信端)
	 */
	@ApiModelProperty(value = "位置(0电脑端，1微信端,2移动网页端)", required = false)
	private Integer platShow;
	/**
	 * 广告标题
	 */
	@ApiModelProperty(value = "广告标题", required = true)
	private String advertTitle;
	/**
	 * 广告图片
	 */
	@ApiModelProperty(value = "广告图片", required = true)
	private String advertImg;
	/**
	 * 广告链接
	 */
	@ApiModelProperty(value = "广告链接", required = false)
	private String advertUrl;
	/**
	 * 跳转方式（_blank：新窗口打开；_self：同窗口打开）
	 */
	@ApiModelProperty(value = "跳转方式（_blank：新窗口打开；_self：同窗口打开）", required = true)
	private String advertTarget;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间", required = true)
	private String beginTime;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间", required = true)
	private String endTime;
	
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
