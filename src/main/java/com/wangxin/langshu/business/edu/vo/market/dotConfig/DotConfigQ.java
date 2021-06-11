package com.wangxin.langshu.business.edu.vo.market.dotConfig;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DotConfigQ  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327818649142325233L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "初次注册获得点券", required = false)
    private String regDot;//初次注册获得点券
	@ApiModelProperty(value = "每次登陆获取点券", required = false)
    private String loginDot;//每次登陆获取点券
	@ApiModelProperty(value = "登陆一天获得的最大点券", required = false)
    private String loginMaxDotPerDay;//登陆一天获得的最大点券
	@ApiModelProperty(value = "分享每次获得得点券", required = false)
    private String shareDot;//分享每次获得得点券
	@ApiModelProperty(value = "分享链接每天获得的最大点券", required = false)
    private String shareMaxDotPerDay;//分享链接每天获得的最大点券
	@ApiModelProperty(value = "分享后每次注册获得的点券", required = false)
    private String shareRegDot;//分享后每次注册获得的点券
	@ApiModelProperty(value = "分享链接被注册每天获得的最大点券", required = false)
    private String shareRegMaxDotPerDay;//分享链接被注册每天获得的最大点券
	@ApiModelProperty(value = "多少点券可以换一个积分", required = false)
    private String dotPoint;//多少点券可以换一个积分
	@ApiModelProperty(value = "推广注册链接", required = false)
    private String regLink;//推广注册链接

	
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
}
