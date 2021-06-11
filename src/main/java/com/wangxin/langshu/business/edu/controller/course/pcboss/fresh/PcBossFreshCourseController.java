package com.wangxin.langshu.business.edu.controller.course.pcboss.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshCourseService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息
 */
@RestController
@RequestMapping(value = "/pcboss/course/fresh/course")
public class PcBossFreshCourseController extends BaseController {

	@Autowired
	//private IPcCommonFreshCourseService pcCommonFreshCourseService;
	private IPcBossFreshCourseService pcBossFreshCourseService;

	
	@ApiOperation(value = "课程分页列出接口", notes = "课程分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshCourseVo>> list(@RequestBody FreshCourseQ q) {
		return Result.success(pcBossFreshCourseService.queryCourses(q));
	}
	
	/**
	 * 课程信息添加接口
	 * 
	 * @param svo
	 * @return
	 */
	@ApiOperation(value = "课程信息添加接口", notes = "课程信息添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<FreshCourseSaveVo> save(@RequestBody FreshCourseSaveVo svo) {
		return Result.success(pcBossFreshCourseService.saveCourse(svo));
	}

	/**
	 * 课程信息更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课程信息更新接口", notes = "课程信息更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  FreshCourseUpdateVo uvo) {
		return pcBossFreshCourseService.updateCourse(uvo);
	}
	
	/**
	 * 课程信息上下架更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课程信息上下架更新接口", notes = "课程信息上下架更新接口")
	@RequestMapping(value = "/updateCoursePutaway", method = RequestMethod.POST)
	public Result<Integer> updateCoursePutaway(@RequestBody  FreshCourseUpdateVo uvo) {
		return pcBossFreshCourseService.updateCoursePutaway(uvo);
	}
	
	/**
	 * 课程信息是否有效更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课程信息是否有效更新接口", notes = "课程信息是否有效更新接口")
	@RequestMapping(value = "/updateCourseValidValue", method = RequestMethod.POST)
	public Result<Integer> updateCourseValidValue(@RequestBody  FreshCourseUpdateVo uvo) {
		return pcBossFreshCourseService.updateCourseValidValue(uvo);
	}
	

	/**
	 * 课程信息删除接口
	 * 
	 * @param dvo
	 * @return
	 */
	@ApiOperation(value = "课程信息删除接口", notes = "课程信息删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  FreshCourseDeleteVo dvo) {
		boolean flag = pcBossFreshCourseService.deleteCourse(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 课程信息查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "课程信息查看接口", notes = "课程信息查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshCourseViewVo> view(@RequestBody  FreshCourseViewVo vvo) {
		FreshCourseViewVo vo = pcBossFreshCourseService.viewCourse(vvo);
		return Result.success(vo);
	}

}
