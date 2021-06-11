/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否学习枚举类
 */
@Getter
@AllArgsConstructor
public enum IsStudyEnum {

    YES(1, "已学习"), NO(0, "未学习");

    private Integer code;

    private String desc;

}
