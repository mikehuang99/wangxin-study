package com.wangxin.langshu.business.mobile.controller.system;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigPcWebViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi/system")
public class MobileApiSystemController extends BaseController {
	public static final String TOKEN = "token";

	@Autowired
	private ISystemConfigService systemConfigService;

	/**
	 * 获取系统配置信息接口(暴露给非控制台使用，必须加限制，只能暴露必须的字段，不能暴露全部字段)
	 */
	@ApiOperation(value = "获取系统配置表信息接口", notes = "获取系统配置信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<SystemConfigPcWebViewVo> view() {
		SystemConfigViewVo systemConfigViewVo = systemConfigService.view();
		SystemConfigPcWebViewVo systemConfigPcWebViewVo = new SystemConfigPcWebViewVo();
		BeanUtils.copyProperties(systemConfigViewVo, systemConfigPcWebViewVo);
		return Result.success(systemConfigPcWebViewVo);
	}

	

}

