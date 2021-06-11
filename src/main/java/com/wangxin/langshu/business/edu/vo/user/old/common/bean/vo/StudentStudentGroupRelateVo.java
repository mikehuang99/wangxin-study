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
public class StudentStudentGroupRelateVo  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -22612709928989512L;
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
	@ApiModelProperty(value = "手机号码", required = false)
    private String mobile;//手机号码
	@ApiModelProperty(value = "真实姓名", required = false)
    private String realName;//真实姓名
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码", required = true)
    private String identityCardNo;//身份证号码
	@ApiModelProperty(value = "昵称", required = false)
    private String nickName;//昵称
	@ApiModelProperty(value = "性别", required = false)
    private String sex;//性别
	@ApiModelProperty(value = "年龄", required = false)
    private String age;//年龄
	@ApiModelProperty(value = "备注", required = false)
    private String mark;//备注
	
	

	
	@ApiModelProperty(value = "用户组ID", required = false)
    private String studentGroupId;//用户组ID
	
	
	@ApiModelProperty(value = "用户分组对应的用户编号", required = false)
    private String userNoForGroup;//用户分组对应的用户编号
	
	@ApiModelProperty(value = "用户编号", required = false)
    private String userNo;//用户编号
}
