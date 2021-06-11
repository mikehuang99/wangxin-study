package com.wangxin.langshu.business.edu.service.user.pccommon;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamPaperDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamPaperMapper;
import com.wangxin.langshu.business.edu.dao.user.StudentGroupDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.StudentGroupMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamPaper;
import com.wangxin.langshu.business.edu.entity.user.old.StudentGroup;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentGroupService;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupSaveVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupViewVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;

import net.sf.json.JSONArray;

/**
 * 用户分组
 */
@Service
public class StudentGroupService  extends ServiceImpl<StudentGroupMapper,StudentGroup>  implements IStudentGroupService{
	
	@Autowired
	public StudentGroupDao studentGroupDao;
	
	@Autowired
    private StudentGroupMapper studentGroupMapper;

	@Override
	public Page<StudentGroupVo> queryStudentGroups(StudentGroupQ studentGroupQ) {

		return studentGroupDao.queryStudentGroups(studentGroupQ);
	}

	@Override
	public boolean saveStudentGroup(StudentGroupSaveVo studentGroupSaveVo) {
		StudentGroup studentGroup = new StudentGroup();
		BeanUtils.copyProperties(studentGroupSaveVo, studentGroup);
		this.save(studentGroup);
		return true;
	}

	@Override
	public boolean updateStudentGroup(StudentGroupUpdateVo studentGroupUpdateVo) {
		StudentGroup studentGroup = new StudentGroup();
		BeanUtils.copyProperties(studentGroupUpdateVo, studentGroup);
		this.updateById(studentGroup);
		return true;
	}

	@Override
	public boolean deleteStudentGroup(StudentGroupDeleteVo studentGroupDeleteVo) {
		/*
		if(studentGroupDeleteVo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<studentGroupDeleteVo.getIds().length;i++){
			String id = studentGroupDeleteVo.getIds()[i];
			this.removeById(id);
		}*/
		this.removeById(studentGroupDeleteVo.getId());
		return true;
	}

	@Override
	public StudentGroupViewVo viewStudentGroup(StudentGroupViewVo studentGroupViewVo) {
		StudentGroup studentGroup = this.getById(studentGroupViewVo.getId());
		BeanUtils.copyProperties(studentGroup, studentGroupViewVo);
		return studentGroupViewVo;
	}


}
