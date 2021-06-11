package com.wangxin.langshu.business.edu.service.market.pcweb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.CouponUserDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.CouponUserMapper;
import com.wangxin.langshu.business.edu.entity.market.Coupon;
import com.wangxin.langshu.business.edu.entity.market.CouponUser;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponUserService;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserSaveVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcWebCouponUserService  extends ServiceImpl<CouponUserMapper, CouponUser> implements IPcWebCouponUserService {

	@Autowired
	public CouponUserDao couponUserDao;
	
	@Autowired
    private CouponUserMapper couponUserMapper;
	
	@Autowired
	private IPcWebCouponService pcWebCouponService;

	@Override
	public Result<Page<CouponUserVo>> queryCouponUsers(CouponUserQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setBindUserNo(q.getUserNo());
		
		/*
		Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String dateStr = sdf.format(date);
		q.setCouponTimeBeginEnd(dateStr);
		q.setCouponTimeEndBegin(dateStr);*/
		
		q.setValidValue("1");
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		
		q.setIsCouponTimeEndBeforeToday("TRUE");
		//q.setIsUsed("0");//未使用的
		return Result.success(couponUserDao.queryCouponUserDetails(q));
	}

	@Override
	@Transactional
	public Result<Integer> saveCouponUser(CouponUserSaveVo vo) {
		if(StringUtils.isEmpty(vo.getCouponId())){
			return Result.error("couponId不能为空");
		}

		Coupon coupon = pcWebCouponService.getById(vo.getCouponId());
		if(ObjectUtil.isNull(coupon)){
			return Result.error("优惠券查询为空");
		}
		if(ValidValueEnum.NO.equals(coupon.getValidValue())){
			return Result.error("优惠券已经停用");
		}
		long couponAssigTimeBeginLong = coupon.getCouponAssignTimeBegin().getTime();
		long couponAssigTimeEndLong = coupon.getCouponAssignTimeEnd().getTime();
		long todayTime = new Date().getTime();
		
		if(!(couponAssigTimeBeginLong<=todayTime&&todayTime<=couponAssigTimeEndLong)){
			return Result.error("优惠券不在领用日期之内");
		}
		
		CouponUserQ couponUserQ = new CouponUserQ();
		couponUserQ.setCouponId(vo.getCouponId());
		couponUserQ.setBindUserNo(vo.getUserNo());
		Page<CouponUserVo> pageCouponUserVo = couponUserDao.queryCouponUsers(couponUserQ);
		if(CollectionUtil.isNotEmpty(pageCouponUserVo.getList())){
			return Result.error("优惠券已经被领取");
		}
		CouponUser couponUser = new CouponUser();
		couponUser.setCouponId(vo.getCouponId());
		couponUser.setBindUserNo(vo.getUserNo());
		couponUser.setValidValue(1);
		String id = UUID.randomUUID().toString();
		couponUser.setId(id);
		
		this.save(couponUser);
		
		return Result.success(1);
	}

	@Override
	public boolean updateCouponUser(CouponUserUpdateVo vo) {
		CouponUser CouponUser = new CouponUser();
		BeanUtils.copyProperties(vo, CouponUser);
		this.updateById(CouponUser);
		
		
		return true;
	}

	@Override
	public boolean deleteCouponUser(CouponUserDeleteVo vo) {
		

		return true;
	}

	@Override
	public CouponUserViewVo viewCouponUser(CouponUserViewVo viewVo) {
		CouponUser examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		

		return viewVo;
	}

	
}
