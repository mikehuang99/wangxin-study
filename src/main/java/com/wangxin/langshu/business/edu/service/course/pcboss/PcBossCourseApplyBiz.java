package com.wangxin.langshu.business.edu.service.course.pcboss;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyApplyStatusREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyGetREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseApplyViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.ChapterApplyViewRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyGetRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseApplyViewRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.LessonApplyViewRESQ;
import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample.Criteria;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduce;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.dao.course.ChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.ChapterDao;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceDao;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseApplyBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.edu.service.user.pccommon.BossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.aliyun.Aliyun;
import com.wangxin.langshu.business.util.aliyun.AliyunUtil;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsDocEnum;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * ????????????-??????
 */
@Service
public class PcBossCourseApplyBiz implements IPcBossCourseApplyBiz{

	@Autowired
	private BossTeacherBiz bossTeacherBiz;

	//@Autowired
	//private BossSysBiz bossSysBiz;
	
	@Autowired
	private ISystemConfigService systemConfigService;

	@Autowired
	private CourseApplyDao dao;
	@Autowired
	private CourseSubjectDao courseSubjectDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ChapterApplyDao chapterApplyDao;
	@Autowired
	private ChapterDao chapterDao;
	@Autowired
	private LessonApplyDao lessonApplyDao;
	@Autowired
	private LessonDao lessonDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;

