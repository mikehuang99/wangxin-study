package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.PointOperateRecord;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordViewVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebPointOperateRecordService  extends IService<PointOperateRecord> {
	
	

	public Page<PointOperateRecordVo> queryPointOperateRecords(PointOperateRecordQ q) ;

	public Result<Integer> savePointOperateRecord(PointOperateRecordSaveVo vo) ;

	public Result<Integer> updatePointOperateRecord(PointOperateRecordUpdateVo vo) ;

	
	public Result<Integer> deletePointOperateRecord(PointOperateRecordDeleteVo vo) ;

	
	public PointOperateRecordViewVo viewPointOperateRecord(PointOperateRecordViewVo vo) ;
	
	
	
	
	
	
}
