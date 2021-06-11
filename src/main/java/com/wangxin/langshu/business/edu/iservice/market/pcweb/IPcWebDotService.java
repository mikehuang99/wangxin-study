package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.Dot;
import com.wangxin.langshu.business.edu.vo.market.dot.DotDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotQ;
import com.wangxin.langshu.business.edu.vo.market.dot.DotSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotViewVo;
import com.wangxin.langshu.business.edu.vo.market.dot.DotVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordViewVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.DotGainRuleEnum;

public interface IPcWebDotService  extends IService<Dot> {
	
	

	public Page<DotVo> queryDots(DotQ q) ;

	public Result<Integer> saveDot(DotSaveVo vo) ;

	public Result<Integer> updateDot(DotUpdateVo vo) ;

	
	public Result<Integer> deleteDot(DotDeleteVo vo) ;

	
	public DotViewVo viewDot(DotViewVo vo) ;
	
	
	public DotViewVo viewDotByUserNo(String userNo) ;
	
	/**
	 * 查询点券可以兑换多少积分
	 * @param dot
	 * @return
	 */
	public Result<PointOperateRecordViewVo> checkDotToPoint(int dot,String userNo);
	

	/**
	 * 点券转换为积分
	 * @param dot
	 * @return
	 */
	public Result<Integer> dotToPoint(int dot,String userNo);

	
	public Result<Integer> dotGainRule(DotGainRuleEnum dotGainRuleEnum,String userNo);
	
	/**
	 * 增加点券
	 * @param point 增加的点券
	 * @param dotChangeReson 点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册
	 * @param userNo 点券对应的用户
	 * @return
	 */
	public Result<Integer> addDot(int dot,int dotChangeReson,String userNo);
	
	/**
	 * 减少点券
	 * @param point 减少的点券
	 * @param dotChangeReson 点券变化原因：0其他、1注册、2登陆、3分享、4分享后被注册
	 * @param userNo 点券对应的用户
	 * @return
	 */
	public Result<Integer> reduceDot(int dot,int dotChangeReson,String userNo);
	
	
}
