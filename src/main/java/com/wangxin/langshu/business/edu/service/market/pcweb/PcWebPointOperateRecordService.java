package com.wangxin.langshu.business.edu.service.market.pcweb;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.PointOperateRecordDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.PointOperateRecordMapper;
import com.wangxin.langshu.business.edu.entity.market.PointOperateRecord;
import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebPointOperateRecordService;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordSaveVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordViewVo;
import com.wangxin.langshu.business.edu.vo.market.pointOperateRecord.PointOperateRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class PcWebPointOperateRecordService  extends ServiceImpl<PointOperateRecordMapper, PointOperateRecord> implements IPcWebPointOperateRecordService {

	@Autowired
	public PointOperateRecordDao PointOperateRecordDao;
	
	@Autowired
    private PointOperateRecordMapper PointOperateRecordMapper;
	

	@Override
	public Page<PointOperateRecordVo> queryPointOperateRecords(PointOperateRecordQ q) {
		return PointOperateRecordDao.queryPointOperateRecords(q);
	}

	@Override
	@Transactional
	public Result<Integer> savePointOperateRecord(PointOperateRecordSaveVo vo) {
		
		PointOperateRecord p = new PointOperateRecord();
		BeanUtils.copyProperties(vo, p);
		String id = UUID.randomUUID().toString();
		p.setId(id);
		this.save(p);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updatePointOperateRecord(PointOperateRecordUpdateVo vo) {
		PointOperateRecord PointOperateRecord = new PointOperateRecord();
		BeanUtils.copyProperties(vo, PointOperateRecord);
		this.updateById(PointOperateRecord);
		
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deletePointOperateRecord(PointOperateRecordDeleteVo vo) {
		return Result.success(1);	
	}

	@Override
	public PointOperateRecordViewVo viewPointOperateRecord(PointOperateRecordViewVo viewVo) {
		PointOperateRecord examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		

		return viewVo;
	}

	
}
