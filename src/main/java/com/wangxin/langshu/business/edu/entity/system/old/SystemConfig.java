package com.wangxin.langshu.business.edu.entity.system.old;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value ="system_config")
public class SystemConfig extends Model<SystemConfig> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7783196042035430491L;
	@TableId(type = IdType.UUID)
	private String id;
	@TableField(value ="create_time")
    private Date createTime;
	@TableField(value ="modified_time")
    private Date modifiedTime;
	@TableField(value ="valid_value")
    private Integer validValue;
	@TableField(value ="sort")
    private Integer sort;
	@TableField(value ="aliyun_access_key_id")
    private String aliyunAccessKeyId;
	@TableField(value ="aliyun_access_key_secret")
    private String aliyunAccessKeySecret;
	@TableField(value ="aliyun_oss_bucket_url")
	private String aliyunOssBucketUrl;//bucket域名
	@TableField(value ="aliyun_oss_bucket")
	private String aliyunOssBucket;//bucket
	@TableField(value ="aliyun_oss_endpoint")
	private String aliyunOssEndpoint;//地域节点
	@TableField(value ="aliyun_oas_vault")
	private String aliyunOasVault;//oas_vault
	@TableField(value ="aliyun_oas_url")
	private String aliyunOasUrl;//归档存储OAS
	@TableField(value ="sms_code")
	private String smsCode;//模版CODE		
	@TableField(value ="sign_name")
	private String signName;//短信签名	
	@TableField(value ="lesson_video_path")
	private String lessonVideoPath;//本地视频存放路径	
	@TableField(value ="doc_storage_path")
	private String docStoragePath;//本地上传文档路径
	@TableField(value ="pic_storage_path")
	private String picStoragePath;//本地上传图片路径	
	@TableField(value ="video_type")
	private Integer videoType;//视频存储平台（1阿里云，3本地[本地未用]）
	@TableField(value ="file_type")
	private Integer fileType;//文件存储类型（1阿里云，3本地[本地未用]）
	
	
	@TableField(value ="is_open_zfb_pc")
	private int isOpenZfbPc;//是否开启PC端支付宝支付，0关闭，1开启
	@TableField(value ="zfb_pc_app_id")
	private String zfbPcAppId;//网站(签约:当面付)支付APPID
	@TableField(value ="zfb_pc_subject")
	private String zfbPcSubject;//消费者扫码支付的时候显示的订单信息
	@TableField(value ="zfb_pc_private_key")
	private String zfbPcPrivateKey;//网站(签约:当面付)支付私钥
	@TableField(value ="zfb_pc_alipay_public_key")
	private String zfbPcAlipayPublicKey;//网站(签约:当面付)支付"支付宝公钥"(非应用公钥)
	@TableField(value ="zfb_pc_open_api_domain")
	private String zfbPcOpenApiDomain;//网站(签约:当面付)支付网关
	
	@TableField(value ="is_open_zfb_mobile")
	private int isOpenZfbMobile;//是否开启移动端支付宝支付，0关闭，1开启

	
	
	@TableField(value ="is_open_weixinpay_pc")
	private int isOpenWeixinpayPc;//是否开启PC端微信支付，0关闭，1开启
	@TableField(value ="is_open_weixinpay_mobile")
	private int isOpenWeixinpayMobile;//是否开启移动端微信支付，0关闭，1开启
	@TableField(value ="is_open_weixinpay_xiaochengxu")
	private int isOpenWeixinpayXiaochengxu;//是否开启小程序端微信支付，0关闭，1开启
	
	@TableField(value ="login_log_retention_days")
	private int loginLogRetentionDays;//登录日志保留天数
	@TableField(value ="operate_log_retention_days")
	private int operateLogRetentionDays;//操作日志保留天数

}