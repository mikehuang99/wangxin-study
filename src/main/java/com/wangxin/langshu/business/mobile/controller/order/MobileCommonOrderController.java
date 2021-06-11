package com.wangxin.langshu.business.mobile.controller.order;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.order.pccommon.IOrderMainService;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/mobilecommon/order")
public class MobileCommonOrderController extends BaseController {
	public static final String TOKEN = "token";

	@Autowired
	private IOrderMainService orderMainService;

	/**
	 * 移动支付宝订单回调接口
	 * 
	 * @param  out_trade_no (实际就是订单号orderNo)
	 * @return
	 */
	@ApiOperation(value = "移动支付宝订单回调接口", notes = "移动支付宝订单回调接口")
	@RequestMapping(value = "/alipayNotice", method = RequestMethod.GET)
	public Result<Integer> alipayNotice(@RequestParam String out_trade_no) {
		//svo.setPlatform("mobile");
		//return orderMainService.saveOrder(svo);
		log.info("支付宝回调查询，订单号:"+out_trade_no);
		OrderMainViewVo orderMainViewVo = new OrderMainViewVo();
		orderMainViewVo.setOrderNo(out_trade_no);
		//订单检查支付结果接口
		 orderMainService.checkPayResult(orderMainViewVo);
		 return Result.success(1);
	}

	
	
	private String clearString(String str){
		if(str==null){
			return "";
		}else{
			return str.trim();
		}
	}
	
	private String formatDate(Date date){
		if(date==null){
			return "";
		}
		String strDateFormat = "yyyy-MM-dd HH:mm";
	    SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return sdf.format(date);
	}
	
	

}

