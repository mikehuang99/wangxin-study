package com.wangxin.langshu.business.edu.iservice.order.pccommon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.order.OrderMain;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDateCountVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainDeleteVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainSaveVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainUpdateVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainViewVo;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IOrderMainService  extends IService<OrderMain> {
	
	

	public Page<OrderMainVo> queryOrders(OrderMainQ q) ;

	public Result<OrderMainSaveVo> saveOrder(OrderMainSaveVo vo) ;

	public boolean updateOrder(OrderMainUpdateVo vo) ;

	
	public boolean deleteOrder(OrderMainDeleteVo vo) ;

	
	public OrderMainViewVo viewOrder(OrderMainViewVo vo) ;
	
	public Result<OrderMainViewVo> checkPayResult(OrderMainViewVo vo) ;

	public Result<Integer> cancelOrder(OrderMainViewVo vo,int payStatus);
	
	public Result<OrderMainViewVo> continuePay(OrderMainViewVo vo);
	
	public Result<OrderMainDateCountVo> queryOrderCountBefore30Days();
	
}
