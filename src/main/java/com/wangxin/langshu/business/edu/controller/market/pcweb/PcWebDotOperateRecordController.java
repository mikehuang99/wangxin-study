package com.wangxin.langshu.business.edu.controller.market.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebDotOperateRecordService;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 点券操作记录管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/market/dotOperateRecord")
public class PcWebDotOperateRecordController extends BaseController {

	@Autowired
	private IPcWebDotOperateRecordService pcWebDotOperateRecordService;
	
	

	/**
	 * 查询该用户的点券操作记录
	 * 
	 * @param DotOperateRecordQ
	 * @return
	 */
	@ApiOperation(value = "查询该用户的点券操作记录", notes = "查询该用户的点券操作记录")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<DotOperateRecordVo>> list(@RequestBody DotOperateRecordQ q) {	
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setUserNoOfDot(q.getUserNo());
		return Result.success(pcWebDotOperateRecordService.queryDotOperateRecords(q));
	}


	


}
