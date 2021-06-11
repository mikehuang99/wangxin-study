package com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class AuthStudentBankBO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -8601414493761809426L;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private String teacherUserNo;
	/**
     * 银行卡号
     */
	@ApiModelProperty(value = "银行卡号", required = true)
    private String bankCardNo;
    /**
     * 银行名称
     */
	@ApiModelProperty(value = "银行名称", required = true)
    private String bankName;
    /**
     * 银行支行名称
     */
	@ApiModelProperty(value = "银行支行名称", required = true)
    private String bankBranchName;
    /**
     * 银行开户名
     */
	@ApiModelProperty(value = "银行开户名", required = true)
    private String bankUserName;
    /**
     * 银行身份证号
     */
	@ApiModelProperty(value = "银行身份证号", required = true)
    private String bankIdCardNo;
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码", required = true)
    private String smsCode;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = true)
	private String mobile;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = true)
    private String clientId;
	
}
