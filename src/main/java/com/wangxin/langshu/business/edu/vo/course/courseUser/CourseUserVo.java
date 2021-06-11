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
public class CourseUserVo  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 6362901587451374039L;
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
	

	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "绑定用户userNo", required = false)
    private String bindUserNo;//绑定用户userNo
	
	@ApiModelProperty(value = "有效期", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date validTerm;//有效期
	
	@ApiModelProperty(value = "有效期字符串yyyy-MM-dd HH:mm", required = false)
    private String validTermStr;//有效期字符串yyyy-MM-dd HH:mm
	
	@ApiModelProperty(value = "课程名称", required = false)
    private String courseName;//课程名称
	@ApiModelProperty(value = "课程logo", required = false)
    private String courseLogo;//课程logo
	
	@ApiModelProperty(value = "真实姓名", required = false)
    private String realName;//真实姓名
	@ApiModelProperty(value = "身份证号码", required = false)
    private String identityCardNo;//身份证号码
	@ApiModelProperty(value = "手机号码", required = false)
    private String mobile;//手机号码
	
	@ApiModelProperty(value = "该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)", required = false)
    private int totalStudyProgress;//该课程的总学习进度(0表示0%，1表示1%，2表示2%......100表示100%)
}
