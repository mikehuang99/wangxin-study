package com.wangxin.langshu.business.edu.controller.course.pcboss.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshChapterApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplySaveVo;
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
@RequestMapping(value = "/pcboss/course/fresh/chapter/apply")
public class PcBossFreshChapterApplyController extends BaseController {

	@Autowired
	private IPcBossFreshChapterApplyService pcBossFreshChapterApplyService;
	
	/**
	 * 章节信息审核列表分页接口
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "章节信息审核列表分页接口", notes = "章节信息审核列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<FreshChapterApplyVo>> list(@RequestBody FreshChapterApplyQ q) {
		return Result.success(pcBossFreshChapterApplyService.queryChapterApplys(q));
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
		return Result.success(pcBossFreshChapterApplyService.saveChapterApply(svo));
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
		
		return Result.success(pcBossFreshChapterApplyService.updateChapterApply(uvo));
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
		return pcBossFreshChapterApplyService.deleteChapterApply(dvo);
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
		FreshChapterApplyViewVo vo = pcBossFreshChapterApplyService.viewChapterApply(vvo);
		return Result.success(vo);
	}
	
	
}
