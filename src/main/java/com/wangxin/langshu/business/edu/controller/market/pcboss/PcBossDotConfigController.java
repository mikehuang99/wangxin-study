package com.wangxin.langshu.business.edu.controller.market.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pccommon.IDotConfigService;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 点券配置表
 */
@RestController
@RequestMapping(value = "/pcboss/market/dot/config")
public class PcBossDotConfigController extends BaseController  {

	@Autowired
	private IDotConfigService dotConfigService;

	/**
	 * 获取点券配置信息接口
	 */
	@ApiOperation(value = "获取点券配置表信息接口", notes = "获取点券配置信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<DotConfigViewVo> view() {
		return Result.success(dotConfigService.view());
	}

	/**
	 * 更新点券配置信息接口
	 */
	@ApiOperation(value = "更新点券配置表信息接口", notes = "更新点券配置信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody DotConfigUpdateVo dotConfigUpdateVo) {
		dotConfigService.updateEntity(dotConfigUpdateVo);
		 return Result.success(1);
	}

}
