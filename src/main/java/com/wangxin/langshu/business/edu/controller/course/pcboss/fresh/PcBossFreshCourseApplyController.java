package com.wangxin.langshu.business.edu.controller.course.pcboss.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyApplyStatusREQ;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshCourseApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyAuditVo;
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
@RequestMapping(value = "/pcboss/course/fresh/course/apply")
public class PcBossFreshCourseApplyController extends BaseController {

	@Autowired
	private IPcBossFreshCourseApplyService pcBossFreshCourseApplyService;
	
	

	/**
	 * 课程信息审核列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课程信息审核列表分页接口", notes = "课程信息审核列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshCourseApplyVo>> list(@RequestBody FreshCourseApplyQ q) {
		return Result.success(pcBossFreshCourseApplyService.queryCourseApplys(q));
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
		return Result.success(pcBossFreshCourseApplyService.saveCourseApply(svo));
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
		boolean flag = pcBossFreshCourseApplyService.updateCourseApply(uvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
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
		return pcBossFreshCourseApplyService.deleteCourseApply(dvo);
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
		FreshCourseApplyViewVo vo = pcBossFreshCourseApplyService.viewCourseApply(vvo);
		return Result.success(vo);
	}
	
	
	/**
	 * 课程-审核接口
	 * 
	 * @return
	 */
	@ApiOperation(value = "课程-审核接口", notes = "课程-审核接口")
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Result<Integer> audit(@RequestBody FreshCourseApplyAuditVo freshCourseApplyAuditVo) {
		return pcBossFreshCourseApplyService.audit(freshCourseApplyAuditVo);
	}
}
