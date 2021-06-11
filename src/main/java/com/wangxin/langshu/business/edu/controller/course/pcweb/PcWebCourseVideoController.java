package com.wangxin.langshu.business.edu.controller.course.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoSaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonIdVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseVideoListDTO;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseVideoBiz;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程视频信息
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/course/video")
public class PcWebCourseVideoController extends BaseController {

	@Autowired
	private IPcWebCourseVideoBiz pcWebCourseVideoBiz;

	/**
	 * 章节视频库，课时视频库添加接口
	 * 
	 * @param authCourseVideoSaveBo
	 */
	@ApiOperation(value = "教师视频库保存接口", notes = "教师视频库保存接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody AuthCourseVideoSaveBO authCourseVideoSaveBO) {
		return pcWebCourseVideoBiz.save(authCourseVideoSaveBO);
	}

	/**
	 * 章节视频库列出（右边的展示）
	 * 
	 * @param chapterId
	 */
	@ApiOperation(value = "章节视频库列出接口", notes = "根据章节ID列出教师章节视频库信息")
	@RequestMapping(value = "/list/chapter", method = RequestMethod.POST)
	public Result<AuthCourseVideoListDTO> listByChapterId(@RequestBody AuthCourseVideoBO authCourseVideoBO) {
		return pcWebCourseVideoBiz.listByChapterId(authCourseVideoBO);
	}

	/**
	 * 课时视频列出（左边的展示）
	 * 
	 * @param lessonId
	 */
	@ApiOperation(value = "课时视频列出接口", notes = "根据课时ID列出教师课时视频信息")
	@RequestMapping(value = "/list/lesson", method = RequestMethod.POST)
	public Result<AuthCourseVideoListDTO> listByLessonId(@RequestBody AuthLessonIdVideoBO authCourseVideoBO) {
		return pcWebCourseVideoBiz.listByLessonId(authCourseVideoBO);
	}

	/**
	 * 课时视频更新接口
	 * 
	 * @param authCourseVideoUpdateBo
	 */
	@ApiOperation(value = "课时视频更新接口", notes = "教师课时视频更新信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody AuthCourseVideoUpdateBO authCourseVideoUpdateBO) {
		return pcWebCourseVideoBiz.update(authCourseVideoUpdateBO);
	}

	/**
	 * 视频删除
	 * 
	 * @param authCourseVideoDeleteBO
	 */
	@ApiOperation(value = "删除接口", notes = "教师视频信息删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> udpateById(@RequestBody AuthCourseVideoDeleteBO authCourseVideoDeleteBO) {
		return pcWebCourseVideoBiz.udpateById(authCourseVideoDeleteBO);
	}

}
