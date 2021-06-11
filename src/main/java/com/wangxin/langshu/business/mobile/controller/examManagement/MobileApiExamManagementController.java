package com.wangxin.langshu.business.mobile.controller.examManagement;


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
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementUserService;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.UserExamPaperAnswerList;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.RedisKey;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/mobileApi/exam/management")
public class MobileApiExamManagementController extends BaseController {
	public static final String TOKEN = "token";
	

	
	@Autowired
	private IExamManagementService examManagementService;
	
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	private IExamManagementUserService examManagementUserService;
	
	/**
	 * 依靠学生userNo查找考试列表分页接口
	 * 
	 * @param ExamManagementQ
	 * @return
	 */
	@ApiOperation(value = "依靠学生userNo查找考试列表分页接口", notes = "依靠学生userNo查找考试列表分页接口")
	@RequestMapping(value = "/queryExamManagementsByStudentUserNo", method = RequestMethod.POST)
	public Result<Page<ExamManagementVo>> queryExamManagementsByStudentUserNo(@RequestBody ExamManagementQ q){
		return examManagementService.queryExamManagementsByStudentUserNo(q);
	}
	
	/**
	 * 根据学生编号查看考试管理接口
	 * 
	 * @param ExamManagementViewVo
	 * @return
	 */
	@ApiOperation(value = "根据学生编号查看考试管理接口", notes = "根据学生编号查看考试管理接口")
	@RequestMapping(value = "/viewByIdAndStudentUserNo", method = RequestMethod.POST)
	public Result<ExamManagementViewVo> viewByIdAndStudentUserNo(@RequestBody  ExamManagementViewVo examManagementViewVo) {
		return examManagementService.viewByIdAndStudentUserNo(examManagementViewVo);
	}

	
	
	@ApiOperation(value = "交试卷", notes = "交试卷")
	@RequestMapping(value = "/handInExamPaper", method = RequestMethod.POST)
	public Result<Integer> handInExamPaper(@RequestBody  UserExamPaperAnswerList userExamPaperAnswerList) {
		if(StringUtils.isEmpty(userExamPaperAnswerList.getUserNo())){
			return  Result.error("缺少用户userNo");
		}
		if(StringUtils.isEmpty(userExamPaperAnswerList.getExamManagementId())){
			return  Result.error("缺少examManagementId");
		}
		if(StringUtils.isEmpty(userExamPaperAnswerList.getExamPaperId())){
			return  Result.error("缺少examPaperId");
		}
		String userNo = userExamPaperAnswerList.getUserNo();
		String examPaperId = userExamPaperAnswerList.getExamPaperId();
		String examManagementId = userExamPaperAnswerList.getExamManagementId();
		
		String userExamPaperAnswerListJson = JSON.toJSONString(userExamPaperAnswerList.getUserExamPaperAnswerList());

		ExamManagementViewVo examManagementViewVo = new ExamManagementViewVo();
		examManagementViewVo.setId(examManagementId);
		examManagementViewVo = examManagementService.viewSimpleExamManagement(examManagementViewVo);
		if(ObjectUtil.isNull(examManagementViewVo)){
			return  Result.error("examManagementViewVo为空");
		}
		
		String suffix = examManagementId+"_"+examPaperId+"_"+userNo;
		if("0".equals(examManagementViewVo.getIsAllowRepeateHandInExamPaper())){
			if(this.redisTemplate.hasKey(RedisKey.EXAM_MANAGEMENT_EXAM_PAPER_USER_ANSWER_+suffix)){
	    		return Result.error(ResultEnum.CAN_NOT_REPEATE_HAND_INT_EXAM);
	    	}
			ExamManagementUserQ examManagementUserQ = new ExamManagementUserQ();
			examManagementUserQ.setExamManagementId(examManagementId);
			examManagementUserQ.setExamPaperId(examPaperId);
			examManagementUserQ.setExamUserNo(userNo);
			Page<ExamManagementUserVo> page = examManagementUserService.queryExamManagementUsers(examManagementUserQ);
			if(CollectionUtil.isNotEmpty(page.getList())) {
	    		return Result.error(ResultEnum.CAN_NOT_REPEATE_HAND_INT_EXAM);
			}
		}

    	logger.info("考试交卷缓存:"+RedisKey.EXAM_MANAGEMENT_EXAM_PAPER_USER_ANSWER_+suffix+":"+userExamPaperAnswerListJson);

		this.redisTemplate.opsForValue().set(RedisKey.EXAM_MANAGEMENT_EXAM_PAPER_USER_ANSWER_+suffix, userExamPaperAnswerListJson);

		return Result.success(1);
	}
	
	/**
	 * 根据考试ID和试卷ID查看考试与用户关系(只能查学生本人)
	 * 
	 * @param examManagementUserQ
	 * @return
	 */
	@ApiOperation(value = "根据考试ID和试卷ID查看考试与用户关系(只能查学生本人)", notes = "根据考试ID和试卷ID查看考试与用户关系(只能查学生本人)")
	@RequestMapping(value = "/viewExamManagementUserByExamManagementIdAndExamPaperId", method = RequestMethod.POST)
	public Result<ExamManagementUserVo> viewExamManagementUserByExamManagementIdAndExamPaperId(@RequestBody ExamManagementUserQ examManagementUserQ) {	
		if(StringUtils.isEmpty(examManagementUserQ.getExamManagementId())) {
			return Result.error("examManagementId不能为空");
		}
		if(StringUtils.isEmpty(examManagementUserQ.getExamPaperId())) {
			return Result.error("examPaperId不能为空");
		}
		/*
		if(StringUtils.isEmpty(examManagementUserQ.getExamUserNo())) {
			return Result.error("examUserNo不能为空");
		}*/
		if(StringUtils.isEmpty(examManagementUserQ.getUserNo())) {
			return Result.error("userNo不能为空");
		}
		
		//先检查一下这个考试是不是属于这个老师的
		ExamManagementViewVo examManagementViewVo = new ExamManagementViewVo();
		examManagementViewVo.setId(examManagementUserQ.getExamManagementId());
		examManagementViewVo = examManagementService.viewSimpleExamManagement(examManagementViewVo);
		if(ObjectUtil.isNull(examManagementViewVo)) {
			return Result.error("根据examManagementId查不到该考试");
		}
		if(StringUtils.isEmpty(examManagementUserQ.getUserNo())) {
			return Result.error("找不到userNo");
		}
		examManagementUserQ.setExamUserNo(examManagementUserQ.getUserNo());
		/*
		if(!examManagementUserQ.getUserNo().equals(examManagementViewVo.getTeacherUserNo())) {
			return Result.error("操作该考试与用户关系的不是该考试的教师，不能操作");
		}*/
		List<ExamManagementUserVo> examManagementUserVos = examManagementUserService.queryExamManagementUsers(examManagementUserQ).getList();
		if(CollectionUtil.isEmpty(examManagementUserVos)) {
			return Result.error("该用户还没参加考试");
		}
		if(examManagementUserVos.size()!=1) {
			return Result.error("该用户本次考试在数据库存在多于一条的答案，数据异常");
		}
		return Result.success(examManagementUserVos.get(0));
	}
	
	@ApiOperation(value = "保持会话,防止被登出", notes = "保持会话,防止被登出")
	@RequestMapping(value = "/keepSession", method = RequestMethod.POST)
	public Result<Integer> keepSession() {
		return Result.success(1);
	}
}

