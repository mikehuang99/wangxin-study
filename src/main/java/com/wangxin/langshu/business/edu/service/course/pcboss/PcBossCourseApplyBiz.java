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
 * 课程信息-审核
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
		// 获取分类名称
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
	 * 更新
	 * 
	 * @param req
	 * @return
	 */
	@Transactional
	public Result<Integer> update(CourseApplyUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		CourseApply courseApply = dao.getById(req.getId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("找不到课程信息");
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
			// 查询课程简介
			CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(courseApply.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceApply)) {
				return Result.error("找不到课程简介信息");
			}
			// 更新课程简介
			courseIntroduceApply.setIntroduce(req.getIntroduce());
			int courseIntroduceResult = courseIntroduceApplyDao.updateById(courseIntroduceApply);
			if (courseIntroduceResult < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}
		return Result.success(result);
	}

	/**
	 * 查看(课程修改使用)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseApplyGetRESQ> get(CourseApplyGetREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		CourseApply record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到课程信息");
		}
		CourseApplyGetRESQ resq = BeanUtil.copyProperties(record, CourseApplyGetRESQ.class);
		// 获取分类名称
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
		// 课程简介
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(resq.getIntroduceId());
		resq.setIntroduceId(courseIntroduceApply.getId());
		resq.setIntroduce(courseIntroduceApply.getIntroduce());

		// 查询教师信息
		TeacherVO teacherresq = bossTeacherBiz.getByTeacherUserNo(resq.getTeacherUserNo());
		if (ObjectUtil.isNull(teacherresq)) {
			throw new BaseException("找不到教师信息");
		}
		resq.setTeacherName(teacherresq.getTeacherName());
		return Result.success(resq);
	}

	/**
	 * 查看详情
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseApplyViewRESQ> view(CourseApplyViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		CourseApply record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到课程信息");
		}
		CourseApplyViewRESQ resq = BeanUtil.copyProperties(record, CourseApplyViewRESQ.class);
		// 获取分类名称
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
		// 课程简介
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(resq.getIntroduceId());
		resq.setIntroduceId(courseIntroduceApply.getId());
		resq.setIntroduce(courseIntroduceApply.getIntroduce());

		// 查询教师信息
		TeacherVO teacherresq = bossTeacherBiz.getByTeacherUserNo(resq.getTeacherUserNo());
		if (ObjectUtil.isNull(teacherresq)) {
			throw new BaseException("找不到教师信息");
		}
		resq.setTeacherName(teacherresq.getTeacherName());
		// 章节
		List<ChapterApply> ChapterList = chapterApplyDao.listByCourseIdAndValidValue(req.getId(), ValidValueEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<ChapterApplyViewRESQ> chapterList = PageUtil.copyList(ChapterList, ChapterApplyViewRESQ.class);
			for (ChapterApplyViewRESQ chapter : chapterList) {
				// 课时
				List<LessonApply> lessonList = lessonApplyDao.listByChapterIdAndValidValue(chapter.getId(), ValidValueEnum.YES.getCode());
				chapter.setLessonApplyList(PageUtil.copyList(lessonList, LessonApplyViewRESQ.class));
			}
			resq.setChapterApplyList(chapterList);
		}
		return Result.success(resq);
	}

	public Result<Integer> audit(CourseApplyApplyStatusREQ req) {
		if (req.getId() == null) {
			return Result.error("课程ID");
		}
		if (req.getApplyStatus() == null) {
			return Result.error("审核状态不能为空");
		}
		// 不成功
		if (!ApplyStatusEnum.SUCCESS.getCode().equals(req.getApplyStatus())) {
			CourseApply audit = BeanUtil.copyProperties(req, CourseApply.class);
			return Result.success(dao.updateById(audit));
		}
		// 审核 课程-章节-课时
		CourseApply courseApply = dao.getById(req.getId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("课程不存在");
		}

		// 根据课程ID查询课时信息集合
		List<LessonApply> lessonAuditList = lessonApplyDao.listByCourseId(courseApply.getId());

		Course course = courseDao.getById(courseApply.getId());
		// 1、对课程操作
		// 如果课程信息表里面有数据就进行更新
		if (ObjectUtil.isNotNull(course)) {
			course = BeanUtil.copyProperties(courseApply, Course.class);
			course.setCreateTime(null);
			course.setModifiedTime(null);
			// 设置总课时数
			if (CollectionUtils.isEmpty(lessonAuditList)) {
				course.setLessonTotal(0);
			} else {
				course.setLessonTotal(lessonAuditList.size());
			}
			// 更新课程信息表
			courseDao.updateById(course);
		} else {
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
		}

		// 2、对课程简介操作
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(courseApply.getIntroduceId());
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(courseApply.getIntroduceId());
		if (ObjectUtil.isNull(courseIntroduceApply)) {
			return Result.error("课程简介信息表不存在");
		}
		if (ObjectUtil.isNull(courseIntroduce)) {
			CourseIntroduce introduce = BeanUtil.copyProperties(courseIntroduceApply, CourseIntroduce.class);
			courseIntroduceDao.save(introduce);
		} else {
			courseIntroduce = BeanUtil.copyProperties(courseIntroduceApply, CourseIntroduce.class);
			courseIntroduceApplyDao.updateById(courseIntroduceApply);
		}
		// 3、对章节操作
		chapter(courseApply);

		// 4、对课时操作
		lesson(courseApply);

		// 更改课程审核状态
		CourseApply audit = BeanUtil.copyProperties(req, CourseApply.class);
		int resultNum = dao.updateById(audit);
		if (resultNum < 0) {
			return Result.error(ResultEnum.COURSE_AUDIT_FAIL);
		}
		return Result.success(resultNum);
	}

	// 审核章节
	private void chapter(CourseApply courseApply) {
		// 根据课程编号查找章节审核信息集合
		List<ChapterApply> chapterApplyList = chapterApplyDao.listByCourseId(courseApply.getId());
		if (CollectionUtils.isEmpty(chapterApplyList)) {
			return;
		}
		for (ChapterApply chapterApply : chapterApplyList) {
			// 根据章节编号查找章节审核信息
			ChapterApply infoAudit = chapterApplyDao.getById(chapterApply.getId());
			if (ObjectUtil.isNull(infoAudit)) {
				throw new BaseException("找不到章节审核信息");
			}
			// 查找章节信息表是否存在该课时信息
			Chapter chapter = chapterDao.getById(chapterApply.getId());
			// 存在就更新章节信息表数据
			if (ObjectUtil.isNotNull(chapter)) {
				chapter = BeanUtil.copyProperties(infoAudit, Chapter.class);
				chapter.setCreateTime(null);
				chapter.setModifiedTime(null);
				chapterDao.updateById(chapter);
			} else {
				// 如果章节不存在则插入章节信息
				chapter = BeanUtil.copyProperties(infoAudit, Chapter.class);
				chapter.setCreateTime(null);
				chapter.setModifiedTime(null);
				chapterDao.save(chapter);
			}
			// 更新审核状态
			infoAudit.setApplyStatus(ApplyStatusEnum.SUCCESS.getCode());
			chapterApplyDao.updateById(infoAudit);
		}
	}

	// 课时审核
	private void lesson(CourseApply courseApply) {
		// 根据课程编号查找课时审核信息集合
		List<LessonApply> lessonApplyList = lessonApplyDao.listByCourseId(courseApply.getId());
		if (CollectionUtils.isEmpty(lessonApplyList)) {
			return;
		}
		for (LessonApply lessonApply : lessonApplyList) {
			// 根据课时编号查找课时审核信息
			LessonApply chapterlessonAudit = lessonApplyDao.getById(lessonApply.getId());
			if (ObjectUtil.isNull(chapterlessonAudit)) {
				throw new BaseException("找不到课时审核信息");
			}
			// 根据课时编号查找课时信息
			Lesson chapterLesson = lessonDao.getById(lessonApply.getId());
			// 如果信息表存在就更新信息表信息
			if (ObjectUtil.isNotNull(chapterLesson)) {
				if (IsDocEnum.NO.getCode().equals(chapterLesson.getIsDoc())) {
					AliyunUtil.delete(chapterLesson.getDocUrl(), BeanUtil.copyProperties(systemConfigService.view(), Aliyun.class));
				}
				chapterLesson = BeanUtil.copyProperties(chapterlessonAudit, Lesson.class);
				chapterLesson.setCreateTime(null);
				chapterLesson.setModifiedTime(null);
				lessonDao.updateById(chapterLesson);
			} else {
				// 如果课时信息表不存在就插入信息
				chapterLesson = BeanUtil.copyProperties(chapterlessonAudit, Lesson.class);
				chapterLesson.setCreateTime(null);
				chapterLesson.setModifiedTime(null);
				lessonDao.save(chapterLesson);
			}
			// 更新审核状态
			chapterlessonAudit.setApplyStatus(ApplyStatusEnum.SUCCESS.getCode());
			lessonApplyDao.updateById(chapterlessonAudit);
		}
	}

}
