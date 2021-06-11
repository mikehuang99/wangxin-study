package com.wangxin.langshu.business.edu.service.exam.pccommon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.exam.ExamManagementDao;
import com.wangxin.langshu.business.edu.dao.exam.impl.mapper.ExamManagementMapper;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagement;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseUserService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementService;
import com.wangxin.langshu.business.edu.iservice.exam.pccommon.IExamManagementStudentGroupRelateService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentGroupService;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IStudentStudentGroupRelateService;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class ExamManagementService  extends ServiceImpl<ExamManagementMapper, ExamManagement> implements IExamManagementService {

	@Autowired
	public ExamManagementDao examManagementDao;
	
	@Autowired
    private ExamManagementMapper examManagementMapper;
	
	@Autowired
    private IExamManagementStudentGroupRelateService examManagementStudentGroupRelateService;
	
	@Autowired
	private IStudentGroupService studentGroupService;
	
	@Autowired
	private IStudentStudentGroupRelateService studentStudentGroupRelateService;
	
	
	@Autowired
	private IPcWebCourseUserService pcWebCourseUserService;
	


	@Override
	public Page<ExamManagementVo> queryExamManagements(ExamManagementQ q) {
		return examManagementDao.queryExamManagements(q);
	}

	@Override
	public Result<Integer> saveExamManagement(ExamManagementSaveVo vo) {
		vo.setTeacherUserNo(vo.getUserNo());
		String id = UUID.randomUUID().toString().replace("-", "");
		ExamManagement ExamManagement = new ExamManagement();
		
		BeanUtils.copyProperties(vo, ExamManagement);
		ExamManagement.setId(id);
		this.save(ExamManagement);
		
		if(null!=vo.getStudentGroupIds()&&"2".equals(vo.getExamObjectType())){
			for(String s:vo.getStudentGroupIds()){
				if(null==s){
					continue;
				}
				ExamManagementStudentGroupRelateSaveVo emugrVo = new ExamManagementStudentGroupRelateSaveVo();
				emugrVo.setExamManagementId(id);
				emugrVo.setStudentGroupId(s);
				examManagementStudentGroupRelateService.saveEntity(emugrVo);
			}
		}
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> updateExamManagement(ExamManagementUpdateVo vo) {
		
		if(StringUtils.isEmpty(vo.getId())){
			return Result.error("缺少ID");
		}
		
		ExamManagement ExamManagement = this.getById(vo.getId());
		if(ObjectUtil.isNull(ExamManagement)){
			return Result.error("根据ID查找不到对象");
		}
		if(!vo.getUserNo().equals(ExamManagement.getTeacherUserNo())){
			return Result.error("本次考试的教师userNo和页面传过来的不相符");
		}
		
		BeanUtils.copyProperties(vo, ExamManagement);
		this.updateById(ExamManagement);
		
		examManagementStudentGroupRelateService.deleteEntityByExamManagementId(vo.getId());
		
		if(null!=vo.getStudentGroupIds()&&("2".equals(vo.getExamObjectType())||"4".equals(vo.getExamObjectType()))){
			for(String s:vo.getStudentGroupIds()){
				if(null==s){
					continue;
				}
				ExamManagementStudentGroupRelateSaveVo emugrVo = new ExamManagementStudentGroupRelateSaveVo();
				emugrVo.setExamManagementId(vo.getId());
				emugrVo.setStudentGroupId(s);
				examManagementStudentGroupRelateService.saveEntity(emugrVo);
			}
		}
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteExamManagement(ExamManagementDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			
			ExamManagement ExamManagement = this.getById(id);
			if(ObjectUtil.isNull(ExamManagement)){
				return Result.error("根据ID查找不到对象");
			}
			if(!vo.getUserNo().equals(ExamManagement.getTeacherUserNo())){
				return Result.error("本次考试的教师userNo和页面传过来的不相符");
			}
			
			this.removeById(id);
			
			examManagementStudentGroupRelateService.deleteEntityByExamManagementId(id);

		}
		

		return Result.success(1);
	}

	@Override
	public ExamManagementViewVo viewExamManagement(ExamManagementViewVo viewVo) {
		ExamManagement examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);
		
		ExamManagementStudentGroupRelateQ q = new ExamManagementStudentGroupRelateQ();
		q.setExamManagementId(viewVo.getId());
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<ExamManagementStudentGroupRelateVo> page = examManagementStudentGroupRelateService.queryEntities(q);
		List<ExamManagementStudentGroupRelateVo> vos = page.getList();
		List<String> studentGroupIdsList = new ArrayList<String>(); 
		for(ExamManagementStudentGroupRelateVo vo : vos){
			studentGroupIdsList.add(vo.getStudentGroupId());
		}
		viewVo.setStudentGroupIds(studentGroupIdsList.toArray(new String[studentGroupIdsList.size()]));
		
		
		
		
		StudentGroupQ studentGroupQ = new StudentGroupQ();
		studentGroupQ.setPageCurrent(-1);
		studentGroupQ.setPageSize(-1);
		Page<StudentGroupVo> pageStudentGroupVo = studentGroupService.queryStudentGroups(studentGroupQ);
		List<StudentGroupVo> studentGroupVosList = pageStudentGroupVo.getList();
		List<String> studentGroupNamesList = new ArrayList<String>();
		for(StudentGroupVo v: studentGroupVosList){
			if(studentGroupIdsList.contains(v.getId())){
				studentGroupNamesList.add(v.getGroupName());
			}
		}
		viewVo.setStudentGroupNames(studentGroupNamesList.toArray(new String[studentGroupNamesList.size()]));
		
		
		return viewVo;
	}

	@Override
	public ExamManagementViewVo viewSimpleExamManagement(ExamManagementViewVo vo) {
		ExamManagement examManagement = this.getById(vo.getId());
		BeanUtils.copyProperties(examManagement, vo);
		return vo;
	}
	
	@Override
	public Result<ExamManagementViewVo> viewByIdAndStudentUserNo(ExamManagementViewVo examManagementViewVo) {
		ExamManagementViewVo vo = this.viewExamManagement(examManagementViewVo);
		//先看看该用户有没有该考试
		ExamManagementQ q = new ExamManagementQ();
		if(StringUtils.isEmpty(examManagementViewVo.getUserNo())){
			return Result.error("缺少学生编号userNo");
		}
		q.setUserNo(examManagementViewVo.getUserNo());
		Result<Page<ExamManagementVo>> result = this.queryExamManagementsByStudentUserNo(q);
		Page<ExamManagementVo>  page = result.getData();
		if(ObjectUtil.isNull(page)){
			return Result.error("该用户还没有考试");
		}
		if(CollectionUtil.isEmpty(page.getList())){
			return Result.error("该用户还没有考试");
		}
		List<ExamManagementVo> vos = page.getList();
		int count =0;
		for(ExamManagementVo v:vos ){
			if(v.getId().equals(vo.getId())){
				count = count +1;
				if(1==vo.getExamType()){
					vo.setEndTime(new Date(vo.getStartTime().getTime()+v.getLimitTime()*60*1000L));
					vo.setLimitTime(v.getLimitTime());
				}
			}
		}
		if(count==0){
			return Result.error("该用户没有该考试");
		}
		
		//根据用户查询到的考试，如果未到考试时间，则屏蔽试卷ID。最多只能提前一分钟获取试卷
		/*
		if((new Date().getTime()<vo.getStartTime().getTime()-60*1000L)){
			return Result.error(ResultEnum.NOT_YET_OPEN_EXAM);
		}*/
		
		return Result.success(vo);
	}
	
	@Override
	public Result<Page<ExamManagementVo>> queryExamManagementsByStudentUserNo(ExamManagementQ q) {
		if(StringUtils.isEmpty(q.getUserNo())){
			return Result.error("用户编号不能为空");
		}
		String userNo = q.getUserNo();
		//1、查询全部的考试，2、查看每个考试的参考对象，3根据参考对象获取用户，4查到的用户看看是否在参考对象里面
		//ExamManagementQ examManagementQ = new ExamManagementQ();
		q.setExamUserNo(q.getUserNo());
		
		q.setValidValue("1");
		q.setPageCurrent(-1);
		q.setPageSize(-1);
		Page<ExamManagementVo> page = examManagementDao.queryExamManagements(q);
		List<ExamManagementVo> list = page.getList();
		if(CollectionUtil.isEmpty(list)){
			return Result.success(page);
		}
		List<ExamManagementVo> newList = new ArrayList<ExamManagementVo>();
		for(int i=0;i<list.size();i++){
			ExamManagementVo examManagementVo = list.get(i);
			String courseId = examManagementVo.getCourseId();
			if(StringUtils.isEmpty(courseId)){
				return Result.error("试卷标题:"+examManagementVo.getExamPaperTitle()+",缺少courseId,数据异常。");
			}
			ExamManagementViewVo examManagementViewVo = new ExamManagementViewVo();
			examManagementViewVo.setId(examManagementVo.getId());
			examManagementViewVo = this.viewExamManagement(examManagementViewVo);
			String[] studentGroupIds= examManagementViewVo.getStudentGroupIds();
			//参考对象类型，1为全体学员，2为分组(user_group_ids)，3为全体学员中拥有该课程的学员，4为分组(user_group_ids)学员中拥有该课程的学员
			if("1".equals(examManagementVo.getExamObjectType())){
				newList.add(examManagementVo);
			}
			if("2".equals(examManagementVo.getExamObjectType())){
				if(isUserNoInStudentGroupIds(userNo, studentGroupIds)){
					newList.add(examManagementVo);
				}
			}
			if("3".equals(examManagementVo.getExamObjectType())){
				if(isMyCourse(userNo,courseId)){
					newList.add(examManagementVo);
				}
			}
			if("4".equals(examManagementVo.getExamObjectType())){
				if(isUserNoInStudentGroupIds(userNo, studentGroupIds)&&isMyCourse(userNo,courseId)){
					newList.add(examManagementVo);
				}
			}
		}

		Page<ExamManagementVo> pageResult = new Page<ExamManagementVo>();
		pageResult.setList(newList);

		return Result.success(pageResult);
	}

	private boolean isMyCourse(String userNo,String courseId) {
		int isMyCourse = 0;
		CourseUserQ courseUserQ = new CourseUserQ();
		courseUserQ.setUserNo(userNo);
		Page<CourseUserVo> pageCourseUserVo = pcWebCourseUserService.queryCourseUsers(courseUserQ);
		if(CollectionUtil.isNotEmpty(pageCourseUserVo.getList())){
			List<CourseUserVo> courseUserVos = pageCourseUserVo.getList();
			for(CourseUserVo courseUserVo : courseUserVos){
				if(courseId.equals(courseUserVo.getCourseId())){
					isMyCourse = isMyCourse+1;
				}
			}
		}
		if(isMyCourse>0){
			return true;
		}
		return false;
	}

	private boolean isUserNoInStudentGroupIds(String userNo, String[] studentGroupIds) {
		int isInGroup = 0;
		for(int c=0;c<studentGroupIds.length;c++){
			StudentStudentGroupRelateQ studentStudentGroupRelateQ = new StudentStudentGroupRelateQ();
			studentStudentGroupRelateQ.setStudentGroupId(studentGroupIds[c]);
			studentStudentGroupRelateQ.setPageCurrent(-1);
			studentStudentGroupRelateQ.setPageSize(-1);
			Page<StudentStudentGroupRelateVo> studentStudentGroupRelatePage = studentStudentGroupRelateService.queryStudentStudentGroupRelates(studentStudentGroupRelateQ);
			List<StudentStudentGroupRelateVo>  studentStudentGroupRelateVoList = studentStudentGroupRelatePage.getList();
			if(CollectionUtil.isNotEmpty(studentStudentGroupRelateVoList)){
				for(StudentStudentGroupRelateVo studentStudentGroupRelateVo : studentStudentGroupRelateVoList){
					//if(userNo.equals(studentStudentGroupRelateVo.getUserNoForGroup())){
					if(userNo.equals(studentStudentGroupRelateVo.getUserNo())){
						isInGroup = isInGroup + 1;
					}
				}
			}
		}
		if(isInGroup>0){
			return true;
		}
		return false;
	}

	@Override
	public Result<Page<ExamManagementUserVo>> queryExamObjectsByExamManagementId(ExamManagementUserQ examManagementUserQ){
		if(StringUtils.isEmpty(examManagementUserQ.getUserNo())) {
			return Result.error("缺少用户userNo");
		}
	
		if(StringUtils.isEmpty(examManagementUserQ.getExamManagementId())) {
			return Result.error("缺少考试ID");
		}
		ExamManagement examManagement = this.getById(examManagementUserQ.getExamManagementId());
		if(ObjectUtil.isNull(examManagement)) {
			return Result.error("查不到该考试");
		}
		if(!examManagementUserQ.getUserNo().equals(examManagement.getTeacherUserNo())) {
			return Result.error("该考试的教师和操作的用户不一致");
		}
		examManagementUserQ.setExamObjectType(examManagement.getExamObjectType());
		Page<ExamManagementUserVo> page = this.examManagementDao.queryExamObjectsByExamObjecTypeAndExamManagementId(examManagementUserQ);
		return Result.success(page);
	}





	


	
	
}
