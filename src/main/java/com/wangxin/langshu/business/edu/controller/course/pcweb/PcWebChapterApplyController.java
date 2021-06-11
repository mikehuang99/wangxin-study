package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplySortBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyUpdateDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyViewDTO;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebChapterApplyBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 章节信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/course/chapter/audit")
public class PcWebChapterApplyController extends BaseController {

	@Autowired
	private IPcWebChapterApplyBiz pcWebChapterApplyBiz;

	/**
	 * 章节查看接口
	 */
	@ApiOperation(value = "章节查看接口", notes = "根据章节ID查看章节信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthChapterApplyViewDTO> view(@RequestBody AuthChapterApplyViewBO authChapterApplyViewBO) {
		return pcWebChapterApplyBiz.view(authChapterApplyViewBO);
	}

	/**
	 * 章节删除接口
	 */
	@ApiOperation(value = "章节删除接口", notes = "章节删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody AuthChapterApplyDeleteBO authChapterApplyDeleteBO) {
		return pcWebChapterApplyBiz.delete(authChapterApplyDeleteBO);
	}

	/**
	 * 章节列出接口
	 */
	@ApiOperation(value = "章节列出接口", notes = "根据课程ID列出章节信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<AuthChapterApplyListDTO> listByCourseNo(@RequestBody AuthChapterApplyBO authChapterApplyBO) {
		return pcWebChapterApplyBiz.listByCourseNo(authChapterApplyBO);
	}

	/**
	 * 章节添加接口
	 */
	@ApiOperation(value = "章节添加接口", notes = "章节添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<AuthChapterApplySaveDTO> save(@RequestBody AuthChapterApplySaveBO authChapterApplySaveBO) {
		return pcWebChapterApplyBiz.save(authChapterApplySaveBO);
	}

	/**
	 * 章节更新接口
	 */
	@ApiOperation(value = "章节更新接口", notes = "章节更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<AuthChapterApplyUpdateDTO> update(@RequestBody AuthChapterApplyUpdateBO authChapterApplyUpdateBO) {
		return pcWebChapterApplyBiz.update(authChapterApplyUpdateBO);
	}

	/**
	 * 更新章节排序接口
	 */
	@ApiOperation(value = "更新章节排序接口", notes = "更新章节排序接口")
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public Result<Integer> sort(@RequestBody AuthChapterApplySortBO authChapterApplySortBO) {
		return pcWebChapterApplyBiz.sort(authChapterApplySortBO);
	}

}
