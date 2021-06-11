package com.wangxin.langshu.business.edu.service.market.pcweb;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.CouponDao;
import com.wangxin.langshu.business.edu.dao.market.CouponUserDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.CouponMapper;
import com.wangxin.langshu.business.edu.entity.market.Coupon;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponService;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;

import io.swagger.annotations.ApiModelProperty;


@Service
public class PcWebCouponService  extends ServiceImpl<CouponMapper, Coupon> implements IPcWebCouponService {

	@Autowired
	public CouponDao couponDao;
	
	@Autowired
	public CouponUserDao couponUserDao;
	
	@Autowired
    private CouponMapper couponMapper;

	@Override
	public Result<Page<CouponVo>> queryCoupons(CouponQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		String userNo = q.getUserNo();//获取来路的userNo
		q = new CouponQ();//重置查询条件
		Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        String dateStr = sdf.format(date);
		q.setCouponAssignTimeBeginEnd(dateStr);
		q.setCouponAssignTimeEndBegin(dateStr);
		
		q.setValidValue("1");
		
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		
		Page<CouponVo> page =  couponDao.queryPcWebCoupons(q);
		
		
		CouponUserQ couponUserQ = new CouponUserQ();
		couponUserQ.setBindUserNo(userNo);
		couponUserQ.setPageCurrent(-1);
		couponUserQ.setPageSize(-1);
		Page<CouponUserVo> pageCouponUser = couponUserDao.queryCouponUsers(couponUserQ);
		if(CollectionUtil.isNotEmpty(pageCouponUser.getList())){
			List<CouponUserVo> couponUserVos = pageCouponUser.getList();
			List<String> couponUserIds = new ArrayList<>();
			for(CouponUserVo couponUserVo: couponUserVos){
				couponUserIds.add(couponUserVo.getCouponId());
			}
			
			List<CouponVo> newCouponVos = new ArrayList<CouponVo>();
			List<CouponVo> couponVos = page.getList();
			for(CouponVo couponVo : couponVos){
				if(!couponUserIds.contains(couponVo.getId())){
					newCouponVos.add(couponVo);
				}
			}
			page.setList(newCouponVos);
		}
		
		
		return Result.success(page);
	}

	

	
}
