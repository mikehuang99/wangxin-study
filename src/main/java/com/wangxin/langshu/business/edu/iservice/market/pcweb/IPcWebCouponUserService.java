package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.CouponUser;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserSaveVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebCouponUserService  extends IService<CouponUser> {
	
	

	/**
	 * 查询用户在有效期内并且有效的优惠券
	 * @param q
	 * @return
	 */
	public Result<Page<CouponUserVo>> queryCouponUsers(CouponUserQ q) ;

	public Result<Integer> saveCouponUser(CouponUserSaveVo vo) ;

	public boolean updateCouponUser(CouponUserUpdateVo vo) ;

	
	public boolean deleteCouponUser(CouponUserDeleteVo vo) ;

	
	public CouponUserViewVo viewCouponUser(CouponUserViewVo vo) ;
	
	
	
	
}
