package com.wangxin.langshu.business.edu.controller.exam.pcweb;

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
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementUpdateVo;
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
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 考试管理管理
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/pcweb/exam/management")
public class AuthApiExamManagementController extends BaseController {

	@Autowired
	private IExamManagementService examManagementService;
	
	@Autowired
	private IExamManagementUserService examManagementUserService;

	
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	

	/**
	 * 考试管理列表分页接口
	 * 
	 * @param ExamManagementQ
	 * @return
	 */
	@ApiOperation(value = "考试管理列表分页接口", notes = "考试管理列表分页接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<ExamManagementVo>> list(@RequestBody ExamManagementQ examManagementQ) {	
		examManagementQ.setTeacherUserNo(examManagementQ.getUserNo());
		return Result.success(examManagementService.queryExamManagements(examManagementQ));
	}

	/**
	 * 考试管理添加接口
	 * 
	 * @param ExamManagementSaveVo
	 * @return
	 */
	@ApiOperation(value = "考试管理添加接口", notes = "考试管理添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody ExamManagementSaveVo examManagementSaveVo) {
		return examManagementService.saveExamManagement(examManagementSaveVo);
	
	}

	/**
	 * 考试管理更新接口
	 * 
	 * @param ExamManagementUpdateVo
	 * @return
	 */
	@ApiOperation(value = "考试管理更新接口", notes = "考试管理更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody  ExamManagementUpdateVo examManagementUpdateVo) {
		return examManagementService.updateExamManagement(examManagementUpdateVo);
		
	}

	/**
	 * 考试管理删除接口
	 * 
	 * @param ExamManagementDeleteVo
	 * @return
	 */
	@ApiOperation(value = "考试管理删除接口", notes = "考试管理删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody  ExamManagementDeleteVo examManagementDeleteVo) {
		return examManagementService.deleteExamManagement(examManagementDeleteVo);
		
	}

	/**
	 * 考试管理查看接口
	 * 
	 * @param ExamManagementViewVo
	 * @return
	 */
	@ApiOperation(value = "考试管理查看接口", notes = "考试管理查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<ExamManagementViewVo> view(@RequestBody  ExamManagementViewVo examManagementViewVo) {
		ExamManagementViewVo vo = examManagementService.viewExamManagement(examManagementViewVo);
		if(ObjectUtil.isNull(vo)){
			Result.error("查找不到考试");
		}
		if(!examManagementViewVo.getUserNo().equals(vo.getTeacherUserNo())){
			Result.error("查找到的考试的所属教师编号与你登录的用户编号不一致");
		}
		return Result.success(vo);
	}
	
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

	@ApiOperation(value = "根据考试ID查询所有考试对象", notes = "根据考试ID查询所有考试对象")
	@RequestMapping(value = "/queryExamObjectsExamManagementId", method = RequestMethod.POST)
	public Result<Page<ExamManagementUserVo>> queryExamObjectsExamManagementId(@RequestBody  ExamManagementUserQ examManagementUserQ) {
		return examManagementService.queryExamObjectsByExamManagementId(examManagementUserQ);
	}
	
	@ApiOperation(value = "保持会话,防止被登出", notes = "保持会话,防止被登出")
	@RequestMapping(value = "/keepSession", method = RequestMethod.POST)
	public Result<Integer> keepSession() {
		return Result.success(1);
	}
	
}
