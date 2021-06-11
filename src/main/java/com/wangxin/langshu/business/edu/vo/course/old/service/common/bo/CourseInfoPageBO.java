package com.wangxin.langshu.business.edu.vo.course.old.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class CourseInfoPageBO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private int pageCurrent = 1;
	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数")
	private int pageSize = 20;
	/**
	 * 一级分类编号
	 */
	@ApiModelProperty(value = "一级分类编号")
	private String subjectId1;
	/**
	 * 二级分类编号
	 */
	@ApiModelProperty(value = "二级分类编号")
	private String subjectId2;
	/**
	 * 三级分类编号
	 */
	@ApiModelProperty(value = "三级分类编号")
	private String subjectId3;
	/**
	 * 课程名称
	 */
	@ApiModelProperty(value = "课程名称")
    private String courseName;
	/**
	 * 是否免费(1:免费，0:收费)
	 */
	@ApiModelProperty(value = "是否免费(1:免费，0:收费)")
    private Integer isFree;
	
	/**
	 * 排序类型数值
	 * 1、智能排序（按预设sort排序）
		2、学习数量
		3、购买数量
		4、原价最高
		5、原价最低
		6、折扣价后最高
		7、折扣价后最低
	 */
	@ApiModelProperty(value = "排序类型数值")
    private String sortType;
}
