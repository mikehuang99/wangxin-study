package com.wangxin.langshu.business.edu.controller.market.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointService;
import com.wangxin.langshu.business.edu.vo.market.dot.DotQ;
import com.wangxin.langshu.business.edu.vo.market.point.PointViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 积分管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/market/point")
public class PcWebPointController extends BaseController {

	@Autowired
	private IPcWebPointService pcWebPointService;
	

	/**
	 * 根据userNo查询积分
	 * 
	 * @param DotQ
	 * @return
	 */
	@ApiOperation(value = "根据userNo查询积分", notes = "根据userNo查询积分")
	@RequestMapping(value = "/viewPointByUserNo", method = RequestMethod.POST)
	public Result<PointViewVo> viewDotByUserNo(@RequestBody DotQ q) {	
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		return Result.success(pcWebPointService.viewPointByUserNo(q.getUserNo()));
	}


}
