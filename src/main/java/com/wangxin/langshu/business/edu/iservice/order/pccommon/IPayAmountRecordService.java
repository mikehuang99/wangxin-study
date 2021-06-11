package com.wangxin.langshu.business.edu.iservice.order.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.order.PayAmountRecord;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordDeleteVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordQ;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordUpdateVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordViewVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPayAmountRecordService  extends IService<PayAmountRecord> {
	
	

	public Page<PayAmountRecordVo> queryPayAmountRecords(PayAmountRecordQ q) ;

	public Result<PayAmountRecordSaveVo> savePayAmountRecord(PayAmountRecordSaveVo vo) ;

	public Result<Integer> updatePayAmountRecord(PayAmountRecordUpdateVo vo) ;

	
	public Result<Integer> deletePayAmountRecord(PayAmountRecordDeleteVo vo) ;

	
	public PayAmountRecordViewVo viewPayAmountRecord(PayAmountRecordViewVo vo) ;
	
	
	
	
}
