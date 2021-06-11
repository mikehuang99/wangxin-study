package com.wangxin.langshu.business.edu.service.user.pccommon;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.user.UserStudyLogDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.UserStudyLogMapper;
import com.wangxin.langshu.business.edu.entity.user.UserStudyLog;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IUserStudyLogService;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogQ;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogSaveVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogViewVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;


@Service
public class UserStudyLogService  extends ServiceImpl<UserStudyLogMapper, UserStudyLog> implements IUserStudyLogService {

	@Autowired
	public UserStudyLogDao userStudyLogDao;
	
	@Autowired
    private UserStudyLogMapper userStudyLogMapper;
	

	

	@Override
	public Page<UserStudyLogVo> queryUserStudyLogs(UserStudyLogQ q) {
		return userStudyLogDao.queryUserStudyLogs(q);
	}

	@Override
	@Transactional
	public Result<Integer> saveUserStudyLog(UserStudyLogSaveVo vo) {
		
		UserStudyLog p = new UserStudyLog();
		BeanUtils.copyProperties(vo, p);
		String id = UUID.randomUUID().toString();
		p.setId(id);
		this.save(p);
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateUserStudyLog(UserStudyLogUpdateVo vo) {
		UserStudyLog UserStudyLog = new UserStudyLog();
		BeanUtils.copyProperties(vo, UserStudyLog);
		this.updateById(UserStudyLog);
		
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteUserStudyLog(UserStudyLogDeleteVo vo) {
		return Result.success(1);	
	}

	@Override
	public UserStudyLogViewVo viewUserStudyLog(UserStudyLogViewVo viewVo) {
		UserStudyLog examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		

		return viewVo;
	}

	@Override
	public Page<FreshCourseVo> queryFreshCoursesOnUserStudyLog(FreshCourseQ q) {
		q.setTeacherUserNo(q.getUserNo());	
		q.setValidValue("1");
		Page<FreshCourseVo> page = userStudyLogDao.queryCommonCourses(q);
		return page;
	}
	
	

	
}
