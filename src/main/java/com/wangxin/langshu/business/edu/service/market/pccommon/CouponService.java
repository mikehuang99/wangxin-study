package com.wangxin.langshu.business.edu.service.market.pccommon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.CouponDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.CouponMapper;
import com.wangxin.langshu.business.edu.entity.market.Coupon;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponLimitCourseService;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponService;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponSaveVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponViewVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseSaveVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class CouponService  extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

	@Autowired
	public CouponDao couponDao;
	
	@Autowired
    private CouponMapper couponMapper;
	

	
	@Autowired
	private ICouponLimitCourseService couponLimitCourseService;


	@Override
	public Page<CouponVo> queryCoupons(CouponQ q) {
		return couponDao.queryCoupons(q);
	}

	@Override
	@Transactional
	public boolean saveCoupon(CouponSaveVo vo) {
		
			String id = UUID.randomUUID().toString().replace("-", "");
			Coupon coupon = new Coupon();
			BeanUtils.copyProperties(vo, coupon);
			
			coupon.setId(id);
			
			String couponCode = UUID.randomUUID().toString().replace("-", "");
			coupon.setCouponCode(couponCode);
			
			this.save(coupon);
			
			String selectedCourseIdsString = vo.getSelectedCourseIdsString();
			if(StringUtils.isNotEmpty(selectedCourseIdsString)){
				String selectedCourseIds[] = selectedCourseIdsString.split(",");
				Set<String> set = new HashSet<>();
				for(int n=0;n<selectedCourseIds.length;n++){
					set.add(selectedCourseIds[n]);
				}
				List<String> list = new ArrayList<>();
				list.addAll(set);
				for(int n=0;n<list.size();n++){
					if(StringUtils.isNotEmpty(list.get(n))&&!"undefined".equals(list.get(n).trim())){
						CouponLimitCourseSaveVo couponLimitCourseSaveVo = new CouponLimitCourseSaveVo();
						couponLimitCourseSaveVo.setValidValue(1);
						couponLimitCourseSaveVo.setCouponId(id);
						couponLimitCourseSaveVo.setCourseId(list.get(n).trim());
						couponLimitCourseService.saveCouponLimitCourse(couponLimitCourseSaveVo);
					}
				}
			}
			
		
		return true;
	}

	@Override
	public boolean updateCoupon(CouponUpdateVo vo) {
		Coupon Coupon = new Coupon();
		BeanUtils.copyProperties(vo, Coupon);
		this.updateById(Coupon);
		
		
		return true;
	}

	@Override
	public boolean deleteCoupon(CouponDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
			couponLimitCourseService.deleteCouponLimitCoursesByCouponId(id);
		}

		return true;
	}

	@Override
	public CouponViewVo viewCoupon(CouponViewVo viewVo) {
		Coupon examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		
		viewVo.setSelectedCourseIdsString(couponLimitCourseService.queryAllCouponLimitCoursesString(viewVo.getId()));

		return viewVo;
	}

	/* 
	 * 返回长度为【strLength】的随机数字，在前面补0 ，最长13位
	 */  
	private static String getFixLenthRandom(int strLength) {  
		if(strLength>0&&strLength<=13){
		    Random rm = new Random();  
		    // 获得随机数  
		    double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);  
		    System.out.println("pross:"+pross);
		    // 将获得的获得随机数转化为字符串  
		    String fixLenthString = String.valueOf(pross); 
		    fixLenthString = fixLenthString.replace(".", "");
		    System.out.println("fixLenthString:"+fixLenthString);
		    // 返回固定的长度的随机数  
		    return fixLenthString.substring(2, strLength + 2);  
		}else{
			throw new BaseException("随机数长度不能超过13");
		}
	}  
	
	
	


	
	
}
