package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单支付信息表
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthOrderPayDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orderNo;

	private String payMessage;

	private String courseName;

	private BigDecimal price;

	private Integer payType;

}
