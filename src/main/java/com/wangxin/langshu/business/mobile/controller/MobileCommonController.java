package com.wangxin.langshu.business.mobile.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossAdvertBiz;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseSubjectBiz;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseBiz;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IApiCourseSubjectBiz;
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
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseMobileView;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.topic.FreshTopicVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.CourseSubjectListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertPageRESQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsViewVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.OperatePlatEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.pretool.IpUtil;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobilecommon")
public class MobileCommonController extends BaseController {
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
	private IApiCourseSubjectBiz apiCourseSubjectBiz;


	
	
	@RequestMapping(value = "/findAdverts", method = RequestMethod.POST)
	public Result<Page<AdvertPageRESQ>> findAdverts() {
		AdvertPageREQ advertPageREQ = new AdvertPageREQ();
		advertPageREQ.setPlatShow(2);
		advertPageREQ.setValidValue(1);
		Result<Page<AdvertPageRESQ>> result = pcBossAdvertBiz.list(advertPageREQ);
		
		return result;
	}
	
	@RequestMapping(value = "/findNewsList", method = RequestMethod.POST)
	public Result<Page<NewsVo>> findNewsList() {
		NewsQ newsQ = new NewsQ();
		newsQ.setPlatShow("2");
		newsQ.setValidValue("1");
		return Result.success(newsService.queryEntities(newsQ));
	}
	
	@RequestMapping(value = "/findNewsById", method = RequestMethod.POST)
	public Result<NewsViewVo> findNewsById(@RequestBody NewsViewVo n) {
		if(StringUtils.isEmpty(n.getId())) {
			return Result.error("缺少id");
		}
		NewsViewVo newsViewVo = new NewsViewVo();
		newsViewVo.setId(n.getId());
		return newsService.viewEntity(newsViewVo);
	}
	
	
	@RequestMapping(value = "/findIndexMiddleNavs", method = RequestMethod.POST)
	public Result<Page<MobileMiddleNavigationVo>> findIndexMiddleNavs() {
		MobileMiddleNavigationQ mobileMiddleNavigationQ = new MobileMiddleNavigationQ();
		mobileMiddleNavigationQ.setValidValue("1");
		Page<MobileMiddleNavigationVo> page = mobileMiddleNavigationService.queryMobileMiddleNavigations(mobileMiddleNavigationQ);
		return Result.success(page);
	}
	
	/**
	 * 专区课程分页列表接口
	 * 
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "专区课程列表接口", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/findTopics", method = RequestMethod.POST)
	public Result<Page<FreshTopicVo>> findTopics() {
		FreshTopicQ freshTopicQ = new FreshTopicQ();
		freshTopicQ.setTopicLocation("2");////设置移动端位置代码
		return pcMobileFreshTopicService.queryTopics(freshTopicQ);
	}
	
	
	/**
	 * 普通课程分类列表接口
	 * 
	 */
	@ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
	@RequestMapping(value = "/findSubjects", method = RequestMethod.POST)
	public Result<CourseSubjectListDTO> findSubjects() {
		return apiCourseSubjectBiz.list();
	}
	


	/**
	 * 课程信息列表接口
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "课程列表接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/findCourselist", method = RequestMethod.POST)
	public Result<Page<FreshCourseVo>> findCourselist(@RequestBody FreshCourseQ freshCourseQ) {
		return Result.success(pcMobileFreshCourseService.queryCourses(freshCourseQ));
	}
	
	/**
	 * 根据ID查找课程详情(未登录)
	 * 
	 * @param 
	 * @return
	 */
	@ApiOperation(value = "根据ID查找课程详情(未登录)", notes = "根据ID查找课程详情(未登录)")
	@RequestMapping(value = "/findCourseDetailNotLogin", method = RequestMethod.POST)
	public Result<FreshCourseMobileView> findCourseDetailNotLogin(@RequestBody FreshCourseMobileView freshCourseMobileView) {
		
		String id = freshCourseMobileView.getId();
		if(StringUtils.isEmpty(id)) {
			return Result.error("缺少ID");
		}
		
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
		

		
		//return JSONUtil.toJsonStr(cResult.getData());
		return cResult;
	}
	


	/**
	 * 获取站点信息接口
	 * 
	 * @return 站点信息
	
	@ApiOperation(value = "获取站点信息接口", notes = "获取站点信息")
	@RequestMapping(value = "/findWebsite", method = RequestMethod.POST)
	public Result<WebsiteDTO> findWebsite() {
		com.wangxin.langshu.authCenter.util.base.Result<com.wangxin.langshu.authCenter.edu.vo.system.old.service.common.dto.WebsiteDTO>	websiteDTO= apiWebsiteBiz.get();
		if(websiteDTO.getCode().equals(ResultEnum.SUCCESS.getCode())) {
			return Result.success(websiteDTO.getData());
		}else {
			Result result = new Result();
			result.setCode(websiteDTO.getCode());
			result.setMsg(websiteDTO.getMsg());
			result.setData(websiteDTO.getData());

			return result;

		}
	}*/
	
	
	/**
	 * 注册接口
	 */
	@ApiOperation(value = "注册接口", notes = "注册成功返回登录信息")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result<com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO> register(@RequestBody com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserRegisterBO userRegisterBO) {
		com.wangxin.langshu.business.util.base.Result<com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO> r = apiUserInfoBiz.register(userRegisterBO);
		if(r.getCode().equals(ResultEnum.SUCCESS.getCode())) {
			return Result.success(r.getData());
		}else {
			Result result = new Result();
			result.setCode(r.getCode());
			result.setMsg(r.getMsg());
			result.setData(r.getData());
			return result;
		}
		
	}

	/**
	 * 用户密码登录接口
	 */
	@ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
	@RequestMapping(value = "/login/password", method = RequestMethod.POST)
	public Result<com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO> loginPassword(@RequestBody com.wangxin.langshu.business.edu.vo.user.old.common.bo.UserLoginPasswordBO userLoginPasswordBO) {
		userLoginPasswordBO.setLoginPlatform(OperatePlatEnum.MOBILE.getCode());
		userLoginPasswordBO.setIp(IpUtil.getIpAddr(httpServletRequest));
		com.wangxin.langshu.business.util.base.Result<com.wangxin.langshu.business.edu.vo.user.old.common.dto.UserLoginDTO> r = apiUserInfoBiz.loginPassword(userLoginPasswordBO);
		if(r.getCode().equals(ResultEnum.SUCCESS.getCode())) {
			return Result.success(r.getData());
		}else {
			Result result = new Result();
			result.setCode(r.getCode());
			result.setMsg(r.getMsg());
			result.setData(r.getData());
			return result;
		}
	}
	
	
	/**
	 * 用户修改密码接口
	 * 
	 */
	@ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public Result<Integer> updatePassword(@RequestBody com.wangxin.langshu.business.edu.vo.user.
			 old.common.bo.auth.UserUpdateBO userUpdateBO) {
		return apiUserInfoBiz.updatePassword(userUpdateBO);
	}

}

