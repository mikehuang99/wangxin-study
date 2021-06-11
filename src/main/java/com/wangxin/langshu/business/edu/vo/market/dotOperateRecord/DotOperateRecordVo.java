package com.wangxin.langshu.business.edu.vo.market.dotOperateRecord;

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
public class DotOperateRecordVo  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6798335604397783866L;
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
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	
	@ApiModelProperty(value = "点券所属用户的用户编号", required = false)
    private String userNoOfDot;//用户编号
	@ApiModelProperty(value = "操作类型，0增加，1减少", required = false)
    private int operateType;//操作类型，0增加，1减少
	@ApiModelProperty(value = "点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册、5兑换积分", required = false)
    private int dotChangeReson;//点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册、5兑换积分
	@ApiModelProperty(value = "操作日期字符串，格式：2010-09-10", required = false)
    private String operateDate;//操作日期字符串，格式：2010-09-10
	@ApiModelProperty(value = "点券", required = false)
    private int dot;//点券
	@ApiModelProperty(value = "点券总量", required = false)
    private int totalDot;//点券总量
	
	
}
