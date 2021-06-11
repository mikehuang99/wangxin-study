package com.wangxin.langshu.business.edu.controller.course.pcweb.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/fresh/lesson/apply")
public class PcWebFreshLessonApplyController extends BaseController {

	@Autowired
	private IPcWebFreshLessonApplyService pcWebFreshLessonApplyService;
	
	/**
	 * 课时信息审核列表分页接口(不分页)
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课时信息审核列表分页接口(不分页)", notes = "课时信息审核列表分页接口(不分页)")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshLessonApplyVo>> list(@RequestBody FreshLessonApplyQ q) {
		if(StringUtils.isEmpty(q.getChapterId())){
			throw new BaseException("缺少chapterId");
		}
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		return Result.success(pcWebFreshLessonApplyService.queryLessonApplys(q));
	}

	/**
	 * 课时信息审核添加接口
	 * 
	 * @param svo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核添加接口", notes = "课时信息审核添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<FreshLessonApplySaveVo> save(@RequestBody FreshLessonApplySaveVo svo) {
		return Result.success(pcWebFreshLessonApplyService.saveLessonApply(svo));
	}

	/**
	 * 课时信息审核更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核更新接口", notes = "课时信息审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<FreshLessonApplyUpdateVo> update(@RequestBody  FreshLessonApplyUpdateVo uvo) {
		return Result.success(pcWebFreshLessonApplyService.updateLessonApply(uvo));
	}
	
	/**
	 * 课时信息审核直播更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核直播更新接口", notes = "课时信息审核更新接口")
	@RequestMapping(value = "/updateLessonApplyLiveBroadcast", method = RequestMethod.POST)
	public Result<FreshLessonApplyUpdateVo> updateLessonApplyLiveBroadcast(@RequestBody  FreshLessonApplyUpdateVo uvo) {
		return pcWebFreshLessonApplyService.updateLessonApplyLiveBroadcast(uvo);
	}
	
	
	/**
	 * 课时信息审核更新课时类型接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核更新课时类型接口", notes = "课时信息审核更新课时类型接口")
	@RequestMapping(value = "/updateLessonApplyLessonType", method = RequestMethod.POST)
	public Result<Integer> updateLessonApplyLessonType(@RequestBody  FreshLessonApplyUpdateVo uvo) {
		return pcWebFreshLessonApplyService.updateLessonApplyLessonType(uvo);
	}
	


	/**
	 * 课时信息审核删除接口
	 * 
	 * @param dvo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核删除接口", notes = "课时信息审核删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  FreshLessonApplyDeleteVo dvo) {
		return pcWebFreshLessonApplyService.deleteLessonApply(dvo);
	}

	/**
	 * 课时信息审核查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核查看接口", notes = "课时信息审核查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshLessonApplyViewVo> view(@RequestBody  FreshLessonApplyViewVo vvo) {
		FreshLessonApplyViewVo vo = pcWebFreshLessonApplyService.viewLessonApply(vvo);
		return Result.success(vo);
	}
	
	/**
	 * 课时信息审核排序接口
	 * 
	 * @param freshLessonApplySortVo
	 * @return
	 */
	@ApiOperation(value = "课时信息审核排序接口", notes = "课时信息审核排序接口")
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public Result<Integer> sort(@RequestBody  FreshLessonApplySortVo freshLessonApplySortVo) {
		return pcWebFreshLessonApplyService.sort(freshLessonApplySortVo);
	
	}
}
