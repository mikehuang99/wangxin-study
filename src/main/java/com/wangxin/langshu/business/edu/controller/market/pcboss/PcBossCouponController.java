package com.wangxin.langshu.business.edu.controller.market.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponService;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponSaveVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponViewVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 优惠券管理
 *
 */
@RestController
@RequestMapping(value = "/pcboss/market/coupon")
public class PcBossCouponController extends BaseController {

	@Autowired
	private ICouponService couponService;
	
	

	/**
	 * 优惠券列表分页接口
	 * 
	 * @param CouponQ
	 * @return
	 */
	@ApiOperation(value = "优惠券列表分页接口", notes = "优惠券列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CouponVo>> list(@RequestBody CouponQ q) {	
		return Result.success(couponService.queryCoupons(q));
	}

	/**
	 * 优惠券添加接口
	 * 
	 * @param CouponSaveVo
	 * @return
	 */
	@ApiOperation(value = "优惠券添加接口", notes = "优惠券添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CouponSaveVo svo) {
		boolean flag = couponService.saveCoupon(svo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券更新接口
	 * 
	 * @param CouponUpdateVo
	 * @return
	 */
	@ApiOperation(value = "优惠券更新接口", notes = "优惠券更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  CouponUpdateVo uvo) {
		boolean flag = couponService.updateCoupon(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券删除接口
	 * 
	 * @param CouponDeleteVo
	 * @return
	 */
	@ApiOperation(value = "优惠券删除接口", notes = "优惠券删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  CouponDeleteVo dvo) {
		boolean flag = couponService.deleteCoupon(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券查看接口
	 * 
	 * @param CouponViewVo
	 * @return
	 */
	@ApiOperation(value = "优惠券查看接口", notes = "优惠券查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CouponViewVo> view(@RequestBody  CouponViewVo vvo) {
		CouponViewVo vo = couponService.viewCoupon(vvo);
		return Result.success(vo);
	}
	
	
	
	
	

}
