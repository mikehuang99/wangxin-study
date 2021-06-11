package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagementUser;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


public interface IExamManagementUserService  extends IService<ExamManagementUser> {
	
	

	public Page<ExamManagementUserVo> queryExamManagementUsers(ExamManagementUserQ q) ;

	public Result<Integer> saveExamManagementUser(ExamManagementUserSaveVo vo) ;

	public Result<Integer> updateExamManagementUser(ExamManagementUserUpdateVo vo) ;

	public Result<Integer> markExamPaper(ExamManagementUserUpdateVo vo) ;
	
	public Result<Integer> deleteExamManagementUser(ExamManagementUserDeleteVo vo) ;

	
	public ExamManagementUserViewVo viewExamManagementUser(ExamManagementUserViewVo vo) ;
	
	
	
	
	
	
}
