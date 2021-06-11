package com.wangxin.langshu.business.edu.vo.exam.examManagement;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ExamManagementQ  implements java.io.Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3981804373190290946L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private String	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	@ApiModelProperty(value = "考试主题", required = false)
	private String title;//考试主题
	@ApiModelProperty(value = "考试简介", required = false)
	private String introduce;//考试简介
	@ApiModelProperty(value = "参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员", required = false)
	private String examObjectType;//参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员
	@ApiModelProperty(value = "参考对象(用户组ID集合)	", required = false)
	private String[] studentGroupIds;//参考对象(用户组ID集合)
	@ApiModelProperty(value = "考试类型，1定时考试，2时段考试", required = false)
	private String examType;//考试类型，1定时考试，2时段考试
	@ApiModelProperty(value = "考试开始时间", required = false)
	private String startTime;//考试开始时间
	@ApiModelProperty(value = "考试结束时间", required = false)
	private String endTime;//考试结束时间
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
	@ApiModelProperty(value = "teacherUserNo", required = false)
    private String teacherUserNo;//user表对应的user_no
	
	@ApiModelProperty(value = "userNo", required = false)
    private String userNo;//用户编号
	
	@ApiModelProperty(value = "课程ID", required = false)
    private String courseId;//课程ID
	@ApiModelProperty(value = "试卷主题", required = false)
    private String examPaperTitle;//试卷主题
	
	@ApiModelProperty(value = "examUserNo", required = false)
    private String examUserNo;//考试用户编号
	
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
