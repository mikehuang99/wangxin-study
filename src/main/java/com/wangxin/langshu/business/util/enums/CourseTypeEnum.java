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
public enum CourseTypeEnum {

    COURSE(1, "课程"), CHAPTER(2, "章节"), PERIOD(3, "课时");

    private Integer code;

    private String desc;

}
