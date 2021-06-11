package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news;

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
public class NewsViewVo  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3077852225624253394L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "新闻标题", required = false)
	private String newsTitle;//新闻标题	
	@ApiModelProperty(value = "展示平台(位置(0电脑端，1微信端，2移动网页端，3全部))", required = false)
    private Integer platShow;
	@ApiModelProperty(value = "新闻内容", required = false)
	private String newsContent;


}
