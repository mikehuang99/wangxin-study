package com.wangxin.langshu.business.edu.vo.exam.examManagement;

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
public class ExamManagementVo  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 2937574845933507023L;
/**
	 * 
	 */
	
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "考试主题", required = false)
	private String title;//考试主题
	@ApiModelProperty(value = "考试简介", required = false)
	private String introduce;//考试简介
	@ApiModelProperty(value = "参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员", required = false)
	private String examObjectType;//参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员
	@ApiModelProperty(value = "参考对象(用户组名称集合)	", required = false)
	private String[] studentGroupNames;//参考对象(用户组名称集合)
	@ApiModelProperty(value = "参考对象(用户组ID集合)	", required = false)
	private String[] studentGroupIds;//参考对象(用户组ID集合)
	@ApiModelProperty(value = "考试类型，1定时考试，2时段考试", required = false)
	private int examType;//考试类型，1定时考试，2时段考试
	@ApiModelProperty(value = "考试开始时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date startTime;//考试开始时间
	@ApiModelProperty(value = "考试结束时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	private Date endTime;//考试结束时间
	@ApiModelProperty(value = "是否允许切换窗口(是1，否0)", required = false)
	private String isAllowSwitchWindow;//是否允许切换窗口(是1，否0)	
	@ApiModelProperty(value = "是否显示确认按钮(是1，否0)", required = false)
	private String isShowConfirmButton;//是否显示确认按钮(是1，否0)
	@ApiModelProperty(value = "是否允许选择(0不允许，1允许)", required = false)
	private String isAllowSelect;//是否允许选择(0不允许，1允许)
	@ApiModelProperty(value = "是否允许右键(0不允许，1允许)", required = false)
	private String isAllowRightClick;//是否允许右键(0不允许，1允许)	
	@ApiModelProperty(value = "是否允许重复提交试卷(0不允许，1允许)", required = false)
	private String isAllowRepeateHandInExamPaper;//是否允许重复提交试卷(0不允许，1允许)	
	@ApiModelProperty(value = "试卷ID", required = false)
	private String examPaperId;//试卷ID
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "teacherUserNo", required = false)
    private String teacherUserNo;//user表对应的user_no
	
	
	@ApiModelProperty(value = "courseName课程名称", required = false)
    private String courseName;//课程名称
	@ApiModelProperty(value = "试卷主题", required = false)
    private String examPaperTitle;//试卷主题
	
	@ApiModelProperty(value = "试卷限时【分数】(针对定时试卷限时)", required = false)
    private int limitTime;//试卷限时【分数】(针对定时试卷限时)
	
	@ApiModelProperty(value = "用户考试答案", required = false)
	private String examPaperAnswer;//用户考试答案	
	@ApiModelProperty(value = "0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分", required = false)
	private int isFinishUserTotalScore;//0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分
	@ApiModelProperty(value = "用户总分", required = false)
	private String userTotalScore;//用户总分
	
}
