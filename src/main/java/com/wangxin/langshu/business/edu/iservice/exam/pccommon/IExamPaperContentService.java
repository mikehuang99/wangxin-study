package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamPaperContent;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


public interface IExamPaperContentService  extends IService<ExamPaperContent> {
	
	

	public Result<Page<ExamPaperContentVo>> queryExamPaperContents(ExamPaperContentQ q) ;

	public Result<Integer> saveExamPaperContent(ExamPaperContentSaveVo vo) ;

	public Result<Integer> updateExamPaperContent(ExamPaperContentUpdateVo vo) ;

	
	public Result<Integer> deleteExamPaperContent(ExamPaperContentDeleteVo vo) ;

	
	public ExamPaperContentViewVo viewExamPaperContent(ExamPaperContentViewVo vo) ;
	
	
	/**
	 * 根据examPaperId查询ExamPaperContentVo（教师角色来查）
	 * @param vo
	 * @return
	 */
	public Result<ExamPaperContentVo> viewExamPaperContentByExamPaperIdAllowTeacher(ExamPaperContentViewVo vo) ;
	
	/**
	 * 根据examPaperId查询ExamPaperContentVo（学生角色来查）
	 * @param vo
	 * @return
	 */
	public Result<ExamPaperContentVo> viewExamPaperContentByExamPaperIdAllowStudent(ExamPaperContentViewVo vo) ;

	
	
	
}
