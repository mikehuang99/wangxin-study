package com.wangxin.langshu.business.edu.service.exam.pccommon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.course.impl.CourseUserDao;
import com.wangxin.langshu.business.edu.dao.exam.ExamQuestionCourseDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamQuestionCourseMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestion;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestionCourse;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionService;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.CacheRedis;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.xiaoleilu.hutool.util.ObjectUtil;

@Service
public class ExamQuestionCourseService  extends ServiceImpl<ExamQuestionCourseMapper, ExamQuestionCourse> implements IExamQuestionCourseService {

	@Autowired
	public ExamQuestionCourseDao examQuestionCourseDao;
	
	@Autowired
    private ExamQuestionCourseMapper examQuestionCourseMapper;
	
	@Autowired
	private IExamQuestionService examQuestionService;
	
	
	@Autowired
	private IPcWebFreshLessonService freshLessonService;
	
	@Autowired
	private IPcWebFreshCourseService freshCourseService;
	
	@Autowired
	public CourseUserDao courseUserDao;

	@Autowired
	private CacheRedis cacheRedis;


	@Override
	public Page<ExamQuestionCourseVo> queryExamQuestionCourses(ExamQuestionCourseQ examQuestionCourseQ) {
    	if(!("1".equals(examQuestionCourseQ.getBindType())||
    			"2".equals(examQuestionCourseQ.getBindType())||
    			"3".equals(examQuestionCourseQ.getBindType()))){
    		throw new BaseException("查询类型getBindType的值不正确，1表示根据课程，2表示根据章节，3表示根据课时");
    	}
    	if("1".equals(examQuestionCourseQ.getBindType())){
    		if(StringUtils.isEmpty(examQuestionCourseQ.getCourseId())){
        		throw new BaseException("查询类型为课程，课程ID不能为空");
    		}
    	}
    	if("2".equals(examQuestionCourseQ.getBindType())){
    		if(StringUtils.isEmpty(examQuestionCourseQ.getCourseId())){
        		throw new BaseException("查询类型为章节，课程ID courseId 不能为空");
    		}
    		if(StringUtils.isEmpty(examQuestionCourseQ.getChapterId())){
        		throw new BaseException("查询类型为章节，章节ID chapterId 不能为空");
    		}
    	}
    	if("3".equals(examQuestionCourseQ.getBindType())){
    		if(StringUtils.isEmpty(examQuestionCourseQ.getCourseId())){
        		throw new BaseException("查询类型为课时，课程ID courseId 不能为空");
    		}
    		if(StringUtils.isEmpty(examQuestionCourseQ.getChapterId())){
        		throw new BaseException("查询类型为课时，章节ID chapterId 不能为空");
    		}
    		if(StringUtils.isEmpty(examQuestionCourseQ.getLessonId())){
        		throw new BaseException("查询类型为课时，课时ID lessonId 不能为空");
    		}
    	}
		Page<ExamQuestionCourseVo> page = examQuestionCourseDao.queryExamQuestionCourses(examQuestionCourseQ);

		//Page<ExamQuestionVo> pageVo = PageUtil.transform(page, ExamQuestionVo.class);
		return page;
	}
	
	@Override
	public int countExamQuestionCourses(ExamQuestionCourseQ examQuestionCourseQ) {
		return examQuestionCourseDao.countExamQuestionCourses(examQuestionCourseQ);
	}

