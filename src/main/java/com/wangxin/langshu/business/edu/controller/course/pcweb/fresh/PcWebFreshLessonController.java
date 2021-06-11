package com.wangxin.langshu.business.edu.controller.course.pcweb.fresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonApplyService;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonService;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonViewVo;
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
 * 课时信息
 *
 */
@RestController
@RequestMapping(value = "/pcweb/course/fresh/lesson")
public class PcWebFreshLessonController extends BaseController {

	@Autowired
	private IPcWebFreshLessonService pcWebFreshLessonService;
	
	/**
	 * 根据课时Id查询课程附件URL
	 * 
	 * @param q
	 * @return
	 */
	@ApiOperation(value = "根据课时Id查询课程附件URL", notes = "根据课时Id查询课程附件URL")
	@RequestMapping(value = "/queryCourseDocUrlByLessonId", method = RequestMethod.POST)
	public Result<FreshLessonViewVo> queryCourseDocUrlByLessonId(@RequestBody FreshLessonViewVo q){
		return pcWebFreshLessonService.queryCourseDocUrlByLessonId(q);
	}
}
