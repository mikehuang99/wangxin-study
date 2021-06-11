package com.wangxin.langshu.business.mobile.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossAdvertBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseSubjectBiz;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseBiz;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseVideoBiz;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiTopicBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshCourseService;
import com.wangxin.langshu.business.edu.iservice.course.pcmobile.fresh.IPcMobileFreshTopicService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseVideoBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseVideoService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.IMobileMiddleNavigationService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.INewsService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IApiUserInfoBiz;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudentBiz;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.CourseInfoPageBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseInfoPageDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectPageRESQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentVO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserLoginPasswordBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentViewBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.auth.AuthStudentDTO;
import com.wangxin.langshu.business.mobile.controller.vo.ExamQuestionCourseQuery;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.OperatePlatEnum;
import com.wangxin.langshu.business.util.pretool.IpUtil;
import com.wangxin.langshu.business.util.pretool.UserNoUtil;
import com.xiaoleilu.hutool.json.JSONUtil;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi")
public class MobileApiController extends BaseController {
	public static final String TOKEN = "token";
	@Autowired
	private IPcBossAdvertBiz pcBossAdvertBiz;
	
	@Autowired
	private INewsService newsService;
	
	@Autowired
	private IPcBossCourseSubjectBiz pcBossCourseSubjectBiz;
	
	@Autowired
	private IApiTopicBiz apiTopicBiz;
	
	
	@Autowired
	private IApiCourseBiz apiCourseBiz;
	

	
	@Autowired
	private IApiUserInfoBiz apiUserInfoBiz;
	
	@Autowired
	private IPcWebCourseBiz pcWebCourseBiz;
	
	@Autowired
	private IApiCourseVideoBiz apiCourseVideoBiz;
	
	@Autowired
	private IPcWebCourseVideoBiz pcWebCourseVideoBiz;
	
	@Autowired
	private IExamQuestionCourseService examQuestionCourseService;
	
	@Autowired
	private IMobileMiddleNavigationService mobileMiddleNavigationService;
	
	@Autowired
	private IPcWebFreshLessonService pcWebFreshLessonService;
	
	@Autowired
	private IPcWebFreshCourseVideoService pcWebFreshCourseVideoService;
	
	
	@Autowired
	private IPcMobileFreshTopicService pcMobileFreshTopicService;
	
	@Autowired
	private IPcMobileFreshCourseService pcMobileFreshCourseService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;

