package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.DotOperateRecord;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordViewVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebDotOperateRecordService  extends IService<DotOperateRecord> {
	
	

	public Page<DotOperateRecordVo> queryDotOperateRecords(DotOperateRecordQ q) ;

	public Result<Integer> saveDotOperateRecord(DotOperateRecordSaveVo vo) ;

	public Result<Integer> updateDotOperateRecord(DotOperateRecordUpdateVo vo) ;

	
	public Result<Integer> deleteDotOperateRecord(DotOperateRecordDeleteVo vo) ;

	
	public DotOperateRecordViewVo viewDotOperateRecord(DotOperateRecordViewVo vo) ;
	
	
	
	
	
	
}
