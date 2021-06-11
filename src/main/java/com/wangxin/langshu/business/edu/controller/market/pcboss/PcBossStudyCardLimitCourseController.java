package com.wangxin.langshu.business.edu.controller.market.pcboss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardLimitCourseService;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 优惠券课程限制管理
 *
 */
@RestController
@RequestMapping(value = "/pcboss/market/studycardlimitcourse")
public class PcBossStudyCardLimitCourseController extends BaseController {

	@Autowired
	private IStudyCardLimitCourseService studyCardLimitCourseService;
	
	

	/**
	 * 优惠券课程限制列表分页接口
	 * 
	 * @param StudyCardLimitCourseQ
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制列表分页接口", notes = "优惠券课程限制列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<StudyCardLimitCourseVo>> list(@RequestBody StudyCardLimitCourseQ q) {	
		return Result.success(studyCardLimitCourseService.queryStudyCardLimitCourses(q));
	}

	/**
	 * 优惠券课程限制添加接口
	 * 
	 * @param StudyCardLimitCourseSaveVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制添加接口", notes = "优惠券课程限制添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody StudyCardLimitCourseSaveVo svo) {
		boolean flag = studyCardLimitCourseService.saveStudyCardLimitCourse(svo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券课程限制更新接口
	 * 
	 * @param StudyCardLimitCourseUpdateVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制更新接口", notes = "优惠券课程限制更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  StudyCardLimitCourseUpdateVo uvo) {
		boolean flag = studyCardLimitCourseService.updateStudyCardLimitCourse(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券课程限制删除接口
	 * 
	 * @param StudyCardLimitCourseDeleteVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制删除接口", notes = "优惠券课程限制删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  StudyCardLimitCourseDeleteVo dvo) {
		boolean flag = studyCardLimitCourseService.deleteStudyCardLimitCourse(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 优惠券课程限制查看接口
	 * 
	 * @param StudyCardLimitCourseViewVo
	 * @return
	 */
	@ApiOperation(value = "优惠券课程限制查看接口", notes = "优惠券课程限制查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<StudyCardLimitCourseViewVo> view(@RequestBody  StudyCardLimitCourseViewVo vvo) {
		StudyCardLimitCourseViewVo vo = studyCardLimitCourseService.viewStudyCardLimitCourse(vvo);
		return Result.success(vo);
	}
	
	
	
	
	

}
