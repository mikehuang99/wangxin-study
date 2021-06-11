package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台用户信息-更新
 *
 *  
 */
@Data
@Accessors(chain = true)
public class SystemUserUpdateREQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2075418893535977305L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private String id;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)")
	private Integer validValue;
	/**
	 * 手机
	 */
	@ApiModelProperty(value = "手机", required = true)
	private String mobile;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
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
