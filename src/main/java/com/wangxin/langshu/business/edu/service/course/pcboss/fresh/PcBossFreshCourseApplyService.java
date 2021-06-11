package com.wangxin.langshu.business.edu.service.course.pcboss.fresh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshChapterDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshCourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.impl.fresh.FreshLessonDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.fresh.FreshCourseApplyMapper;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapter;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshChapterApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourse;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshCourseApply;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLesson;
import com.wangxin.langshu.business.edu.entity.course.fresh.FreshLessonApply;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.fresh.IPcBossFreshCourseApplyService;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyAuditVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyDeleteVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplySaveVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyUpdateVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseApply.FreshCourseApplyVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonMobileViewVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lesson.FreshLessonVo;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.lessonApply.FreshLessonApplyVo;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsDocEnum;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.IsPutawayEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Service
public class PcBossFreshCourseApplyService  extends ServiceImpl<FreshCourseApplyMapper, FreshCourseApply> implements IPcBossFreshCourseApplyService {

	@Autowired
	public FreshCourseApplyDao freshCourseApplyDao;
	
	@Autowired
    private FreshCourseApplyMapper freshCourseApplyMapper;
	
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;
	
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	
	@Autowired
	private PcBossFreshChapterApplyService freshChapterApplyService;
	
	@Autowired
	private PcBossFreshChapterService freshChapterService;
	
	@Autowired
	private PcBossFreshLessonApplyService freshLessonApplyService;
	
	@Autowired
	private PcBossFreshLessonService freshLessonService;
	
	@Autowired
	private PcBossFreshCourseService freshCourseService;
	
	@Autowired
	private ISystemConfigService systemConfigService;
	
	@Autowired
	public FreshChapterDao freshChapterDao;
	
	@Autowired
	public FreshChapterApplyDao freshChapterApplyDao;
	
	@Autowired
	public FreshLessonApplyDao freshLessonApplyDao;
	
	@Autowired
	public FreshLessonDao freshLessonDao;
	

	@Override
	public Page<FreshCourseApplyVo> queryCourseApplys(FreshCourseApplyQ q) {
			return freshCourseApplyDao.queryPcBossCourseApplys(q);
	}
	

	@Override
	public FreshCourseApplySaveVo saveCourseApply(FreshCourseApplySaveVo vo) {

		if (StringUtils.isEmpty(vo.getUserNo())) {
			throw new BaseException("userNo不能为空");
		}
		// 原价小于0
		if (vo.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			throw new BaseException("售价不能小于0");
		}
		// 课程收费但价格为空
		if (IsFreeEnum.CHARGE.getCode().equals(vo.getIsFree())) {
			if (vo.getCourseOriginal() == null) {
				throw new BaseException("价格不能为空");
			}
		}
		// 课程介绍
		CourseIntroduceApply courseIntroduceApply = new CourseIntroduceApply();
		String introduceId = UUID.randomUUID().toString().replace("-", "");
		courseIntroduceApply.setId(introduceId);//等改完后就起效果了
		courseIntroduceApply.setIntroduce(vo.getIntroduce());
		courseIntroduceApplyDao.save(courseIntroduceApply);
		
		
		String id = UUID.randomUUID().toString().replace("-", "");
		vo.setId(id);
		vo.setIntroduceId(courseIntroduceApply.getId());
		vo.setValidValue(ValidValueEnum.YES.getCode());
		vo.setIsPutaway(IsPutawayEnum.YES.getCode());
		vo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		vo.setTeacherUserNo(vo.getUserNo());
		
		FreshCourseApply course = new FreshCourseApply();
		
		BeanUtils.copyProperties(vo, course);
		this.save(course);

		
		return vo;
	}

	@Override
	public boolean updateCourseApply(FreshCourseApplyUpdateVo vo) {
		FreshCourseApply Course = new FreshCourseApply();
		BeanUtils.copyProperties(vo, Course);
		this.updateById(Course);
		
		// 课程简介更新
		if(StringUtils.isNotEmpty(vo.getIntroduce())){
			FreshCourseApply freshCourseApply = this.getById(vo.getId());
			CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(freshCourseApply.getIntroduceId());
			courseIntroduceApply.setIntroduce(vo.getIntroduce());
			courseIntroduceApplyDao.updateById(courseIntroduceApply);
		}
		
		return true;
	}

