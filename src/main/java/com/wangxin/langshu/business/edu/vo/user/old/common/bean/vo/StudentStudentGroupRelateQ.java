package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class StudentStudentGroupRelateQ  implements java.io.Serializable{
	

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8000888140973426677L;
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
	@ApiModelProperty(value = "用户组ID", required = false)
    private String studentGroupId;//用户组ID
	@ApiModelProperty(value = "用户编号", required = false)
    private String userNo;//用户编号
	@ApiModelProperty(value = "是否相关，是就1，不是就0，查询全部为空", required = false)
	private String isRelate;
	
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
	
	@ApiModelProperty(value = "用户编号(查对应用户的分组)", required = false)
    private String userNoForGroup;//用户编号(查对应用户的分组)
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
