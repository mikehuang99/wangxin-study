package com.wangxin.langshu.business.edu.controller.course.pcweb.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshChapterApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/fresh/chapter/apply")
public class PcWebFreshChapterApplyController extends BaseController {

	@Autowired
	private IPcWebFreshChapterApplyService pcWebFreshChapterApplyService;
	
	/**
	 * 章节信息审核列表分页接口(不分页)
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "章节信息审核列表分页接口(不分页)", notes = "章节信息审核列表分页接口(不分页)")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshChapterApplyVo>> list(@RequestBody FreshChapterApplyQ q) {
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		return Result.success(pcWebFreshChapterApplyService.queryChapterApplys(q));
	}

	/**
	 * 章节信息审核添加接口
	 * 
	 * @param svo
	 * @return
	 */
	@ApiOperation(value = "章节信息审核添加接口", notes = "章节信息审核添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<FreshChapterApplySaveVo> save(@RequestBody FreshChapterApplySaveVo svo) {
		return Result.success(pcWebFreshChapterApplyService.saveChapterApply(svo));
	}

	/**
	 * 章节信息审核更新接口
	 * 
	 * @param uvo
	 * @return
	 */
	@ApiOperation(value = "章节信息审核更新接口", notes = "章节信息审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<FreshChapterApplyUpdateVo> update(@RequestBody  FreshChapterApplyUpdateVo uvo) {
		
		return Result.success(pcWebFreshChapterApplyService.updateChapterApply(uvo));
	}

	/**
	 * 章节信息审核删除接口
	 * 
	 * @param dvo
	 * @return
	 */
	@ApiOperation(value = "章节信息审核删除接口", notes = "章节信息审核删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  FreshChapterApplyDeleteVo dvo) {
		return pcWebFreshChapterApplyService.deleteChapterApply(dvo);
	}

	/**
	 * 章节信息审核查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "章节信息审核查看接口", notes = "章节信息审核查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<FreshChapterApplyViewVo> view(@RequestBody  FreshChapterApplyViewVo vvo) {
		FreshChapterApplyViewVo vo = pcWebFreshChapterApplyService.viewChapterApply(vvo);
		return Result.success(vo);
	}
	
	/**
	 * 更新章节排序接口
	 */
	@ApiOperation(value = "更新章节排序接口", notes = "更新章节排序接口")
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public Result<Integer> sort(@RequestBody FreshChapterApplySortVo freshChapterApplySortVo) {
		return pcWebFreshChapterApplyService.sort(freshChapterApplySortVo);
	}
}
