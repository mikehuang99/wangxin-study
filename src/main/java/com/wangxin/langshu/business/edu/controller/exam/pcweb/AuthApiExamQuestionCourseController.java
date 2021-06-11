package com.wangxin.langshu.business.edu.controller.exam.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamQuestionCourseService;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 试题课程绑定管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/exam/question/course")
public class AuthApiExamQuestionCourseController extends BaseController {

	@Autowired
	private IExamQuestionCourseService examQuestionCourseService;
	
	

	/**
	 * 试题课程绑定列表分页接口
	 * 
	 * @param examQuestionREQ
	 * @return
	 */
	@ApiOperation(value = "试题课程绑定列表分页接口", notes = "试题课程绑定列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<ExamQuestionCourseVo>> list(@RequestBody ExamQuestionCourseQ examQuestionCourseQ) {	
		return Result.success(examQuestionCourseService.queryExamQuestionCourses(examQuestionCourseQ));
	}

	/**
	 * 试题课程绑定添加接口
	 * 
	 * @param advertSaveREQ
	 * @return
	 */
	@ApiOperation(value = "试题课程绑定添加接口", notes = "试题课程绑定添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody ExamQuestionCourseSaveVo examQuestionCourseSaveVo) {
		boolean flag = examQuestionCourseService.saveExamQuestionCourse(examQuestionCourseSaveVo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 试题课程绑定更新接口
	 * 
	 * @param examQuestion
	 * @return
	 */
	@ApiOperation(value = "试题课程绑定更新接口", notes = "试题课程绑定更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  ExamQuestionCourseUpdateVo examQuestionCourseUpdateVo) {
		boolean flag = examQuestionCourseService.updateExamQuestionCourse(examQuestionCourseUpdateVo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 试题课程绑定删除接口
	 * 
	 * @param examQuestion
	 * @return
	 */
	@ApiOperation(value = "试题课程绑定删除接口", notes = "试题课程绑定删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  ExamQuestionCourseDeleteVo examQuestionCourseDeleteVo) {
		boolean flag = examQuestionCourseService.deleteExamQuestionCourse(examQuestionCourseDeleteVo);
		if (flag) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
	}

	/**
	 * 试题课程绑定查看接口
	 * 
	 * @param advertViewREQ
	 * @return
	 */
	@ApiOperation(value = "试题课程绑定查看接口", notes = "试题课程绑定查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<ExamQuestionCourseViewVo> view(@RequestBody  ExamQuestionCourseViewVo examQuestionCourseViewVo) {
		ExamQuestionCourseViewVo vo = examQuestionCourseService.viewExamQuestionCourse(examQuestionCourseViewVo);
		return Result.success(vo);
	}

}
