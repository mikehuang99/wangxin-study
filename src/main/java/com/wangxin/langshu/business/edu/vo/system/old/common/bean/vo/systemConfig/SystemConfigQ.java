package com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class SystemConfigQ  implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -671939567495524490L;
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
	@ApiModelProperty(value = "aliyunAccessKeyId", required = false)
    private String aliyunAccessKeyId;
	@ApiModelProperty(value = "aliyunAccessKeySecret", required = false)
    private String aliyunAccessKeySecret;
	@ApiModelProperty(value = "bucket域名", required = false)
	private String aliyunOssBucketUrl;//bucket域名
	@ApiModelProperty(value = "bucket", required = false)
	private String aliyunOssBucket;//bucket
	@ApiModelProperty(value = "地域节点", required = false)
	private String aliyunOssEndpoint;//地域节点
	@ApiModelProperty(value = "oas_vault", required = false)
	private String aliyunOasVault;//oas_vault
	@ApiModelProperty(value = "归档存储OAS", required = false)
	private String aliyunOasUrl;//归档存储OAS
	@ApiModelProperty(value = "模版CODE", required = false)
	private String smsCode;//模版CODE		
	@ApiModelProperty(value = "短信签名", required = false)
	private String signName;//短信签名	
	@ApiModelProperty(value = "本地视频存放路径", required = false)
	private String lessonVideoPath;//本地视频存放路径	
	@ApiModelProperty(value = "本地上传文档路径", required = false)
	private String docStoragePath;//本地上传文档路径
	@ApiModelProperty(value = "本地上传图片路径", required = false)
	private String picStoragePath;//本地上传图片路径
	@ApiModelProperty(value = "视频存储平台（1阿里云，3本地[本地未用]）", required = false)
	private Integer videoType;//视频存储平台（1阿里云，3本地[本地未用]）
	@ApiModelProperty(value = "文件存储类型（1阿里云，3本地[本地未用]）", required = false)
	private Integer fileType;//文件存储类型（1阿里云，3本地[本地未用]）
	
	@ApiModelProperty(value = "是否开启PC端支付宝支付，0关闭，1开启", required = false)
	private int isOpenZfbPc;//是否开启PC端支付宝支付，0关闭，1开启
	@ApiModelProperty(value = "网站(签约:当面付)支付APPID", required = false)
	private String zfbPcAppId;//网站(签约:当面付)支付APPID
	@ApiModelProperty(value = "消费者扫码支付的时候显示的订单信息", required = false)
	private String zfbPcSubject;//消费者扫码支付的时候显示的订单信息
	@ApiModelProperty(value = "网站(签约:当面付)支付私钥", required = false)
	private String zfbPcPrivateKey;//网站(签约:当面付)支付私钥
	@ApiModelProperty(value = "网站(签约:当面付)支付'支付宝公钥'(非应用公钥)", required = false)
	private String zfbPcAlipayPublicKey;//网站(签约:当面付)支付"支付宝公钥"(非应用公钥)
	@ApiModelProperty(value = "网站(签约:当面付)支付网关", required = false)
	private String zfbPcOpenApiDomain;//网站(签约:当面付)支付网关
	
	@ApiModelProperty(value = "是否开启移动端支付宝支付，0关闭，1开启", required = false)
	private int isOpenZfbMobile;//是否开启移动端支付宝支付，0关闭，1开启
	@ApiModelProperty(value = "支付宝页面跳转(目前仅针对移动端)同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问", required = false)
	private String zfbReturnUrl; //支付宝页面跳转(目前仅针对移动端)同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	
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
