package com.wangxin.langshu.business.edu.vo.course.fresh.courseApply;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshCourseApplyQ  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8041787926791850724L;
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
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "教师userNo", required = false)
    private String teacherUserNo;//教师userNo
	@ApiModelProperty(value = "教师名称", required = false)
    private String teacherName;//教师名称
	@ApiModelProperty(value = "教师手机", required = false)
    private String teacherMobile;//教师手机
	
	@ApiModelProperty(value = "userNo", required = false)
    private String userNo;//教师userNo
	@ApiModelProperty(value = "一级分类ID", required = false)
    private String subjectId1;//一级分类ID
	@ApiModelProperty(value = "二级分类ID", required = false)
    private String subjectId2;//二级分类ID
	@ApiModelProperty(value = "三级分类ID", required = false)
    private String subjectId3;//三级分类ID
	@ApiModelProperty(value = "课程名称", required = false)
    private String courseName;//课程名称
	@ApiModelProperty(value = "课程封面", required = false)
    private String courseLogo;//课程封面
	@ApiModelProperty(value = "课程介绍ID", required = false)
    private String introduceId;//课程介绍ID
	@ApiModelProperty(value = "是否免费：1免费，0收费", required = false)
    private String isFree;//是否免费：1免费，0收费
	@ApiModelProperty(value = "原价", required = false)
    private String courseOriginal;//原价
	@ApiModelProperty(value = "优惠价", required = false)
    private String courseDiscount;//优惠价
	@ApiModelProperty(value = "是否上架(1:上架，0:下架)", required = false)
    private String isPutaway;//是否上架(1:上架，0:下架)
	@ApiModelProperty(value = "课程排序(前端显示使用)", required = false)
    private String courseSort;//课程排序(前端显示使用)
	@ApiModelProperty(value = "购买人数", required = false)
    private String buyCount;//购买人数
	@ApiModelProperty(value = "学习人数", required = false)
    private String studyCount;//学习人数
	@ApiModelProperty(value = "总课时数", required = false)
    private String lessonTotal;//总课时数
	@ApiModelProperty(value = "ids集合", required = false)
	private List<String> ids;//ids集合
	@ApiModelProperty(value = "是否用ids集合查询，如果值为1就要用ids集合查询", required = false)
	private String isQueryByIds;//是否用ids集合查询，如果值为1就要用ids集合查询
	
	
	@ApiModelProperty(value = "计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)", required = false)
    private int costTimeType;//计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
	@ApiModelProperty(value = "课程类型，0点播，1直播，2点播+直播", required = false)
    private int courseType;//课程类型，0点播，1直播，2点播+直播
	
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)", required = false)
    private String applyStatus;//审核状态(0:待审核,1:审核通过,2:审核不通过)
	@ApiModelProperty(value = "审核意见", required = false)
    private String applyResultDesc;//审核意见
	
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private int pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private int pageSize = 20;
}
