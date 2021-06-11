package com.wangxin.langshu.business.edu.service.exam.pccommon;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamQuestionCourseDao;
import com.wangxin.langshu.business.edu.dao.exam.ExamQuestionDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamQuestionMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestion;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionService;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.CacheRedis;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.HtmlFilterUtil;

@Service
public class ExamQuestionService  extends ServiceImpl<ExamQuestionMapper, ExamQuestion> implements IExamQuestionService {

	@Autowired
	public ExamQuestionDao examQuestionDao;
	
	@Autowired
	public ExamQuestionCourseDao examQuestionCourseDao;
	
	@Autowired
    private ExamQuestionMapper examQuestionMapper;
	
	
	@Autowired
	private CacheRedis cacheRedis;

	/*
	public List<Map<String, Object>> queryUser() {
		List<Map<String, Object>> list = examQuestionDao.queryUser();
		return list;
	}
	
	public com.wangxin.langshu.business.util.base.Page queryUser2() {
		return examQuestionDao.queryUser2();
	}
	
    public List<ExamQuestion> selectQuestionPage(int current,int size){
        QueryWrapper<ExamQuestion> queryWrapper = new QueryWrapper<>();
        //queryWrapper.ge("age",12);

        //设置当前页和页容量
        Page<ExamQuestion> page = new Page<>(current, size,false);

        System.out.println("page当前页："+page.getCurrent());
        System.out.println("page.size："+page.getSize());
        
        IPage<ExamQuestion> userIPage = examQuestionMapper.selectPage(page, queryWrapper);
        
        System.out.println("总页数："+userIPage.getPages());
        System.out.println("总记录数："+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
        return userIPage.getRecords();
    }

	@Override
	public com.wangxin.langshu.business.util.base.Page<ExamQuestion> queryQuestions() {
		return examQuestionDao.queryQuestions();
	}
	**/
	
	

	@Override
	public Page<ExamQuestionVo> queryExamQuestions(ExamQuestionQ examQuestionQ) {
		Page<ExamQuestionVo> page = examQuestionDao.queryExamQuestions(examQuestionQ);

		//Page<ExamQuestionVo> pageVo = PageUtil.transform(page, ExamQuestionVo.class);
		return page;
	}

	@Override
	public boolean saveExamQuestion(ExamQuestionSaveVo examQuestionSaveVo) {
		ExamQuestion examQuestion = new ExamQuestion();
		BeanUtils.copyProperties(examQuestionSaveVo, examQuestion);
		String id = UUID.randomUUID().toString().replace("-", "");
		examQuestion.setId(id);
		this.save(filterHtml(examQuestion));
		cacheRedis.set(RedisKey.EXAM_QUESTION_PREFIX_+id, examQuestion);
		return true;
	}

	@Override
	public boolean updateExamQuestion(ExamQuestionUpdateVo examQuestionUpdateVo) {
		ExamQuestion examQuestion = new ExamQuestion();
		BeanUtils.copyProperties(examQuestionUpdateVo, examQuestion);
		this.updateById(filterHtml(examQuestion));
		cacheRedis.set(RedisKey.EXAM_QUESTION_PREFIX_+examQuestionUpdateVo.getId(), examQuestion);
		return true;
	}

	@Override
	public boolean deleteExamQuestion(ExamQuestionDeleteVo examQuestionDeleteVo) {
		if(StringUtils.isEmpty(examQuestionDeleteVo.getId())){
			String ids[] = examQuestionDeleteVo.getIds();
			for(int i=0;i<ids.length;i++){
				this.removeById(ids[i]);
				examQuestionCourseDao.deleteExamQuestionId(ids[i]);
				cacheRedis.delete(RedisKey.EXAM_QUESTION_PREFIX_+ids[i]);
			}
		}else{
			this.removeById(examQuestionDeleteVo.getId());
			examQuestionCourseDao.deleteExamQuestionId(examQuestionDeleteVo.getId());
			cacheRedis.delete(RedisKey.EXAM_QUESTION_PREFIX_+examQuestionDeleteVo.getId());
		}
		return true;
	}

	@Override
	public ExamQuestionViewVo viewExamQuestion(ExamQuestionViewVo examQuestionViewVo) {
		ExamQuestion examQuestion = this.getById(examQuestionViewVo.getId());
		BeanUtils.copyProperties(examQuestion, examQuestionViewVo);
		return examQuestionViewVo;
	}
	
	@Override
	public ExamQuestion viewExamQuestionFromCache(String id) {
		ExamQuestion examQuestion = this.cacheRedis.get(RedisKey.EXAM_QUESTION_PREFIX_+id, ExamQuestion.class);
		if(examQuestion==null){
			examQuestion = this.getById(id);
			if(examQuestion!=null){
				cacheRedis.set(RedisKey.EXAM_QUESTION_PREFIX_+id, examQuestion);
			}
		}
		return examQuestion;
	}
	

	@Override
	public boolean saveBatchExamQuestion(List<ExamQuestionSaveVo> examQuestionSaveVos) {
		for(ExamQuestionSaveVo examQuestionSaveVo:examQuestionSaveVos ){
			this.saveExamQuestion(examQuestionSaveVo);
		}
		return true;
	}
	
	public ExamQuestion filterHtml(ExamQuestion examQuestion){
		examQuestion.setQuestionTitle(HtmlFilterUtil.clean(examQuestion.getQuestionTitle()));
		examQuestion.setItemA(HtmlFilterUtil.clean(examQuestion.getItemA()));
		examQuestion.setItemB(HtmlFilterUtil.clean(examQuestion.getItemB()));
		examQuestion.setItemC(HtmlFilterUtil.clean(examQuestion.getItemC()));
		examQuestion.setItemD(HtmlFilterUtil.clean(examQuestion.getItemD()));
		examQuestion.setItemE(HtmlFilterUtil.clean(examQuestion.getItemE()));
		examQuestion.setItemF(HtmlFilterUtil.clean(examQuestion.getItemF()));
		examQuestion.setItemG(HtmlFilterUtil.clean(examQuestion.getItemG()));
		examQuestion.setItemH(HtmlFilterUtil.clean(examQuestion.getItemH()));
		examQuestion.setAnswer(HtmlFilterUtil.clean(examQuestion.getAnswer()));
		examQuestion.setAnalysis(HtmlFilterUtil.clean(examQuestion.getAnalysis()));
		return examQuestion;
	}

	@Override
	public void cacheAllExamQuestion() {
		List<String> ids = examQuestionDao.queryAllExamQuestionIds();
		for(String id : ids){
			ExamQuestion examQuestion = this.getById(id);
			cacheRedis.delete(RedisKey.EXAM_QUESTION_PREFIX_+id);
			if(examQuestion!=null){
				cacheRedis.set(RedisKey.EXAM_QUESTION_PREFIX_+id, examQuestion);
			}
		}
	}



}
