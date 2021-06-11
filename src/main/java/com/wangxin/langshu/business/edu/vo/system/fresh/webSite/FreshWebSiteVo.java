package com.wangxin.langshu.business.edu.vo.system.fresh.webSite;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshWebSiteVo  implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -5861283001377640184L;
	@ApiModelProperty(value = "id", required = false)
    private String id;
	@ApiModelProperty(value = "创建时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;//创建时间	
	@ApiModelProperty(value = "修改时间", required = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date modifiedTime;	//修改时间	
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private int	validValue;//状态(1:正常，0:禁用)	
	@ApiModelProperty(value = "排序", required = false)
    private int sort;//排序
	
	@ApiModelProperty(value = "faviconIcoUrl", required = false)
	private String faviconIcoUrl;
	@ApiModelProperty(value = "logoUrl", required = false)
    private String logoUrl;

	@ApiModelProperty(value = "站点标题", required = false)
    private String websiteTitle;

	@ApiModelProperty(value = "站点关键词", required = false)
    private String websiteKeyword;

	@ApiModelProperty(value = "站点描述", required = false)
    private String websiteDesc;

	@ApiModelProperty(value = "站点版权", required = false)
    private String copyright;

	@ApiModelProperty(value = "备案号", required = false)
    private String icp;
    
	@ApiModelProperty(value = "公安备案号(用于指向公安备案网站链接的ID)", required = false)
    private String gonganbeianId;

	@ApiModelProperty(value = "公安备案号", required = false)
    private String gonganbeian;

	@ApiModelProperty(value = "站点微信", required = false)
    private String weixinQrUrl;

	@ApiModelProperty(value = "小程序二维码", required = false)
    private String weixinXiaochengxuQrUrl;

	@ApiModelProperty(value = "站点微博", required = false)
    private String weibo;

	@ApiModelProperty(value = "是否开启统计", required = false)
    private Integer isOpenExternalCode;

	@ApiModelProperty(value = "统计代码", required = false)
    private String externalCode;

	@ApiModelProperty(value = "是否显示客服信息", required = false)
    private Integer isOpenQqService;

	@ApiModelProperty(value = "客服信息1", required = false)
    private String serviceQq1;

	@ApiModelProperty(value = "客服信息2", required = false)
    private String serviceQq2;

	@ApiModelProperty(value = "用户协议", required = false)
    private String userAgreement;
	
	
	@ApiModelProperty(value = "机构编号", required = false)
    private String orgNo;
	@ApiModelProperty(value = "cookie作用域	", required = false)
    private String cookieDomain;
	@ApiModelProperty(value = "cookie的token名称", required = false)
    private String cookieTokenName;
	@ApiModelProperty(value = "PC网站地址(带http://或者https://开头)，可以带工程名", required = false)
    private String pcwebMainUrl;
	@ApiModelProperty(value = "移动网站地址(带http://或者https://开头)，可以带工程名", required = false)
    private String mobileMainUrl;
	@ApiModelProperty(value = "网站主域名(不需要带http://等开头，直接如test.com即可)", required = false)
    private String mainUrl;
	
	@ApiModelProperty(value = "学习证明主印章名称(大圆的那个印章名称)", required = false)
    private String mainSealContent;//学习证明主印章名称(大圆的那个印章名称)
	@ApiModelProperty(value = "学习证明印章内横着的那行字内容", required = false)
    private String secondSealContent;//学习证明印章内横着的那行字内容
	
}
