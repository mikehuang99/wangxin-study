package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息-添加
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserSaveREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3075279779740508859L;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String adminUserNo;
	/**
	 * 手机
	 */
	@ApiModelProperty(value = "手机", required = true)
	private String mobile;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty(value = "真实姓名", required = true)
	private String realName;
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码", required = true)
    private String identityCardNo;//身份证号码
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = false)
	private String remark;
	
}
