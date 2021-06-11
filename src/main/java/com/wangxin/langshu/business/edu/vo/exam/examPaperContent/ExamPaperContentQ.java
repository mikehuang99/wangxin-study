package com.wangxin.langshu.business.edu.vo.exam.examPaperContent;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ExamPaperContentQ  implements java.io.Serializable{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1191215451068817495L;
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
	
	@ApiModelProperty(value = "exam_paper表的ID", required = false)
	private String examPaperId;//exam_paper表的ID
	@ApiModelProperty(value = "试卷内容", required = false)
	private String examPaperContent;//试卷内容
	@ApiModelProperty(value = "出这试卷的教师用户编号", required = false)
    private String teacherUserNo;//出这试卷的教师用户编号
	
	@ApiModelProperty(value = "用户编号", required = false)
	private String userNo;//用户编号
	
	

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