	@Override
	public boolean saveExamQuestionCourse(ExamQuestionCourseSaveVo examQuestionCourseSaveVo) {
		if(("1".equals(examQuestionCourseSaveVo.getBindType())||
				"2".equals(examQuestionCourseSaveVo.getBindType())||
				"3".equals(examQuestionCourseSaveVo.getBindType()))==false){
    		throw new BaseException("绑定类型bindType的值不正确，1表示根据课程，2表示根据章节，3表示根据课时");
		}

		String[] questionIds = examQuestionCourseSaveVo.getQuestionIds();
		for(int i=0;i<questionIds.length;i++){
			ExamQuestionCourse examQuestionCourse = new ExamQuestionCourse();
			BeanUtils.copyProperties(examQuestionCourseSaveVo, examQuestionCourse);
			String id = UUID.randomUUID().toString().replace("-", "");
			examQuestionCourse.setId(id);
			examQuestionCourse.setQuestionId(questionIds[i]);
			this.save(examQuestionCourse);
			
			String questionId = questionIds[i];
			
			if("1".equals(examQuestionCourseSaveVo.getBindType())){
				String courseId = examQuestionCourseSaveVo.getCourseId();
				List<String> cacheList = cacheRedis.get(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+courseId, List.class);
				if(null==cacheList){
					List<String> list = new ArrayList<String>();
					list.add(questionId);
					cacheRedis.set(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+courseId, list);
				}else{
					if(!cacheList.contains(questionId)){
						cacheList.add(questionId);
						cacheRedis.set(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+courseId, cacheList);
					}
				}
			}
			
			if("2".equals(examQuestionCourseSaveVo.getBindType())){
				String chapterId = examQuestionCourseSaveVo.getChapterId();
				List<String> cacheList = cacheRedis.get(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+chapterId, List.class);
				if(null==cacheList){
					List<String> list = new ArrayList<String>();
					list.add(questionId);
					cacheRedis.set(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+chapterId, list);
				}else{
					if(!cacheList.contains(questionId)){
						cacheList.add(questionId);
						cacheRedis.set(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+chapterId, cacheList);
					}
				}
			}
			
			if("3".equals(examQuestionCourseSaveVo.getBindType())){
				String lessonId = examQuestionCourseSaveVo.getLessonId();
				List<String> cacheList = cacheRedis.get(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+lessonId, List.class);
				if(null==cacheList){
					List<String> list = new ArrayList<String>();
					list.add(questionId);
					cacheRedis.set(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+lessonId, list);
				}else{
					if(!cacheList.contains(questionId)){
						cacheList.add(questionId);
						cacheRedis.set(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+lessonId, cacheList);
					}
				}
			}
			
		}
		
		
		return true;
	}

	@Override
	public boolean updateExamQuestionCourse(ExamQuestionCourseUpdateVo examQuestionCourseUpdateVo) {
		ExamQuestionCourse examQuestionCourse = new ExamQuestionCourse();
		BeanUtils.copyProperties(examQuestionCourseUpdateVo, examQuestionCourse);
		this.updateById(examQuestionCourse);
		return true;
	}

