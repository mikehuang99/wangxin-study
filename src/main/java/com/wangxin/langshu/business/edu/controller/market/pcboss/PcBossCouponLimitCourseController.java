package com.wangxin.langshu.business.edu.controller.market.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponLimitCourseService;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 优惠券课程限制管理
 *
 */
@RestController
@RequestMapping(value = "/pcboss/market/couponlimitcourse")
public class PcBossCouponLimitCourseController extends BaseController {

	@Autowired
	private ICouponLimitCourseService couponLimitCourseService;
	
	

	/**
	 * 优惠券课程限制列表分页接口
	 * 
	 * @param CouponLimitCourseQ
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制列表分页接口", notes = "优惠券课程限制列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CouponLimitCourseVo>> list(@RequestBody CouponLimitCourseQ q) {	
		return Result.success(couponLimitCourseService.queryCouponLimitCourses(q));
	}

	/**
	 * 优惠券课程限制添加接口
	 * 
	 * @param CouponLimitCourseSaveVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制添加接口", notes = "优惠券课程限制添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CouponLimitCourseSaveVo svo) {
		boolean flag = couponLimitCourseService.saveCouponLimitCourse(svo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券课程限制更新接口
	 * 
	 * @param CouponLimitCourseUpdateVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制更新接口", notes = "优惠券课程限制更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  CouponLimitCourseUpdateVo uvo) {
		boolean flag = couponLimitCourseService.updateCouponLimitCourse(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券课程限制删除接口
	 * 
	 * @param CouponLimitCourseDeleteVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制删除接口", notes = "优惠券课程限制删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  CouponLimitCourseDeleteVo dvo) {
		boolean flag = couponLimitCourseService.deleteCouponLimitCourse(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券课程限制查看接口
	 * 
	 * @param CouponLimitCourseViewVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制查看接口", notes = "优惠券课程限制查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CouponLimitCourseViewVo> view(@RequestBody  CouponLimitCourseViewVo vvo) {
		CouponLimitCourseViewVo vo = couponLimitCourseService.viewCouponLimitCourse(vvo);
		return Result.success(vo);
	}
	
	
	
	
	

}
