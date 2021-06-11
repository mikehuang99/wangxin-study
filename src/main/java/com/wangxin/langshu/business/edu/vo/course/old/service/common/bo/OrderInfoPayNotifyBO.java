/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单异步通知
 * 
 */
@Data
@Accessors(chain = true)
public class OrderInfoPayNotifyBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serialNumber;


    private int orderStatus;

}
