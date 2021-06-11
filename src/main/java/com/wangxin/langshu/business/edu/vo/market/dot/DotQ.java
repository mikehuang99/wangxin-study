package com.wangxin.langshu.business.edu.vo.market.dot;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DotQ  implements Serializable {



	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5200273652462646784L;
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
	
	
	
	@ApiModelProperty(value = "点券总量", required = false)
    private String totalDot;//点券总量
	@ApiModelProperty(value = "绑定用户userNo", required = false)
    private String bindUserNo;//绑定用户userNo
	
	
	@ApiModelProperty(value = "用户userNo", required = false)
    private String userNo;//用户userNo
	
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