	@Override
	public boolean deleteExamQuestionCourse(ExamQuestionCourseDeleteVo examQuestionCourseDeleteVo) {
		if(null==examQuestionCourseDeleteVo.getQuestionIds()||examQuestionCourseDeleteVo.getQuestionIds().length==0){
    		throw new BaseException("没有questionId");
		}
		if(("1".equals(examQuestionCourseDeleteVo.getBindType())||
				"2".equals(examQuestionCourseDeleteVo.getBindType())||
				"3".equals(examQuestionCourseDeleteVo.getBindType()))==false){
    		throw new BaseException("绑定类型bindType的值不正确，1表示根据课程，2表示根据章节，3表示根据课时");
		}
		String[] questionIds = examQuestionCourseDeleteVo.getQuestionIds();
		for(int i=0;i<questionIds.length;i++){
			ExamQuestionCourse examQuestionCourse = new ExamQuestionCourse();
			examQuestionCourse.setQuestionId(questionIds[i]);
			examQuestionCourse.setBindType(examQuestionCourseDeleteVo.getBindType());
			examQuestionCourse.setCourseId(examQuestionCourseDeleteVo.getCourseId());
			examQuestionCourse.setChapterId(examQuestionCourseDeleteVo.getChapterId());
			examQuestionCourse.setLessonId(examQuestionCourseDeleteVo.getLessonId());
			this.examQuestionCourseDao.deleteExamQuestionCourse(examQuestionCourse);
			
			String questionId = questionIds[i];
			if("1".equals(examQuestionCourseDeleteVo.getBindType())){
				String courseId = examQuestionCourseDeleteVo.getCourseId();
				List<String> cacheList = cacheRedis.get(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+courseId, List.class);
				List<String> newCacheList = new ArrayList<>();
				if(null!=cacheList){
					for(String cacheId: cacheList){
						if(!questionId.equals(cacheId)){
							newCacheList.add(cacheId);
						}
					}
					if(newCacheList.size()==cacheList.size()){
						//cacheRedis.delete(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+courseId);
					}else{
						cacheRedis.set(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+courseId, newCacheList);
					}
				}
			}
			
			if("2".equals(examQuestionCourseDeleteVo.getBindType())){
				String chapterId = examQuestionCourseDeleteVo.getChapterId();
				List<String> cacheList = cacheRedis.get(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+chapterId, List.class);
				List<String> newCacheList = new ArrayList<>();
				if(null!=cacheList){
					for(String cacheId: cacheList){
						if(!questionId.equals(cacheId)){
							newCacheList.add(cacheId);
						}
					}
					
					if(newCacheList.size()==cacheList.size()){
						//cacheRedis.delete(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+chapterId);
					}else{
						cacheRedis.set(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+chapterId, newCacheList);
					}
				}
			}
			
			if("3".equals(examQuestionCourseDeleteVo.getBindType())){
				String lessonId = examQuestionCourseDeleteVo.getLessonId();
				List<String> cacheList = cacheRedis.get(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+lessonId, List.class);
				List<String> newCacheList = new ArrayList<>();
				if(null!=cacheList){
					for(String cacheId: cacheList){
						if(!questionId.equals(cacheId)){
							newCacheList.add(cacheId);
						}
					}
					if(newCacheList.size()==cacheList.size()){
						//cacheRedis.delete(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+lessonId);
					}else{
						cacheRedis.set(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+lessonId, newCacheList);
					}
				}
			}
			
		}
		return true;
	}

	@Override
	public ExamQuestionCourseViewVo viewExamQuestionCourse(ExamQuestionCourseViewVo examQuestionCourseViewVo) {
		ExamQuestionCourse examQuestionCourse = this.getById(examQuestionCourseViewVo.getId());
		BeanUtils.copyProperties(examQuestionCourse, examQuestionCourseViewVo);
		return examQuestionCourseViewVo;
	}

	@Override
	public List<Map<String, Object>> countExamQuestionCourses(List<String> ids, String bindType, String status) {
		return this.examQuestionCourseDao.countExamQuestionCourses(ids, bindType, status);
	}

