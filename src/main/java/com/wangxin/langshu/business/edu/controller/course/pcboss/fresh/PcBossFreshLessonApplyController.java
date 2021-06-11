package com.wangxin.langshu.business.edu.controller.course.pcboss.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshLessonApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcboss/course/fresh/lesson/apply")
public class PcBossFreshLessonApplyController extends BaseController {

	@Autowired
	private IPcBossFreshLessonApplyService pcBossFreshLessonApplyService;
	
	/**
	 * 课时信息审核列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "课时信息审核列表分页接口", notes = "课时信息审核列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshLessonApplyVo>> list(@RequestBody FreshLessonApplyQ q) {
		if(StringUtils.isEmpty(q.getChapterId())){
			return Result.error("章节ID不能为空");
		}
		return Result.success(pcBossFreshLessonApplyService.queryLessonApplys(q));
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
		return Result.success(pcBossFreshLessonApplyService.saveLessonApply(svo));
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
		return Result.success(pcBossFreshLessonApplyService.updateLessonApply(uvo));
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
		boolean flag = pcBossFreshLessonApplyService.deleteLessonApply(dvo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
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
		FreshLessonApplyViewVo vo = pcBossFreshLessonApplyService.viewLessonApply(vvo);
		return Result.success(vo);
	}
	
	
}
