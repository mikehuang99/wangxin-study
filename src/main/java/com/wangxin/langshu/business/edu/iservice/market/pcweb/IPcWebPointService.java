package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import org.bouncycastle.pqc.jcajce.provider.rainbow.SignatureSpi.withSha224;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.Point;
import com.wangxin.langshu.business.edu.vo.market.point.PointDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointQ;
import com.wangxin.langshu.business.edu.vo.market.point.PointSaveVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointViewVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebPointService  extends IService<Point> {
	
	

	public Page<PointVo> queryPoints(PointQ q) ;

	public Result<Integer> savePoint(PointSaveVo vo) ;

	public Result<Integer> updatePoint(PointUpdateVo vo) ;

	
	public Result<Integer> deletePoint(PointDeleteVo vo) ;

	
	public PointViewVo viewPoint(PointViewVo vo) ;
	
	
	public PointViewVo viewPointByUserNo(String userNo) ;
	
	
	/**
	 * 增加积分
	 * @param point 增加的积分
	 * @param userNo 积分对应的用户
	 * @return
	 */
	public Result<Integer> addPoint(int point,String userNo);
	
	/**
	 * 减少积分
	 * @param point 减少的积分
	 * @param userNo 积分对应的用户
	 * @return
	 */
	public Result<Integer> reducePoint(int point,String userNo);
	
	
}
