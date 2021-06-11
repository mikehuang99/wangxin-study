package com.wangxin.langshu.business.edu.vo.exam.examManagementUser;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ExamManagementUserQ  implements java.io.Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4197197201044371920L;
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
	
	@ApiModelProperty(value = "考试ID", required = false)
	private String examManagementId;//考试ID	
	@ApiModelProperty(value = "试卷ID", required = false)
	private String examPaperId;//试卷ID
	@ApiModelProperty(value = "考试用户", required = false)
	private String examUserNo;//考试用户
	@ApiModelProperty(value = "用户考试答案", required = false)
	private String examPaperAnswer;//用户考试答案	

	
	@ApiModelProperty(value = "0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分", required = false)
	private String isFinishUserTotalScore;//0为未完成评分(其实不会有0的数据)，1为已经完成评分，2为等待教师评分
	@ApiModelProperty(value = "用户总分", required = false)
	private String userTotalScore;//用户总分
	
	@ApiModelProperty(value = "参考类型ID，1全体人员,2学员分组,3全体学员中拥有该课程的学员,4分组学员中拥有该课程的学员", required = false)
	private String examObjectType;//参考类型ID，1全体人员,2学员分组,3全体学员中拥有该课程的学员,4分组学员中拥有该课程的学员
	@ApiModelProperty(value = "用户编号", required = false)
	private String userNo;//用户编号
	
	@ApiModelProperty(value = "用户真实姓名", required = false)
	private String realName;//用户真实姓名
	
	@ApiModelProperty(value = "身份证号码", required = false)
    private String identityCardNo;//身份证号码
	
	
	
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
