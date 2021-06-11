/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * 
 */
@Data
@Accessors(chain = true)
public class CourseSubjectTwoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类编号
	 */
	@ApiModelProperty(value = "分类编号")
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String subjectName;
	/**
	 * 层级
	 */
	@ApiModelProperty(value = "分类层级")
	private Integer floor;

	/**
	 * 分类备注信息
	 */
	@ApiModelProperty(value = "分类备注")
	private String remark;

	/**
	 * 三级分类列表
	 */
	@ApiModelProperty(value = "三级分类列表")
	private List<CourseSubjectThreeDTO> threeList = new ArrayList<>();

}
