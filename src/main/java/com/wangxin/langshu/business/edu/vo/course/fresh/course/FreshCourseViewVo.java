package com.wangxin.langshu.business.edu.vo.course.fresh.course;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshCourseViewVo  implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919819893879502167L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	

	@ApiModelProperty(value = "教师userNo", required = false)
    private String teacherUserNo;//教师userNo
	@ApiModelProperty(value = "一级分类ID", required = false)
    private String subjectId1;//一级分类ID
	@ApiModelProperty(value = "二级分类ID", required = false)
    private String subjectId2;//二级分类ID
	@ApiModelProperty(value = "三级分类ID", required = false)
    private String subjectId3;//三级分类ID
	
	@ApiModelProperty(value = "一级分类名称", required = false)
    private String subjectName1;//一级分类名称
	@ApiModelProperty(value = "二级分类名称", required = false)
    private String subjectName2;//二级分类名称
	@ApiModelProperty(value = "三级分类名称", required = false)
    private String subjectName3;//三级分类名称
	
	
	@ApiModelProperty(value = "课程名称", required = false)
    private String courseName;//课程名称
	@ApiModelProperty(value = "课程封面", required = false)
    private String courseLogo;//课程封面
	@ApiModelProperty(value = "课程介绍ID", required = false)
    private String introduceId;//课程介绍ID
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
    private int isFree;//是否免费：1免费，0收费
	@ApiModelProperty(value = "原价", required = false)
    private BigDecimal courseOriginal;//原价
	@ApiModelProperty(value = "优惠价", required = false)
    private BigDecimal courseDiscount;//优惠价
	@ApiModelProperty(value = "是否上架(1:上架，0:下架)", required = false)
    private int isPutaway;//是否上架(1:上架，0:下架)
	@ApiModelProperty(value = "课程排序(前端显示使用)", required = false)
    private int courseSort;//课程排序(前端显示使用)
	@ApiModelProperty(value = "购买人数", required = false)
    private int buyCount;//购买人数
	@ApiModelProperty(value = "学习人数", required = false)
    private int studyCount;//学习人数
	@ApiModelProperty(value = "总课时数", required = false)
    private int lessonTotal;//总课时数
	@ApiModelProperty(value = "计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)", required = false)
    private int costTimeType;//计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
	@ApiModelProperty(value = "课程类型，0点播，1直播，2点播+直播", required = false)
    private int courseType;//课程类型，0点播，1直播，2点播+直播
	
	
}
