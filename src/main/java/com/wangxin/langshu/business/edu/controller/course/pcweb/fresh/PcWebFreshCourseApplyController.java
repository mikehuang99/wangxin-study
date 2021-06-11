package com.wangxin.langshu.business.edu.controller.course.pcweb.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshCourseApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/fresh/course/apply")
public class PcWebFreshCourseApplyController extends BaseController {

	@Autowired
	private IPcWebFreshCourseApplyService pcWebFreshCourseApplyService;
	
	

	/**
	 * 课程信息审核列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课程信息审核列表分页接口", notes = "课程信息审核列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshCourseApplyVo>> list(@RequestBody FreshCourseApplyQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("缺少userNo,必须在登录的情况下查询");
		}
		q.setTeacherUserNo(q.getUserNo());//教师只能查询教师本人当下的课程
		return Result.success(pcWebFreshCourseApplyService.queryCourseApplys(q));
	}

	/**
	 * 课程信息审核添加接口
	 * 
	 * @param svo
	 * @return
	 */
	@ApiOperation(value = "课程信息审核添加接口", notes = "课程信息审核添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<FreshCourseApplySaveVo> save(@RequestBody FreshCourseApplySaveVo svo) {
		return Result.success(pcWebFreshCourseApplyService.saveCourseApply(svo));
	}

	/**
	 * 课程信息审核更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课程信息审核更新接口", notes = "课程信息审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  FreshCourseApplyUpdateVo uvo) {
		uvo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		boolean flag = pcWebFreshCourseApplyService.updateCourseApply(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
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
		return pcWebFreshCourseApplyService.updateCoursePutaway(uvo);
	}
	
	/**
	 * 课程信息审核删除接口
	 * 
	 * @param dvo
	 * @return
	 */
	@ApiOperation(value = "课程信息审核删除接口", notes = "课程信息审核删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  FreshCourseApplyDeleteVo dvo) {
		boolean flag = pcWebFreshCourseApplyService.deleteCourseApply(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 课程信息审核查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "课程信息审核查看接口", notes = "课程信息审核查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshCourseApplyViewVo> view(@RequestBody  FreshCourseApplyViewVo vvo) {
		return pcWebFreshCourseApplyService.viewCourseApply(vvo);
	}
}
