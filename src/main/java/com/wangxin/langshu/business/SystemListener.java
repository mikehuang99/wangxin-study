package com.wangxin.langshu.business;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.alipay.trade.config.Configs;
 
/**
 * 系统启动监听
 * @author htz
 *
 */
@Component
public class SystemListener implements CommandLineRunner {
 
	@Autowired
	private ISystemConfigService systemConfigService;
	
	@Override
	public void run(String... args) throws Exception {
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
		//1、初始化支付宝配置
		initAlipayConfig(systemConfigViewVo);
	}

	private void initAlipayConfig(SystemConfigViewVo systemConfigViewVo) {
		//Configs configs = new Configs();
		Configs.init(systemConfigViewVo.getZfbPcOpenApiDomain(),systemConfigViewVo.getZfbPcAppId(), systemConfigViewVo.getZfbPcPrivateKey(), systemConfigViewVo.getZfbPcAlipayPublicKey(),systemConfigViewVo.getZfbReturnUrl());
	}
}