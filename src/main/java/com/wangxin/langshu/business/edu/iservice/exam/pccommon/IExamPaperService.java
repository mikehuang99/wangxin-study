package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamPaper;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


public interface IExamPaperService  extends IService<ExamPaper> {
	
	

	public Result<Page<ExamPaperVo>> queryExamPapers(ExamPaperQ ExamPaperQ) ;
	
	public Page<ExamPaperVo> queryNotYetBuildExamPapers();

	public Result<Integer> saveExamPaper(ExamPaperSaveVo ExamPaperSaveVo) ;

	public Result<Integer> updateExamPaper(ExamPaperUpdateVo ExamPaperUpdateVo) ;

	
	public Result<Integer> deleteExamPaper(ExamPaperDeleteVo ExamPaperDeleteVo) ;

	
	public ExamPaperViewVo viewExamPaper(ExamPaperViewVo ExamPaperViewVo) ;
	
	/**
	 * 根据课程ID返回detailByChapterList
	 * @param courseId
	 * @return
	 */
	public ExamPaperViewVo findDetailByChapterList(String courseId);
	

	
	public String testJunit();
	
	/**
	 * 重新生成试卷
	 * @param id
	 * @return
	 */
	public Result<Integer> reGeneralExamPaper(String id);
	
	
}
