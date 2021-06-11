package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestion;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.util.base.Page;


public interface IExamQuestionService  extends IService<ExamQuestion> {
	/**

	public List<Map<String, Object>> queryUser() ;
	
	public Page queryUser2();
	
    public List<ExamQuestion> selectQuestionPage(int current,int size);
    
    public Page<ExamQuestion> queryQuestions();
    **/
    
    ///--------------------...
    
    public Page<ExamQuestionVo> queryExamQuestions(ExamQuestionQ examQuestionREQ);
    
    public boolean saveExamQuestion(ExamQuestionSaveVo examQuestionSaveVo);
    
    public boolean updateExamQuestion(ExamQuestionUpdateVo examQuestionUpdateVo);
    
    public boolean deleteExamQuestion(ExamQuestionDeleteVo examQuestionDeleteVo);
    
    public ExamQuestionViewVo viewExamQuestion(ExamQuestionViewVo examQuestionViewVo);
    
    public ExamQuestion viewExamQuestionFromCache(String id);

    
    public boolean saveBatchExamQuestion(List<ExamQuestionSaveVo> examQuestionSaveVos);
    
    /**
     * 重新缓存所有试题
     */
    public void cacheAllExamQuestion();

    

}
