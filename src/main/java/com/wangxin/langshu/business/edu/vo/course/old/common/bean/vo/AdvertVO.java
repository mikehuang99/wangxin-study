package com.wangxin.langshu.business.edu.vo.course.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AdvertVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	private Integer validValue;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 广告标题
	 */
	private String advertTitle;
	/**
	 * 广告图片
	 */
	private String advertImg;
	/**
	 * 广告链接
	 */
	private String advertUrl;
	/**
	 * 广告跳转方式
	 */
	private String advertTarget;
	/**
	 * 广告位置(1首页轮播)
	 */
	private Integer advertLocation;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 位置(0电脑端，1微信端)
	 */
	private Integer platShow;

}
