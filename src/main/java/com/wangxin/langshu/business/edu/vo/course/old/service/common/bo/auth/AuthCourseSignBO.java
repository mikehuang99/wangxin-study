/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取课时code值实体类
 * 
 *  forest
 */
@Data
@Accessors(chain = true)
public class AuthCourseSignBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String userNo;
	/**
	 * 课时编号
	 */
	@ApiModelProperty(value = "课时编号", required = true)
	private String lessonId;

	/**
	 * 播放ip
	 */
	@ApiModelProperty(value = "播放IP地址", required = false)
	private String ip = "127.0.0.1";

}
