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
public class StudentGroupViewVo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8431762595472495053L;
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
	@ApiModelProperty(value = "用户组名称", required = false)
    private String groupName;//用户组名称
	@ApiModelProperty(value = "备注", required = false)
    private String remark;//用户组名称

}
