package com.wangxin.langshu.business.edu.controller.exam.pcweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementUserService;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 考试与用户关系
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/pcweb/exam/management/user")
public class AuthApiExamManagementUserController extends BaseController {

	@Autowired
	private IExamManagementUserService examManagementUserService;

	@Autowired
	private IExamManagementService examManagementService;
	
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	/**
	 * 评卷
	 * 
	 * @param vo
	 * @return
	 */
	@ApiOperation(value = "评卷", notes = "评卷")
	@RequestMapping(value = "/markExamPaperByTeacher", method = RequestMethod.POST)
	public Result<Integer> markExamPaperByTeacher(@RequestBody ExamManagementUserUpdateVo vo) {	
		return examManagementUserService.markExamPaper(vo);
	}

	/**
	 * 查看考试与用户关系
	 * 
	 * @param examManagementUserQ
	 * @return
	 */
	@ApiOperation(value = "查看考试与用户关系", notes = "查看考试与用户关系")
	@RequestMapping(value = "/viewExamManagementUserByMPU", method = RequestMethod.POST)
	public Result<ExamManagementUserVo> viewExamManagementUserByMPU(@RequestBody ExamManagementUserQ examManagementUserQ) {	
		if(StringUtils.isEmpty(examManagementUserQ.getExamManagementId())) {
			return Result.error("examManagementId不能为空");
		}
		if(StringUtils.isEmpty(examManagementUserQ.getExamPaperId())) {
			return Result.error("examPaperId不能为空");
		}
		if(StringUtils.isEmpty(examManagementUserQ.getExamUserNo())) {
			return Result.error("examUserNo不能为空");
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
		if(!examManagementUserQ.getUserNo().equals(examManagementViewVo.getTeacherUserNo())) {
			return Result.error("操作该考试与用户关系的不是该考试的教师，不能操作");
		}
		List<ExamManagementUserVo> examManagementUserVos = examManagementUserService.queryExamManagementUsers(examManagementUserQ).getList();
		if(CollectionUtil.isEmpty(examManagementUserVos)) {
			return Result.error("该用户还没参加考试");
		}
		if(examManagementUserVos.size()!=1) {
			return Result.error("该用户本次考试在数据库存在多于一条的答案，数据异常");
		}
		return Result.success(examManagementUserVos.get(0));
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
	
}