	@Override
	public List<ExamQuestionVo> queryNotYetDoneExamQuestions(String userNo, String ccpId, String type, int amount) {
		
        List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();

		//查找已经做了试题
		List<String> hadDoneList = new ArrayList<String>();
		//总试题
		List<String> totalExamQuestionList = new ArrayList<String>();
		
		if("1".equals(type)){
			hadDoneList = this.cacheRedis.get(RedisKey.USER_HAD_DONE_COURSE_EXAM_QUESTION_PREFIX_+userNo+"_"+ccpId, List.class);
			totalExamQuestionList = this.cacheRedis.get(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+ccpId, List.class);
		}
		if("2".equals(type)){
			hadDoneList = this.cacheRedis.get(RedisKey.USER_HAD_DONE_CHAPTER_EXAM_QUESTION_PREFIX_+userNo+"_"+ccpId, List.class);
			totalExamQuestionList = this.cacheRedis.get(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+ccpId, List.class);
		}
		if("3".equals(type)){
			hadDoneList = this.cacheRedis.get(RedisKey.USER_HAD_DONE_PERIOD_EXAM_QUESTION_PREFIX_+userNo+"_"+ccpId, List.class);
			totalExamQuestionList = this.cacheRedis.get(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+ccpId, List.class);
		}
		if(null==hadDoneList){
			hadDoneList = new ArrayList<String>();
		}
		if(null==totalExamQuestionList){
			return list;
		}
        Set<String> result = new HashSet<String>();
        result.clear();
        result.addAll(totalExamQuestionList);
        result.removeAll(hadDoneList);
        
        
        if(result.size()>=15){
        	List<String> ids= new ArrayList<String>();
        	ids.addAll(result);
        	//List<String> keys = new ArrayList<String>();
        	for(int n=0;n<15;n++){
        		        		
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(ids.get(n));
        		
        		if(examQuestion!=null){
	        		ExamQuestionVo examQuestionVo = new ExamQuestionVo();
	        		BeanUtils.copyProperties(examQuestion, examQuestionVo);
	        		list.add(examQuestionVo);
        		}else{
        			ExamQuestionViewVo viewVo = new ExamQuestionViewVo();
        			viewVo.setId(ids.get(n));
        			ExamQuestionViewVo examQuestionViewVo = examQuestionService.viewExamQuestion(viewVo);
        			ExamQuestion examQuestionDb = new ExamQuestion();
	        		BeanUtils.copyProperties(examQuestionViewVo, examQuestionDb);
	        		this.cacheRedis.set(RedisKey.EXAM_QUESTION_PREFIX_+ids.get(n), examQuestionDb);
	        		
	        		ExamQuestionVo examQuestionVo = new ExamQuestionVo();
	        		BeanUtils.copyProperties(examQuestionDb, examQuestionVo);
	        		list.add(examQuestionVo);

        		}
        		/**
        		keys.add(RedisKey.EXAM_QUESTION_PREFIX+ids.get(n));
        		Map<String, Object> map = this.cacheRedis.batchQueryByKeys(keys, true);
        		**/
        	}
        }else{
        	for(String id: result){
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(id);

        		if(examQuestion!=null){
	        		ExamQuestionVo examQuestionVo = new ExamQuestionVo();
	        		BeanUtils.copyProperties(examQuestion, examQuestionVo);
	        		list.add(examQuestionVo);
        		}else{
        			ExamQuestionViewVo viewVo = new ExamQuestionViewVo();
        			viewVo.setId(id);
        			ExamQuestionViewVo examQuestionViewVo = examQuestionService.viewExamQuestion(viewVo);
        			ExamQuestion examQuestionDb = new ExamQuestion();
	        		BeanUtils.copyProperties(examQuestionViewVo, examQuestionDb);
	        		this.cacheRedis.set(RedisKey.EXAM_QUESTION_PREFIX_+id, examQuestionDb);
	        		
	        		ExamQuestionVo examQuestionVo = new ExamQuestionVo();
	        		BeanUtils.copyProperties(examQuestionDb, examQuestionVo);
	        		list.add(examQuestionVo);
        		}
        	}
        }
		return list;
	}

	

	@Override
	public List<ExamQuestionVo> queryExamQuestions(String ccpId, String type) {
		List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();
		List<String> cacheList = null;
		if("1".equals(type)){
			cacheList = cacheRedis.get(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+ccpId, List.class);
		}
		if("2".equals(type)){
			cacheList = cacheRedis.get(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+ccpId, List.class);
		}
		if("3".equals(type)){
			cacheList = cacheRedis.get(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+ccpId, List.class);
		}
		if(cacheList!=null){
			for(String id : cacheList){
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(id);
				ExamQuestionVo  examQuestionVo = new ExamQuestionVo();
				if(examQuestion!=null){
					BeanUtils.copyProperties(examQuestion, examQuestionVo);
					list.add(examQuestionVo);
				}
			}
		}
		return list;
	}