	public Result<Page<CourseApplyPageRESQ>> list(CourseApplyPageREQ req) {
		CourseApplyExample example = new CourseApplyExample();
		Criteria c = example.createCriteria();

		if (!StringUtils.isEmpty(req.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(req.getCourseName()));
		}
		if (req.getValidValue() != null) {
			c.andValidValueEqualTo(req.getValidValue());
		}
		if (req.getApplyStatus() == null) {
			c.andApplyStatusNotEqualTo(ApplyStatusEnum.SUCCESS.getCode());
		} else {
			c.andApplyStatusEqualTo(req.getApplyStatus());
		}
		if (req.getIsFree() != null) { 
			c.andIsFreeEqualTo(req.getIsFree());
		}
		if (req.getIsPutaway() != null) {
			c.andIsPutawayEqualTo(req.getIsPutaway());
		}

		example.setOrderByClause(" apply_status asc, valid_value desc, is_putaway desc, sort desc, id desc ");
		Page<CourseApply> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseApplyPageRESQ> listForPage = PageUtil.transform(page, CourseApplyPageRESQ.class);
		// ??????????????????
		for (CourseApplyPageRESQ resq : listForPage.getList()) {
			if (resq.getSubjectId1() != null && !"0".equals(resq.getSubjectId1())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId1());
				if (!StringUtils.isEmpty(courseSubject)) {
					resq.setSubjectName1(courseSubject.getSubjectName());
				}
			}
			if (resq.getSubjectId2() != null && !"0".equals(resq.getSubjectId2())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId2());
				if (!StringUtils.isEmpty(courseSubject)) {
					resq.setSubjectName2(courseSubject.getSubjectName());
				}
			}
			if (resq.getSubjectId3() != null && !"0".equals(resq.getSubjectId1())) {
				CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId3());
				if (!StringUtils.isEmpty(courseSubject)) {
					resq.setSubjectName3(courseSubject.getSubjectName());
				}
			}
		}
		return Result.success(listForPage);
	}

	/**
	 * ??????
	 * 
	 * @param req
	 * @return
	 */
	@Transactional
	public Result<Integer> update(CourseApplyUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID????????????");
		}
		CourseApply courseApply = dao.getById(req.getId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("?????????????????????");
		}
		if (IsFreeEnum.FREE.getCode().equals(req.getIsFree())) {
			req.setCourseOriginal(BigDecimal.ZERO);
			req.setCourseDiscount(BigDecimal.ZERO);
		}
		CourseApply record = BeanUtil.copyProperties(req, CourseApply.class);
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		int result = dao.updateById(record);
		if (result < 0) {
			return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
		}
		if (StringUtils.hasText(req.getIntroduce())) {
			// ??????????????????
			CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(courseApply.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceApply)) {
				return Result.error("???????????????????????????");
			}
			// ??????????????????
			courseIntroduceApply.setIntroduce(req.getIntroduce());
			int courseIntroduceResult = courseIntroduceApplyDao.updateById(courseIntroduceApply);
			if (courseIntroduceResult < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}
		return Result.success(result);
	}

	/**
	 * ??????(??????????????????)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseApplyGetRESQ> get(CourseApplyGetREQ req) {
		if (req.getId() == null) {
			return Result.error("ID????????????");
		}
		CourseApply record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("?????????????????????");
		}
		CourseApplyGetRESQ resq = BeanUtil.copyProperties(record, CourseApplyGetRESQ.class);
		// ??????????????????
		if (resq.getSubjectId1() != null && !"0".equals(resq.getSubjectId1())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId1());
			resq.setSubjectName1(courseSubject.getSubjectName());
		}
		if (resq.getSubjectId2() != null && !"0".equals(resq.getSubjectId2())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId2());
			resq.setSubjectName2(courseSubject.getSubjectName());
		}
		if (resq.getSubjectId3() != null && !"0".equals(resq.getSubjectId3())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId3());
			resq.setSubjectName3(courseSubject.getSubjectName());
		}
		// ????????????
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(resq.getIntroduceId());
		resq.setIntroduceId(courseIntroduceApply.getId());
		resq.setIntroduce(courseIntroduceApply.getIntroduce());

		// ??????????????????
		TeacherVO teacherresq = bossTeacherBiz.getByTeacherUserNo(resq.getTeacherUserNo());
		if (ObjectUtil.isNull(teacherresq)) {
			throw new BaseException("?????????????????????");
		}
		resq.setTeacherName(teacherresq.getTeacherName());
		return Result.success(resq);
	}

	/**
	 * ????????????
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseApplyViewRESQ> view(CourseApplyViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID????????????");
		}
		CourseApply record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("?????????????????????");
		}
		CourseApplyViewRESQ resq = BeanUtil.copyProperties(record, CourseApplyViewRESQ.class);
		// ??????????????????
		if (resq.getSubjectId1() != null && !"0".equals(resq.getSubjectId1())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId1());
			resq.setSubjectName1(courseSubject.getSubjectName());
		}
		if (resq.getSubjectId2() != null && !"0".equals(resq.getSubjectId2())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId2());
			resq.setSubjectName2(courseSubject.getSubjectName());
		}
		if (resq.getSubjectId3() != null && !"0".equals(resq.getSubjectId3())) {
			CourseSubject courseSubject = courseSubjectDao.getById(resq.getSubjectId3());
			resq.setSubjectName3(courseSubject.getSubjectName());
		}
		// ????????????
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(resq.getIntroduceId());
		resq.setIntroduceId(courseIntroduceApply.getId());
		resq.setIntroduce(courseIntroduceApply.getIntroduce());

		// ??????????????????
		TeacherVO teacherresq = bossTeacherBiz.getByTeacherUserNo(resq.getTeacherUserNo());
		if (ObjectUtil.isNull(teacherresq)) {
			throw new BaseException("?????????????????????");
		}
		resq.setTeacherName(teacherresq.getTeacherName());
		// ??????
		List<ChapterApply> ChapterList = chapterApplyDao.listByCourseIdAndValidValue(req.getId(), ValidValueEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<ChapterApplyViewRESQ> chapterList = PageUtil.copyList(ChapterList, ChapterApplyViewRESQ.class);
			for (ChapterApplyViewRESQ chapter : chapterList) {
				// ??????
				List<LessonApply> lessonList = lessonApplyDao.listByChapterIdAndValidValue(chapter.getId(), ValidValueEnum.YES.getCode());
				chapter.setLessonApplyList(PageUtil.copyList(lessonList, LessonApplyViewRESQ.class));
			}
			resq.setChapterApplyList(chapterList);
		}
		return Result.success(resq);
	}

	public Result<Integer> audit(CourseApplyApplyStatusREQ req) {
		if (req.getId() == null) {
			return Result.error("??????ID");
		}
		if (req.getApplyStatus() == null) {
			return Result.error("????????????????????????");
		}
		// ?????????
		if (!ApplyStatusEnum.SUCCESS.getCode().equals(req.getApplyStatus())) {
			CourseApply audit = BeanUtil.copyProperties(req, CourseApply.class);
			return Result.success(dao.updateById(audit));
		}
		// ?????? ??????-??????-??????
		CourseApply courseApply = dao.getById(req.getId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("???????????????");
		}

		// ????????????ID????????????????????????
		List<LessonApply> lessonAuditList = lessonApplyDao.listByCourseId(courseApply.getId());

		Course course = courseDao.getById(courseApply.getId());
		// 1??????????????????
		// ???????????????????????????????????????????????????
		if (ObjectUtil.isNotNull(course)) {
			course = BeanUtil.copyProperties(courseApply, Course.class);
			course.setCreateTime(null);
			course.setModifiedTime(null);
			// ??????????????????
			if (CollectionUtils.isEmpty(lessonAuditList)) {
				course.setLessonTotal(0);
			} else {
				course.setLessonTotal(lessonAuditList.size());
			}
			// ?????????????????????
			courseDao.updateById(course);
		} else {
			// ???????????????????????????????????????????????????
			Course info = BeanUtil.copyProperties(courseApply, Course.class);
			info.setCreateTime(null);
			info.setModifiedTime(null);
			// ??????????????????
			if (CollectionUtils.isEmpty(lessonAuditList)) {
				info.setLessonTotal(0);
			} else {
				info.setLessonTotal(lessonAuditList.size());
			}
			courseDao.save(info);
		}

		// 2????????????????????????
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(courseApply.getIntroduceId());
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(courseApply.getIntroduceId());
		if (ObjectUtil.isNull(courseIntroduceApply)) {
			return Result.error("??????????????????????????????");
		}
		if (ObjectUtil.isNull(courseIntroduce)) {
			CourseIntroduce introduce = BeanUtil.copyProperties(courseIntroduceApply, CourseIntroduce.class);
			courseIntroduceDao.save(introduce);
		} else {
			courseIntroduce = BeanUtil.copyProperties(courseIntroduceApply, CourseIntroduce.class);
			courseIntroduceApplyDao.updateById(courseIntroduceApply);
		}
		// 3??????????????????
		chapter(courseApply);

		// 4??????????????????
		lesson(courseApply);

		// ????????????????????????
		CourseApply audit = BeanUtil.copyProperties(req, CourseApply.class);
		int resultNum = dao.updateById(audit);
		if (resultNum < 0) {
			return Result.error(ResultEnum.COURSE_AUDIT_FAIL);
		}
		return Result.success(resultNum);
	}

	// ????????????
	private void chapter(CourseApply courseApply) {
		// ????????????????????????????????????????????????
		List<ChapterApply> chapterApplyList = chapterApplyDao.listByCourseId(courseApply.getId());
		if (CollectionUtils.isEmpty(chapterApplyList)) {
			return;
		}
		for (ChapterApply chapterApply : chapterApplyList) {
			// ??????????????????????????????????????????
			ChapterApply infoAudit = chapterApplyDao.getById(chapterApply.getId());
			if (ObjectUtil.isNull(infoAudit)) {
				throw new BaseException("???????????????????????????");
			}
			// ????????????????????????????????????????????????
			Chapter chapter = chapterDao.getById(chapterApply.getId());
			// ????????????????????????????????????
			if (ObjectUtil.isNotNull(chapter)) {
				chapter = BeanUtil.copyProperties(infoAudit, Chapter.class);
				chapter.setCreateTime(null);
				chapter.setModifiedTime(null);
				chapterDao.updateById(chapter);
			} else {
				// ??????????????????????????????????????????
				chapter = BeanUtil.copyProperties(infoAudit, Chapter.class);
				chapter.setCreateTime(null);
				chapter.setModifiedTime(null);
				chapterDao.save(chapter);
			}
			// ??????????????????
			infoAudit.setApplyStatus(ApplyStatusEnum.SUCCESS.getCode());
			chapterApplyDao.updateById(infoAudit);
		}
	}

	// ????????????
	private void lesson(CourseApply courseApply) {
		// ????????????????????????????????????????????????
		List<LessonApply> lessonApplyList = lessonApplyDao.listByCourseId(courseApply.getId());
		if (CollectionUtils.isEmpty(lessonApplyList)) {
			return;
		}
		for (LessonApply lessonApply : lessonApplyList) {
			// ??????????????????????????????????????????
			LessonApply chapterlessonAudit = lessonApplyDao.getById(lessonApply.getId());
			if (ObjectUtil.isNull(chapterlessonAudit)) {
				throw new BaseException("???????????????????????????");
			}
			// ????????????????????????????????????
			Lesson chapterLesson = lessonDao.getById(lessonApply.getId());
			// ?????????????????????????????????????????????
			if (ObjectUtil.isNotNull(chapterLesson)) {
				if (IsDocEnum.NO.getCode().equals(chapterLesson.getIsDoc())) {
					AliyunUtil.delete(chapterLesson.getDocUrl(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
				}
				chapterLesson = BeanUtil.copyProperties(chapterlessonAudit, Lesson.class);
				chapterLesson.setCreateTime(null);
				chapterLesson.setModifiedTime(null);
				lessonDao.updateById(chapterLesson);
			} else {
				// ?????????????????????????????????????????????
				chapterLesson = BeanUtil.copyProperties(chapterlessonAudit, Lesson.class);
				chapterLesson.setCreateTime(null);
				chapterLesson.setModifiedTime(null);
				lessonDao.save(chapterLesson);
			}
			// ??????????????????
			chapterlessonAudit.setApplyStatus(ApplyStatusEnum.SUCCESS.getCode());
			lessonApplyDao.updateById(chapterlessonAudit);
		}
	}

}
