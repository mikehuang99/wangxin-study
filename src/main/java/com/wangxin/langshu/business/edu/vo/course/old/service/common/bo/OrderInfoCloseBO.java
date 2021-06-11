package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 关闭订单信息
 * 
 * 
 */
@Data
@Accessors(chain = true)
public class OrderInfoCloseBO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNo; // 订单编号

}
