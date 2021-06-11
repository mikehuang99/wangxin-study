package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class CacheLogVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8117207805465427481L;
	@ApiModelProperty(value = "id", required = false)
	private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;// 创建时间
	@ApiModelProperty(value = "修改时间", required = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifiedTime; // 修改时间
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private int validValue;// 状态(1:正常，0:禁用)
	@ApiModelProperty(value = "排序", required = false)
	private int sort;// 排序
	@ApiModelProperty(value = "缓存状态(1正在缓存，2缓存完毕,3执行失败)", required = false)
	private String cacheStatus;// 缓存状态(1正在缓存，2缓存完毕,3执行失败)
	@ApiModelProperty(value = "用户userNo", required = false)
	private String userNo;// 缓存操作历史

}
