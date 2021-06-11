package com.wangxin.langshu.business.edu.vo.market.pointOperateRecord;

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
public class PointOperateRecordUpdateVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2284140062627145074L;
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
	
	@ApiModelProperty(value = "积分所属用户的用户编号", required = false)
    private String userNoOfPoint;//用户编号
	@ApiModelProperty(value = "操作类型，0增加，1减少", required = false)
    private int operateType;//操作类型，0增加，1减少
	@ApiModelProperty(value = "积分", required = false)
    private int point;//积分
	@ApiModelProperty(value = "总积分", required = false)
    private int totalPoint;//总积分
	
	
	
}
