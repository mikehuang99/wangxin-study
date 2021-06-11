package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class SystemConfigPcWebViewVo  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5490324089383368617L;


	@ApiModelProperty(value = "id", required = false)
    private String id;
	

	@ApiModelProperty(value = "是否开启PC端支付宝支付，0关闭，1开启", required = false)
	private int isOpenZfbPc;//是否开启PC端支付宝支付，0关闭，1开启

	
	@ApiModelProperty(value = "是否开启移动端支付宝支付，0关闭，1开启", required = false)
	private int isOpenZfbMobile;//是否开启移动端支付宝支付，0关闭，1开启
	
	@ApiModelProperty(value = "是否开启PC端微信支付，0关闭，1开启", required = false)
	private int isOpenWeixinpayPc;//是否开启PC端微信支付，0关闭，1开启
	@ApiModelProperty(value = "是否开启移动端微信支付，0关闭，1开启", required = false)
	private int isOpenWeixinpayMobile;//是否开启移动端微信支付，0关闭，1开启
	@ApiModelProperty(value = "是否开启小程序端微信支付，0关闭，1开启", required = false)
	private int isOpenWeixinpayXiaochengxu;//是否开启小程序端微信支付，0关闭，1开启

	@ApiModelProperty(value = "登录日志保留天数", required = false)
	private int loginLogRetentionDays;//登录日志保留天数
	@ApiModelProperty(value = "操作日志保留天数", required = false)
	private int operateLogRetentionDays;//操作日志保留天数
}
