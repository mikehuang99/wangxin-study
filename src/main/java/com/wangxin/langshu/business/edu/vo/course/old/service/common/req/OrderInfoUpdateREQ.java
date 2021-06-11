package com.wangxin.langshu.business.edu.vo.course.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单信息表-更新
 *
 *  
 */
@Data
@Accessors(chain = true)
public class OrderInfoUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 后台备注
	 */
	@ApiModelProperty(value = "后台备注", required = false)
	private String remark;

}
