package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplySortBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebLessonApplyBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课时信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/course/chapter/lesson/audit")
public class PcWebLessonApplyController extends BaseController {

	@Autowired
	private IPcWebLessonApplyBiz pcWebLessonApplyBiz;

	/**
	 * 课时列出接口
	 */
	@ApiOperation(value = "课时列出接口", notes = "根据章节ID列出课时信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<AuthLessonAuditListDTO> listByChapterId(@RequestBody AuthLessonApplyBO authLessonApplyBO) {
		return pcWebLessonApplyBiz.listByChapterId(authLessonApplyBO);
	}

	/**
	 * 课时查看接口
	 */
	@ApiOperation(value = "课时查看接口", notes = "课时查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthLessonAuditViewDTO> view(@RequestBody AuthLessonApplyViewBO authLessonApplyViewBO) {
		return pcWebLessonApplyBiz.view(authLessonApplyViewBO);
	}

	/**
	 * 课时删除接口
	 */
	@ApiOperation(value = "课时删除接口", notes = "课时删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody AuthLessonApplyDeleteBO authLessonApplyDeleteBO) {
		return pcWebLessonApplyBiz.delete(authLessonApplyDeleteBO);
	}

	/**
	 * 课时添加接口
	 */
	@ApiOperation(value = "课时添加接口", notes = "课时添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<AuthLessonApplySaveDTO> save(@RequestBody AuthLessonApplySaveBO authLessonApplySaveBO) {
		return pcWebLessonApplyBiz.save(authLessonApplySaveBO);
	}

	/**
	 * 课时更新接口
	 */
	@ApiOperation(value = "课时更新接口", notes = "课时更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody AuthLessonApplyUpdateBO authLessonApplyUpdateBO) {
		return pcWebLessonApplyBiz.update(authLessonApplyUpdateBO);
	}

	/**
	 * 课时排序接口
	 */
	@ApiOperation(value = "更新课时排序", notes = "更新课时排序")
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public Result<Integer> sort(@RequestBody AuthLessonApplySortBO authLessonApplySortBO) {
		return pcWebLessonApplyBiz.sort(authLessonApplySortBO);
	}

}
