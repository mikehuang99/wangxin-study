package com.wangxin.langshu.business.edu.controller.user.pcweb;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudyProofService;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofViewVo;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseSaveVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 学习证明
 *
 */
@RestController
@RequestMapping(value = "/pcweb/user/studyProof")
public class PcWebStudyProofController extends BaseController {

	@Autowired
	private IPcWebStudyProofService pcWebStudyProofService;
	

	/**
	 * 学习证明添加接口
	 * 
	 * @param svo
	 * @return*/
	
	@ApiOperation(value = "学习证明添加接口", notes = "学习证明添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<StudyProofSaveVo> save(@RequestBody StudyProofSaveVo svo) {
		if(CollectionUtil.isEmpty(svo.getCourseUserIds())) {
			return Result.error("必须勾选课程才能打印学习证明");
		}
	
		 
		return pcWebStudyProofService.saveStudyProof(svo);
	} 


	/**
	 * 学习证明查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	/**
	@ApiOperation(value = "学习证明查看接口", notes = "学习证明查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<StudyProofViewVo> view(@RequestBody  StudyProofViewVo vvo) {
		return pcWebStudyProofService.viewStudyProof(vvo);
	}*/
	
}
