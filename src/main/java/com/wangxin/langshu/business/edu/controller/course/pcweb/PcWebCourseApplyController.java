package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyListBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyStandBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplyListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplyViewDTO;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseApplyBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/course/apply")
public class PcWebCourseApplyController extends BaseController {

	@Autowired
	private IPcWebCourseApplyBiz pcWebCourseApplyBiz;

	/**
	 * 教师课程详情接口
	 */
	@ApiOperation(value = "课程详情接口", notes = "根据课程ID返回课程详情信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthCourseApplyViewDTO> view(@RequestBody AuthCourseApplyViewBO authCourseApplyViewBO) {
		return pcWebCourseApplyBiz.view(authCourseApplyViewBO);
	}

	/**
	 * 教师课程分页接口
	 */
	@ApiOperation(value = "课程分页接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthCourseApplyListDTO>> listForPage(@RequestBody AuthCourseApplyListBO authCourseApplyListBO) {
		return pcWebCourseApplyBiz.listForPage(authCourseApplyListBO);
	}

	/**
	 * 教师课程保存接口
	 */
	@ApiOperation(value = "课程保存接口", notes = "课程保存接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<AuthCourseApplySaveDTO> save(@RequestBody AuthCourseApplySaveBO authCourseApplySaveBO) {
		return pcWebCourseApplyBiz.save(authCourseApplySaveBO);
	}

	/**
	 * 教师课程更新接口
	 */
	@ApiOperation(value = "课程更新接口", notes = "课程更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody AuthCourseApplyUpdateBO authCourseApplyUpdateBO) {
		return pcWebCourseApplyBiz.update(authCourseApplyUpdateBO);
	}

	/**
	 * 教师课程信息设置上下架接口
	 */
	@ApiOperation(value = "课程上下架接口", notes = "课程上下架接口")
	@RequestMapping(value = "/stand", method = RequestMethod.POST)
	public Result<Integer> stand(@RequestBody AuthCourseApplyStandBO authCourseApplyStandBO) {
		return pcWebCourseApplyBiz.stand(authCourseApplyStandBO);
	}

	/**
	 * 教师课程信息删除接口
	 * 
	 */
	@ApiOperation(value = "删除接口", notes = "教师课程信息删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody AuthCourseApplyDeleteBO authCourseApplyDeleteBO) {
		return pcWebCourseApplyBiz.delete(authCourseApplyDeleteBO);
	}

}
