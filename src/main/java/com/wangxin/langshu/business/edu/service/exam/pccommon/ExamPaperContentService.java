package com.wangxin.langshu.business.edu.service.exam.pccommon;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamPaperContentDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamPaperContentMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamPaperContent;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperContentService;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class ExamPaperContentService  extends ServiceImpl<ExamPaperContentMapper, ExamPaperContent> implements IExamPaperContentService {

	@Autowired
	public ExamPaperContentDao examManagementDao;
	
	@Autowired
    private ExamPaperContentMapper examManagementMapper;

	@Override
	public Result<Page<ExamPaperContentVo>> queryExamPaperContents(ExamPaperContentQ q) {
		return Result.success(examManagementDao.queryExamPaperContents(q));
	}

	@Override
	public Result<Integer> saveExamPaperContent(ExamPaperContentSaveVo vo) {
	
		
		String id = UUID.randomUUID().toString().replace("-", "");
		ExamPaperContent examManagementUser = new ExamPaperContent();
		
		BeanUtils.copyProperties(vo, examManagementUser);
		examManagementUser.setId(id);
		this.save(examManagementUser);
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateExamPaperContent(ExamPaperContentUpdateVo vo) {
		
		if(StringUtils.isEmpty(vo.getId())){
			return Result.error("缺少ID");
		}
		
		ExamPaperContent ExamPaperContent = this.getById(vo.getId());
		if(ObjectUtil.isNull(ExamPaperContent)){
			return Result.error("根据ID查找不到对象");
		}
		
		BeanUtils.copyProperties(vo, ExamPaperContent);
		this.updateById(ExamPaperContent);
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteExamPaperContent(ExamPaperContentDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			
			ExamPaperContent ExamPaperContent = this.getById(id);
			if(ObjectUtil.isNull(ExamPaperContent)){
				return Result.error("根据ID查找不到对象");
			}
			this.removeById(id);
		}
		
		return Result.success(1);
	}

	@Override
	public ExamPaperContentViewVo viewExamPaperContent(ExamPaperContentViewVo viewVo) {
		ExamPaperContent examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		
		return viewVo;
	}
	
	@Override
	public Result<ExamPaperContentVo> viewExamPaperContentByExamPaperIdAllowTeacher(ExamPaperContentViewVo viewVo) {
		if(StringUtils.isEmpty(viewVo.getExamPaperId())) {
			return Result.error("缺少examPaperId");
		}
		if(StringUtils.isEmpty(viewVo.getUserNo())) {
			return Result.error("缺少userNo");
		}
		ExamPaperContentQ q = new ExamPaperContentQ();
		q.setExamPaperId(viewVo.getExamPaperId());
		q.setTeacherUserNo(viewVo.getUserNo());
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<ExamPaperContentVo> page = examManagementDao.queryExamPaperContents(q);
		List<ExamPaperContentVo> examPaperContentVos = page.getList();
		if(CollectionUtil.isEmpty(examPaperContentVos)) {
			return Result.error("查不到数据");
		}
		if(examPaperContentVos.size()!=1) {
			return Result.error("数据异常");
		}
		
		ExamPaperContentVo examPaperContentVo = examPaperContentVos.get(0);
		if(StringUtils.isNotEmpty(examPaperContentVo.getExamPaperContent())) {
			List<ExamQuestionVo> examQuestionVos =  (List<ExamQuestionVo>) JSON.parse(examPaperContentVo.getExamPaperContent());
			examPaperContentVo.setExamQuestionVos(examQuestionVos);
		}
		
		return Result.success(examPaperContentVo);
	}

	@Override
	public Result<ExamPaperContentVo> viewExamPaperContentByExamPaperIdAllowStudent(ExamPaperContentViewVo viewVo) {
		if(StringUtils.isEmpty(viewVo.getExamPaperId())) {
			return Result.error("缺少examPaperId");
		}
		if(StringUtils.isEmpty(viewVo.getUserNo())) {
			return Result.error("缺少userNo");
		}
		//1、首先要确认一下该学生是否拥有这个考试
		
		//2、再进行以下操作
		ExamPaperContentQ q = new ExamPaperContentQ();
		q.setExamPaperId(viewVo.getExamPaperId());
		//q.setTeacherUserNo(viewVo.getUserNo());
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<ExamPaperContentVo> page = examManagementDao.queryExamPaperContents(q);
		List<ExamPaperContentVo> examPaperContentVos = page.getList();
		if(CollectionUtil.isEmpty(examPaperContentVos)) {
			return Result.error("查不到数据");
		}
		if(examPaperContentVos.size()!=1) {
			return Result.error("数据异常");
		}
		
		ExamPaperContentVo examPaperContentVo = examPaperContentVos.get(0);
		if(StringUtils.isNotEmpty(examPaperContentVo.getExamPaperContent())) {
			List<ExamQuestionVo> examQuestionVos =  (List<ExamQuestionVo>) JSON.parse(examPaperContentVo.getExamPaperContent());
			examPaperContentVo.setExamQuestionVos(examQuestionVos);
		}
		
		return Result.success(examPaperContentVo);
	}

	
}
