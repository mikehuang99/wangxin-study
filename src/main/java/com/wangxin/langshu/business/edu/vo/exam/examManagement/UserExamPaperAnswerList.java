package com.wangxin.langshu.business.edu.vo.exam.examManagement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class UserExamPaperAnswerList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6821545386108581213L;

	private String id;
	
	@ApiModelProperty(value = "创建时间", required = false)
    private Date createTime;//创建时间	
	
	@ApiModelProperty(value = "userNo", required = false)
	private String userNo;//用户userNo
	
	@ApiModelProperty(value = "examManagementId", required = false)
	private String examManagementId;//考试ID
	
	@ApiModelProperty(value = "examPaperId", required = false)
	private String examPaperId;//试卷ID
	
	@ApiModelProperty(value = "试题答案列表", required = false)
	private List<UserExamPaperAnswer> userExamPaperAnswerList;//试题答案列表

}
