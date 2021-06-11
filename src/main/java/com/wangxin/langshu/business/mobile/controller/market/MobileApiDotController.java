package com.wangxin.langshu.business.mobile.controller.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebDotService;
import com.wangxin.langshu.business.edu.vo.market.dot.DotQ;
import com.wangxin.langshu.business.edu.vo.market.dot.DotToPointVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotViewVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 点券管理
 *
 */
@RestController
@RequestMapping(value = "/mobileApi/market/dot")
public class MobileApiDotController extends BaseController {

	@Autowired
	private IPcWebDotService pcWebDotService;
	
	
	/**
	 * 点券转换为多少积分
	 * 
	 * @param DotToPointVo
	 * @return
	 */
	@ApiOperation(value = "点券转换为多少积分", notes = "点券转换为多少积分")
	@RequestMapping(value = "/checkDotToPoint", method = RequestMethod.POST)
	public Result<PointOperateRecordViewVo> checkDotToPoint(@RequestBody DotToPointVo dotToPointVo) {	
		if(StringUtils.isEmpty(dotToPointVo.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		return pcWebDotService.checkDotToPoint(dotToPointVo.getDot(), dotToPointVo.getUserNo());
	}
	

	/**
	 * 点券转换为积分
	 * 
	 * @param DotToPointVo
	 * @return
	 */
	@ApiOperation(value = "点券转换为积分", notes = "点券转换为积分")
	@RequestMapping(value = "/dotToPoint", method = RequestMethod.POST)
	public Result<Integer> dotToPoint(@RequestBody DotToPointVo dotToPointVo) {	
		if(StringUtils.isEmpty(dotToPointVo.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		return pcWebDotService.dotToPoint(dotToPointVo.getDot(), dotToPointVo.getUserNo());
	}

	/**
	 * 根据userNo查询点券
	 * 
	 * @param DotQ
	 * @return
	 */
	@ApiOperation(value = "根据userNo查询点券", notes = "根据userNo查询点券")
	@RequestMapping(value = "/viewDotByUserNo", method = RequestMethod.POST)
	public Result<DotViewVo> viewDotByUserNo(@RequestBody DotQ q) {	
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		return Result.success(pcWebDotService.viewDotByUserNo(q.getUserNo()));
	}


}
