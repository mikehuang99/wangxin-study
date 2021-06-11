package com.wangxin.langshu.business.edu.vo.market.dotOperateRecord;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class DotOperateRecordQ  implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 7842017126446670765L;
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
	
	@ApiModelProperty(value = "点券所属用户的用户编号", required = false)
    private String userNoOfDot;//用户编号
	@ApiModelProperty(value = "操作类型，0增加，1减少", required = false)
    private String operateType;//操作类型，0增加，1减少
	@ApiModelProperty(value = "点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册、5兑换积分", required = false)
    private String dotChangeReson;//点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册、5兑换积分
	@ApiModelProperty(value = "操作日期字符串，格式：2010-09-10", required = false)
    private String operateDate;//操作日期字符串，格式：2010-09-10
	@ApiModelProperty(value = "点券", required = false)
    private String dot;//点券
	@ApiModelProperty(value = "点券总量", required = false)
    private String totalDot;//点券总量
	
	
	
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