	@Override
	public List<ExamQuestionVo> queryErrorExamQuestions(String userNo, String courseId) {
		List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();
		List<String> cacheList = cacheRedis.get(RedisKey.USER_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null){
			for(String id: cacheList){
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(id);

				if(examQuestion!=null){
					ExamQuestionVo  examQuestionVo = new ExamQuestionVo();
					BeanUtils.copyProperties(examQuestion, examQuestionVo);
					list.add(examQuestionVo);
				}
			}
		}
		return list;
	}

	@Override
	public void cacheErrorExamQuestion(String userNo, String courseId,String questionId) {
		List<String> cacheList = cacheRedis.get(RedisKey.USER_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList==null){
			cacheList = new ArrayList<String>();
		}
		if(!cacheList.contains(questionId)){
			cacheList.add(questionId);
		}
		cacheRedis.set(RedisKey.USER_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, cacheList);
	}
	
	@Override
	public void delErrorExamQuestion(String userNo, String courseId, String questionId) {
		List<String> cacheList = cacheRedis.get(RedisKey.USER_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null&&cacheList.contains(questionId)){
			List<String> newCacheList = new ArrayList<String>();
			for(int i=0;i<cacheList.size();i++){
				if(!questionId.equals(cacheList.get(i))){
					newCacheList.add(cacheList.get(i));
				}
			}
			cacheRedis.set(RedisKey.USER_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId,newCacheList);
		}
		
	}
	
	
	@Override
	public List<ExamQuestionVo> queryHighErrorExamQuestions(String userNo, String courseId) {
		List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();
		List<String> cacheList = cacheRedis.get(RedisKey.USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null){
			for(String id: cacheList){
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(id);

				if(examQuestion!=null){
					ExamQuestionVo  examQuestionVo = new ExamQuestionVo();
					BeanUtils.copyProperties(examQuestion, examQuestionVo);
					list.add(examQuestionVo);
				}
			}
		}
		return list;
	}

	@Override
	public void cacheHighErrorExamQuestion(String userNo, String courseId,String questionId) {
		List<String> cacheList = cacheRedis.get(RedisKey.USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList==null){
			cacheList = new ArrayList<String>();
		}
		if(!cacheList.contains(questionId)){
			cacheList.add(questionId);
		}
		cacheRedis.set(RedisKey.USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, cacheList);
	}
	
	@Override
	public void delHighErrorExamQuestion(String userNo, String courseId, String questionId) {
		List<String> cacheList = cacheRedis.get(RedisKey.USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null&&cacheList.contains(questionId)){
			List<String> newCacheList = new ArrayList<String>();
			for(int i=0;i<cacheList.size();i++){
				if(!questionId.equals(cacheList.get(i))){
					newCacheList.add(cacheList.get(i));
				}
			}
			cacheRedis.set(RedisKey.USER_HIGH_ERROR_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId,newCacheList);
		}
		
	}
	
	@Override
	public List<ExamQuestionVo> queryFavoritesExamQuestions(String userNo, String courseId) {
		List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();
		List<String> cacheList = cacheRedis.get(RedisKey.USER_FAVORITES_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null){
			for(String id: cacheList){
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(id);

				if(examQuestion!=null){
					ExamQuestionVo  examQuestionVo = new ExamQuestionVo();
					BeanUtils.copyProperties(examQuestion, examQuestionVo);
					list.add(examQuestionVo);
				}
			}
		}
		return list;
	}

	@Override
	public void cacheFavoritesExamQuestion(String userNo, String courseId,String questionId) {
		Set<String> cacheList = cacheRedis.get(RedisKey.USER_FAVORITES_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, Set.class);
		if(cacheList==null){
			cacheList = new HashSet<String>();
		}
		if(!cacheList.contains(questionId)){
			cacheList.add(questionId);
		}
		cacheRedis.set(RedisKey.USER_FAVORITES_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, cacheList);
	}
	
