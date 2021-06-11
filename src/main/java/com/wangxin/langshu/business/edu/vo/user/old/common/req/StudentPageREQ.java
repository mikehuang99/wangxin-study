package com.wangxin.langshu.business.edu.vo.user.old.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentPageREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6377051014042296657L;
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
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = false)
	private String userNo;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = false)
	private String mobile;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer validValue;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称", required = false)
	private String nickname;
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间", required = false)
	private String beginCreateTime;
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间", required = false)
	private String endCreateTime;

}
