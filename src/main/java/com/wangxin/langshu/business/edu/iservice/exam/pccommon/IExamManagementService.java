package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagement;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


public interface IExamManagementService  extends IService<ExamManagement> {
	
	

	public Page<ExamManagementVo> queryExamManagements(ExamManagementQ q) ;

	public Result<Integer> saveExamManagement(ExamManagementSaveVo vo) ;

	public Result<Integer> updateExamManagement(ExamManagementUpdateVo vo) ;

	
	public Result<Integer> deleteExamManagement(ExamManagementDeleteVo vo) ;

	
	public ExamManagementViewVo viewExamManagement(ExamManagementViewVo vo) ;
	
	public ExamManagementViewVo viewSimpleExamManagement(ExamManagementViewVo vo) ;

	
	public Result<ExamManagementViewVo> viewByIdAndStudentUserNo(ExamManagementViewVo examManagementViewVo) ;
	
	
	/**
	 * 查询学生的考试
	 * @param q
	 * @return
	 */
	public Result<Page<ExamManagementVo>> queryExamManagementsByStudentUserNo(ExamManagementQ q) ;
	
	/**
	 * 根据考试ID查询所有考试对象
	 * @param examManagementQ
	 * @return
	 */
	public Result<Page<ExamManagementUserVo>> queryExamObjectsByExamManagementId(ExamManagementUserQ examManagementUserQ);
	
	
}
