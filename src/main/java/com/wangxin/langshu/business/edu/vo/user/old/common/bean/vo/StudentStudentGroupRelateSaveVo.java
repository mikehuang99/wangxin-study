package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudentStudentGroupRelateSaveVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8819623388056953953L;
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
	@ApiModelProperty(value = "用户组ID", required = false)
    private String studentGroupId;//用户组ID
	@ApiModelProperty(value = "userIds[保存studentGroupId和userNo关系，只能使用userIds存userNo的值]", required = false)
    private String[] userIds;//用户组ID
	@ApiModelProperty(value = "用户分组对应的用户编号", required = false)
    private String userNoForGroup;//用户分组对应的用户编号
	@ApiModelProperty(value = "bindType绑定类型。1为绑定，2为解绑", required = true)
	private String bindType;//绑定类型。1为绑定，2为解绑
}
