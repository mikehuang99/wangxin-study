package com.wangxin.langshu.business.edu.entity.system.fresh;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="website")
public class FreshWebSite extends Model<FreshWebSite> implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8704923415801466337L;
	
	@TableField(value ="id")
    private String id;
	@TableField(value ="create_time")
    private Date createTime;//创建时间	
	@TableField(value = "modified_time",update="now()")
    private Date modifiedTime;	//修改时间	
	@TableField(value = "valid_value")
    private int	validValue;//状态(1:正常，0:禁用)	
	@TableField(value = "sort")
    private int sort;//排序
	

	
	@TableField(value ="favicon_ico_url")
	private String faviconIcoUrl;
	@TableField(value ="logo_url")
    private String logoUrl;
	@TableField(value ="website_title")
    private String websiteTitle;//站点标题
	@TableField(value ="website_keyword")
    private String websiteKeyword;//站点关键词

	@TableField(value ="website_desc")
    private String websiteDesc;//站点描述

	@TableField(value ="copyright")
    private String copyright;//站点版权

	@TableField(value ="icp")
    private String icp;//备案号
    
	@TableField(value ="gonganbeian_id")
    private String gonganbeianId;//公安备案号(用于指向公安备案网站链接的ID)

	@TableField(value ="gonganbeian")
    private String gonganbeian;//公安备案号

	@TableField(value ="weixin_qr_url")
    private String weixinQrUrl;//站点微信

	@TableField(value ="weixin_xiaochengxu_qr_url")
    private String weixinXiaochengxuQrUrl;//小程序二维码

	@TableField(value ="weibo")
    private String weibo;//站点微博

	@TableField(value ="is_open_external_code")
    private Integer isOpenExternalCode;//是否开启统计

	@TableField(value ="external_code")
    private String externalCode;//统计代码

	@TableField(value ="is_open_qq_service")
    private Integer isOpenQqService;//是否显示客服信息

	@TableField(value ="service_qq1")
    private String serviceQq1;//客服信息1

	@TableField(value ="service_qq2")
    private String serviceQq2;//客服信息2	

	@TableField(value ="user_agreement")
    private String userAgreement;//用户协议
	

		
	@TableField(value ="org_no")
    private String orgNo;//机构编号
	@TableField(value ="cookie_domain")
    private String cookieDomain;//cookie作用域	
	@TableField(value ="cookie_token_name")
    private String cookieTokenName;//cookie的token名称
	@TableField(value ="pcweb_main_url")
    private String pcwebMainUrl;//PC网站地址(带http://或者https://开头)，可以带工程名
	@TableField(value ="mobile_main_url")
    private String mobileMainUrl;//移动网站地址(带http://或者https://开头)，可以带工程名	
	@TableField(value ="main_url")
    private String mainUrl;//网站主域名(不需要带http://等开头，直接如test.com即可)
	
	@TableField(value ="main_seal_content")
    private String mainSealContent;//学习证明主印章名称(大圆的那个印章名称)
	@TableField(value ="second_seal_content")
    private String secondSealContent;//学习证明印章内横着的那行字内容

}
