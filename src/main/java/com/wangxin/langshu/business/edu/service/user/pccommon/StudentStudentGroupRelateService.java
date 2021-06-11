package com.wangxin.langshu.business.edu.service.user.pccommon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.user.StudentStudentGroupRelateDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.StudentStudentGroupRelateMapper;
import com.wangxin.langshu.business.edu.entity.user.old.StudentStudentGroupRelate;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentStudentGroupRelateService;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateSaveVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateViewVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;

/**
 * 用户分组
 */
@Service
public class StudentStudentGroupRelateService  extends ServiceImpl<StudentStudentGroupRelateMapper,StudentStudentGroupRelate>  implements IStudentStudentGroupRelateService{
	
	@Autowired
	public StudentStudentGroupRelateDao StudentStudentGroupRelateDao;
	
	@Autowired
    private StudentStudentGroupRelateMapper studentStudentGroupRelateMapper;

	@Override
	public Page<StudentStudentGroupRelateVo> queryStudentStudentGroupRelates(StudentStudentGroupRelateQ StudentStudentGroupRelateQ) {
		return StudentStudentGroupRelateDao.queryStudentStudentGroupRelates(StudentStudentGroupRelateQ);
	}

	@Override
	public boolean saveStudentStudentGroupRelate(StudentStudentGroupRelateSaveVo studentStudentGroupRelateSaveVo) {
		if(!"1".equals(studentStudentGroupRelateSaveVo.getBindType())&&!"2".equals(studentStudentGroupRelateSaveVo.getBindType())){
    		throw new BaseException("绑定类型bindType必须为1或者2，1为绑定，2为解绑");
		}
		
		if(studentStudentGroupRelateSaveVo.getUserIds().length<1){
    		throw new BaseException("用户ID列表不能为空");
		}
		
		
		
		if("1".equals(studentStudentGroupRelateSaveVo.getBindType())){
			for(int i=0;i<studentStudentGroupRelateSaveVo.getUserIds().length;i++){
				String id = studentStudentGroupRelateSaveVo.getUserIds()[i];
				StudentStudentGroupRelate StudentStudentGroupRelate = new StudentStudentGroupRelate();
				BeanUtils.copyProperties(studentStudentGroupRelateSaveVo, StudentStudentGroupRelate);
				StudentStudentGroupRelate.setUserNoForGroup(id);
				this.save(StudentStudentGroupRelate);
			}
		}
		if("2".equals(studentStudentGroupRelateSaveVo.getBindType())){
			for(int i=0;i<studentStudentGroupRelateSaveVo.getUserIds().length;i++){
				String id = studentStudentGroupRelateSaveVo.getUserIds()[i];
				StudentStudentGroupRelate StudentStudentGroupRelate = new StudentStudentGroupRelate();
				BeanUtils.copyProperties(studentStudentGroupRelateSaveVo, StudentStudentGroupRelate);
				StudentStudentGroupRelate.setUserNoForGroup(id);
				UpdateWrapper<StudentStudentGroupRelate> updateWrapper = new UpdateWrapper<>();
				updateWrapper.eq("student_group_id", StudentStudentGroupRelate.getStudentGroupId());
				updateWrapper.eq("user_no_for_group", StudentStudentGroupRelate.getUserNoForGroup());

				studentStudentGroupRelateMapper.delete(updateWrapper);
			}
		}

		return true;
	}

	@Override
	public boolean updateStudentStudentGroupRelate(StudentStudentGroupRelateUpdateVo StudentStudentGroupRelateUpdateVo) {
		StudentStudentGroupRelate StudentStudentGroupRelate = new StudentStudentGroupRelate();
		BeanUtils.copyProperties(StudentStudentGroupRelateUpdateVo, StudentStudentGroupRelate);
		this.updateById(StudentStudentGroupRelate);
		return true;
	}

	@Override
	public boolean deleteStudentStudentGroupRelate(StudentStudentGroupRelateDeleteVo StudentStudentGroupRelateDeleteVo) {
		if(StudentStudentGroupRelateDeleteVo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<StudentStudentGroupRelateDeleteVo.getIds().length;i++){
			String id = StudentStudentGroupRelateDeleteVo.getIds()[i];
			this.removeById(id);
		}
		return true;
	}

	@Override
	public StudentStudentGroupRelateViewVo viewStudentStudentGroupRelate(StudentStudentGroupRelateViewVo StudentStudentGroupRelateViewVo) {
		StudentStudentGroupRelate StudentStudentGroupRelate = this.getById(StudentStudentGroupRelateViewVo.getId());
		BeanUtils.copyProperties(StudentStudentGroupRelate, StudentStudentGroupRelateViewVo);
		return StudentStudentGroupRelateViewVo;
	}


}
