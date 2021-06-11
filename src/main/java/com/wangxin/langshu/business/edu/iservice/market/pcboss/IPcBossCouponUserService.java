package com.wangxin.langshu.business.edu.iservice.market.pcboss;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.CouponUser;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcBossCouponUserService  extends IService<CouponUser> {
	
	


	public Result<Page<CouponUserVo>> queryCouponUsers(CouponUserQ q) ;

	
	
	
	
}
