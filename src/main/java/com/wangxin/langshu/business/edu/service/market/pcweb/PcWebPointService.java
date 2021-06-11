package com.wangxin.langshu.business.edu.service.market.pcweb;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.PointDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.PointMapper;
import com.wangxin.langshu.business.edu.entity.market.Point;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointOperateRecordService;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointService;
import com.wangxin.langshu.business.edu.vo.market.point.PointDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointQ;
import com.wangxin.langshu.business.edu.vo.market.point.PointSaveVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointViewVo;
import com.wangxin.langshu.business.edu.vo.market.point.PointVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordSaveVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;


@Service
public class PcWebPointService  extends ServiceImpl<PointMapper, Point> implements IPcWebPointService {

	@Autowired
	public PointDao pointDao;
	
	@Autowired
    private PointMapper pointMapper;
	
	@Autowired
	private IPcWebCouponService pcWebCouponService;
	
	@Autowired
	private IPcWebPointOperateRecordService pcWebPointOperateRecordService;

	@Override
	public Page<PointVo> queryPoints(PointQ q) {
		return pointDao.queryPoints(q);
	}

	@Override
	@Transactional
	public Result<Integer> savePoint(PointSaveVo vo) {
		Point point = new Point();
		BeanUtils.copyProperties(vo, point);
		String id = UUID.randomUUID().toString();
		point.setId(id);
		point.setValidValue(1);
		this.save(point);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updatePoint(PointUpdateVo vo) {
		Point Point = new Point();
		BeanUtils.copyProperties(vo, Point);
		this.updateById(Point);
		
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deletePoint(PointDeleteVo vo) {
		return Result.success(1);	
	}

	@Override
	public PointViewVo viewPoint(PointViewVo viewVo) {
		Point examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		return viewVo;
	}

	@Override
	public PointViewVo viewPointByUserNo(String userNo) {
		PointViewVo pointViewVo = new PointViewVo();
		PointQ q= new PointQ();
		q.setBindUserNo(userNo);
		Page<PointVo> page = pointDao.queryPoints(q);
		if(CollectionUtil.isEmpty(page.getList())){
			//增加初始化数据
			Point point = new Point();
			String id = UUID.randomUUID().toString();
			point.setId(id);
			point.setBindUserNo(userNo);
			point.setTotalPoint(0);
			point.setValidValue(1);
			this.save(point);
			BeanUtils.copyProperties(point, pointViewVo);
		}else{
			BeanUtils.copyProperties(page.getList().get(0), pointViewVo);
		}
		return pointViewVo;
	}

	@Override
	public Result<Integer> addPoint(int point,String userNo) {
    	PointViewVo pointViewVo = this.viewPointByUserNo(userNo);
        int totalPoint = pointViewVo.getTotalPoint();
        
		Point p = this.getById(pointViewVo);
        int newTotalPoint = totalPoint+point;
        p.setTotalPoint(newTotalPoint);
        this.updateById(p);

        PointOperateRecordSaveVo pointOperateRecordSaveVo = new PointOperateRecordSaveVo();
        pointOperateRecordSaveVo.setOperateType(0);
        pointOperateRecordSaveVo.setUserNoOfPoint(p.getBindUserNo());
        pointOperateRecordSaveVo.setPoint(point);
        pointOperateRecordSaveVo.setTotalPoint(newTotalPoint);
        pointOperateRecordSaveVo.setValidValue(1);
        pcWebPointOperateRecordService.savePointOperateRecord(pointOperateRecordSaveVo);
        
		return Result.success(1);
	}

	@Override
	public Result<Integer> reducePoint(int point,String userNo) {
    	PointViewVo pointViewVo = this.viewPointByUserNo(userNo);
        int totalPoint = pointViewVo.getTotalPoint();
		if(totalPoint<point){
			return Result.error("积分不够，扣分失败");
		}
        int newTotalPoint = totalPoint-point;
        Point p = this.getById(pointViewVo.getId());
        p.setTotalPoint(newTotalPoint);
        this.updateById(p);

        PointOperateRecordSaveVo pointOperateRecordSaveVo = new PointOperateRecordSaveVo();
        pointOperateRecordSaveVo.setOperateType(1);
        pointOperateRecordSaveVo.setUserNoOfPoint(p.getBindUserNo());
        pointOperateRecordSaveVo.setPoint(point);
        pointOperateRecordSaveVo.setTotalPoint(newTotalPoint);
        pointOperateRecordSaveVo.setValidValue(1);
        pcWebPointOperateRecordService.savePointOperateRecord(pointOperateRecordSaveVo);
        
		return Result.success(1);
	}

	
}
