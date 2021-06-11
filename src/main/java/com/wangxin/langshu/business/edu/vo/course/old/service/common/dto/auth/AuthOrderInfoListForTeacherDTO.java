package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class AuthOrderInfoListForTeacherDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
	@JsonSerialize(using = ToStringSerializer.class)
    private String orderNo;
    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal priceDiscount;
    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal pricePay;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 手机号（脱敏处理）
     */
    @ApiModelProperty(value = "手机号（脱敏处理）")
    private String phone;

}