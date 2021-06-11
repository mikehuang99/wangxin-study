package com.wangxin.langshu.business.mobile.controller.examPaper;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperContentService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperService;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi/exam/paper")
public class MobileApiExamPaperController extends BaseController {
	public static final String TOKEN = "token";
	
	@Autowired
	private IExamPaperService examPaperService;
	
	@Autowired
	private IExamManagementService examManagementService;
	
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private IExamPaperContentService examPaperContentService;
	
	
	/**
	 * 根据考试管理的ID和试卷ID查看试卷接口
	 * 
	 * @param examPaperViewVo
	 * @return
	 */
	@ApiOperation(value = "根据考试管理的ID和试卷ID查看试卷接口", notes = "根据考试管理的ID和试卷ID查看试卷接口")
	@RequestMapping(value = "/viewByExamManagementIdAndExamPaperId", method = RequestMethod.POST)
	public Result<ExamPaperViewVo> viewByExamManagementIdAndExamPaperId(@RequestBody  ExamManagementViewVo examManagementViewVo) {
		if(StringUtils.isEmpty(examManagementViewVo.getId())){
			return Result.error("缺少考试管理的ID");
		}
		if(StringUtils.isEmpty(examManagementViewVo.getExamPaperId())){
			return Result.error("缺少试卷ID");
		}
		ExamPaperViewVo examPaperViewVo = new ExamPaperViewVo();
		examPaperViewVo.setId(examManagementViewVo.getExamPaperId());
		ExamPaperViewVo vo = examPaperService.viewExamPaper(examPaperViewVo);
		
		Result<ExamManagementViewVo> result = examManagementService.viewByIdAndStudentUserNo(examManagementViewVo);
		if(result.getCode()==ResultEnum.ERROR.getCode()){
			return Result.error(result.getMsg());
		}
		return Result.success(vo);
	}
	
	/**
	 * 根据考试管理的ID和试卷ID查看试卷内容接口
	 * 
	 * @param examPaperViewVo
	 * @return
	 */
	@ApiOperation(value = "根据考试管理的ID和试卷ID查看试卷内容接口", notes = "根据考试管理的ID和试卷ID查看试卷内容接口")
	@RequestMapping(value = "/viewExamPaperContentByExamManagementIdAndExamPaperId", method = RequestMethod.POST)
	public Result<ExamPaperViewVo> viewExamPaperContentByExamManagementIdAndExamPaperId(@RequestBody  ExamManagementViewVo examManagementViewVo) {
		if(StringUtils.isEmpty(examManagementViewVo.getId())){
			return Result.error("缺少考试管理的ID");
		}
		if(StringUtils.isEmpty(examManagementViewVo.getExamPaperId())){
			return Result.error("缺少试卷ID");
		}
		ExamPaperViewVo examPaperViewVo = new ExamPaperViewVo();
		examPaperViewVo.setId(examManagementViewVo.getExamPaperId());
		ExamPaperViewVo vo = examPaperService.viewExamPaper(examPaperViewVo);
		
		ExamManagementViewVo evo = examManagementService.viewExamManagement(examManagementViewVo);
		//如果未到考试时间,最多只能提前一分钟获取试卷
		
		
		
		if((new Date().getTime()<evo.getStartTime().getTime()-60*1000L)){
			return Result.error(ResultEnum.NOT_YET_OPEN_EXAM);
		}else{
			if(this.redisTemplate.hasKey(RedisKey.EXAM_PAPER_WITH_NO_ANSWER_PREFIX_+examManagementViewVo.getExamPaperId())){
				String examPaperContent = (String) this.redisTemplate.opsForValue().get(RedisKey.EXAM_PAPER_WITH_NO_ANSWER_PREFIX_+examManagementViewVo.getExamPaperId());
				List<ExamQuestionVo> examQuestionVos =  (List<ExamQuestionVo>) JSON.parse(examPaperContent);
				vo.setExamQuestionVos(examQuestionVos);
			}else{
				return Result.error("还没有生成试卷，获取试卷失败");
			}
		}
		
		
		return Result.success(vo);
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

