package com.wangxin.langshu.business.edu.service.exam.pccommon;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamManagementUserDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamManagementUserMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagement;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagementUser;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementUserService;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class ExamManagementUserService  extends ServiceImpl<ExamManagementUserMapper, ExamManagementUser> implements IExamManagementUserService {

	@Autowired
	public ExamManagementUserDao examManagementDao;
	
	@Autowired
    private ExamManagementUserMapper examManagementMapper;
	
	@Autowired
	private IExamManagementService examManagementService;

	@Override
	public Page<ExamManagementUserVo> queryExamManagementUsers(ExamManagementUserQ q) {
		return examManagementDao.queryExamManagementUsers(q);
	}

	@Override
	public Result<Integer> saveExamManagementUser(ExamManagementUserSaveVo vo) {
		
		if(StringUtils.isEmpty(vo.getExamManagementId())) {
			return Result.error("??????examManagementId");
		}
		if(StringUtils.isEmpty(vo.getExamPaperId())) {
			return Result.error("??????examPaperId");
		}
		if(StringUtils.isEmpty(vo.getExamUserNo())) {
			return Result.error("??????userNo");
		}
		
		ExamManagementUserQ q = new ExamManagementUserQ();
		q.setExamManagementId(vo.getExamManagementId());
		q.setExamPaperId(vo.getExamPaperId());
		q.setExamUserNo(vo.getExamUserNo());
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<ExamManagementUserVo> page = examManagementDao.queryExamManagementUsers(q);
		if(CollectionUtil.isNotEmpty(page.getList())) {
			List<ExamManagementUserVo> examManagementUserVos = page.getList();
			for(ExamManagementUserVo examManagementUserVo: examManagementUserVos) {
				this.removeById(examManagementUserVo.getId());
			}
		}
		
		String id = UUID.randomUUID().toString().replace("-", "");
		ExamManagementUser examManagementUser = new ExamManagementUser();
		
		BeanUtils.copyProperties(vo, examManagementUser);
		examManagementUser.setId(id);
		this.save(examManagementUser);
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateExamManagementUser(ExamManagementUserUpdateVo vo) {
		
		if(StringUtils.isEmpty(vo.getId())){
			return Result.error("??????ID");
		}
		
		ExamManagementUser ExamManagementUser = this.getById(vo.getId());
		if(ObjectUtil.isNull(ExamManagementUser)){
			return Result.error("??????ID??????????????????");
		}
		
		BeanUtils.copyProperties(vo, ExamManagementUser);
		this.updateById(ExamManagementUser);
		
		return Result.success(1);
	}
	
	@Override
	public Result<Integer> markExamPaper(ExamManagementUserUpdateVo vo) {
		if(StringUtils.isEmpty(vo.getId())) {
			return Result.error("??????ID"); 
		}
		if(StringUtils.isEmpty(vo.getUserNo())) {
			return Result.error("??????userNo"); 
		}
		ExamManagementUser examManagementUser = this.getById(vo.getId());
		if(ObjectUtil.isNull(examManagementUser)) {
			return Result.error("?????????????????????????????????"); 
		}
		ExamManagement examManagement = examManagementService.getById(examManagementUser.getExamManagementId());
		if(ObjectUtil.isNull(examManagement)) {
			return Result.error("?????????????????????"); 
		}
		if(!vo.getUserNo().equals(examManagement.getTeacherUserNo())) {
			return Result.error("???????????????????????????????????????????????????"); 
		}
		
		examManagementUser.setIsFinishUserTotalScore(1);
		examManagementUser.setExamPaperAnswer(vo.getExamPaperAnswer());
		examManagementUser.setUserTotalScore(vo.getUserTotalScore());
		
		this.updateById(examManagementUser);
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteExamManagementUser(ExamManagementUserDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("??????ID????????????");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			
			ExamManagementUser ExamManagementUser = this.getById(id);
			if(ObjectUtil.isNull(ExamManagementUser)){
				return Result.error("??????ID??????????????????");
			}
			this.removeById(id);
		}
		
		return Result.success(1);
	}

	@Override
	public ExamManagementUserViewVo viewExamManagementUser(ExamManagementUserViewVo viewVo) {
		ExamManagementUser examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		
		return viewVo;
	}

	

	
}
