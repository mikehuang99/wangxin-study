package com.wangxin.langshu.business.util.aliyun;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 *  
 */
@Data
@Accessors(chain = true)
public class Aliyun implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private String aliyunAccessKeyId;
	private String aliyunAccessKeySecret;
	private String aliyunOssBucketUrl;
	
	private String aliyunOssEndpoint;

	private String aliyunOssBucket;
	private String aliyunOasVault;
	private String aliyunOasUrl;//归档存储OAS

	
	private String smsCode;
	private String signName;
}
