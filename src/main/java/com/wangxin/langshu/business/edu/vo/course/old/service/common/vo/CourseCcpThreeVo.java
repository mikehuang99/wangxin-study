/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.vo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/*
 * 课程三级分类
 */
@Data
@Accessors(chain = true)
public class CourseCcpThreeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582185140753136693L;
	/**
	 * 分类编号
	 */
	@ApiModelProperty(value = "分类编号")
	private String id;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String name;
	/**
	 * 层级
	 */
	@ApiModelProperty(value = "分类名称")
	private Integer floor;

	/**
	 * 分页备注信息
	 */
	@ApiModelProperty(value = "分类备注信息")
	private String remark;

}
