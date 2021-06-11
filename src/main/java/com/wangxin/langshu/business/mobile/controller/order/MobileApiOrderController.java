package com.wangxin.langshu.business.mobile.controller.order;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.order.pccommon.IOrderMainService;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDeleteVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainSaveVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainUpdateVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainViewVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi/order")
public class MobileApiOrderController extends BaseController {
	public static final String TOKEN = "token";

	@Autowired
	private IOrderMainService orderMainService;
	
	

	/**
	 * 订单列表分页接口
	 * 
	 * @param OrderMainQ
	 * @return
	 */
	@ApiOperation(value = "订单列表分页接口", notes = "订单列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<OrderMainVo>> list(@RequestBody OrderMainQ q) {	
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setOrderUserNo(q.getUserNo());
		return Result.success(orderMainService.queryOrders(q));
	}

	/**
	 * 订单添加接口
	 * 
	 * @param OrderMainSaveVo
	 * @return
	 */
	@ApiOperation(value = "订单添加接口", notes = "订单添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<OrderMainSaveVo> save(@RequestBody OrderMainSaveVo svo) {
		svo.setPlatform("mobile");
		return orderMainService.saveOrder(svo);
		
	}

	/**
	 * 订单检查支付结果接口
	 * 
	 * @param OrderMainViewVo
	 * @return
	*/
	@ApiOperation(value = "订单检查支付结果接口", notes = "订单检查支付结果接口")
	@RequestMapping(value = "/checkPayResult", method = RequestMethod.POST)
	public Result<OrderMainViewVo> checkPayResult(@RequestBody OrderMainViewVo svo) {
		return orderMainService.checkPayResult(svo);
		
	} 
	
	/**
	 * 取消订单
	 * 
	 * @param OrderMainViewVo
	 * @return
	*/
	@ApiOperation(value = "取消订单接口", notes = "取消订单接口")
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public Result<Integer> cancelOrder(@RequestBody OrderMainViewVo svo) {
		return orderMainService.cancelOrder(svo,3);
	} 
	
	/**
	 * 取消订单
	 * 
	 * @param OrderMainViewVo
	 * @return
	*/
	@ApiOperation(value = "继续支付订单接口", notes = "继续支付订单接口")
	@RequestMapping(value = "/continuePay", method = RequestMethod.POST)
	public Result<OrderMainViewVo> continuePay(@RequestBody OrderMainViewVo svo) {
		return orderMainService.continuePay(svo);
	} 
	
	
	/**
	 * 订单更新接口
	 * 
	 * @param OrderMainUpdateVo
	 * @return
	 */
	@ApiOperation(value = "订单更新接口", notes = "订单更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  OrderMainUpdateVo uvo) {
		boolean flag = orderMainService.updateOrder(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 订单删除接口
	 * 
	 * @param OrderMainDeleteVo
	 * @return
	 */
	@ApiOperation(value = "订单删除接口", notes = "订单删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  OrderMainDeleteVo dvo) {
		boolean flag = orderMainService.deleteOrder(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 订单查看接口
	 * 
	 * @param OrderMainViewVo
	 * @return
	 */
	@ApiOperation(value = "订单查看接口", notes = "订单查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<OrderMainViewVo> view(@RequestBody  OrderMainViewVo vvo) {
		OrderMainViewVo vo = orderMainService.viewOrder(vvo);
		return Result.success(vo);
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

