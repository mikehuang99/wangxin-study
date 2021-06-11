package com.wangxin.langshu.business.edu.controller.market.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pccommon.IDotConfigService;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigQ;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigViewVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 点券配置表
 *
 */
@RestController
@RequestMapping(value = "/pcweb/market/dotConfig")
public class PcWebDotConfigController extends BaseController {

	@Autowired
	private IDotConfigService dotConfigService;
	
	


	/**
	 * 查询点券配置表
	 * 
	 * @param DotConfigQ
	 * @return
	 */
	@ApiOperation(value = "查询点券配置表", notes = "查询点券配置表")
	@RequestMapping(value = "/viewDotConfig", method = RequestMethod.POST)
	public Result<DotConfigVo> viewDotConfig(@RequestBody DotConfigQ q) {	
		Page<DotConfigVo> page = dotConfigService.queryEntities(q);
		if(CollectionUtil.isEmpty(page.getList())){
			Result.error("系统点券配置表初始化失败，请系统管理员配置后才能使用该功能");
		}
		return Result.success(dotConfigService.queryEntities(q).getList().get(0));
	}


}
