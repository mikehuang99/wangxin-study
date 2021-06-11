package com.wangxin.langshu.business.edu.vo.user.teacherStudentGroup;

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
public class TeacherStudentGroupQ  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1688019053410517486L;
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
	
	@ApiModelProperty(value = "教师ID", required = false)
    private String teacherId;//教师userNo
	@ApiModelProperty(value = "学生群组ID", required = false)
    private String studentGroupId;//学生群组ID
	
	@ApiModelProperty(value = "用户组名称", required = false)
    private String groupName;//用户组名称
	@ApiModelProperty(value = "是否已经绑定,1为绑定，2为未绑定，0为所有", required = false)
    private String bindType;//是否已经绑定,1为绑定，2为未绑定，0为所有
	
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
