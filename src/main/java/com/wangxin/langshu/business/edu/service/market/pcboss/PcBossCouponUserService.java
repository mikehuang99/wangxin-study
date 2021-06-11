package com.wangxin.langshu.business.edu.service.market.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.CouponUserDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.CouponUserMapper;
import com.wangxin.langshu.business.edu.entity.market.CouponUser;
import com.wangxin.langshu.business.edu.iservice.market.pcboss.IPcBossCouponUserService;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcBossCouponUserService  extends ServiceImpl<CouponUserMapper, CouponUser> implements IPcBossCouponUserService {

	@Autowired
	public CouponUserDao couponUserDao;
	

	@Override
	public Result<Page<CouponUserVo>> queryCouponUsers(CouponUserQ q) {
		return Result.success(couponUserDao.queryCouponUsers(q));
	}



	
}
