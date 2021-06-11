package com.wangxin.langshu.business.mobile.controller.market;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponUserService;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserSaveVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 优惠券用户关系管理
 *
 */
@RestController
@RequestMapping(value = "/mobileApi/market/couponUser")
public class MobileApiCouponUserController extends BaseController {

	@Autowired
	private IPcWebCouponUserService pcWebCouponUserService;
	
	@Autowired
	private ICouponService couponService;
	

	/**
	 *优惠券用户关系列表(不分页)
	 * 
	 * @param CouponUserQ
	 * @return
	 */
	@ApiOperation(value = "优惠券用户关系列表(不分页)", notes = "优惠券用户关系列表(不分页)")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CouponUserVo>> list(@RequestBody CouponUserQ q) {
		
		//return pcWebCouponUserService.queryCouponUsers(q);
		Result<Page<CouponUserVo>> page =  pcWebCouponUserService.queryCouponUsers(q);
		if(StringUtils.isNotEmpty(q.getCourseId())){//有此值说明是下订单的时候调用的接口
			String courseId = q.getCourseId();
			List<CouponUserVo> list = page.getData().getList();
			List<CouponUserVo> newList = new ArrayList<CouponUserVo>();
			if(CollectionUtil.isNotEmpty(list)){
				for(CouponUserVo couponUserVo: list){
					if(1==couponUserVo.getIsLimitCourse()){//如果限制课程
						CouponViewVo couponViewVo = new CouponViewVo();
						couponViewVo.setId(couponUserVo.getCouponId());
						couponViewVo = couponService.viewCoupon(couponViewVo);
						String selectedCourseIdsString = couponViewVo.getSelectedCourseIdsString();
						if(StringUtils.isNotEmpty(selectedCourseIdsString)){
							String selectedCourseIds[] = selectedCourseIdsString.split(",");
							int count = 0;
							for(int i=0;i<selectedCourseIds.length;i++){
								if(courseId.equals(selectedCourseIds[i])){
									count ++;
								}
							}
							if(count!=0){
								if(couponUserVo.getIsUsed()==0){//如果优惠券还没被使用
									newList.add(couponUserVo);
								}
							}
						}
					}else{
						if(couponUserVo.getIsUsed()==0){//如果优惠券还没被使用
							newList.add(couponUserVo);
						}
					}
				}
			}
			
			Date date = new Date();
			List<CouponUserVo> newList2 = new ArrayList<CouponUserVo>();
			for(CouponUserVo couponUserVo: newList){
				if(couponUserVo.getCouponTimeBegin().getTime()<=date.getTime()&&date.getTime()<=couponUserVo.getCouponTimeEnd().getTime()){
					newList2.add(couponUserVo);
				}
			}
			

			
			page.getData().setList(newList2);
		}
		return page;
	}

	/**
	 * 优惠券用户关系添加接口
	 * 
	 * @param CouponUserSaveVo
	 * @return
	 */
	@ApiOperation(value = "优惠券用户关系添加接口", notes = "优惠券用户关系添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody CouponUserSaveVo svo) {
		return pcWebCouponUserService.saveCouponUser(svo);
	}

	

	/**
	 * 优惠券用户关系删除接口
	 * 
	 * @param CouponUserDeleteVo
	 * @return
	 */
	@ApiOperation(value = "优惠券用户关系删除接口", notes = "优惠券用户关系删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  CouponUserDeleteVo dvo) {
		boolean flag = pcWebCouponUserService.deleteCouponUser(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券用户关系查看接口
	 * 
	 * @param CouponUserViewVo
	 * @return
	 */
	@ApiOperation(value = "优惠券用户关系查看接口", notes = "优惠券用户关系查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CouponUserViewVo> view(@RequestBody  CouponUserViewVo vvo) {
		CouponUserViewVo vo = pcWebCouponUserService.viewCouponUser(vvo);
		return Result.success(vo);
	}
	
	
	
	
	

}
