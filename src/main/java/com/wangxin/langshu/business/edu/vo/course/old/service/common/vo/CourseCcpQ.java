package com.wangxin.langshu.business.edu.vo.course.old.service.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类
 *
 */
@Data
@Accessors(chain = true)
public class CourseCcpQ implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 587085701111894605L;
	@ApiModelProperty(value = "用户user_no", required = true)
	private String userNo;
	
}
