package com.wangxin.langshu.business.util.alipay.wxtrade;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.wangxin.langshu.business.util.alipay.trade.config.Configs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WxMobileAlipayTool {
	
    
    static {
    	//Configs.init();
    }
   
    public static String returnAlipayHtmlForm(String outTradeNo,String subject,String courseId,String courseName,String totalAmount) {
    	/*
    	// 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = "2021311132423152000";
    	// 订单名称，必填
        String subject = "手机网站支付测试商品";
    	System.out.println(subject);
        // 付款金额，必填
        String total_amount= "0.01";
        // 商品描述，可空
        String body = "购买测试商品0.01元";*/
    	
    	// 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = outTradeNo;
    	// 订单名称，必填
        //String subject = "手机网站支付测试商品";
    	//System.out.println(subject);
        // 付款金额，必填
        String total_amount= totalAmount;
        // 商品描述，可空
        String body = "购买了课程["+courseName+"]，支付["+totalAmount+"]";;
        
        // 超时时间 可空
       String timeout_express="2m";
        // 销售产品码 必填
        String product_code="QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
        //调用RSA签名方式
        //AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);

        AlipayClient client = new DefaultAlipayClient(Configs.getOpenApiDomain(), Configs.getAppid(), Configs.getPrivateKey(), Configs.FORMAT, Configs.CHARSET, Configs.getAlipayPublicKey(),Configs.getSignType());
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
        
        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(Configs.getNotifyUrl());
        // 设置同步地址
        alipay_request.setReturnUrl(Configs.getReturnUrl());   
        
        // form表单生产
        String form = "";
        
        try {
    		// 调用SDK生成表单
    		form = client.pageExecute(alipay_request).getBody();
    		
    	} catch (AlipayApiException e) {
    		e.printStackTrace();
    	} catch (Exception e) {
			e.printStackTrace();
		}
        
    	return form;
    }

    /*
    public static void main(String args[]) {
    	System.out.println(WxMobileAlipayTool.returnAlipayHtmlForm());
    }*/
	
}
