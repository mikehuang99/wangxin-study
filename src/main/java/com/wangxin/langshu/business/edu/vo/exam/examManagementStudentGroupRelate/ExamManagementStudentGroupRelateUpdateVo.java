package com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class ExamManagementStudentGroupRelateUpdateVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3019188641375677126L;
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
	@ApiModelProperty(value = "考试管理表exam_management的ID", required = false)
	private String examManagementId;//考试管理表exam_management的ID
	@ApiModelProperty(value = "student_group学员组表的ID", required = false)
	private String studentGroupId;//student_group学员组表的ID
}
