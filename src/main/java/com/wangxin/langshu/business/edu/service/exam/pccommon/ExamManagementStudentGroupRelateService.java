package com.wangxin.langshu.business.edu.service.exam.pccommon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamManagementStudentGroupRelateDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamManagementStudentGroupRelateMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagementStudentGroupRelate;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementStudentGroupRelateService;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class ExamManagementStudentGroupRelateService  extends ServiceImpl<ExamManagementStudentGroupRelateMapper, ExamManagementStudentGroupRelate> implements IExamManagementStudentGroupRelateService {

	@Autowired
	public ExamManagementStudentGroupRelateDao examManagementStudentGroupRelateDao;
	
	@Autowired
    private ExamManagementStudentGroupRelateMapper examManagementStudentGroupRelateMapper;

	@Override
	public Page<ExamManagementStudentGroupRelateVo> queryEntities(ExamManagementStudentGroupRelateQ q) {
		return examManagementStudentGroupRelateDao.queryEntities(q);
	}

	@Override
	public boolean saveEntity(ExamManagementStudentGroupRelateSaveVo vo) {
		ExamManagementStudentGroupRelate entity = new ExamManagementStudentGroupRelate();
		BeanUtils.copyProperties(vo, entity);
		this.save(entity);
		return true;
	}

	@Override
	public boolean updateEntity(ExamManagementStudentGroupRelateUpdateVo vo) {
		ExamManagementStudentGroupRelate entity = new ExamManagementStudentGroupRelate();
		BeanUtils.copyProperties(vo, entity);
		this.updateById(entity);
		return true;
	}

	@Override
	public boolean deleteEntity(ExamManagementStudentGroupRelateDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
		}
		return true;
	}

	@Override
	public boolean deleteEntityByExamManagementId(String examManagementId) {
		UpdateWrapper<ExamManagementStudentGroupRelate> wrapper = new UpdateWrapper<>();
		wrapper.eq("exam_management_id", examManagementId);
        examManagementStudentGroupRelateMapper.delete(wrapper);
		//this.remove(queryWrapper);
		return false;
	}
	
	@Override
	public ExamManagementStudentGroupRelateViewVo viewEntity(ExamManagementStudentGroupRelateViewVo vo) {
		ExamManagementStudentGroupRelate entity = this.getById(vo.getId());
		BeanUtils.copyProperties(entity, vo);

		return vo;
	}




	


	
	
}
