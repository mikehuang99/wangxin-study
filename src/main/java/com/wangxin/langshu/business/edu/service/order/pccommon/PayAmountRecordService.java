package com.wangxin.langshu.business.edu.service.order.pccommon;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.order.PayAmountRecordDao;
import com.wangxin.langshu.business.edu.dao.order.impl.mapper.PayAmountRecordMapper;
import com.wangxin.langshu.business.edu.entity.order.PayAmountRecord;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardService;
import com.wangxin.langshu.business.edu.iservice.order.pccommon.IPayAmountRecordService;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordDeleteVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordQ;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordUpdateVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordViewVo;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PayAmountRecordService  extends ServiceImpl<PayAmountRecordMapper, PayAmountRecord> implements IPayAmountRecordService {

	@Autowired
	public PayAmountRecordDao payAmountRecordDao;
	
	@Autowired
    private PayAmountRecordMapper payAmountRecordMapper;
	
	@Autowired
	private IPcWebFreshCourseService pcWebFreshCourseService;
	@Autowired
	private IStudyCardService studyCardService;


	@Override
	public Page<PayAmountRecordVo> queryPayAmountRecords(PayAmountRecordQ q) {
		return payAmountRecordDao.queryPayAmountRecords(q);
	}

	@Override
	@Transactional
	public Result<PayAmountRecordSaveVo> savePayAmountRecord(PayAmountRecordSaveVo vo) {
		String id = UUID.randomUUID().toString();
		vo.setId(id);
		vo.setValidValue(1);
		PayAmountRecord payAmountRecord = new PayAmountRecord();
		BeanUtils.copyProperties(vo, payAmountRecord);
		this.save(payAmountRecord);
		return Result.success(vo);
	}

	@Override
	public Result<Integer> updatePayAmountRecord(PayAmountRecordUpdateVo vo) {
	
		
		return Result.error("1");
	}

	@Override
	public Result<Integer> deletePayAmountRecord(PayAmountRecordDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
		}

		return Result.success(1);
	}

	@Override
	public PayAmountRecordViewVo viewPayAmountRecord(PayAmountRecordViewVo viewVo) {
		PayAmountRecord examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		
		return viewVo;
	}

	

	
	
}
