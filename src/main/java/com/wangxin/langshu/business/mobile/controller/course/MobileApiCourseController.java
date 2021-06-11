package com.wangxin.langshu.business.mobile.controller.course;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseVideoService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonMobileViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi/course")
public class MobileApiCourseController extends BaseController {
	public static final String TOKEN = "token";

	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
	
	@Autowired
	private IPcMobileFreshCourseService pcMobileFreshCourseService;
	
	@Autowired
	private IExamQuestionCourseService examQuestionCourseService;
	
	@Autowired
	private IPcWebFreshCourseVideoService pcWebFreshCourseVideoService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	/**
	 * 课程用户关系列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课程用户关系列表分页接口", notes = "课程用户关系列表分页接口")
	@RequestMapping(value = "/listMyCourse", method = RequestMethod.POST)
	public Result<Page<CourseUserVo>> list(@RequestBody CourseUserQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setBindUserNo(q.getUserNo());
		return Result.success(pcWebCourseUserService.queryCourseUsers(q));
	}
	
	/**
	 * 根据ID查找课程详情(已登录)
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "根据ID查找课程详情(已登录)", notes = "根据ID查找课程详情(已登录)")
	@RequestMapping(value = "/findCourseDetailHadLogin", method = RequestMethod.POST)
	public Result<FreshCourseMobileView> findCourseDetailHadLogin(@RequestBody FreshCourseMobileView freshCourseMobileView) {
		if(StringUtils.isEmpty(freshCourseMobileView.getUserNo())){
			return Result.error("缺少userNo，未登录");
		}
		
		String id = freshCourseMobileView.getId();
		if(StringUtils.isEmpty(id)) {
			return Result.error("缺少ID");
		}
		
		Result<FreshCourseMobileView> cResult = pcMobileFreshCourseService.viewMobileCourse(id,freshCourseMobileView.getUserNo());
		
		List<String> courseIds = new ArrayList<String>();
		courseIds.add(id);
		List<String> chapterIds = new ArrayList<String>();
		List<String> lessonIds = new ArrayList<String>();

		FreshCourseMobileView courseViewDTO = cResult.getData();
		List<FreshChapterMobileViewVo> chapterDTOs = courseViewDTO.getChapterList();
		if(CollectionUtils.isNotEmpty(chapterDTOs)){
			for(int i=0;i<chapterDTOs.size();i++){
				chapterIds.add(chapterDTOs.get(i).getId());
				List<FreshLessonMobileViewVo>  lessonDTOs = chapterDTOs.get(i).getLessonList();
				if(CollectionUtils.isNotEmpty(lessonDTOs)){
					for(int j=0;j<lessonDTOs.size();j++){
						lessonIds.add(lessonDTOs.get(j).getId());
					}
				}
			}
		}
		List<Map<String, Object>> courseExamQuestionIds = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> chapterExamQuestionIds = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> lessonExamQuestionIds = new ArrayList<Map<String, Object>>();
		if(courseIds.size()!=0){
			courseExamQuestionIds = examQuestionCourseService.countExamQuestionCourses(courseIds, "1", "1");
		}
		if(chapterIds.size()!=0){
			chapterExamQuestionIds = examQuestionCourseService.countExamQuestionCourses(chapterIds, "4", "1");
		}
		if(lessonIds.size()!=0){
			lessonExamQuestionIds = examQuestionCourseService.countExamQuestionCourses(lessonIds, "5", "1");
		}
		
		Integer courseExamQuestionCourseCount = 0;
		if(courseExamQuestionIds.size()!=0&&null!=courseExamQuestionIds.get(0)&&null!=courseExamQuestionIds.get(0).get("count")){
			courseExamQuestionCourseCount = Integer.parseInt(courseExamQuestionIds.get(0).get("count").toString());
		}
		courseViewDTO.setExamQuestionCourseCount(courseExamQuestionCourseCount);
		
		
		Map<String, String> chapterExamQuestionIdsMap = new HashMap<String, String>();
		for(int i=0;i<chapterExamQuestionIds.size();i++){
			if(null!=chapterExamQuestionIds.get(i).get("id")){
				String dataId = (String)(chapterExamQuestionIds.get(i).get("id"));
				chapterExamQuestionIdsMap.put(dataId, chapterExamQuestionIds.get(i).get("count")+"");
			}
		}
		Map<String, String> lessonExamQuestionIdsMap =  new HashMap<String, String>();
		for(int i=0;i<lessonExamQuestionIds.size();i++){
			if(null!=lessonExamQuestionIds.get(i).get("id")){
				String dataId = (String)(lessonExamQuestionIds.get(i).get("id"));
				lessonExamQuestionIdsMap.put(dataId, lessonExamQuestionIds.get(i).get("count")+"");
			}
		}
		
		//用户正确试题数量(针对课时刷题统计)
		int userRightCount =0;
		//用户错误试题数量(针对课时刷题统计)
		int userErrorCount =0;
		
		
		if(CollectionUtils.isNotEmpty(chapterDTOs)){
			for(int i=0;i<chapterDTOs.size();i++){
				String chapterId = chapterDTOs.get(i).getId();
				String chapterCount = chapterExamQuestionIdsMap.get(chapterId);
				chapterDTOs.get(i).setExamQuestionChapterCount(Integer.parseInt(chapterCount==null?"0":chapterCount));
				List<FreshLessonMobileViewVo>  lessonDTOs = chapterDTOs.get(i).getLessonList();
				if(CollectionUtils.isNotEmpty(lessonDTOs)){
					for(int j=0;j<lessonDTOs.size();j++){
						String lessonId = lessonDTOs.get(j).getId();
						String lessonCount = lessonExamQuestionIdsMap.get(lessonId);
						lessonDTOs.get(j).setExamQuestionLessonCount(Integer.parseInt(lessonCount==null?"0":lessonCount));
						
						String keyPrefix = "DO_EXERCISES_"+freshCourseMobileView.getUserNo()+"_";
						int continueStartIndex =0;
						Object continueStartIndexObject =  redisTemplate.opsForValue().get(keyPrefix+"continueStartIndex_"+lessonId);
						if(ObjectUtil.isNotNull(continueStartIndexObject)) {
							try {
								continueStartIndex = Integer.parseInt((String)continueStartIndexObject);
							}catch (Exception e) {
								continueStartIndex =0;
							}
						}
						lessonDTOs.get(j).setExamQuestionLessonCountHadFinished(continueStartIndex);
						
						/*统计正确、错误题目数开始*/
						int answerRight =0;
						Object answerRightObject =  redisTemplate.opsForValue().get(keyPrefix+"answer_right_"+lessonId);
						if(ObjectUtil.isNotNull(answerRightObject)) {
							try {
								JSONArray ar = JSONUtil.parseArray(answerRightObject);
								answerRight = ar.size();
							}catch (Exception e) {
								answerRight =0;
							}
						}
						userRightCount = userRightCount + answerRight;
						int answerError =0;
						Object answerErrorObject =  redisTemplate.opsForValue().get(keyPrefix+"answer_error_"+lessonId);
						if(ObjectUtil.isNotNull(answerErrorObject)) {
							try {
								JSONArray ae = JSONUtil.parseArray(answerErrorObject);
								answerError = ae.size();
							}catch (Exception e) {
								answerError =0;
							}
						}
						userErrorCount = userErrorCount + answerError;
						
						
						/*统计正确、错误题目数结束*/
					}
				}
			}
		}
		/*设置正确、错误题目数开始*/
		if(userErrorCount==0&&userRightCount==0) {
			userErrorCount = 0;
			userRightCount = 100;
		}
		courseViewDTO.setUserRightCount(userRightCount);
		courseViewDTO.setUserErrorCount(userErrorCount);
		/*设置正确、错误题目数结束*/
		
		//return JSONUtil.toJsonStr(cResult.getData());
		return cResult;
	}
	
	/**
	 * 根据课时Id查询课程视频
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "根据课时Id查询课程视频", notes = "根据课时Id查询课程视频")
	@RequestMapping(value = "/queryCourseVideoByLessonId", method = RequestMethod.POST)
	public Result<Page<FreshCourseVideoVo>> queryCourseVideoByLessonId(@RequestBody FreshCourseVideoQ q){
		return pcWebFreshCourseVideoService.queryCourseVideoByLessonId(q);
	}
	
	

}

