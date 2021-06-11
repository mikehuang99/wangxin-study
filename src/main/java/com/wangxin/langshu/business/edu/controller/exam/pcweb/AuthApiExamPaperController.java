package com.wangxin.langshu.business.edu.controller.exam.pcweb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.ChapterBO;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamPaperService;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperVo;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.CacheRedis;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 试卷管理
 *
 */
@RestController
@RequestMapping(value = "/pcweb/exam/paper")
public class AuthApiExamPaperController extends BaseController {

	@Autowired
	private IExamPaperService examPaperService;
	
	@Autowired
	private IExamManagementService examManagementService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/**
	 * 试卷列表分页接口
	 * 
	 * @param examPaperQ
	 * @return
	 */
	@ApiOperation(value = "试卷列表分页接口", notes = "试卷列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<ExamPaperVo>> list(@RequestBody ExamPaperQ examPaperQ) {	
		examPaperQ.setTeacherUserNo(examPaperQ.getUserNo());
		return examPaperService.queryExamPapers(examPaperQ);
	}

	/**
	 * 试卷添加接口
	 * 
	 * @param examPaperSaveVo
	 * @return
	 */
	@ApiOperation(value = "试卷添加接口", notes = "试卷添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody ExamPaperSaveVo examPaperSaveVo) {
		examPaperSaveVo.setTeacherUserNo(examPaperSaveVo.getUserNo());
		return examPaperService.saveExamPaper(examPaperSaveVo);
	}

	/**
	 * 试卷更新接口
	 * 
	 * @param examPaperUpdateVo
	 * @return
	 */
	@ApiOperation(value = "试卷更新接口", notes = "试卷更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  ExamPaperUpdateVo examPaperUpdateVo) {
		return examPaperService.updateExamPaper(examPaperUpdateVo);
	}

	/**
	 * 试卷删除接口
	 * 
	 * @param examPaperDeleteVo
	 * @return
	 */
	@ApiOperation(value = "试卷删除接口", notes = "试卷删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  ExamPaperDeleteVo examPaperDeleteVo) {
		return examPaperService.deleteExamPaper(examPaperDeleteVo);
	}

	/**
	 * 试卷查看接口
	 * 
	 * @param examPaperViewVo
	 * @return
	 */
	@ApiOperation(value = "试卷查看接口", notes = "试卷查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<ExamPaperViewVo> view(@RequestBody  ExamPaperViewVo examPaperViewVo) {
		ExamPaperViewVo vo = examPaperService.viewExamPaper(examPaperViewVo);
		if(!examPaperViewVo.getUserNo().equals(vo.getTeacherUserNo())){
			return Result.error("只有该试卷的教师才可以查看");
		}
		return Result.success(vo);
	}
	
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
		/*
		if(result.getCode()==ResultEnum.NOT_YET_OPEN_EXAM.getCode()){
			return Result.success(vo);
		}else{
			if(this.redisTemplate.hasKey(RedisKey.EXAM_PAPER_PREFIX_+examManagementViewVo.getExamPaperId())){
				String examPaperContent = (String) this.redisTemplate.opsForValue().get(RedisKey.EXAM_PAPER_PREFIX_+examManagementViewVo.getExamPaperId());
				List<ExamQuestionVo> examQuestionVos =  (List<ExamQuestionVo>) JSON.parse(examPaperContent);
				vo.setExamQuestionVos(examQuestionVos);
			}
		}*/
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
		
		/*
		Result<ExamManagementViewVo> result = examManagementService.viewByIdAndStudentUserNo(examManagementViewVo);
		if(result.getCode()==ResultEnum.ERROR.getCode()){
			return Result.error(result.getMsg());
		}
		if(result.getCode()==ResultEnum.NOT_YET_OPEN_EXAM.getCode()){
			return Result.success(vo);
		}else{
			if(this.redisTemplate.hasKey(RedisKey.EXAM_PAPER_PREFIX_+examManagementViewVo.getExamPaperId())){
				String examPaperContent = (String) this.redisTemplate.opsForValue().get(RedisKey.EXAM_PAPER_PREFIX_+examManagementViewVo.getExamPaperId());
				List<ExamQuestionVo> examQuestionVos =  (List<ExamQuestionVo>) JSON.parse(examPaperContent);
				vo.setExamQuestionVos(examQuestionVos);
			}
		}*/
		return Result.success(vo);
	}
	
	
	@ApiOperation(value = "获取章节对应的题目数量列表", notes = "获取章节对应的题目数量列表")
	@RequestMapping(value = "/findDetailByChapterList", method = RequestMethod.POST)
	public Result<ExamPaperViewVo> findDetailByChapterList(@RequestBody  ExamPaperViewVo vo) {
		ExamPaperViewVo examPaperViewVo= examPaperService.findDetailByChapterList(vo.getCourseId());
		return Result.success(examPaperViewVo);
	}
	
	
	
	@ApiOperation(value = "生成试卷接口", notes = "生成试卷接口")
	@RequestMapping(value = "/generalExamPaper", method = RequestMethod.POST)
	public Result<Integer> generalExamPaper(@RequestBody  ExamPaperViewVo examPaperViewVo) {
		return examPaperService.reGeneralExamPaper(examPaperViewVo.getId());
	}
	
	

}
