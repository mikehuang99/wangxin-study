package com.wangxin.langshu.business.mobile.controller.exam;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.mobile.controller.vo.DoExercisesVo;
import com.wangxin.langshu.business.mobile.controller.vo.ExamQuestionCourseQuery;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@RestController
@RequestMapping(value = "/mobileApi/exam")
public class MobileApiExamController extends BaseController {
	public static final String TOKEN = "token";
	
	@Autowired
	private IExamQuestionCourseService examQuestionCourseService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@RequestMapping(value = "/queryNotYetDoneExamQuestions", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>> queryNotYetDoneExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryNotYetDoneExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(), examQuestionCourseQuery.getType(), 15);
		//String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		Page<ExamQuestionVo> page = new Page<ExamQuestionVo>();
		page.setList(examQuestionVos);
		return Result.success(page);
	}
	
	@RequestMapping(value = "/queryExamQuestions", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>>  queryExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		if("3".equals(examQuestionCourseQuery.getType())) {
			FreshCourseVideoQ q = new FreshCourseVideoQ();
			q.setLessonId(examQuestionCourseQuery.getCcpId());
			q.setUserNo(examQuestionCourseQuery.getUserNo());
			Result<Integer> r = examQuestionCourseService.isHaveLessonPermission(q);
			if((int)r.getCode()!=(int)ResultEnum.SUCCESS.getCode()) {
				Result rr = new Result();
				return rr.error(ResultEnum.NOT_YET_BUY);
			}
			
		}
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryExamQuestions(examQuestionCourseQuery.getCcpId(), examQuestionCourseQuery.getType());
		//String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		Page<ExamQuestionVo> page = new Page<ExamQuestionVo>();
		page.setList(examQuestionVos);
		return Result.success(page);
	}
	
	@RequestMapping(value = "/queryErrorExamQuestions", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>> queryErrorExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryErrorExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		//String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		Page<ExamQuestionVo> page = new Page<ExamQuestionVo>();
		page.setList(examQuestionVos);
		return Result.success(page);
	}
	
	@RequestMapping(value = "/cacheErrorExamQuestion", method = RequestMethod.POST)
	public Result<Integer> cacheErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.cacheErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);
	}
	
	
	@RequestMapping(value = "/delErrorExamQuestion", method = RequestMethod.POST)
	public Result<Integer> delErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);
	}
	
	
	@RequestMapping(value = "/queryHighErrorExamQuestions", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>> queryHighErrorExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryHighErrorExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		//String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		Page<ExamQuestionVo> page = new Page<ExamQuestionVo>();
		page.setList(examQuestionVos);
		return Result.success(page);
	}
	
	@RequestMapping(value = "/cacheHighErrorExamQuestion", method = RequestMethod.POST)
	public Result<Integer> cacheHighErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.cacheHighErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);
	}
	
	
	@RequestMapping(value = "/delHighErrorExamQuestion", method = RequestMethod.POST)
	public Result<Integer> delHighErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delHighErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);
	}
	
	@RequestMapping(value = "/queryFavoritesExamQuestions", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>> queryFavoritesExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryFavoritesExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		//String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		Page<ExamQuestionVo> page = new Page<ExamQuestionVo>();
		page.setList(examQuestionVos);
		return Result.success(page);	
	}
	
	@RequestMapping(value = "/cacheFavoritesExamQuestion", method = RequestMethod.POST)
	public Result<Integer> cacheFavoritesExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.cacheFavoritesExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);

	}
	
	@RequestMapping(value = "/delFavoritesExamQuestion", method = RequestMethod.POST)
	public Result<Integer>  delFavoritesExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delFavoritesExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);


	}
	
	
	@RequestMapping(value = "/queryNoteExamQuestions", method = RequestMethod.POST)
	public Result<Page<ExamQuestionVo>>  queryNoteExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryNoteExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		//String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		Page<ExamQuestionVo> page = new Page<ExamQuestionVo>();
		page.setList(examQuestionVos);
		return Result.success(page);	
	}
	
	
	@RequestMapping(value = "/queryNoteContentByQuestionId", method = RequestMethod.POST)
	public Result<String> queryNoteContentByQuestionId(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		String  noteContent = examQuestionCourseService.queryNoteContentByQuestionId(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\",\"noteContent\":\""+noteContent+"\"}";
		return Result.success(noteContent);

	}
	
	@RequestMapping(value = "/cacheNoteExamQuestion", method = RequestMethod.POST)
	public Result<Integer> cacheNoteExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		if(examQuestionCourseQuery.getNoteContent()!=null&&!"".equals(examQuestionCourseQuery.getNoteContent().trim())){
			examQuestionCourseService.cacheNoteExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId(),examQuestionCourseQuery.getNoteContent());
		}
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);
	}
	
	
	

	
	@RequestMapping(value = "/delNoteExamQuestion", method = RequestMethod.POST)
	public Result<Integer> delNoteExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delNoteExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		//return "{\"result\":null,\"status\":200,\"message\":\"??????\"}";
		return Result.success(1);

	}
	
	/**
	 * ??????????????????????????????
	 * ?????????????????????

	 * ?????????key
	 * continueStartIndex_ccpId ??????
		answer_ids_ccpId  ??????ID??????
		answer_ccpId_questionId  ?????????????????????,questionId????????????ID?????????????????????answer_ccpId_*??????????????????
		answer_right_ccpId
		answer_error_ccpId
		
	* ccpId??????????????????ID????????????????????????
	 * ???????????????????????????ID???
	 * ????????????_ERROR_COLLECTION_XXX?????????ID???XXX??????courseId???
	 * ????????????_FAVORITES_COLLECTION_XXX???????????????ID???XXX???courseId???
	 * ????????????_HIGH_ERROR_COLLECTION_XXX??????????????????ID???XXX???courseId???
	 * ????????????_NOTE_COLLECTION_XXX???????????????ID???XXX???courseId???
	 * @param doExercisesVo
	 * @return
	 */
	@RequestMapping(value = "/cacheDoExercisesData", method = RequestMethod.POST)
	public Result<Integer> cacheDoExercisesData(@RequestBody DoExercisesVo doExercisesVo){
		String keyPrefix = "DO_EXERCISES_"+doExercisesVo.getUserNo()+"_";
		if(StringUtils.isNotEmpty(doExercisesVo.getKey())&&StringUtils.isNotEmpty(doExercisesVo.getValue())) {
			if(doExercisesVo.getKey().startsWith("continueStartIndex_")||
					doExercisesVo.getKey().startsWith("answer_ids_")||
					doExercisesVo.getKey().startsWith("answer_")||
					doExercisesVo.getKey().startsWith("answer_right_")||
					doExercisesVo.getKey().startsWith("answer_error_")) {
			redisTemplate.opsForValue().set(keyPrefix+doExercisesVo.getKey(), doExercisesVo.getValue());
			}
		}
		
		return Result.success(1);
	}
	
	/**
	 * ??????????????????????????????
	 * ?????????????????????

	 * ?????????key
	 * continueStartIndex_ccpId ??????
		answer_ids_ccpId  ??????ID??????
		answer_ccpId_questionId  ?????????????????????,questionId????????????ID?????????????????????answer_ccpId_*??????????????????
		answer_right_ccpId
		answer_error_ccpId
		
	* ccpId??????????????????ID????????????????????????
	 * ???????????????????????????ID???
	 * ????????????_ERROR_COLLECTION_XXX?????????ID???XXX??????courseId???
	 * ????????????_FAVORITES_COLLECTION_XXX???????????????ID???XXX???courseId???
	 * ????????????_HIGH_ERROR_COLLECTION_XXX??????????????????ID???XXX???courseId???
	 * ????????????_NOTE_COLLECTION_XXX???????????????ID???XXX???courseId???
	 * @param doExercisesVo
	 * @return
	 */
	@RequestMapping(value = "/deleteDoExercisesData", method = RequestMethod.POST)
	public Result<Integer> deleteDoExercisesData(@RequestBody DoExercisesVo doExercisesVo){
		String keyPrefix = "DO_EXERCISES_"+doExercisesVo.getUserNo()+"_";
		if(StringUtils.isNotEmpty(doExercisesVo.getKey())) {
			if(doExercisesVo.getKey().startsWith("continueStartIndex_")||
					doExercisesVo.getKey().startsWith("answer_ids_")||
					doExercisesVo.getKey().startsWith("answer_")||
					doExercisesVo.getKey().startsWith("answer_right_")||
					doExercisesVo.getKey().startsWith("answer_error_")) {
			redisTemplate.delete(keyPrefix+doExercisesVo.getKey());
			}
		}
		
		return Result.success(1);
	}
	/**
	 * ??????????????????????????????
	 * @param doExercisesVo
	 * @return
	 */
	@RequestMapping(value = "/findDoExercisesDataByCcpId", method = RequestMethod.POST)
	public Result<DoExercisesVo> findDoExercisesDataByCcpId(@RequestBody DoExercisesVo doExercisesVo){
		if(StringUtils.isEmpty(doExercisesVo.getCcpId())) {
			Result.error("key????????????");
		}
		
		HashMap<String, String> hashMap = new HashMap<>();
		
		String keyPrefix = "DO_EXERCISES_"+doExercisesVo.getUserNo()+"_";
		
		/*
		continueStartIndex_ccpId ??????
		answer_ids_ccpId  ??????ID??????
		answer_ccpId_questionId  ?????????????????????,questionId????????????ID?????????????????????answer_ccpId_*??????????????????
		answer_right_ccpId
		answer_error_ccpId
		 */
		String continueStartIndexKey = "continueStartIndex_"+doExercisesVo.getCcpId();
		String continueStartIndexValue = (String)redisTemplate.opsForValue().get(keyPrefix+continueStartIndexKey);
		hashMap.put(continueStartIndexKey, continueStartIndexValue);
		
		String answerIdsKey = "answer_ids_"+doExercisesVo.getCcpId();
		String answerIdsValue = (String)redisTemplate.opsForValue().get(keyPrefix+answerIdsKey);
		hashMap.put(answerIdsKey, answerIdsValue);
		
		String answerRightKey = "answer_right_"+doExercisesVo.getCcpId();
		String answerRightValue = (String)redisTemplate.opsForValue().get(keyPrefix+answerRightKey);
		hashMap.put(answerRightKey, answerRightValue);
		
		String answerErrorKey = "answer_error_"+doExercisesVo.getCcpId();
		String answerErrorValue = (String)redisTemplate.opsForValue().get(keyPrefix+answerErrorKey);
		hashMap.put(answerErrorKey, answerErrorValue);
		
		String answerQuestionKey = keyPrefix+"answer_"+doExercisesVo.getCcpId()+"_*";
		Set<Object> set = redisTemplate.keys(answerQuestionKey);
		if(ObjectUtil.isNotNull(set)) {
			for(Object s: set) {
				if(ObjectUtil.isNotNull(s)) {
					String aqk = (String)s;
					String answerQValue = (String)redisTemplate.opsForValue().get(aqk);
					String original = aqk.replace(keyPrefix, "");
					hashMap.put(original, answerQValue);
				}
			}
		}
		
		doExercisesVo.setDataMap(hashMap);
		doExercisesVo.setDataList(JSONUtil.toJsonStr(hashMap));
		//Set<Object> set = redisTemplate.keys(key+"_"+"*");
		

		return Result.success(doExercisesVo);
	}

}

