package com.wangxin.langshu.business.edu.vo.exam.examManagementUser;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamManagementUserUpdateVo  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4946164851265936972L;
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
	@ApiModelProperty(value = "考试ID", required = false)
	private String examManagementId;//考试ID	
	@ApiModelProperty(value = "试卷ID", required = false)
	private String examPaperId;//试卷ID
	@ApiModelProperty(value = "考试用户", required = false)
	private String examUserNo;//考试用户
	@ApiModelProperty(value = "用户考试答案", required = false)
	private String examPaperAnswer;//用户考试答案	
	@ApiModelProperty(value = "0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分", required = false)
	private int isFinishUserTotalScore;//0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分
	@ApiModelProperty(value = "用户总分", required = false)
	private String userTotalScore;//用户总分
	
	@ApiModelProperty(value = "userNo", required = false)
    private String userNo;//页面传过来的userNo
	
}
