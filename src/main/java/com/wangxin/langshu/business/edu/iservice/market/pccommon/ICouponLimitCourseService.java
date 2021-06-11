package com.wangxin.langshu.business.edu.iservice.market.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.CouponLimitCourse;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseVo;
import com.wangxin.langshu.business.util.base.Page;

public interface ICouponLimitCourseService  extends IService<CouponLimitCourse> {
	
	

	public Page<CouponLimitCourseVo> queryCouponLimitCourses(CouponLimitCourseQ q) ;

	public boolean saveCouponLimitCourse(CouponLimitCourseSaveVo vo) ;

	public boolean updateCouponLimitCourse(CouponLimitCourseUpdateVo vo) ;

	
	public boolean deleteCouponLimitCourse(CouponLimitCourseDeleteVo vo) ;

	
	public CouponLimitCourseViewVo viewCouponLimitCourse(CouponLimitCourseViewVo vo) ;
	
	public String queryAllCouponLimitCoursesString(String couponId);
	
	public int deleteCouponLimitCoursesByCouponId(String couponId);
	
	
	
	
}
