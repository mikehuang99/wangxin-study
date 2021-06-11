package com.wangxin.langshu.business.edu.vo.user.userStudyLog;

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
public class UserStudyLogQ  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5384188977187080200L;
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
	@ApiModelProperty(value = "课程名称", required = false)
    private String courseName;//课程名称
	@ApiModelProperty(value = "章节ID", required = false)
    private String chapterId;//章节ID
	@ApiModelProperty(value = "章节名称", required = false)
    private String chapterName;//章节名称
	@ApiModelProperty(value = "课时ID", required = false)
    private String lessonId;//课时ID
	@ApiModelProperty(value = "课时名称", required = false)
    private String lessonName;//课时名称
	@ApiModelProperty(value = "学习的用户编号", required = false)
    private String studyUserNo;//学习的用户编号
	@ApiModelProperty(value = "学习进度(0表示0%，1表示1%，2表示2%......100表示100%)", required = false)
    private String studyProgressGreaterThan;//学习进度大于(0表示0%，1表示1%，2表示2%......100表示100%)
    private String studyProgressLessThan;//学习进度小于(0表示0%，1表示1%，2表示2%......100表示100%)

	@ApiModelProperty(value = "学习进度详细，以英文逗号分开，如值为1,2,3,6,60,65,80   表示学过了1%,2%,3%,6%,60%,65%,80%", required = false)
    private String studyProgessDetails;//学习进度详细，以英文逗号分开，如值为1,2,3,6,60,65,80   表示学过了1%,2%,3%,6%,60%,65%,80%
	
	@ApiModelProperty(value = "教师UserNo", required = false)
    private String teacherUserNo;//教师UserNo
	
	@ApiModelProperty(value = "用户编号", required = false)
    private String userNo;//用户编号
	
	@ApiModelProperty(value = "真实姓名", required = false)
    private String realName;//真实姓名
	@ApiModelProperty(value = "手机号码", required = false)
    private String mobile;//手机号码
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
