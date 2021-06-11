package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class NewsQ  implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7970513555457971286L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "新闻标题", required = false)
	private String newsTitle;//新闻标题	
	@ApiModelProperty(value = "展示平台(位置(0电脑端，1微信端，2移动网页端，3全部))", required = false)
    private String platShow;
	@ApiModelProperty(value = "新闻内容", required = false)
	private String newsContent;
	
	@ApiModelProperty(value = "新闻类型ID", required = false)
	private String newsTypeId;
	@ApiModelProperty(value = "新闻类型内容", required = false)
	private String newsType;
	
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
