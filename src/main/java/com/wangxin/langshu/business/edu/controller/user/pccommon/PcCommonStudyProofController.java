package com.wangxin.langshu.business.edu.controller.user.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.user.pcweb.IPcWebStudyProofService;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofViewVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 学习证明
 *
 */
@RestController
@RequestMapping(value = "/pccommon/user/studyProof")
public class PcCommonStudyProofController extends BaseController {

	@Autowired
	private IPcWebStudyProofService pcWebStudyProofService;
	

	


	/**
	 * 学习证明查看接口
	 * 
	 * @param vvo
	 * @return
	 */
	@ApiOperation(value = "学习证明查看接口", notes = "学习证明查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<StudyProofViewVo> view(@RequestBody  StudyProofViewVo vvo) {
		return pcWebStudyProofService.viewStudyProof(vvo);
	}
	
}
