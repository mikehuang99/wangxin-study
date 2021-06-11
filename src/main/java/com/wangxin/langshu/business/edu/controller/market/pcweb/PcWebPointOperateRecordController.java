package com.wangxin.langshu.business.edu.controller.market.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointOperateRecordService;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 积分操作记录管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/market/pointOperateRecord")
public class PcWebPointOperateRecordController extends BaseController {

	@Autowired
	private IPcWebPointOperateRecordService pcWebPointOperateRecordService;
	
	

	/**
	 * 查询该用户的积分操作记录
	 * 
	 * @param PointOperateRecordQ
	 * @return
	 */
	@ApiOperation(value = "查询该用户的积分操作记录", notes = "查询该用户的积分操作记录")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<PointOperateRecordVo>> list(@RequestBody PointOperateRecordQ q) {	
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setUserNoOfPoint(q.getUserNo());
		return Result.success(pcWebPointOperateRecordService.queryPointOperateRecords(q));
	}


	


}
