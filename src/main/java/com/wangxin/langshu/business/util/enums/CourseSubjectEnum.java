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
public enum CourseSubjectEnum {

	ORDINARY(1, "普通课程",""), RESOURCES(0, "资源区课程","red");
	
	private Integer code;

    private String desc;
    
    private String color;
}
