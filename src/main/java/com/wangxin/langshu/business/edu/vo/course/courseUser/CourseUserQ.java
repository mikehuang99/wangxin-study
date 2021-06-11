package com.wangxin.langshu.business.edu.vo.course.courseUser;

import java.io.Serializable;
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
public class CourseUserQ  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6124476128331186089L;
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
	
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "绑定用户userNo", required = false)
    private String bindUserNo;//绑定用户userNo

	@ApiModelProperty(value = "有效期", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date validTerm;//有效期
	
	@ApiModelProperty(value = "该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)", required = false)
    private String totalStudyProgress;//该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)
	
	
	@ApiModelProperty(value = "该课程的总学习进度大于(0表示0%，1表示1%，2表示2%......100表示100%)", required = false)
    private String totalStudyProgressGreaterThan;//该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)
	
	@ApiModelProperty(value = "该课程的总学习进度小于(0表示0%，1表示1%，2表示2%......100表示100%)", required = false)
    private String totalStudyProgressLessThan;//该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)
	
	@ApiModelProperty(value = "教师userNo", required = false)
    private String teacherUserNo;//教师userNo
	
	@ApiModelProperty(value = "用户userNo", required = false)
    private String userNo;//用户userNo
	
	
	@ApiModelProperty(value = "课程名称", required = false)
    private String courseName;//课程名称
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