	@Override
	public Result<Integer> deleteCourseApply(FreshCourseApplyDeleteVo vo) {
		/*
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
		}*/
		if(StringUtils.isEmpty(vo.getId())){
			return Result.error("课程ID不能为空");
		}
		// 课程申请信息
		FreshCourseApply courseApply = this.getById(vo.getId());
		if (courseApply == null) {
			return Result.error("找不到该课程信息");
		}
		log.warn("准备开始删除课程申请："+JSONObject.toJSONString(courseApply));
		String courseId = vo.getId();
		
		// 章节申请信息
		FreshChapterApplyQ freshChapterApplyQ = new FreshChapterApplyQ();
		freshChapterApplyQ.setCourseId(courseId);
		freshChapterApplyQ.setPageCurrent(-1);
		freshChapterApplyQ.setPageSize(-1);
		List<FreshChapterApplyVo> chapterApplyList =  freshChapterApplyDao.queryAllChapterApplysByCourseId(freshChapterApplyQ).getList();
		if (CollectionUtil.isNotEmpty(chapterApplyList)) {
			for(FreshChapterApplyVo fcv :chapterApplyList){
				log.warn("删除章节申请："+JSONObject.toJSONString(fcv));
				freshCourseApplyDao.deleteTableByField("chapter_apply", "id", fcv.getId());
			}
		}		
		
		// 课时申请信息
		if (CollectionUtil.isNotEmpty(chapterApplyList)) {
			for (FreshChapterApplyVo fcmvv : chapterApplyList) {
				FreshLessonApplyQ freshLessonApplyQ = new FreshLessonApplyQ();
				freshLessonApplyQ.setChapterId(fcmvv.getId());
				freshLessonApplyQ.setPageCurrent(-1);
				freshLessonApplyQ.setPageSize(-1);
				List<FreshLessonApplyVo> lessonApplyList = freshLessonApplyDao.queryAllLessonApplysByChapterId(freshLessonApplyQ).getList();
				if(CollectionUtil.isNotEmpty(lessonApplyList)){
					for(FreshLessonApplyVo ffv:lessonApplyList){
						log.warn("删除课时申请："+JSONObject.toJSONString(ffv));
						freshCourseApplyDao.deleteTableByField("lesson_apply", "id", ffv.getId());
					}
				}
			}
		}	
		
		// 课程信息
		FreshCourse course = freshCourseService.getById(vo.getId());
		if (course != null) {//如果课程信息不为空，说明已经有了审核通过的课程
			//return Result.error("找不到该课程信息");
			// 章节信息
			FreshChapterQ freshChapterQ = new FreshChapterQ();
			freshChapterQ.setCourseId(vo.getId());
			freshChapterQ.setPageCurrent(-1);
			freshChapterQ.setPageSize(-1);
			List<FreshChapterVo> chapterList =  freshChapterDao.queryAllChaptersByCourseId(freshChapterQ).getList();
			if (CollectionUtil.isNotEmpty(chapterList)) {
				for(FreshChapterVo fcv :chapterList){
						log.warn("删除章节："+JSONObject.toJSONString(fcv));
						freshCourseApplyDao.deleteTableByField("chapter", "id", fcv.getId());
				}
			}		
					
			// 课时信息
			if (CollectionUtil.isNotEmpty(chapterList)) {
				for (FreshChapterVo fcmvv : chapterList) {
						FreshLessonQ freshLessonQ = new FreshLessonQ();
						freshLessonQ.setChapterId(fcmvv.getId());
						freshLessonQ.setPageCurrent(-1);
						freshLessonQ.setPageSize(-1);
						List<FreshLessonVo> lessonList = freshLessonDao.queryAllLessonsByChapterId(freshLessonQ).getList();
						if(CollectionUtil.isNotEmpty(lessonList)){
							for(FreshLessonVo ffv:lessonList){
								log.warn("删除课时："+JSONObject.toJSONString(ffv));
								freshCourseApplyDao.deleteTableByField("lesson", "id", ffv.getId());
							}
						}
				}
			}			
		}
		log.warn("准备开始删除课程："+JSONObject.toJSONString(course));

				
		
		
		//根据课程ID删除课程
		log.warn("根据课程ID删除课程："+"表course=>"+"字段id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("course", "id", courseId);
		//根据课程ID删除申请课程
		log.warn("根据课程ID删除课程申请："+"表course_apply=>"+"字段id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("course_apply", "id", courseId);
		
		//根据课程ID删除课程视频
		log.warn("根据课程ID删除课程视频："+"表course_video=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("course_video", "course_id", courseId);
		//根据课程ID删除课程相关试题绑定
		log.warn("根据课程ID删除课程相关试题绑定："+"表exam_question_course=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("exam_question_course", "course_id", courseId);
		//根据课程ID删除课程相关试卷
		log.warn("根据课程ID删除课程相关试卷："+"表exam_paper=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("exam_paper", "course_id", courseId);
		//根据上面的试卷ID删除课程相关考试
		log.warn("根据上面的试卷ID删除课程相关考试："+"表exam_management=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("exam_management", "course_id", courseId);
		//根据课程ID删除优惠券限制课程关系
		log.warn("根据课程ID删除优惠券限制课程关系："+"表coupon_limit_course=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("coupon_limit_course", "course_id", courseId);
		//根据课程ID删除学习卡限制课程关系
		log.warn("根据课程ID删除学习卡限制课程关系："+"表study_card_limit_course=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("study_card_limit_course", "course_id", courseId);
		//根据课程ID删除课程专区
		log.warn("根据课程ID删除课程专区："+"表topic_course=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("topic_course", "course_id", courseId);
		//根据课程ID删除课程用户关系
		log.warn("根据课程ID删除课程用户关系："+"表course_user=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("course_user", "course_id", courseId);
		//根据课程ID删除学习记录
		log.warn("根据课程ID删除学习记录："+"表user_study_log=>"+"字段course_id=>"+courseId);
		freshCourseApplyDao.deleteTableByField("user_study_log", "course_id", courseId);

		return Result.success(1);
	}

	@Override
	public FreshCourseApplyViewVo viewCourseApply(FreshCourseApplyViewVo viewVo) {
		FreshCourseApply examManagement = this.getById(viewVo.getId());
		BeanUtils.copyProperties(examManagement, viewVo);

		return viewVo;
	}


	@Override
	public Result<Integer> audit(FreshCourseApplyAuditVo vo) {
		if (vo.getId() == null) {
			return Result.error("缺少课程ID");
		}
		/*
		if (vo.getApplyStatus() == null) {
			return Result.error("审核状态不能为空");
		}*/
		// 不成功
		if (!ApplyStatusEnum.SUCCESS.getCode().equals(vo.getApplyStatus())) {
			FreshCourseApply apply = this.getById(vo.getId());
			apply.setApplyStatus(vo.getApplyStatus());
			this.updateById(apply);
			return Result.success(1);
		}
		// 审核 课程-章节-课时
		FreshCourseApply freshCourseApply = this.getById(vo.getId());
		if (ObjectUtil.isNull(freshCourseApply)) {
			return Result.error("课程不存在");
		}


		// 根据课程ID查询课时信息集合
		//List<LessonApply> lessonAuditList = lessonApplyDao.listByCourseId(courseApply.getId());

		FreshCourse freshCourse = freshCourseService.getById(freshCourseApply.getId());
		// 1、对课程操作
		// 如果课程信息表里面有数据就进行更新
		if (ObjectUtil.isNotNull(freshCourse)) {
			BeanUtils.copyProperties(freshCourseApply, freshCourse);
			
			// 设置总课时数
			/*
			if (CollectionUtils.isEmpty(lessonAuditList)) {
				course.setLessonTotal(0);
			} else {
				course.setLessonTotal(lessonAuditList.size());
			}*/
			// 更新课程信息表
			freshCourseService.updateById(freshCourse);
		} else {
			freshCourse  = new FreshCourse();
			BeanUtils.copyProperties(freshCourseApply,freshCourse);
			freshCourseService.save(freshCourse);
			
			/*
			// 如果课程信息表里面没数据就进行插入
			Course info = BeanUtil.copyProperties(courseApply, Course.class);
			info.setCreateTime(null);
			info.setModifiedTime(null);
			// 设置总课时数
			if (CollectionUtils.isEmpty(lessonAuditList)) {
				info.setLessonTotal(0);
			} else {
				info.setLessonTotal(lessonAuditList.size());
			}
			courseDao.save(info);
			*/
		}

		// 2、对课程简介操作
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(freshCourseApply.getIntroduceId());
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(freshCourseApply.getIntroduceId());
		if (ObjectUtil.isNull(courseIntroduceApply)) {
			return Result.error("课程简介表不存在");
		}
		if (ObjectUtil.isNull(courseIntroduce)) {
			CourseIntroduce introduce = new CourseIntroduce();
			BeanUtils.copyProperties(courseIntroduceApply,introduce);

			courseIntroduceDao.save(introduce);
		} else {
			BeanUtils.copyProperties(courseIntroduceApply, courseIntroduce);
			//courseIntroduceApplyDao.updateById(courseIntroduceApply);
			courseIntroduceDao.updateById(courseIntroduce);
		}
		
		// 3、对章节操作
		chapter(freshCourseApply);

		// 4、对课时操作
		lesson(freshCourseApply);

		// 更改课程审核状态
		FreshCourseApply audit = new FreshCourseApply();
		BeanUtils.copyProperties(freshCourseApply,audit);
		audit.setApplyStatus(vo.getApplyStatus());
		audit.setApplyResultDesc(vo.getApplyResultDesc());
		boolean flag = this.updateById(audit);
		if (!flag) {
			return Result.error(ResultEnum.COURSE_AUDIT_FAIL);
		}
		return Result.success(1);
	}

	// 审核章节
	private void chapter(FreshCourseApply courseApply) {
		
		FreshChapterApplyQ freshChapterApplyQ = new FreshChapterApplyQ();
		freshChapterApplyQ.setCourseId(courseApply.getId());
		freshChapterApplyQ.setValidValue("4");
		freshChapterApplyQ.setPageCurrent(-1);
		freshChapterApplyQ.setPageSize(-1);
		List<FreshChapterApplyVo> freshChapterApplyList = freshChapterApplyService.queryChapterApplys(freshChapterApplyQ).getList();
		if (CollectionUtils.isEmpty(freshChapterApplyList)) {
			return;
		}
		for (FreshChapterApplyVo freshChapterApplyVo : freshChapterApplyList) {
			// 根据章节编号查找章节审核信息
			FreshChapterApply fca = freshChapterApplyService.getById(freshChapterApplyVo.getId());
			if (ObjectUtil.isNull(fca)) {
				throw new BaseException("找不到章节审核信息");
			}
			// 查找章节信息表是否存在该课时信息
			FreshChapter freshChapter = freshChapterService.getById(freshChapterApplyVo.getId());
			// 存在就更新章节信息表数据
			if (ObjectUtil.isNotNull(freshChapter)) {
				BeanUtils.copyProperties(fca,freshChapter);
				freshChapterService.updateById(freshChapter);
			}else{
				// 如果章节不存在则插入章节信息
				freshChapter = new FreshChapter();
				BeanUtils.copyProperties(fca,freshChapter);
				freshChapterService.save(freshChapter);
			}
			// 更新审核状态
			fca.setApplyStatus(ApplyStatusEnum.SUCCESS.getCode());
			freshChapterService.updateById(freshChapter);
		}
	}

	// 课时审核
	private void lesson(FreshCourseApply freshCourseApply) {
		// 根据课程编号查找课时审核信息集合
		FreshLessonApplyQ freshLessonApplyQ = new FreshLessonApplyQ();
		freshLessonApplyQ.setCourseId(freshCourseApply.getId());
		freshLessonApplyQ.setValidValue("4");
		freshLessonApplyQ.setPageCurrent(-1);
		freshLessonApplyQ.setPageSize(-1);
		List<FreshLessonApplyVo> FreshLessonApplyVoList = freshLessonApplyService.queryLessonApplys(freshLessonApplyQ).getList();
		if (CollectionUtils.isEmpty(FreshLessonApplyVoList)) {
			return;
		}
		for (FreshLessonApplyVo freshLessonApplyVo : FreshLessonApplyVoList) {
			// 根据课时编号查找课时审核信息
			FreshLessonApply freshLessonApply = freshLessonApplyService.getById(freshLessonApplyVo.getId());
			if (ObjectUtil.isNull(freshLessonApply)) {
				throw new BaseException("找不到课时审核信息");
			}
			// 根据课时编号查找课时信息
			FreshLesson freshLesson = freshLessonService.getById(freshLessonApply.getId());
			// 如果信息表存在就更新信息表信息
			if (ObjectUtil.isNotNull(freshLesson)) {
				if (IsDocEnum.NO.getCode().equals(freshLesson.getIsDoc())) {
					AliyunUtil.delete(freshLesson.getDocUrl(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
				}
			}
			
			// 如果信息表存在就更新信息表信息
			if (ObjectUtil.isNotNull(freshLesson)) {
				if (IsDocEnum.NO.getCode().equals(freshLesson.getIsDoc())) {
					AliyunUtil.delete(freshLesson.getDocUrl(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
				}
				freshLesson = BeanUtil.copyProperties(freshLessonApply, FreshLesson.class);
				freshLessonService.updateById(freshLesson);
			} else {
				// 如果课时信息表不存在就插入信息
				freshLesson = BeanUtil.copyProperties(freshLessonApply, FreshLesson.class);
				freshLessonService.save(freshLesson);
			}
			// 更新审核状态
			freshLessonApply.setApplyStatus(ApplyStatusEnum.SUCCESS.getCode());
			freshLessonApplyService.updateById(freshLessonApply);
		}
		
	}


	
	
}
