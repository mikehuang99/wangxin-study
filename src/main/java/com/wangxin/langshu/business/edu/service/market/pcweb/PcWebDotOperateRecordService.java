package com.wangxin.langshu.business.edu.service.market.pcweb;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.DotOperateRecordDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.DotOperateRecordMapper;
import com.wangxin.langshu.business.edu.entity.market.DotOperateRecord;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebDotOperateRecordService;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordViewVo;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcWebDotOperateRecordService  extends ServiceImpl<DotOperateRecordMapper, DotOperateRecord> implements IPcWebDotOperateRecordService {

	@Autowired
	public DotOperateRecordDao dotOperateRecordDao;
	
	@Autowired
    private DotOperateRecordMapper dotOperateRecordMapper;
	

	@Override
	public Page<DotOperateRecordVo> queryDotOperateRecords(DotOperateRecordQ q) {
		return dotOperateRecordDao.queryDotOperateRecords(q); 
	}

	@Override
	@Transactional
	public Result<Integer> saveDotOperateRecord(DotOperateRecordSaveVo vo) {
		DotOperateRecord d = new DotOperateRecord();
		BeanUtils.copyProperties(vo, d);
		String id = UUID.randomUUID().toString();
		d.setId(id);
		this.save(d);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateDotOperateRecord(DotOperateRecordUpdateVo vo) {
		DotOperateRecord DotOperateRecord = new DotOperateRecord();
		BeanUtils.copyProperties(vo, DotOperateRecord);
		this.updateById(DotOperateRecord);
		
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteDotOperateRecord(DotOperateRecordDeleteVo vo) {
		return Result.success(1);	
	}

	@Override
	public DotOperateRecordViewVo viewDotOperateRecord(DotOperateRecordViewVo viewVo) {
		DotOperateRecord examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		

		return viewVo;
	}

	
}
