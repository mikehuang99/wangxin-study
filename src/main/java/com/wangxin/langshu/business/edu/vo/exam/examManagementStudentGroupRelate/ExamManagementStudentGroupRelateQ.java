package com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ExamManagementStudentGroupRelateQ  implements java.io.Serializable{
	

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3457526484380700191L;
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
	
	@ApiModelProperty(value = "考试管理表exam_management的ID", required = false)
	private String examManagementId;//考试管理表exam_management的ID
	@ApiModelProperty(value = "student_group学员组表的ID", required = false)
	private String studentGroupId;//student_group学员组表的ID
	
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
