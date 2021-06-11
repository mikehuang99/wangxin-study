package com.wangxin.langshu.business.edu.service.course.pcweb.fresh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshLessonApplyMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapterApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLessonApply;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.fresh.IPcWebFreshLessonApplyService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplySortVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcWebFreshLessonApplyService  extends ServiceImpl<FreshLessonApplyMapper, FreshLessonApply> implements IPcWebFreshLessonApplyService {

	@Autowired
	public FreshLessonApplyDao freshLessonDao;
	
	@Autowired
    private FreshLessonApplyMapper freshCourseMapper;
	
	
	@Autowired
	private PcWebFreshCourseApplyService freshCourseApplyService;
	
	@Autowired
	private PcWebFreshChapterApplyService freshChapterApplyService;
	
	@Autowired
	private PcWebFreshLessonService freshLessonService;

	@Override
	public Page<FreshLessonApplyVo> queryLessonApplys(FreshLessonApplyQ q) {
			return freshLessonDao.queryLessonApplys(q);
	}
	

	@Override
	public FreshLessonApplySaveVo saveLessonApply(FreshLessonApplySaveVo vo) {
		FreshChapterApplyViewVo freshChapterApplyViewVo = new FreshChapterApplyViewVo();
		freshChapterApplyViewVo.setId(vo.getChapterId());
		FreshChapterApplyViewVo fcavo = freshChapterApplyService.viewChapterApply(freshChapterApplyViewVo);
		
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(fcavo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			throw new BaseException("不是本课程教师，不允许操作");
		}
		
		
		/*
		FreshLessonApplyQ freshLessonApplyQ = new FreshLessonApplyQ();
		freshLessonApplyQ.setChapterId(vo.getChapterId());
		List<FreshLessonApplyVo> freshLessonApplyVos = this.queryLessonApplys(freshLessonApplyQ).getList();
		int maxSort = 0;
		if(CollectionUtils.isNotEmpty(freshLessonApplyVos)){
			for(FreshLessonApplyVo freshLessonApplyVo: freshLessonApplyVos){
				if(freshLessonApplyVo.getSort()>maxSort){
					maxSort = freshLessonApplyVo.getSort();
				}
			}
		}*/
		
		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		vo.setCourseId(fcavo.getCourseId());
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		FreshLessonApply fca = new FreshLessonApply();
		BeanUtils.copyProperties(vo, fca);
		this.save(fca);
		
		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(fcavo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());
		freshCourseApplyService.updateById(freshCourseApply);
		
		return vo;
	}

	@Override
	public FreshLessonApplyUpdateVo updateLessonApply(FreshLessonApplyUpdateVo vo) {
		FreshLessonApplyViewVo freshLessonApplyViewVo = new FreshLessonApplyViewVo();
		freshLessonApplyViewVo.setId(vo.getId());
		FreshLessonApplyViewVo csavVo = this.viewLessonApply(freshLessonApplyViewVo);
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(csavVo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			throw new BaseException("不是本课程教师，不允许操作");
		}
		
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		
		FreshLessonApply Course = new FreshLessonApply();
		BeanUtils.copyProperties(vo, Course);
		this.updateById(Course);
		
		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(csavVo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		//freshCourseApply.setModifiedTime(new Date());
		freshCourseApplyService.updateById(freshCourseApply);
		
		
		return vo;
	}
	
	@Override
	public Result<FreshLessonApplyUpdateVo> updateLessonApplyLiveBroadcast(FreshLessonApplyUpdateVo vo) {
		FreshLessonApplyViewVo freshLessonApplyViewVo = new FreshLessonApplyViewVo();
		freshLessonApplyViewVo.setId(vo.getId());
		FreshLessonApplyViewVo csavVo = this.viewLessonApply(freshLessonApplyViewVo);
		
		FreshCourseApplyViewVo freshCourseApplyViewVo = new FreshCourseApplyViewVo();
		freshCourseApplyViewVo.setId(csavVo.getCourseId());
		FreshCourseApplyViewVo fCourseavo = freshCourseApplyService.viewCourseApply(freshCourseApplyViewVo).getData();
		if(!vo.getUserNo().equals(fCourseavo.getTeacherUserNo())){
			return Result.error("不是本课程教师，不允许操作");
		}
		
		FreshLessonApply freshLessonApply = this.getById(vo.getId());
		freshLessonApply.setLiveBroadcastBeginTime(vo.getLiveBroadcastBeginTime());
		freshLessonApply.setLiveBroadcastPlanLength(vo.getLiveBroadcastPlanLength());
		freshLessonApply.setLiveBroadcastHlsAddress(vo.getLiveBroadcastHlsAddress());
		freshLessonApply.setLiveBroadcastObsStreamAddress(vo.getLiveBroadcastObsStreamAddress());
		freshLessonApply.setLiveBroadcastObsStreamName(vo.getLiveBroadcastObsStreamName());
		freshLessonApply.setLessonType(1);
		freshLessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		
		this.updateById(freshLessonApply);

		//更新课程的审核状态
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(csavVo.getCourseId());
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshCourseApplyService.updateById(freshCourseApply);
				
		BeanUtils.copyProperties(freshLessonApply, vo);

		return Result.success(vo);
	}
	
	@Override
	public Result<Integer> updateLessonApplyLessonType(FreshLessonApplyUpdateVo vo) {
		FreshLessonApply freshLessonApply = this.getById(vo.getId());
		if(ObjectUtil.isNull(freshLessonApply)){
			return Result.error("找不到课时信息");
		}
		FreshCourseApply freshCourseApply = freshCourseApplyService.getById(freshLessonApply.getCourseId());
		if(ObjectUtil.isNull(freshCourseApply)){
			return Result.error("找不到课程");
		}
		if(!vo.getUserNo().equals(freshCourseApply.getTeacherUserNo())){
			return Result.error("不是本课程教师，不允许操作");
		}
		
		
		freshLessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshLessonApply.setLessonType(vo.getLessonType());
		this.updateById(freshLessonApply);
		
		freshCourseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshCourseApplyService.updateById(freshCourseApply);
		
		return Result.success(1);
	}

	@Override
	public Result<Integer> deleteLessonApply(FreshLessonApplyDeleteVo vo) {
		/*
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
		}*/
		
		
		if (vo.getId() == null) {
			return Result.error("课时ID不能为空");
		}
		if (vo.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}
		FreshLessonApply freshLessonApply = this.getById(vo.getId());
		if (ObjectUtil.isNull(freshLessonApply)) {
			return Result.error("找不到课时信息");
		}
		FreshCourseApply course = freshCourseApplyService.getById(freshLessonApply.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}

		if (!vo.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("不是本课程教师，不允许操作");
		}

		// 更新课时接口状态为已冻结，设置为未审核
		freshLessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		freshLessonApply.setValidValue(Constants.FREEZE);
		boolean flag = this.updateById(freshLessonApply);
		if (flag) {
			// 更新章节信息审核表状态为未审核
			FreshChapterApply freshChapterApply = freshChapterApplyService.getById(freshLessonApply.getChapterId());
			freshChapterApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			freshChapterApplyService.updateById(freshChapterApply);
			// 更新课程信息审核表状态为未审核
			FreshCourseApply courseApply = freshCourseApplyService.getById(freshLessonApply.getCourseId());
			courseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			freshCourseApplyService.updateById(courseApply);
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	
	}

	@Override
	public FreshLessonApplyViewVo viewLessonApply(FreshLessonApplyViewVo viewVo) {
		FreshLessonApply e = this.getById(viewVo.getId());
		BeanUtils.copyProperties(e, viewVo);
		if(StringUtils.isNotEmpty(viewVo.getUserNo())){//用户编号不为空
			//再检查是否该用户是该课程教师，如果不是该课程教师，需要将OBS推流地址和名称隐藏，防止泄漏
			FreshCourseApply freshCourseApply = freshCourseApplyService.getById(e.getCourseId());
			if(!freshCourseApply.getTeacherUserNo().equals(viewVo.getUserNo())){
				viewVo.setLiveBroadcastObsStreamAddress("*");
				viewVo.setLiveBroadcastObsStreamName("*");
			}
		}else{
			viewVo.setLiveBroadcastObsStreamAddress("*");
			viewVo.setLiveBroadcastObsStreamName("*");
		}

		return viewVo;
	}

	
	/**
	 * 更新课时排序接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> sort(FreshLessonApplySortVo freshLessonApplySortVo) {
		
		List<AuthLessonAuditViewDTO> lessonViewList = freshLessonApplySortVo.getLessonViewList();
		List<String> lesseonIds = new ArrayList<String>();
		if(CollectionUtil.isNotEmpty(lessonViewList)){
			for(int i=lessonViewList.size()-1;i>=0;i--){
				lesseonIds.add(lessonViewList.get(i).getId());
			}
			
		}
		if (CollectionUtil.isEmpty(lesseonIds)) {
			return Result.error("课时信息不能为空");
		}
		if (freshLessonApplySortVo.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		if (CollectionUtil.isNotEmpty(lesseonIds)) {
			int i = 1;
			for (String lessonId : lesseonIds) {
				// 更新课时审核信息表的排序
				int sort = i++;
	
				FreshLessonApply fla = this.getById(lessonId);
				fla.setSort(sort);
				this.updateById(fla);
				
				
				FreshLessonApply freshLessonApply = this.getById(lessonId);
				FreshLesson freshLesson = freshLessonService.getById(lessonId);
				FreshCourseApply course = freshCourseApplyService.getById(freshLessonApply.getCourseId());
				if (ObjectUtil.isNull(course)) {
					return Result.error("找不到课程信息");
				}
				if (!freshLessonApplySortVo.getUserNo().equals(course.getTeacherUserNo())) {
					return Result.error("不是本课程教师，不允许操作");
				}
				// 更新课时信息集合
				if (ObjectUtil.isNotNull(freshLesson)) {
					FreshLesson record = freshLessonService.getById(freshLesson.getId());
					record.setSort(sort);
					freshLessonService.updateById(record);
				}
			}
			return Result.success(i);
		}
		return Result.error("设置排序失败");
	}







	
	
}
