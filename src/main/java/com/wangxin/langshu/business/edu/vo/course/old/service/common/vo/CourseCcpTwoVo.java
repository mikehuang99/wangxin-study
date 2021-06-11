/**
 *  广州旺鑫计算机科技有限公司
 */
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
 * <p>
 * 课程分类
 * </p>
 *
 */
@Data
@Accessors(chain = true)
public class CourseCcpTwoVo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8649588359128717608L;
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
	private List<CourseCcpThreeVo> threeList = new ArrayList<>();

}
