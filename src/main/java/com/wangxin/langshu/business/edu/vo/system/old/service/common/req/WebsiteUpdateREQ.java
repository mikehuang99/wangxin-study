package com.wangxin.langshu.business.edu.vo.system.old.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点信息-更新
 *
 */
@Data
@Accessors(chain = true)
public class WebsiteUpdateREQ implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1017928299451032091L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id", required = true)
	private String id;
	/**
	 * 状态(1有效, 0无效)
	 */
	@ApiModelProperty(value = "状态(1有效, 0无效)")
	private Integer validValue;
	/**
	 * 站点标题
	 */
	@ApiModelProperty(value = "站点标题")
	private String websiteTitle;
	/**
	 * 站点关键词
	 */
	@ApiModelProperty(value = "站点关键词")
	private String websiteKeyword;
	/**
	 * 站点描述
	 */
	@ApiModelProperty(value = "站点描述")
	private String websiteDesc;
	/**
	 * 站点版权
	 */
	@ApiModelProperty(value = "站点版权")
	private String copyright;
	/**
	 * 备案号
	 */
	@ApiModelProperty(value = "备案号")
	private String icp;
	
	/**
	 * 公安备案号
	 */
	@ApiModelProperty(value = "公安备案号")
	private String gonganbeianId;
	
	/**
	 * 公安备案
	 */
	@ApiModelProperty(value = "公安备案")
	private String gonganbeian;
	/**
	 * 站点微信
	 */
	@ApiModelProperty(value = "站点微信")
	private String weixinQrUrl;
	/**
	 * 微信小程序二维码
	 */
	@ApiModelProperty(value = "微信小程序二维码")
	private String weixinXiaochengxuQrUrl;
	/**
	 * 站点微博
	 */
	@ApiModelProperty(value = "站点微博")
	private String weibo;
	/**
	 * 是否开启统计
	 */
	@ApiModelProperty(value = "是否开启统计")
	private Integer isOpenExternalCode;
	/**
	 * 统计代码
	 */
	@ApiModelProperty(value = "统计代码")
	private String externalCode;
	/**
	 * 是否显示客服信息
	 */
	@ApiModelProperty(value = "是否显示客服信息")
	private Integer isOpenQqService;
	/**
	 * 客服信息1
	 */
	@ApiModelProperty(value = "客服信息1")
	private String serviceQq1;
	/**
	 * 客服信息2
	 */
	@ApiModelProperty(value = "客服信息2")
	private String serviceQq2;
	/**
	 * logoUrl
	 */
	@ApiModelProperty(value = "logoUrl")
	private String logoUrl;
	/**
	 * faviconIcoUrl
	 */
	@ApiModelProperty(value = "faviconIcoUrl")
	private String faviconIcoUrl;
	/**
	 * 用户协议
	 */
	@ApiModelProperty(value = "用户协议")
	private String userAgreement;
	
}