	@Override
	public void delFavoritesExamQuestion(String userNo, String courseId, String questionId) {
		List<String> cacheList = cacheRedis.get(RedisKey.USER_FAVORITES_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null&&cacheList.contains(questionId)){
			List<String> newCacheList = new ArrayList<String>();
			for(int i=0;i<cacheList.size();i++){
				if(!questionId.equals(cacheList.get(i))){
					newCacheList.add(cacheList.get(i));
				}
			}
			cacheRedis.set(RedisKey.USER_FAVORITES_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId,newCacheList);
		}
	}
	
	@Override
	public List<ExamQuestionVo> queryNoteExamQuestions(String userNo, String courseId) {
		List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();
		List<String> cacheList = cacheRedis.get(RedisKey.USER_NOTE_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null){
			for(String questionId: cacheList){
        		ExamQuestion examQuestion = examQuestionService.viewExamQuestionFromCache(questionId);

				
				String noteContent = cacheRedis.get(RedisKey.USER_NOTE_CONTENT_PREFIX_+userNo+"_"+courseId+"_"+questionId,String.class);

				if(examQuestion!=null){
					ExamQuestionVo  examQuestionVo = new ExamQuestionVo();
					BeanUtils.copyProperties(examQuestion, examQuestionVo);
					if(noteContent!=null){
						examQuestionVo.setNoteContent(noteContent);
					}
					list.add(examQuestionVo);
				}
			}
		}
		return list;
	}
	
	@Override
	public String queryNoteContentByQuestionId(String userNo, String courseId, String questionId) {
		String noteContent = cacheRedis.get(RedisKey.USER_NOTE_CONTENT_PREFIX_+userNo+"_"+courseId+"_"+questionId,String.class);
		return noteContent;
	}

	@Override
	public void cacheNoteExamQuestion(String userNo, String courseId, String questionId,String noteContent) {
		Set<String> cacheList = cacheRedis.get(RedisKey.USER_NOTE_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, Set.class);
		if(cacheList==null){
			cacheList = new HashSet<String>();
		}
		if(!cacheList.contains(questionId)){
			cacheList.add(questionId);
		}
		cacheRedis.set(RedisKey.USER_NOTE_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, cacheList);
		cacheRedis.set(RedisKey.USER_NOTE_CONTENT_PREFIX_+userNo+"_"+courseId+"_"+questionId, noteContent);
	}
	

	@Override
	public void delNoteExamQuestion(String userNo, String courseId, String questionId) {
		List<String> cacheList = cacheRedis.get(RedisKey.USER_NOTE_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId, List.class);
		if(cacheList!=null&&cacheList.contains(questionId)){
			List<String> newCacheList = new ArrayList<String>();
			for(int i=0;i<cacheList.size();i++){
				if(!questionId.equals(cacheList.get(i))){
					newCacheList.add(cacheList.get(i));
				}
			}
			cacheRedis.set(RedisKey.USER_NOTE_EXAM_QUESTION_PREFIX_+userNo+"_"+courseId,newCacheList);
		}
		cacheRedis.delete(RedisKey.USER_NOTE_CONTENT_PREFIX_+userNo+"_"+courseId+"_"+questionId);
	}
	
