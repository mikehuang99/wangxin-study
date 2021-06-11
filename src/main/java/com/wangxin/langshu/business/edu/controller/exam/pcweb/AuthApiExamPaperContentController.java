package com.wangxin.langshu.business.edu.controller.exam.pcweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperContentService;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 试卷内容管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/exam/paper/content")
public class AuthApiExamPaperContentController extends BaseController {

	@Autowired
	private IExamPaperContentService examPaperContentService;
	
	@Autowired
	private IExamManagementService examManagementService;

	

	
	/**
	 * 根据examPaperId查看试卷内容接口(允许教师角色)
	 * 
	 * @param examPaperContentViewVo
	 * @return
	 */
	@ApiOperation(value = "根据examPaperId查看试卷内容接口(允许教师角色)", notes = "根据examPaperId查看试卷内容接口(允许教师角色)")
	@RequestMapping(value = "/viewExamPaperContentByExamPaperIdAllowTeacher", method = RequestMethod.POST)
	public Result<ExamPaperContentVo> viewExamPaperContentByExamPaperIdAllowTeacher(@RequestBody  ExamPaperContentViewVo examPaperContentViewVo) {
		return examPaperContentService.viewExamPaperContentByExamPaperIdAllowTeacher(examPaperContentViewVo);
	}
	
	/**
	 * 根据examPaperId查看试卷内容接口(允许学生角色)
	 * 
	 * @param examPaperContentViewVo
	 * @return
	 */
	@ApiOperation(value = "根据examPaperId查看试卷内容接口(允许学生角色)", notes = "根据examPaperId查看试卷内容接口(允许学生角色)")
	@RequestMapping(value = "/viewExamPaperContentByExamPaperIdAllowStudent", method = RequestMethod.POST)
	public Result<ExamPaperContentVo> viewExamPaperContentByExamPaperIdAllowStudent(@RequestBody  ExamPaperContentViewVo examPaperContentViewVo) {
		return examPaperContentService.viewExamPaperContentByExamPaperIdAllowStudent(examPaperContentViewVo);
	}
	
	
	

}
