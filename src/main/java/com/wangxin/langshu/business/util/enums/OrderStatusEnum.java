/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    WAIT(1, "待支付"), SUCCESS(2, "支付成功"), FAIL(3, "支付失败"), CLOSE(4, "已关闭");

    private Integer code;

    private String desc;

}