	@Autowired
	private IPcWebStudentBiz pcWebStudentBiz;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	
	//@RequestMapping(value = "/login", method = RequestMethod.POST)
	//public String listForPage(@RequestBody User user) {
	//	return "{\"result\":null,\"status\":true,\"msg\":\"登录成功\"}";
	//}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPassword(@RequestBody UserLoginPasswordBO userLoginPasswordBO) {
		userLoginPasswordBO.setLoginPlatform(OperatePlatEnum.MOBILE.getCode());
		userLoginPasswordBO.setIp(IpUtil.getIpAddr(httpServletRequest));
		Result<UserLoginDTO> userLogin = apiUserInfoBiz.loginPassword(userLoginPasswordBO);
		if(userLogin.getCode() == 200){
			return "{\"result\":null,\"status\":true,\"msg\":\"登录成功\""+",\"realName\""+":\""+userLogin.getData().getRealName()+"\""+
					",\"token\""+":\""+userLogin.getData().getToken()+"\""+"}";
		}else{
			return "{\"result\":null,\"status\":false,\"msg\":\""+userLogin.getMsg()+"\"}";
		}
	}
	
	@RequestMapping(value = "/carousel", method = RequestMethod.GET)
	public Result<Page<AdvertPageRESQ>> carousel() {
		AdvertPageREQ advertPageREQ = new AdvertPageREQ();
		advertPageREQ.setPlatShow(2);
		advertPageREQ.setValidValue(1);
		Result<Page<AdvertPageRESQ>> result = pcBossAdvertBiz.list(advertPageREQ);
		//List<AdvertPageRESQ> list = result.getData().getList();
		//return "{\"result\":null,\"status\":true,\"carousel\":[\"img/banner_test.jpg\",\"img/round01.jpg\",\"img/round01.jpg\",\"img/round01.jpg\"]}";
		return result;
	}
	
	
	
	@RequestMapping(value = "/newslist", method = RequestMethod.GET)
	public Result<Page<NewsVo>> newsList() {
		NewsQ newsQ = new NewsQ();
		newsQ.setPlatShow("2");
		newsQ.setValidValue("1");
		return Result.success(newsService.queryEntities(newsQ));
		//List<AdvertPageRESQ> list = result.getData().getList();
		//return "{\"result\":null,\"status\":true,\"carousel\":[\"img/banner_test.jpg\",\"img/round01.jpg\",\"img/round01.jpg\",\"img/round01.jpg\"]}";
		//return result;
	}
	
	
	
	@RequestMapping(value = "/newsView", method = RequestMethod.GET)
	public Result<NewsViewVo> newsView(@RequestParam(required=true) String id) {
		NewsViewVo newsViewVo = new NewsViewVo();
		newsViewVo.setId(id);
		return newsService.viewEntity(newsViewVo);
	}
	
	
	
	@RequestMapping(value = "/indexClass", method = RequestMethod.GET)
	public Result<Page<MobileMiddleNavigationVo>> indexClass() {
		MobileMiddleNavigationQ mobileMiddleNavigationQ = new MobileMiddleNavigationQ();
		mobileMiddleNavigationQ.setValidValue("1");
		Page<MobileMiddleNavigationVo> page = mobileMiddleNavigationService.queryMobileMiddleNavigations(mobileMiddleNavigationQ);
		return Result.success(page);
		
		/*
		CourseSubjectPageREQ courseSubjectPageREQ = new CourseSubjectPageREQ();
		courseSubjectPageREQ.setFloor(1);
		return pcBossCourseSubjectBiz.listForPage(courseSubjectPageREQ);*/
	}
	
	
	
	@RequestMapping(value = "/studentView", method = RequestMethod.GET)
	public Result<StudentVO> studentView(@RequestParam(required=true) String mobile) {
		StudentVO studentVO = apiUserInfoBiz.findStudentByMobile(mobile);
		return Result.success(studentVO);
	}
	
	
	
	
	@RequestMapping(value = "/subject", method = RequestMethod.GET)
	public String subject(@RequestParam(required=false) String id) {
		long startTime = System.currentTimeMillis();
		CourseSubjectPageREQ courseSubjectPageREQ = new CourseSubjectPageREQ();
		courseSubjectPageREQ.setFloor(1);
		List<CourseSubjectPageRESQ> subjectData = pcBossCourseSubjectBiz.listForPage(courseSubjectPageREQ).getData().getList();
		
		List<Map<String,String>> gradeOneList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> gradeTwoList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> gradeThreeList = new ArrayList<Map<String,String>>();
		
		if(!StringUtils.isNotEmpty(id)){
			if(subjectData.size()>0){
				id= subjectData.get(0).getId() + "";
			}
		}

	      for(int i=0; i< subjectData.size(); i++){
	    	  Map<String,String> gradeOneMap = new HashMap<String,String>();
	    	  gradeOneMap.put("gradeId",subjectData.get(i).getId()+"");
	    	  gradeOneMap.put("class_name",subjectData.get(i).getSubjectName()+"");
	    	  gradeOneList.add(gradeOneMap);
	    	  
	    	  if(StringUtils.isNotEmpty(id)&&id.equals(subjectData.get(i).getId()+"")){
	    		  
	    		  for(int k=0;k < subjectData.get(i).getChildren().size(); k++){
		      		  Map<String,String> gradeTwoMap = new HashMap<String,String>();

		        	  gradeTwoMap.put("gradeTwoId",subjectData.get(i).getChildren().get(k).getId()+"");
		        	  gradeTwoMap.put("twoClass_name",subjectData.get(i).getChildren().get(k).getSubjectName());
		        	  gradeTwoMap.put("pid",subjectData.get(i).getChildren().get(k).getParentId()+"");
		        	  gradeTwoList.add(gradeTwoMap);

		              for(int n=0;n < subjectData.get(i).getChildren().get(k).getChildren().size(); n++){
		          		  Map<String,String> gradeThreeMap = new HashMap<String,String>();
		            	  gradeThreeMap.put("gradeThreeId",subjectData.get(i).getChildren().get(k).getChildren().get(n).getId()+"");
		            	  gradeThreeMap.put("threeClass_name",subjectData.get(i).getChildren().get(k).getChildren().get(n).getSubjectName()+"");
		            	  gradeThreeMap.put("pid",subjectData.get(i).getChildren().get(k).getChildren().get(n).getParentId()+"");
			        	  gradeThreeList.add(gradeThreeMap);

		              }
		          }
	    	  }
	      }
	      
	      Map<String,List<Map<String,String>>> subject = new HashMap<>();
	      subject.put("gradeOne", gradeOneList);
	      subject.put("gradeTwo", gradeTwoList);
	      subject.put("gradeThree", gradeThreeList);
	      System.out.println("/api/ategory耗时："+(System.currentTimeMillis()-startTime));
	      
		return JSONUtil.toJsonStr(subject);
		//return pcBossCourseSubjectBiz.listForPage(courseSubjectPageREQ);
	}


	
	/**
	 * 专区课程分页列表接口
	 * 
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "专区课程列表接口", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/topic", method = RequestMethod.GET)
	public Result<Page<FreshTopicVo>> topic() {
		FreshTopicQ freshTopicQ = new FreshTopicQ();
		freshTopicQ.setTopicLocation("2");////设置移动端位置代码
		return pcMobileFreshTopicService.queryTopics(freshTopicQ);
		
	/*
		TopicBO topicBO = new TopicBO();
		topicBO.setTopicLocation(2);
		return apiTopicBiz.listForPage(topicBO);*/
		
	}
	
	

	/**
	 * 课程信息列表接口
	 * 
	 * @param courseInfoPageBO
	 * @return
	 */
	@ApiOperation(value = "课程列表接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/courselist", method = RequestMethod.GET)
	public String courselist(
			@RequestParam(required=false) Integer offset,
			@RequestParam(required=false) Integer limit,
			@RequestParam(required=false) Integer type,
			@RequestParam(required=false) String sort,
			@RequestParam(required=false) String subjectId,
			@RequestParam(required=false) String selectScreenStr,
			@RequestParam(required=false) String name) {
		CourseInfoPageBO courseInfoPageBO = new CourseInfoPageBO();
		courseInfoPageBO.setPageCurrent(offset/15+1);
		courseInfoPageBO.setPageSize(limit);
		
		if(type==2){
			courseInfoPageBO.setSubjectId2(subjectId);
		}
		if(type==3){
			courseInfoPageBO.setSubjectId3(subjectId);
		}
		
		if(StringUtils.isNotEmpty(selectScreenStr)){
			String [] selectScreenStrArray = selectScreenStr.split("/");
			List<String> selectScreenStrList = Arrays.asList(selectScreenStrArray);
			boolean isContainAllFeeType = false;//是否包含免费和付费
			if(selectScreenStrList.contains("is_free=0")&&selectScreenStrList.contains("is_free=1")){
				isContainAllFeeType = true;
			}
			if(!isContainAllFeeType){
				for(int i=0;i<selectScreenStrArray.length;i++){
					if("is_free=0".equals(selectScreenStrArray[i])){
						courseInfoPageBO.setIsFree(0);
					}
					if("is_free=1".equals(selectScreenStrArray[i])){
						courseInfoPageBO.setIsFree(1);
					}
				}
			}
		}
		courseInfoPageBO.setSortType(sort);
		
		courseInfoPageBO.setCourseName(name);
		
		Result<Page<CourseInfoPageDTO>> page = apiCourseBiz.list(courseInfoPageBO);
		List<CourseInfoPageDTO> list = page.getData().getList();
		/*
		for(CourseInfoPageDTO courseInfoPageDTO : list){
			courseInfoPageDTO.setIdCopy(courseInfoPageDTO.getId()+"");
		}*/
		
		return "{\"data\":"+JSONUtil.toJsonStr(page.getData().getList())+",\"offset\":\""+offset+"\"}";
	}

	/**
	 * 我的课程课程信息列表接口
	 * 
	 * @param courseInfoPageBO
	 * @return
	 */
	@ApiOperation(value = "我的课程列表接口", notes = "根据条件分页列出我的课程课程信息")
	@RequestMapping(value = "/myCourseList", method = RequestMethod.GET)
	public String myCourseList(
			@RequestParam(required=false) Integer offset,
			@RequestParam(required=false) Integer limit,
			@RequestParam(required=false) String name
			) {
		
		String userNo = UserNoUtil.getUserNo(httpServletRequest);
		
		if(StringUtils.isEmpty(userNo)) {
			return "{\"data\":"+JSONUtil.toJsonStr(new ArrayList())+",\"offset\":\""+0+"\"}";
		}
		CourseUserQ courseUserQ = new CourseUserQ();
		courseUserQ.setBindUserNo(userNo);
		courseUserQ.setCourseName(name);
		courseUserQ.setPageCurrent(offset/15+1);
		courseUserQ.setPageSize(limit);
		
		Page<CourseUserVo> page = pcWebCourseUserService.queryCourseUsers(courseUserQ);
		List<CourseUserVo> courseUserVos ;
		if(CollectionUtils.isEmpty(page.getList())) {
			courseUserVos = new ArrayList<>();
		}else {
			courseUserVos = page.getList();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for(CourseUserVo courseUserVo : courseUserVos) {
				Date validTerm = courseUserVo.getValidTerm();
				courseUserVo.setValidTermStr(simpleDateFormat.format(validTerm));
			}
		}
		
		return "{\"data\":"+JSONUtil.toJsonStr(courseUserVos)+",\"offset\":\""+offset+"\"}";

		/*
		CourseInfoPageBO courseInfoPageBO = new CourseInfoPageBO();
		courseInfoPageBO.setPageCurrent(offset/15+1);
		courseInfoPageBO.setPageSize(limit);
		
		if(type==2){
			courseInfoPageBO.setSubjectId2(subjectId);
		}
		if(type==3){
			courseInfoPageBO.setSubjectId3(subjectId);
		}
		
		if(StringUtils.isNotEmpty(selectScreenStr)){
			String [] selectScreenStrArray = selectScreenStr.split("/");
			List<String> selectScreenStrList = Arrays.asList(selectScreenStrArray);
			boolean isContainAllFeeType = false;//是否包含免费和付费
			if(selectScreenStrList.contains("is_free=0")&&selectScreenStrList.contains("is_free=1")){
				isContainAllFeeType = true;
			}
			if(!isContainAllFeeType){
				for(int i=0;i<selectScreenStrArray.length;i++){
					if("is_free=0".equals(selectScreenStrArray[i])){
						courseInfoPageBO.setIsFree(0);
					}
					if("is_free=1".equals(selectScreenStrArray[i])){
						courseInfoPageBO.setIsFree(1);
					}
				}
			}
		}
		courseInfoPageBO.setSortType(sort);
		
		courseInfoPageBO.setCourseName(name);
		
		Result<Page<CourseInfoPageDTO>> page = apiCourseBiz.list(courseInfoPageBO);
		List<CourseInfoPageDTO> list = page.getData().getList();

		
		return "{\"data\":"+JSONUtil.toJsonStr(page.getData().getList())+",\"offset\":\""+offset+"\"}";
		*/
	}
	
	
	@RequestMapping(value = "/getSearchCourse", method = RequestMethod.GET)
	public String getSearchCourse(@RequestParam(required=false) Integer offset,
			@RequestParam(required=false) Integer limit,
			@RequestParam(required=false) String name) {
		CourseInfoPageBO courseInfoPageBO = new CourseInfoPageBO();
		courseInfoPageBO.setPageCurrent(offset/15+1);
		courseInfoPageBO.setPageSize(limit);
		
		courseInfoPageBO.setCourseName(name);
		
		Result<Page<CourseInfoPageDTO>> page = apiCourseBiz.list(courseInfoPageBO);
		return "{\"data\":"+JSONUtil.toJsonStr(page.getData().getList())+",\"offset\":\""+offset+"\"}";
	}
	
	@RequestMapping(value = "/getSearchMyCourse", method = RequestMethod.GET)
	public String getSearchMyCourse(@RequestParam(required=false) Integer offset,
			@RequestParam(required=false) Integer limit,
			@RequestParam(required=false) String name) {
		String userNo = UserNoUtil.getUserNo(httpServletRequest);
		
		if(StringUtils.isEmpty(userNo)) {
			return "{\"data\":"+JSONUtil.toJsonStr(new ArrayList())+",\"offset\":\""+0+"\"}";
		}
		CourseUserQ courseUserQ = new CourseUserQ();
		courseUserQ.setBindUserNo(userNo);
		courseUserQ.setCourseName(name);
		courseUserQ.setPageCurrent(offset/15+1);
		courseUserQ.setPageSize(limit);
		
		Page<CourseUserVo> page = pcWebCourseUserService.queryCourseUsers(courseUserQ);
		return "{\"data\":"+JSONUtil.toJsonStr(page.getList())+",\"offset\":\""+offset+"\"}";
	}
	/*
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex(@RequestParam(required=false) String timeStamp) {
		//return "{\"allparentinfo\":[{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"中小学\",\"imgurl\":\"img/icon_middle.png\"},\"course\":{\"classList\":[{\"classname\":\"精品钢琴课\",\"imgurl\":\"img/cls_mid1.jpg\",\"coueseGradeId\":15963591},{\"classname\":\"精品数学课\",\"imgurl\":\"img/cls_mid2.jpg\",\"coueseGradeId\":15963591}],\"classMoreList\":[{\"classname\":\"高中语文\",\"imgurl\":\"img/cls_icon_chaness.png\",\"coueseGradeId\":15963591},{\"classname\":\"小学英语\",\"imgurl\":\"img/cls_icon_english.png\",\"coueseGradeId\":15963591},{\"classname\":\"初中物理\",\"imgurl\":\"img/cls_icon_wuli.png\",\"coueseGradeId\":15963591},{\"classname\":\"高中数学\",\"imgurl\":\"img/cls_icon_math.png\",\"coueseGradeId\":15963591}]}},{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"艺术/体育\",\"imgurl\":\"img/icon_art.png\"},\"course\":{\"classList\":[{\"classname\":\"精品散打课\",\"imgurl\":\"img/cls_mid3.jpg\",\"coueseGradeId\":15963587},{\"classname\":\"精品美声课\",\"imgurl\":\"img/cls_mid4.jpg\",\"coueseGradeId\":15963587}],\"classMoreList\":[{\"classname\":\"钢琴\",\"imgurl\":\"img/cls_icon_gangqin.png\",\"coueseGradeId\":15963587},{\"classname\":\"古筝\",\"imgurl\":\"img/cls_icon_guzheng.png\",\"coueseGradeId\":15963587},{\"classname\":\"吉他\",\"imgurl\":\"img/cls_icon_jita.png\",\"coueseGradeId\":15963587},{\"classname\":\"小提琴\",\"imgurl\":\"img/cls_icon_tiqin.png\",\"coueseGradeId\":15963587}]}},{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"语言/留学\",\"imgurl\":\"img/icon_liuxue.png\"},\"course\":{\"classList\":[{\"classname\":\"精品日语课\",\"imgurl\":\"img/cls_mid5.jpg\",\"coueseGradeId\":15963589},{\"classname\":\"精品德语课\",\"imgurl\":\"img/cls_mid6.jpg\",\"coueseGradeId\":15963589}],\"classMoreList\":[{\"classname\":\"葡萄牙语\",\"imgurl\":\"img/cls_icon_putao.png\",\"coueseGradeId\":15963589},{\"classname\":\"外教口语\",\"imgurl\":\"img/cls_icon_waijiao.png\",\"coueseGradeId\":15963589},{\"classname\":\"新概念英语\",\"imgurl\":\"img/cls_icon_xingainian.png\",\"coueseGradeId\":15963589},{\"classname\":\"剑桥英语\",\"imgurl\":\"img/cls_icon_jianqiao.png\",\"coueseGradeId\":15963589}]}},{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"拓展训练\",\"imgurl\":\"img/icon_tuozhan.png\"},\"course\":{\"classList\":[{\"classname\":\"精品出游课\",\"imgurl\":\"img/cls_mid7.jpg\",\"coueseGradeId\":15963600},{\"classname\":\"精品玩耍课\",\"imgurl\":\"img/cls_mid8.jpg\",\"coueseGradeId\":15963600}],\"classMoreList\":[{\"classname\":\"冬夏令营\",\"imgurl\":\"img/cls_icon_dongxia.png\",\"coueseGradeId\":15963600},{\"classname\":\"亲子出游\",\"imgurl\":\"img/cls_icon_qinzi.png\",\"coueseGradeId\":15963600},{\"classname\":\"儿童乐园\",\"imgurl\":\"img/cls_icon_ertong.png\",\"coueseGradeId\":15963600},{\"classname\":\"拓展培训\",\"imgurl\":\"img/cls_icon_tuozhanpei.png\",\"coueseGradeId\":15963600}]}}],\"indexClass\":[{\"showClassImg\":\"img/class_art_icon.png\",\"showClassName\":\"艺术\",\"classPid\":\"15963587\"},{\"showClassImg\":\"img/class_tiyu_icon.png\",\"showClassName\":\"体育\",\"classPid\":\"15963588\"},{\"showClassImg\":\"img/class_yuyan_icon.png\",\"showClassName\":\"语言\",\"classPid\":\"15963589\"},{\"showClassImg\":\"img/class_liuxue_icon.png\",\"showClassName\":\"留学\",\"classPid\":\"15963590\"},{\"showClassImg\":\"img/class_xiao_icon.png\",\"showClassName\":\"小学\",\"classPid\":\"15963591\"},{\"showClassImg\":\"img/class_sheng_icon.png\",\"showClassName\":\"小升初\",\"classPid\":\"15963592\"},{\"showClassImg\":\"img/class_chuzhong_icon.png\",\"showClassName\":\"初中\",\"classPid\":\"15963593\"},{\"showClassImg\":\"img/class_zhongkao_icon.png\",\"showClassName\":\"中考\",\"classPid\":\"15963594\"},{\"showClassImg\":\"img/class_gao_icon.png\",\"showClassName\":\"高中\",\"classPid\":\"15963595\"},{\"showClassImg\":\"img/class_sheng_icon.png\",\"showClassName\":\"更多\",\"classPid\":\"all\"}]}";
		return "{\"allparentinfo\":[{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"中小学\",\"imgurl\":\"img/icon_middle.png\"},\"course\":{\"classList\":[{\"classname\":\"精品钢琴课\",\"imgurl\":\"img/cls_mid1.jpg\",\"coueseGradeId\":15963591},{\"classname\":\"精品数学课\",\"imgurl\":\"img/cls_mid2.jpg\",\"coueseGradeId\":15963591}],\"classMoreList\":[{\"classname\":\"高中语文\",\"imgurl\":\"img/cls_icon_chaness.png\",\"coueseGradeId\":15963591},{\"classname\":\"小学英语\",\"imgurl\":\"img/cls_icon_english.png\",\"coueseGradeId\":15963591},{\"classname\":\"初中物理\",\"imgurl\":\"img/cls_icon_wuli.png\",\"coueseGradeId\":15963591},{\"classname\":\"高中数学\",\"imgurl\":\"img/cls_icon_math.png\",\"coueseGradeId\":15963591}]}},{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"艺术/体育\",\"imgurl\":\"img/icon_art.png\"},\"course\":{\"classList\":[{\"classname\":\"精品散打课\",\"imgurl\":\"img/cls_mid3.jpg\",\"coueseGradeId\":15963587},{\"classname\":\"精品美声课\",\"imgurl\":\"img/cls_mid4.jpg\",\"coueseGradeId\":15963587}],\"classMoreList\":[{\"classname\":\"钢琴\",\"imgurl\":\"img/cls_icon_gangqin.png\",\"coueseGradeId\":15963587},{\"classname\":\"古筝\",\"imgurl\":\"img/cls_icon_guzheng.png\",\"coueseGradeId\":15963587},{\"classname\":\"吉他\",\"imgurl\":\"img/cls_icon_jita.png\",\"coueseGradeId\":15963587},{\"classname\":\"小提琴\",\"imgurl\":\"img/cls_icon_tiqin.png\",\"coueseGradeId\":15963587}]}},{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"语言/留学\",\"imgurl\":\"img/icon_liuxue.png\"},\"course\":{\"classList\":[{\"classname\":\"精品日语课\",\"imgurl\":\"img/cls_mid5.jpg\",\"coueseGradeId\":15963589},{\"classname\":\"精品德语课\",\"imgurl\":\"img/cls_mid6.jpg\",\"coueseGradeId\":15963589}],\"classMoreList\":[{\"classname\":\"葡萄牙语\",\"imgurl\":\"img/cls_icon_putao.png\",\"coueseGradeId\":15963589},{\"classname\":\"外教口语\",\"imgurl\":\"img/cls_icon_waijiao.png\",\"coueseGradeId\":15963589},{\"classname\":\"新概念英语\",\"imgurl\":\"img/cls_icon_xingainian.png\",\"coueseGradeId\":15963589},{\"classname\":\"剑桥英语\",\"imgurl\":\"img/cls_icon_jianqiao.png\",\"coueseGradeId\":15963589}]}},{\"headinfo\":{\"before\":\"更多课程\",\"after\":\"拓展训练\",\"imgurl\":\"img/icon_tuozhan.png\"},\"course\":{\"classList\":[{\"classname\":\"精品出游课\",\"imgurl\":\"img/cls_mid7.jpg\",\"coueseGradeId\":15963600},{\"classname\":\"精品玩耍课\",\"imgurl\":\"img/cls_mid8.jpg\",\"coueseGradeId\":15963600}],\"classMoreList\":[{\"classname\":\"冬夏令营\",\"imgurl\":\"img/cls_icon_dongxia.png\",\"coueseGradeId\":15963600},{\"classname\":\"亲子出游\",\"imgurl\":\"img/cls_icon_qinzi.png\",\"coueseGradeId\":15963600},{\"classname\":\"儿童乐园\",\"imgurl\":\"img/cls_icon_ertong.png\",\"coueseGradeId\":15963600},{\"classname\":\"拓展培训\",\"imgurl\":\"img/cls_icon_tuozhanpei.png\",\"coueseGradeId\":15963600}]}}],\"indexClass\":[{\"showClassImg\":\"img/class_art_icon.png\",\"showClassName\":\"艺术\",\"classPid\":\"15963587\"},{\"showClassImg\":\"img/class_tiyu_icon.png\",\"showClassName\":\"体育\",\"classPid\":\"15963588\"},{\"showClassImg\":\"img/class_yuyan_icon.png\",\"showClassName\":\"语言\",\"classPid\":\"15963589\"},{\"showClassImg\":\"img/class_liuxue_icon.png\",\"showClassName\":\"留学\",\"classPid\":\"15963590\"},{\"showClassImg\":\"img/class_xiao_icon.png\",\"showClassName\":\"小学\",\"classPid\":\"15963591\"},{\"showClassImg\":\"img/class_sheng_icon.png\",\"showClassName\":\"小升初\",\"classPid\":\"15963592\"},{\"showClassImg\":\"img/class_chuzhong_icon.png\",\"showClassName\":\"初中\",\"classPid\":\"15963593\"},{\"showClassImg\":\"img/class_sheng_icon.png\",\"showClassName\":\"更多\",\"classPid\":\"all\"}]}";
	}*/
	
	@RequestMapping(value = "/listhome", method = RequestMethod.GET)
	public String listhome() {
		return "{\"gradeOne\":[{\"gradeId\":15963587,\"class_name\":\"艺术\"},{\"gradeId\":15963588,\"class_name\":\"体育\"},{\"gradeId\":15963589,\"class_name\":\"语言\"},{\"gradeId\":15963590,\"class_name\":\"留学\"},{\"gradeId\":15963591,\"class_name\":\"小学\"},{\"gradeId\":15963592,\"class_name\":\"小升初\"},{\"gradeId\":15963593,\"class_name\":\"初中\"},{\"gradeId\":15963594,\"class_name\":\"中考\"},{\"gradeId\":15963595,\"class_name\":\"高中\"},{\"gradeId\":15963596,\"class_name\":\"益智\"},{\"gradeId\":15963597,\"class_name\":\"高考\"},{\"gradeId\":15963598,\"class_name\":\"学前\"},{\"gradeId\":15963599,\"class_name\":\"营地\"},{\"gradeId\":15963600,\"class_name\":\"其它\"}],\"gradeTwo\":[{\"gradeTwoId\":25963587,\"twoClass_name\":\"乐器\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963588,\"twoClass_name\":\"声乐\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963589,\"twoClass_name\":\"舞蹈\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963590,\"twoClass_name\":\"绘画\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963591,\"twoClass_name\":\"书法\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963592,\"twoClass_name\":\"媒体艺术\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963593,\"twoClass_name\":\"雕塑\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963594,\"twoClass_name\":\"设计\",\"pid\":\"15963587\"},{\"gradeTwoId\":25963595,\"twoClass_name\":\"戏剧\",\"pid\":\"15963587\"}],\"gradeThree\":[{\"gradeThreeId\":35963587,\"threeClass_name\":\"钢琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963588,\"threeClass_name\":\"小提琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963589,\"threeClass_name\":\"大提琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963590,\"threeClass_name\":\"电子琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963591,\"threeClass_name\":\"琵琶\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963592,\"threeClass_name\":\"二胡\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963593,\"threeClass_name\":\"萨克斯\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963594,\"threeClass_name\":\"吉他\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963595,\"threeClass_name\":\"电吉他\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963596,\"threeClass_name\":\"圆号\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963597,\"threeClass_name\":\"葫芦四\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963598,\"threeClass_name\":\"锁呐\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963599,\"threeClass_name\":\"杨琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963600,\"threeClass_name\":\"长笛\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963601,\"threeClass_name\":\"码头琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963602,\"threeClass_name\":\"手风琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963603,\"threeClass_name\":\"古筝\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963604,\"threeClass_name\":\"巴杨琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963605,\"threeClass_name\":\"黑管\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963606,\"threeClass_name\":\"阮\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963607,\"threeClass_name\":\"单簧管\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963608,\"threeClass_name\":\"双簧管\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963609,\"threeClass_name\":\"打击乐\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963610,\"threeClass_name\":\"小号\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963611,\"threeClass_name\":\"坚琴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963612,\"threeClass_name\":\"尤克里里\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963613,\"threeClass_name\":\"电贝斯\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963614,\"threeClass_name\":\"马林巴\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963615,\"threeClass_name\":\"架子鼓\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963616,\"threeClass_name\":\"竹笛\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963617,\"threeClass_name\":\"竖笛\",\"pid\":\"25963587\"},{\"gradeThreeId\":35963618,\"threeClass_name\":\"美声\",\"pid\":\"25963588\"},{\"gradeThreeId\":35963619,\"threeClass_name\":\"民族\",\"pid\":\"25963588\"},{\"gradeThreeId\":35963620,\"threeClass_name\":\"流行\",\"pid\":\"25963588\"},{\"gradeThreeId\":35963621,\"threeClass_name\":\"卡拉OK\",\"pid\":\"25963588\"},{\"gradeThreeId\":35963622,\"threeClass_name\":\"原生态\",\"pid\":\"25963588\"},{\"gradeThreeId\":35963623,\"threeClass_name\":\"音乐基础\",\"pid\":\"25963588\"},{\"gradeThreeId\":35963624,\"threeClass_name\":\"芭蕾舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963625,\"threeClass_name\":\"拉丁舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963626,\"threeClass_name\":\"街舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963627,\"threeClass_name\":\"广场舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963628,\"threeClass_name\":\"爵士舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963629,\"threeClass_name\":\"古舞蹈\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963630,\"threeClass_name\":\"民族舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963631,\"threeClass_name\":\"健美操\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963632,\"threeClass_name\":\"民间舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963633,\"threeClass_name\":\"国际舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963634,\"threeClass_name\":\"迪斯科\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963635,\"threeClass_name\":\"拉拉操\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963636,\"threeClass_name\":\"HipHop\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963637,\"threeClass_name\":\"摩登舞\",\"pid\":\"25963589\"},{\"gradeThreeId\":35963638,\"threeClass_name\":\"素描\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963639,\"threeClass_name\":\"水粉\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963640,\"threeClass_name\":\"水彩\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963641,\"threeClass_name\":\"国画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963642,\"threeClass_name\":\"油画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963643,\"threeClass_name\":\"沙画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963644,\"threeClass_name\":\"插画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963645,\"threeClass_name\":\"速写\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963646,\"threeClass_name\":\"创意画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963647,\"threeClass_name\":\"简笔画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963648,\"threeClass_name\":\"幼儿绘画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963649,\"threeClass_name\":\"卡通画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963650,\"threeClass_name\":\"漫画\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963651,\"threeClass_name\":\"线描\",\"pid\":\"25963590\"},{\"gradeThreeId\":35963652,\"threeClass_name\":\"硬笔书法\",\"pid\":\"25963591\"},{\"gradeThreeId\":35963653,\"threeClass_name\":\"软笔书法\",\"pid\":\"25963591\"},{\"gradeThreeId\":35963654,\"threeClass_name\":\"播音主持\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963655,\"threeClass_name\":\"影视表演\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963656,\"threeClass_name\":\"摄影培训\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963657,\"threeClass_name\":\"编导培训\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963658,\"threeClass_name\":\"电视报装\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963659,\"threeClass_name\":\"DJ培训\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963660,\"threeClass_name\":\"模特培训\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963661,\"threeClass_name\":\"舞台表演\",\"pid\":\"25963592\"},{\"gradeThreeId\":35963662,\"threeClass_name\":\"陶艺\",\"pid\":\"25963593\"},{\"gradeThreeId\":35963663,\"threeClass_name\":\"3D游戏设计\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963664,\"threeClass_name\":\"插画\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963665,\"threeClass_name\":\"泥塑\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963666,\"threeClass_name\":\"装置\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963667,\"threeClass_name\":\"装饰\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963668,\"threeClass_name\":\"手工\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963669,\"threeClass_name\":\"拼贴\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963670,\"threeClass_name\":\"石膏\",\"pid\":\"25963594\"},{\"gradeThreeId\":35963671,\"threeClass_name\":\"戏剧教育\",\"pid\":\"25963595\"},{\"gradeThreeId\":35963672,\"threeClass_name\":\"戏剧表演\",\"pid\":\"25963595\"},{\"gradeThreeId\":35963673,\"threeClass_name\":\"京剧\",\"pid\":\"25963595\"}]}";
	}
	
	/*
	@RequestMapping(value = "/courselist", method = RequestMethod.GET)
	public String courselist(@RequestParam String offset) {
		return "{\"data\":[{\"institutionsName\":\"校管家教育\",\"gradeId\":15963591,\"gradeTwoId\":25963603,\"teacherName\":\"李季\",\"evalPerson\":104,\"campusesName\":\"北京一中\",\"courseId\":55702467,\"teacher_id\":\"17056315\",\"course_id\":\"35963737\",\"teacher_actor\":\"1_tpl_43.jpg\",\"name\":\"语文\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":8890,\"mall_cost\":8890,\"person\":\"钢琴基础\",\"goods_week\":\"3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"1\",\"is_detailTime\":\"1\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"2\",\"retreat_rule\":\"1\",\"total\":36,\"saled\":9,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-09-07T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-09-08\"},{\"institutionsName\":\"佳一教育\",\"gradeId\":15963591,\"gradeTwoId\":25963606,\"teacherName\":\"李季\",\"evalPerson\":104,\"campusesName\":\"西城校区\",\"courseId\":55701514,\"teacher_id\":\"17055722\",\"course_id\":\"35963756\",\"teacher_actor\":\"1_tpl_52.jpg\",\"name\":\"英语QFX--007\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-4@3-5\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":60,\"saled\":0,\"open_date\":\"2015-06-03T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2015-06-04\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"佳一教育\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"米伦1\",\"evalPerson\":104,\"campusesName\":\"撒地方\",\"courseId\":55701367,\"teacher_id\":\"17055705\",\"course_id\":\"35963763\",\"teacher_actor\":\"1_tpl_98.jpg\",\"name\":\"作文CFX--005\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1500,\"mall_cost\":1500,\"person\":\"钢琴基础\",\"goods_week\":\"3-3@3-4@3-5@3-6@3-7\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"1\",\"is_minus\":\"1\",\"is_detailTime\":\"3\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"2\",\"is_signup\":\"1\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":80,\"saled\":43,\"open_date\":\"2016-09-12T16:00:00.000Z\",\"end_date\":\"2016-10-10T16:00:00.000Z\",\"open_date1\":\"2016-09-13\",\"end_date1\":\"2016-10-11\"},{\"institutionsName\":\"学而思教育\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"米伦1\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699703,\"teacher_id\":\"17055570\",\"course_id\":\"35963762\",\"teacher_actor\":\"1_tpl_76.jpg\",\"name\":\"英语教学教室\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":60,\"mall_cost\":60,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2017-05-02T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-05-03\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"佳一教育\",\"gradeId\":15963591,\"gradeTwoId\":25963605,\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"西城校区\",\"courseId\":55701326,\"teacher_id\":\"17055719\",\"course_id\":\"35963750\",\"teacher_actor\":\"1_tpl_57.jpg\",\"name\":\"英语一班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1000,\"mall_cost\":1000,\"person\":\"钢琴基础\",\"goods_week\":\"3-3@3-4@3-5@3-6@3-7\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"1\",\"is_minus\":\"1\",\"is_detailTime\":\"3\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"2\",\"is_signup\":\"1\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":80,\"saled\":43,\"open_date\":\"2016-08-14T16:00:00.000Z\",\"end_date\":\"2016-10-16T16:00:00.000Z\",\"open_date1\":\"2016-08-15\",\"end_date1\":\"2016-10-17\"},{\"institutionsName\":\"校管家教育\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"米伦1\",\"evalPerson\":104,\"campusesName\":\"北京分校\",\"courseId\":55702307,\"teacher_id\":\"17056273\",\"course_id\":\"35963761\",\"teacher_actor\":\"1_tpl_75.jpg\",\"name\":\"语文FX--003\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-5@3-6\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":68,\"saled\":45,\"open_date\":\"2017-05-10T16:00:00.000Z\",\"end_date\":\"2019-04-03T16:00:00.000Z\",\"open_date1\":\"2017-05-11\",\"end_date1\":\"2019-04-04\"},{\"institutionsName\":\"佳一教育\",\"gradeId\":15963591,\"gradeTwoId\":25963606,\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"新西兰分校\",\"courseId\":55701612,\"teacher_id\":\"17055738\",\"course_id\":\"35963758\",\"teacher_actor\":\"1_tpl_47.jpg\",\"name\":\"奥数CFX--005\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":60,\"mall_cost\":60,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-4@3-5\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":60,\"saled\":0,\"open_date\":\"2017-01-24T16:00:00.000Z\",\"end_date\":\"2017-01-28T16:00:00.000Z\",\"open_date1\":\"2017-01-25\",\"end_date1\":\"2017-01-29\"},{\"institutionsName\":\"爱斯创\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"教师1959\",\"evalPerson\":104,\"campusesName\":\"德美北京东城分校\",\"courseId\":55701970,\"teacher_id\":\"17055940\",\"course_id\":\"35963762\",\"teacher_actor\":\"1_tpl_20.jpg\",\"name\":\"英语教室\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":200,\"mall_cost\":200,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-5@3-6\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":68,\"saled\":45,\"open_date\":\"2016-06-19T16:00:00.000Z\",\"end_date\":\"2016-07-01T16:00:00.000Z\",\"open_date1\":\"2016-06-20\",\"end_date1\":\"2016-07-02\"},{\"institutionsName\":\"校管家教育\",\"gradeId\":15963591,\"gradeTwoId\":25963603,\"teacherName\":\"李季\",\"evalPerson\":104,\"campusesName\":\"北京一中\",\"courseId\":55702476,\"teacher_id\":\"17056315\",\"course_id\":\"35963737\",\"teacher_actor\":\"1_tpl_52.jpg\",\"name\":\"语文\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"1\",\"is_detailTime\":\"1\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"2\",\"retreat_rule\":\"1\",\"total\":36,\"saled\":9,\"open_date\":\"2017-06-28T16:00:00.000Z\",\"end_date\":\"2017-07-12T16:00:00.000Z\",\"open_date1\":\"2017-06-29\",\"end_date1\":\"2017-07-13\"},{\"institutionsName\":\"校管家教育\",\"gradeId\":15963591,\"gradeTwoId\":25963606,\"teacherName\":\"丁加加加\",\"evalPerson\":104,\"campusesName\":\"魏公村分校\",\"courseId\":55702560,\"teacher_id\":\"17056343\",\"course_id\":\"35963755\",\"teacher_actor\":\"1_tpl_32.jpg\",\"name\":\"语文\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-5@3-6@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":36,\"saled\":9,\"open_date\":\"2016-03-06T16:00:00.000Z\",\"end_date\":\"2017-09-07T16:00:00.000Z\",\"open_date1\":\"2016-03-07\",\"end_date1\":\"2017-09-08\"},{\"institutionsName\":\"杰睿教育\",\"gradeId\":15963591,\"gradeTwoId\":25963605,\"teacherName\":\"王娜\",\"evalPerson\":104,\"campusesName\":\"魏公村分校\",\"courseId\":55702815,\"teacher_id\":\"17056432\",\"course_id\":\"35963751\",\"teacher_actor\":\"1_tpl_06.jpg\",\"name\":\"作文\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1000,\"mall_cost\":1000,\"person\":\"钢琴基础\",\"goods_week\":\"3-5@3-6@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":36,\"saled\":9,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-09-07T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-09-08\"},{\"institutionsName\":\"腾百万教育\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"李测试\",\"evalPerson\":104,\"campusesName\":\"安贞门分校\",\"courseId\":55701829,\"teacher_id\":\"17055799\",\"course_id\":\"35963760\",\"teacher_actor\":\"1_tpl_81.jpg\",\"name\":\"数学室002\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":60,\"mall_cost\":60,\"person\":\"钢琴基础\",\"goods_week\":\"3-4@3-5@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":60,\"saled\":0,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"校管家教育\",\"gradeId\":15963591,\"gradeTwoId\":25963606,\"teacherName\":\"米伦1\",\"evalPerson\":104,\"campusesName\":\"燕郊羽毛球俱乐部\",\"courseId\":55702516,\"teacher_id\":\"17056329\",\"course_id\":\"35963755\",\"teacher_actor\":\"1_tpl_92.jpg\",\"name\":\"语文班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1000,\"mall_cost\":1000,\"person\":\"钢琴基础\",\"goods_week\":\"3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"1\",\"is_detailTime\":\"1\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"2\",\"retreat_rule\":\"1\",\"total\":36,\"saled\":9,\"open_date\":\"2017-03-31T16:00:00.000Z\",\"end_date\":\"2017-03-31T16:00:00.000Z\",\"open_date1\":\"2017-04-01\",\"end_date1\":\"2017-04-01\"},{\"institutionsName\":\"佳一教育\",\"gradeId\":15963591,\"gradeTwoId\":25963603,\"teacherName\":\"E教师\",\"evalPerson\":104,\"campusesName\":\"西城校区\",\"courseId\":55701166,\"teacher_id\":\"17055726\",\"course_id\":\"35963738\",\"teacher_actor\":\"1_tpl_90.jpg\",\"name\":\"英语QFX--007\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":200,\"mall_cost\":200,\"person\":\"钢琴基础\",\"goods_week\":\"3-5@3-6@3-7\",\"is_openClass\":\"1\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":80,\"saled\":43,\"open_date\":\"2016-03-06T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2016-03-07\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"校管家教育\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"北京小雨点教育培训\",\"courseId\":55702284,\"teacher_id\":\"17056254\",\"course_id\":\"35963761\",\"teacher_actor\":\"1_tpl_52.jpg\",\"name\":\"语文-010\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":100,\"mall_cost\":100,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-5@3-6\",\"is_openClass\":\"1\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":68,\"saled\":45,\"open_date\":\"2016-10-20T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2016-10-21\",\"end_date1\":\"2017-08-09\"}],\"offset\":\"0\"}";
	}*/
	
	
	//@RequestMapping(value = "/courseDetail/index/55702467", method = RequestMethod.GET)
	//public String courseDetail() {
	//	return "{\"line\":\"-\",\"platform\":\"web\",\"page\":\"http://nodet.cn/courseDetail/index?id=55699859\",\"pagetype\":\"courseDetail\",\"tracking_type\":\"pageload\",\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1\",\"ip\":\"119.129.226.184\",\"project_id\":\"5b17f7d31992da6302591580\",\"eventid\":\"15162722825401477075\",\"guid\":\"af5bce20-6a87-11ea-98ab-cb56f505f2e6\",\"extra\":{}}";

	//}
	
	@RequestMapping(value = "/courseDetail/index", method = RequestMethod.GET)
	public String courseDetail(@RequestParam String id) {
		/*
		CourseVideoBO courseVideoBO = new CourseVideoBO();
		courseVideoBO.setCourseId(id);
		Result<CourseViewDTO> cResult = apiCourseBiz.view(courseVideoBO);
		*/
		
		Result<FreshCourseMobileView> cResult = pcMobileFreshCourseService.viewMobileCourse(id,null);
		
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
		
		/*
		Map<String, String> courseExamQuestionIdsMap = new HashMap<String, String>();
		for(int i=0;i<courseExamQuestionIds.size();i++){
			if(null!=courseExamQuestionIds.get(i).get("id")){
				String dataId = (String)(courseExamQuestionIds.get(i).get("id"));
				courseExamQuestionIdsMap.put(dataId, courseExamQuestionIds.get(i).get("count")+"");
			}
		}*/
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
					}
				}
			}
		}
		

		
		return JSONUtil.toJsonStr(cResult.getData());
		//return "{\"courseinfoId\":44480074,\"pid\":\"55701829\",\"teachingTarget\":\"我是教学目标啊\",\"couresStar\":\"我是教学亮点啊\",\"isClass\":\"1\",\"content\":\"不错哦\",\"institutionsName\":\"腾百万教育\",\"gradeId\":15963591,\"gradeTwoId\":25963607,\"teacherName\":\"李测试\",\"evalPerson\":104,\"campusesName\":\"安贞门分校\",\"courseId\":55701829,\"teacher_id\":\"17055799\",\"course_id\":\"35963760\",\"teacher_actor\":\"1_tpl_81.jpg\",\"name\":\"数学室002\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":60,\"mall_cost\":60,\"person\":\"钢琴基础\",\"goods_week\":\"3-4@3-5@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":60,\"saled\":0,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\",\"evaluate\":[\"不错哦\",\"老师的物理课程值得推荐！\"]}";
	}
	
	@RequestMapping(value = "/findByVideoLocalNumber", method = RequestMethod.GET)
	public String findByVideoLocalNumber(@RequestParam String lessonId) {
		
		
		FreshLessonViewVo freshLessonViewVo = new FreshLessonViewVo();
		freshLessonViewVo.setId(lessonId);
		FreshLessonViewVo vo = pcWebFreshLessonService.viewLesson(freshLessonViewVo);
		
		FreshCourseVideoQ freshCourseVideoQ = new FreshCourseVideoQ();
		freshCourseVideoQ.setVideoLocalNumber(vo.getVideoLocalNumber());
		Result<Page<FreshCourseVideoVo>> result = pcWebFreshCourseVideoService.queryAllCourseVideos(freshCourseVideoQ);
		Page<FreshCourseVideoVo> page = result.getData();
		if(CollectionUtils.isNotEmpty(page.getList())){
			return JSONUtil.toJsonStr(page.getList().get(0));
		}
		
		/*
		AuthLessonIdVideoBO authCourseVideoBO = new AuthLessonIdVideoBO();
		authCourseVideoBO.setLessonId(lessonId);
		Result<AuthCourseVideoListDTO> AuthCourseVideoListDTOResult = pcWebCourseVideoBiz.listByLessonId(authCourseVideoBO);
		AuthCourseVideoListDTO authCourseVideoListDTO = AuthCourseVideoListDTOResult.getData();
		if(authCourseVideoListDTO.getList().size()>=1){
			CourseVideo courseVideo = apiCourseVideoBiz.getByVideoLocalNumber(authCourseVideoListDTO.getList().get(0).getVideoLocalNumber());
			return JSONUtil.toJsonStr(courseVideo);
		}*/
		return null;
	}
	
	/**
	 * 当bindType为4时，id为chapterId，当bindType为5时，id为lessonId
	 * @param id
	 * @param bindType
	 * @return
	 */
	/*
	@RequestMapping(value = "/countExamQuestionCourses", method = RequestMethod.GET)
	public String countExamQuestionCourses(@RequestParam String id,@RequestParam String bindType) {
		ExamQuestionCourseQ examQuestionCourseQ = new ExamQuestionCourseQ();
		examQuestionCourseQ.setBindType(bindType);
		examQuestionCourseQ.setValidValue("1");
		if("4".equals(bindType)){
			examQuestionCourseQ.setChapterId(id);
		}
		if("5".equals(bindType)){
			examQuestionCourseQ.setLessonId(id);
		}
		int count  = examQuestionCourseService.countExamQuestionCourses(examQuestionCourseQ);
		return "{\"count\":\""+count+"\"}";
		
	}*/

	@RequestMapping(value = "/queryNotYetDoneExamQuestions", method = RequestMethod.POST)
	public String queryNotYetDoneExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryNotYetDoneExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(), examQuestionCourseQuery.getType(), 15);
		String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		//return "{\"result\":null,\"status\":200,\"message\":\"成功\",\"examQuestions\":\""+examQuestionsString+"\"}";
		return examQuestionsString;
	}
	
	@RequestMapping(value = "/queryExamQuestions", method = RequestMethod.POST)
	public String queryExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryExamQuestions(examQuestionCourseQuery.getCcpId(), examQuestionCourseQuery.getType());
		String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		return examQuestionsString;
	}
	
	@RequestMapping(value = "/queryErrorExamQuestions", method = RequestMethod.POST)
	public String queryErrorExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryErrorExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		return examQuestionsString;
	}
	
	@RequestMapping(value = "/cacheErrorExamQuestion", method = RequestMethod.POST)
	public String cacheErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.cacheErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";
	}
	
	
	@RequestMapping(value = "/delErrorExamQuestion", method = RequestMethod.POST)
	public String delErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";
	}
	
	
	@RequestMapping(value = "/queryHighErrorExamQuestions", method = RequestMethod.POST)
	public String queryHighErrorExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryHighErrorExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		return examQuestionsString;
	}
	
	@RequestMapping(value = "/cacheHighErrorExamQuestion", method = RequestMethod.POST)
	public String cacheHighErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.cacheHighErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";
	}
	
	
	@RequestMapping(value = "/delHighErrorExamQuestion", method = RequestMethod.POST)
	public String delHighErrorExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delHighErrorExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";
	}
	
	@RequestMapping(value = "/queryFavoritesExamQuestions", method = RequestMethod.POST)
	public String queryFavoritesExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryFavoritesExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		return examQuestionsString;
	}
	
	@RequestMapping(value = "/cacheFavoritesExamQuestion", method = RequestMethod.POST)
	public String cacheFavoritesExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.cacheFavoritesExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";

	}
	
	@RequestMapping(value = "/delFavoritesExamQuestion", method = RequestMethod.POST)
	public String delFavoritesExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delFavoritesExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";

	}
	
	
	@RequestMapping(value = "/queryNoteExamQuestions", method = RequestMethod.POST)
	public String queryNoteExamQuestions(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		List<ExamQuestionVo> examQuestionVos = examQuestionCourseService.queryNoteExamQuestions(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId());
		String examQuestionsString = JSONUtil.toJsonStr(examQuestionVos);
		return examQuestionsString;
	}
	
	
	@RequestMapping(value = "/queryNoteContentByQuestionId", method = RequestMethod.POST)
	public String queryNoteContentByQuestionId(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		String  noteContent = examQuestionCourseService.queryNoteContentByQuestionId(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\",\"noteContent\":\""+noteContent+"\"}";

	}
	
	@RequestMapping(value = "/cacheNoteExamQuestion", method = RequestMethod.POST)
	public String cacheNoteExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		if(examQuestionCourseQuery.getNoteContent()!=null&&!"".equals(examQuestionCourseQuery.getNoteContent().trim())){
			examQuestionCourseService.cacheNoteExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId(),examQuestionCourseQuery.getNoteContent());
		}
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";
	}
	
	
	

	
	@RequestMapping(value = "/delNoteExamQuestion", method = RequestMethod.POST)
	public String delNoteExamQuestion(@RequestBody ExamQuestionCourseQuery examQuestionCourseQuery){
		examQuestionCourseService.delNoteExamQuestion(examQuestionCourseQuery.getUserNo(), examQuestionCourseQuery.getCcpId(),examQuestionCourseQuery.getQuestionId());
		return "{\"result\":null,\"status\":200,\"message\":\"成功\"}";

	}
	
	/*
	@RequestMapping(value = "/courseView", method = RequestMethod.GET)
	public String courseView(@RequestParam String courseId) {
		
		AuthCourseViewDTO authCourseViewDTO = pcWebCourseBiz.viewByCourseId(courseId);
		return JSONUtil.toJsonStr(authCourseViewDTO);
	}*/
	
	
	
	@RequestMapping(value = "/isexistCart", method = RequestMethod.GET)
	public String isexistCart(@RequestParam String id) {
		return "{\"data\":null,\"status\":true,\"msg\":0}";
	}
	
	@RequestMapping(value = "/submitOrder", method = RequestMethod.GET)
	public String submitOrder() {
		return "{\"line\":\"-\",\"platform\":\"web\",\"page\":\"http://nodet.cn/submitOrder/index?id=55701773\",\"pagetype\":\"submitOrder\",\"tracking_type\":\"click\",\"extra\":{\"offsetX\":55,\"offsetY\":21,\"dompath\":\"#__layout>div>div>div:nth-child(3)>div:nth-child(2)>div>input[type='button']:nth-child(2)\"},\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1\",\"ip\":\"119.129.226.184\",\"project_id\":\"5b17f7d31992da6302591580\",\"eventid\":\"14152099310616478377\",\"guid\":\"af5bce20-6a87-11ea-98ab-cb56f505f2e6\"}";
	}
	
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
	public String getOrderList() {
		return "{\"status\":true,\"result\":{\"15263774134472020319\":[{\"name\":\"法语QFX--007"+new Date().toLocaleString()+"\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"cost\":1000,\"teacher_id\":\"17055706\",\"teacherName\":\"赵测试\",\"teacher_actor\":\"1_tpl_47.jpg\",\"id\":2011523911,\"phone\":{\"type\":\"Buffer\",\"data\":[49,51,49,57,57,57,57,48,48,48,48]},\"courseOrder\":\"55701034\",\"orderId\":\"15263774134472020319\",\"total\":\"1000\",\"order_date\":\"2020-03-18T16:00:00.000Z\",\"orderSataus\":\"1\",\"open_date1\":\"2016-03-07 12-00-00\",\"end_date1\":\"2017-08-09 12-00-00\",\"order_date1\":\"2020-03-19 12-00-00\"}],\"15263774134482020320\":[{\"name\":\"语文LSQFX--007\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"end_date\":\"2016-06-10T16:00:00.000Z\",\"cost\":500,\"teacher_id\":\"17055743\",\"teacherName\":\"高敏\",\"teacher_actor\":\"1_tpl_15.jpg\",\"id\":2011523912,\"phone\":{\"type\":\"Buffer\",\"data\":[49,51,49,57,57,57,57,48,48,48,48]},\"courseOrder\":\"55701773\",\"orderId\":\"15263774134482020320\",\"total\":\"500\",\"order_date\":\"2020-03-19T16:00:00.000Z\",\"orderSataus\":\"1\",\"open_date1\":\"2016-06-01 12-00-00\",\"end_date1\":\"2016-06-11 12-00-00\",\"order_date1\":\"2020-03-20 12-00-00\"}]}}";
	}
	
	@RequestMapping(value = "/listhome/filter/data", method = RequestMethod.GET)
	public String listhomeFilter(){
		//return "[{\"title\":\"班级类型\",\"classlist\":[{\"name\":\"免费试听\",\"id\":\"is_free=1\"},{\"name\":\"随时插班\",\"id\":\"is_transfer=1\"},{\"name\":\"随时退班\",\"id\":\"retreat_rule=1\"},{\"name\":\"未开课\",\"id\":\"is_openClass=1\"}]},{\"title\":\"活动优惠\",\"classlist\":[{\"name\":\"连报优惠\",\"id\":\"is_discount=1\"},{\"name\":\"限时团购\",\"id\":\"is_groupPurchase=1\"},{\"name\":\"折扣\",\"id\":\"is_discountRate=1\"},{\"name\":\"立减\",\"id\":\"is_minus=1\"}]},{\"title\":\"上课时间\",\"classlist\":[{\"name\":\"周一\",\"id\":\"goods_week=3-1\"},{\"name\":\"周二\",\"id\":\"goods_week=3-2\"},{\"name\":\"周三\",\"id\":\"goods_week=3-3\"},{\"name\":\"周四\",\"id\":\"goods_week=3-4\"},{\"name\":\"周五\",\"id\":\"goods_week=3-5\"},{\"name\":\"周六\",\"id\":\"goods_week=3-6\"},{\"name\":\"周日\",\"id\":\"goods_week=3-7\"}]},{\"title\":\"具体时间\",\"classlist\":[{\"name\":\"上午\",\"id\":\"is_detailTime=1\"},{\"name\":\"下午\",\"id\":\"is_detailTime=2\"},{\"name\":\"晚上\",\"id\":\"is_detailTime=3\"}]},{\"title\":\"价格区间\",\"classlist\":[{\"name\":\"0～1000\",\"id\":\"0.00^1000.00\"},{\"name\":\"1000～3000\",\"id\":\"1000.00^3000.00\"},{\"name\":\"3000～5000\",\"id\":\"3000.00^5000.00\"},{\"name\":\"5000～7000\",\"id\":\"5000.00^7000.00\"},{\"name\":\"7000～9000\",\"id\":\"7000.00^9000.00\"},{\"name\":\"9000以上\",\"id\":\"9000.00^10000000.00\"}]}]";
		return "[{\"title\":\"付费类型\",\"classlist\":[{\"name\":\"免费\",\"id\":\"is_free=1\"},{\"name\":\"收费\",\"id\":\"is_free=0\"}]}]";
	}
	
	@RequestMapping(value = "/cartList", method = RequestMethod.GET)
	public String cartList(){
		return "{\"result\":[{\"courseId\":55701773,\"teacher_id\":\"17055743\",\"course_id\":\"35963755\",\"teacher_actor\":\"1_tpl_15.jpg\",\"name\":\"语文LSQFX--007\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-4@3-5@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":60,\"saled\":12,\"open_date\":\"2016-05-31T16:00:00.000Z\",\"end_date\":\"2016-06-10T16:00:00.000Z\",\"id\":71125174,\"campusesName\":\"新西兰分校\",\"teacherName\":\"高敏\",\"open_date1\":\"2016-06-01\",\"end_date1\":\"2016-06-11\"},{\"courseId\":55702799,\"teacher_id\":\"17056424\",\"course_id\":\"35963758\",\"teacher_actor\":\"1_tpl_89.jpg\",\"name\":\"奥数\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":300,\"mall_cost\":300,\"person\":\"钢琴基础\",\"goods_week\":\"3-5@3-6@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":36,\"saled\":9,\"open_date\":\"2016-03-12T16:00:00.000Z\",\"end_date\":\"2016-03-15T16:00:00.000Z\",\"id\":71125175,\"campusesName\":\"北京新华字典教育\",\"teacherName\":\"教师0007\",\"open_date1\":\"2016-03-13\",\"end_date1\":\"2016-03-16\"}],\"status\":true,\"total\":2}";
	}
	
	@RequestMapping(value = "/mostAddClass", method = RequestMethod.GET)
	public String mostAddClass(){
		return "{\"status\":true,\"data\":[{\"courseinfoId\":44480018,\"pid\":\"55701773\",\"teachingTarget\":\"我是教学目标啊\",\"couresStar\":\"我是教学亮点啊\",\"isClass\":\"1\",\"institutionsName\":\"佳一教育\",\"gradeId\":15963591,\"gradeTwoId\":25963606,\"teacherName\":\"高敏\",\"evalPerson\":104,\"campusesName\":\"新西兰分校\",\"courseId\":55701773,\"teacher_id\":\"17055743\",\"course_id\":\"35963755\",\"teacher_actor\":\"1_tpl_15.jpg\",\"name\":\"语文LSQFX--007\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-4@3-5@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"2\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":60,\"saled\":12,\"open_date\":\"2016-05-31T16:00:00.000Z\",\"end_date\":\"2016-06-10T16:00:00.000Z\",\"open_date1\":\"2016-06-01\",\"end_date1\":\"2016-06-11\"},{\"courseinfoId\":44481044,\"pid\":\"55702799\",\"teachingTarget\":\"我是教学目标啊\",\"couresStar\":\"我是教学亮点啊\",\"isClass\":\"1\",\"institutionsName\":\"杰睿教育\",\"gradeId\":15963591,\"gradeTwoId\":25963606,\"teacherName\":\"教师0007\",\"evalPerson\":104,\"campusesName\":\"北京新华字典教育\",\"courseId\":55702799,\"teacher_id\":\"17056424\",\"course_id\":\"35963758\",\"teacher_actor\":\"1_tpl_89.jpg\",\"name\":\"奥数\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":300,\"mall_cost\":300,\"person\":\"钢琴基础\",\"goods_week\":\"3-5@3-6@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"2\",\"is_discountRate\":\"1\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"3\",\"retreat_rule\":\"2\",\"total\":36,\"saled\":9,\"open_date\":\"2016-03-12T16:00:00.000Z\",\"end_date\":\"2016-03-15T16:00:00.000Z\",\"open_date1\":\"2016-03-13\",\"end_date1\":\"2016-03-16\"}]}";
	}
	
	@RequestMapping(value = "/paySuccess", method = RequestMethod.GET)
	public String paySuccess(){
		return "{\"status\":true,\"result\":{\"fieldCount\":0,\"affectedRows\":0,\"insertId\":0,\"serverStatus\":34,\"warningCount\":0,\"message\":\"\",\"protocol41\":true,\"changedRows\":0}}";
	}
	
	/*
	@RequestMapping(value = "/getleckCourse", method = RequestMethod.GET)
	public String getleckCourse() {
		return "{\"data\":[{\"institutionsName\":\"学而思教育\",\"teacherName\":\"张武\",\"evalPerson\":104,\"campusesName\":\"纽约分校666\",\"courseId\":55699661,\"teacher_id\":\"17055560\",\"course_id\":\"35963749\",\"teacher_actor\":\"1_tpl_24.jpg\",\"name\":\"语文T--001\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":100,\"mall_cost\":100,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":24,\"open_date\":\"2017-05-02T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-05-03\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"小学10班\",\"evalPerson\":104,\"campusesName\":\"纽约分校666\",\"courseId\":55699667,\"teacher_id\":\"17055561\",\"course_id\":\"35963755\",\"teacher_actor\":\"1_tpl_30.jpg\",\"name\":\"语文教学教室\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":3500,\"mall_cost\":3500,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":24,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"纽约分校666\",\"courseId\":55699673,\"teacher_id\":\"17055563\",\"course_id\":\"35963761\",\"teacher_actor\":\"1_tpl_36.jpg\",\"name\":\"语文X--007\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":60,\"mall_cost\":60,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"纽约分校666\",\"courseId\":55699674,\"teacher_id\":\"17055563\",\"course_id\":\"35963762\",\"teacher_actor\":\"1_tpl_37.jpg\",\"name\":\"英语文一班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":600,\"mall_cost\":600,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2016-02-19T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2016-02-20\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"巢老师\",\"evalPerson\":104,\"campusesName\":\"纽约分校666\",\"courseId\":55699679,\"teacher_id\":\"17055567\",\"course_id\":\"35963767\",\"teacher_actor\":\"1_tpl_42.jpg\",\"name\":\"语文学教室\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":100,\"mall_cost\":100,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"巢老师\",\"evalPerson\":104,\"campusesName\":\"纽约分校666\",\"courseId\":55699682,\"teacher_id\":\"17055567\",\"course_id\":\"35963770\",\"teacher_actor\":\"1_tpl_45.jpg\",\"name\":\"奥数op语文一班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1000,\"mall_cost\":1000,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2016-02-11T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2016-02-12\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"王娜\",\"evalPerson\":104,\"campusesName\":\"国土分校\",\"courseId\":55699687,\"teacher_id\":\"17055568\",\"course_id\":\"35963775\",\"teacher_actor\":\"1_tpl_60.jpg\",\"name\":\"语文儿教育\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":50,\"mall_cost\":50,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2016-10-08T16:00:00.000Z\",\"end_date\":\"2016-10-15T16:00:00.000Z\",\"open_date1\":\"2016-10-09\",\"end_date1\":\"2016-10-16\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"教师006\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699698,\"teacher_id\":\"17055569\",\"course_id\":\"35963786\",\"teacher_actor\":\"1_tpl_71.jpg\",\"name\":\"语文WGCFX--004\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":100,\"mall_cost\":100,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"米伦1\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699701,\"teacher_id\":\"17055570\",\"course_id\":\"35963761\",\"teacher_actor\":\"1_tpl_74.jpg\",\"name\":\"语文教学教室\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":0,\"mall_cost\":0,\"person\":\"钢琴基础\",\"goods_week\":\"3-2@3-3@3-4@3-6\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"2\",\"is_detailTime\":\"3\",\"is_audition\":\"1\",\"is_transfer\":\"2\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2017-07-02T16:00:00.000Z\",\"end_date\":\"2017-07-02T16:00:00.000Z\",\"open_date1\":\"2017-07-03\",\"end_date1\":\"2017-07-03\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699717,\"teacher_id\":\"17055571\",\"course_id\":\"35963767\",\"teacher_actor\":\"1_tpl_90.jpg\",\"name\":\"语文LSQFX--007\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":0,\"mall_cost\":0,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2016-03-06T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2016-03-07\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"晓梅\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699725,\"teacher_id\":\"17055573\",\"course_id\":\"35963775\",\"teacher_actor\":\"1_tpl_98.jpg\",\"name\":\"语文1班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":500,\"mall_cost\":500,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":54,\"saled\":15,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"李季\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699735,\"teacher_id\":\"17055575\",\"course_id\":\"35963762\",\"teacher_actor\":\"1_tpl_09.jpg\",\"name\":\"英语文一班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1,\"mall_cost\":1,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":120,\"saled\":85,\"open_date\":\"2015-06-03T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2015-06-04\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"赵老师\",\"evalPerson\":104,\"campusesName\":\"游泳分校2\",\"courseId\":55699741,\"teacher_id\":\"17055576\",\"course_id\":\"35963762\",\"teacher_actor\":\"1_tpl_15.jpg\",\"name\":\"英语文一班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":200,\"mall_cost\":200,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":120,\"saled\":23,\"open_date\":\"2017-03-12T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2017-03-13\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"学而思教育\",\"teacherName\":\"米伦1\",\"evalPerson\":104,\"campusesName\":\"中关村分校\",\"courseId\":55699745,\"teacher_id\":\"17055580\",\"course_id\":\"35963604\",\"teacher_actor\":\"1_tpl_19.jpg\",\"name\":\"巴杨琴语文二班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":60,\"mall_cost\":60,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"1\",\"is_enforce\":\"3\",\"retreat_rule\":\"1\",\"total\":120,\"saled\":23,\"open_date\":\"2013-02-28T16:00:00.000Z\",\"end_date\":\"2017-08-08T16:00:00.000Z\",\"open_date1\":\"2013-03-01\",\"end_date1\":\"2017-08-09\"},{\"institutionsName\":\"红黄蓝\",\"teacherName\":\"张明敏\",\"evalPerson\":104,\"campusesName\":\"北京美嘉校区\",\"courseId\":55699781,\"teacher_id\":\"17055593\",\"course_id\":\"35963623\",\"teacher_actor\":\"1_tpl_55.jpg\",\"name\":\"音乐基础语文一班\",\"class_time_names\":\"09:30-10:30\",\"address\":\"北京市海淀区西北旺中关村科技园1\",\"district\":\"海淀区\",\"browse_number\":132,\"lesson_total\":12,\"cost\":1000,\"mall_cost\":1000,\"person\":\"钢琴基础\",\"goods_week\":\"3-1@3-2@3-3@3-7\",\"is_openClass\":\"2\",\"is_discount\":\"1\",\"is_groupPurchase\":\"1\",\"is_discountRate\":\"2\",\"is_minus\":\"1\",\"is_detailTime\":\"2\",\"is_audition\":\"2\",\"is_transfer\":\"1\",\"is_free\":\"1\",\"is_signup\":\"2\",\"is_enforce\":\"1\",\"retreat_rule\":\"1\",\"total\":120,\"saled\":23,\"open_date\":\"2017-01-09T16:00:00.000Z\",\"end_date\":\"2017-01-17T16:00:00.000Z\",\"open_date1\":\"2017-01-10\",\"end_date1\":\"2017-01-18\"}],\"offset\":\"0\"}";
	}*/
	


	/**
	 * 用户信息查看接口
	 */
	@ApiOperation(value = "用户信息查看接口", notes = "根据userNo获取用户信息接口")
	@RequestMapping(value = "/userView", method = RequestMethod.POST)
	public Result<AuthStudentDTO> userView(@RequestBody AuthStudentViewBO authStudentViewBO) {
		return pcWebStudentBiz.view(authStudentViewBO);
	}

	/**
	 * 用户信息更新接口
	 */
	@ApiOperation(value = "用户信息更新接口", notes = "用户信息更新接口")
	@RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
	public Result<AuthStudentDTO> userUpdate(@RequestBody AuthStudentBO authStudentBO) {		
		return pcWebStudentBiz.update(authStudentBO);
	}

	/**
	 * 用户注销接口
	 */
	@ApiOperation(value = "用户注销接口", notes = "用户信息更新接口")
	@RequestMapping(value = "/userLogout", method = RequestMethod.POST)
	public Result<Integer> userLogout(@RequestBody AuthStudentViewBO authStudentViewBO) {
		return pcWebStudentBiz.logout(authStudentViewBO);
	}
	

	
	public static void main(String args[]){
		String string = "{\"id\":1080759557651369985,\"name\":\"ddd\"}";

		com.alibaba.fastjson.JSONObject jsobject = com.alibaba.fastjson.JSONObject.parseObject(string);
		//fastjson解析方法
		for (Map.Entry<String, Object> entry : jsobject.entrySet()) {
			if(entry.getValue() instanceof Long){
				System.out.println("key值=-=-----"+entry.getKey());
				System.out.println("长整型");
				entry.setValue(entry.getValue()+"");
			}
				System.out.println("key值="+entry.getKey());
				System.out.println("value="+entry.getValue());
		}
		
		System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(jsobject));
			
	}
	
	
}

