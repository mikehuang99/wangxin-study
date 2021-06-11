package com.wangxin.langshu.business.edu.entity.user.old;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="student_student_group_relate")
public class StudentStudentGroupRelate  implements Serializable{
	



	/**
	 * 
	 */
	private static final long serialVersionUID = 5179152095242771688L;
	@TableId(type = IdType.UUID)
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	@TableField(value = "student_group_id")
	private String studentGroupId;//用户组ID
	@TableField(value = "user_no_for_group")
	private String userNoForGroup;//用户分组对应的用户编号


}