	@Override
	public void cacheAllExamQuestionCourses() {
		List<String> courseIds  = examQuestionCourseDao.queryExamQuestionCourseCcpIds("1");
		if(courseIds!=null){
			for(String ccpId : courseIds){
				List<String> questionIds = examQuestionCourseDao.queryExamQuestionCourseQuestionIdsByCcpId(ccpId, "1");
				cacheRedis.delete(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+ccpId);
				cacheRedis.set(RedisKey.COURSE_EXAM_QUESTION_PREFIX_+ccpId, questionIds);
			}
		}
		List<String> chapterIds  = examQuestionCourseDao.queryExamQuestionCourseCcpIds("2");
		if(chapterIds!=null){
			for(String ccpId : chapterIds){
				List<String> questionIds = examQuestionCourseDao.queryExamQuestionCourseQuestionIdsByCcpId(ccpId, "2");
				cacheRedis.delete(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+ccpId);
				cacheRedis.set(RedisKey.CHAPTER_EXAM_QUESTION_PREFIX_+ccpId, questionIds);
			}
		}
		List<String> lessonIds  = examQuestionCourseDao.queryExamQuestionCourseCcpIds("3");
		if(lessonIds!=null){
			for(String ccpId : lessonIds){
				List<String> questionIds = examQuestionCourseDao.queryExamQuestionCourseQuestionIdsByCcpId(ccpId, "3");
				cacheRedis.delete(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+ccpId);
				cacheRedis.set(RedisKey.PERIOD_EXAM_QUESTION_PREFIX_+ccpId, questionIds);
			}
		}
	}
	
	@Override
	public Result<Integer> isHaveLessonPermission(FreshCourseVideoQ q) {
		if(StringUtils.isEmpty(q.getLessonId())){
			return Result.error("课时ID不能为空");
		}
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("userNo不能为空");
		}
		FreshLesson freshLesson = freshLessonService.getById(q.getLessonId());
		if(ObjectUtil.isNull(freshLesson)){
			return Result.error("根据课时ID找不到课时");
		}
		
		if(StringUtils.isEmpty(freshLesson.getCourseId())){
			return Result.error("课时对应的courseId为空，数据异常");
		}
	
		FreshCourse freshCourse = freshCourseService.getById(freshLesson.getCourseId());
		if(ObjectUtil.isNull(freshCourse)){
			return Result.error("课程为空，数据异常");
		}
		
		if(freshCourse.getIsPutaway()==0||freshCourse.getValidValue()==0){
			return Result.error("课程已经下架，不能操作");
		}
		
		if(freshCourse.getIsFree()==0&&freshLesson.getIsFree()==0){
			boolean flag = courseUserDao.queryCourseUserIsExist(freshLesson.getCourseId(), q.getUserNo());
			if(!flag){
				return Result.error(ResultEnum.NOT_YET_BUY);
			}
		}
		return Result.success(1); 
	}
	
	 public static void main(String[] args) {

	        Set<String> result = new HashSet<String>();
	        Set<String> set1 = new HashSet<String>() {
	            {
	                add("王者荣耀");
	                add("英雄联盟");
	                add("穿越火线");
	                add("地下城与勇士");
	            }   
	        };
	        /*/
	        Set<String> newSet = new HashSet<String>();
	        for(int i=0;i<5000;i++){
	        	String id = UUID.randomUUID().toString();
	        	set1.add(id);
	        	if(i%5==0){
	        		newSet.add(id);
	        	}
	        }*/
	        Set<String> set2 = new HashSet<String>() {
	            {
	                add("王者荣耀");
	                add("地下城与勇士");
	                add("魔兽世界");
	            }
	        };
	        /*
	        for(int i=0;i<500000;i++){
	        	String id = UUID.randomUUID().toString();
	        	set1.add(id);
	        }
	        set2.addAll(newSet);
	        */

	        long a = System.currentTimeMillis();
	        result.clear();
	        result.addAll(set1);
	        result.retainAll(set2);
	        System.out.println("交集：" + result);
	        System.out.println("耗时：" +(System.currentTimeMillis()-a));

	        long b = System.currentTimeMillis();
	        result.clear();
	        result.addAll(set1);
	        result.removeAll(set2);
	        System.out.println("差集：" + result);
	        System.out.println("耗时2：" +(System.currentTimeMillis()-b));

	        long c = System.currentTimeMillis();
	        result.clear();
	        result.addAll(set1);
	        result.addAll(set2);
	        System.out.println("并集：" + result);
	        System.out.println("耗时3：" +(System.currentTimeMillis()-c));


	    }

	









	

	

	

		
	
	
}
