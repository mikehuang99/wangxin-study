package com.wangxin.langshu.business.edu.controller.course.pcweb.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseVideoService;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 课程视频
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/fresh/course/video")
public class PcWebFreshCourseVideoController extends BaseController {

	@Autowired
	private IPcWebFreshCourseVideoService pcWebFreshCourseVideoService;
	
	/**
	 * 课程视频列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课程视频列表分页接口", notes = "课程视频列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshCourseVideoVo>> list(@RequestBody FreshCourseVideoQ q) {
		return pcWebFreshCourseVideoService.queryCourseVideos(q);
	}

	/**
	 * 课程视频添加接口
	 * 
	 * @param svo
	 * @return
	 */
	@ApiOperation(value = "课程视频添加接口", notes = "课程视频添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<FreshCourseVideoSaveVo> save(@RequestBody FreshCourseVideoSaveVo svo) {
		return pcWebFreshCourseVideoService.saveCourseVideo(svo);
	}

	/**
	 * 课程视频更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课程视频更新接口", notes = "课程视频更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  FreshCourseVideoUpdateVo uvo) {
		return pcWebFreshCourseVideoService.updateCourseVideo(uvo);
	}

	/**
	 * 课程视频删除接口
	 * 
	 * @param dvo
	 * @return
	 */
	@ApiOperation(value = "课程视频删除接口", notes = "课程视频删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  FreshCourseVideoDeleteVo dvo) {
		return pcWebFreshCourseVideoService.deleteCourseVideo(dvo);
	}

	/**
	 * 课程视频查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "课程视频查看接口", notes = "课程视频查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshCourseVideoViewVo> view(@RequestBody  FreshCourseVideoViewVo vvo) {
		return pcWebFreshCourseVideoService.viewCourseVideo(vvo);
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
