package com.wangxin.langshu.business.edu.service.course.pcweb;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoSaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseVideoUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonIdVideoBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseVideoForListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseVideoListDTO;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseVideo;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.dao.course.ChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseVideoDao;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseVideoBiz;
import com.wangxin.langshu.business.edu.iservice.system.pccommon.ISystemConfigService;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsVideoEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.ArrayListUtil;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程视频信息
 *
 * 
 */
@Service
public class PcWebCourseVideoBiz extends BaseBiz implements IPcWebCourseVideoBiz{

	@Autowired
	private CourseVideoDao dao;
	@Autowired
	private LessonApplyDao lessonAuditDao;
	@Autowired
	private ChapterApplyDao chapterAuditDao;
	@Autowired
	private CourseApplyDao courseApplyDao;

	//@Autowired
	//private BossSysBiz bossSysBiz;
	
	
	@Autowired
	private ISystemConfigService systemConfigService;

	/**
	 * 章节视频库，课时视频库添加接口
	 * 
	 * @param authCourseVideoSaveBo
	 * 
	 */
	public Result<Integer> save(AuthCourseVideoSaveBO authCourseVideoSaveBO) {
		if ("0".equals(authCourseVideoSaveBO.getChapterId())) {
			return Result.error("章节ID不能为空");
		}
		if (authCourseVideoSaveBO.getUserNo() == null) {
			return Result.error("useNo不能为空");
		}
		// 根据章节编号查找章节信息
		ChapterApply chapterInfoAudit = chapterAuditDao.getById(authCourseVideoSaveBO.getChapterId());
		if (ObjectUtil.isNull(chapterInfoAudit)) {
			return Result.error("找不到章节信息");
		}
		CourseApply courseApply = courseApplyDao.getById(chapterInfoAudit.getCourseId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("找不到课程信息");
		}
		if (!authCourseVideoSaveBO.getUserNo().equals(courseApply.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}
		// 查找视频信息
		CourseVideo courseVideo = dao.getByVideoLocalNumber(authCourseVideoSaveBO.getVideoLocalNumber());
		if (ObjectUtil.isNull(courseVideo)) {
			return Result.error("找不到视频信息");
		}
		courseVideo.setCourseId(chapterInfoAudit.getCourseId());
		courseVideo.setChapterId(chapterInfoAudit.getId());
		courseVideo.setValidValue(ValidValueEnum.YES.getCode());
		int result = dao.updateById(courseVideo);
		if (result > 0) {
			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 章节视频库列出
	 * 
	 * @param chapterId
	 * 
	 */
	public Result<AuthCourseVideoListDTO> listByChapterId(AuthCourseVideoBO authCourseVideoBO) {
		// 查找该章节下可用的视频信息集合
		List<CourseVideo> courseVideoList = dao.listByChapterIdAndLessonIdAndValidValue(authCourseVideoBO.getChapterId(), "0", ValidValueEnum.YES.getCode());
		AuthCourseVideoListDTO dto = new AuthCourseVideoListDTO();
		if (CollectionUtil.isNotEmpty(courseVideoList)) {
			List<AuthCourseVideoForListDTO> dtoList = ArrayListUtil.copy(courseVideoList, AuthCourseVideoForListDTO.class);
			dto.setList(dtoList);
		}
		return Result.success(dto);
	}

	/**
	 * 课时视频列出
	 * 
	 * @param lessonId
	 * 
	 */
	public Result<AuthCourseVideoListDTO> listByLessonId(AuthLessonIdVideoBO authCourseVideoBO) {
		List<CourseVideo> courseVideoList = dao.listByLessonIdAndValidValue(authCourseVideoBO.getLessonId(), ValidValueEnum.YES.getCode());
		AuthCourseVideoListDTO dto = new AuthCourseVideoListDTO();
		if (CollectionUtil.isNotEmpty(courseVideoList)) {
			List<AuthCourseVideoForListDTO> dtoList = ArrayListUtil.copy(courseVideoList, AuthCourseVideoForListDTO.class);
			dto.setList(dtoList);
		}
		return Result.success(dto);
	}

	/**
	 * 课时视频更新接口
	 * 
	 * @param authCourseVideoUpdateBo
	 * 
	 */
	@Transactional
	public Result<Integer> update(AuthCourseVideoUpdateBO authCourseVideoUpdateBO) {
		if (authCourseVideoUpdateBO.getLessonId() == null) {
			return Result.error("lessonId不能为空");
		}
		if (authCourseVideoUpdateBO.getUserNo() == null) {
			return Result.error("useNo不能为空");
		}
		LessonApply lessonApply = lessonAuditDao.getById(authCourseVideoUpdateBO.getLessonId());
		if (ObjectUtil.isNull(lessonApply)) {
			return Result.error("找不到课时信息");
		}
		CourseApply course = courseApplyDao.getById(lessonApply.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!authCourseVideoUpdateBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		// 查找该课时下的所有视频信息集合更改为冻结状态
		List<CourseVideo> lessonVideoInfoAuditList = dao.listByLessonIdAndValidValue(authCourseVideoUpdateBO.getLessonId(), ValidValueEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(lessonVideoInfoAuditList)) {
			for (CourseVideo lessonVideoInfoAudit : lessonVideoInfoAuditList) {
				CourseVideo audit = new CourseVideo();
				audit.setId(lessonVideoInfoAudit.getId());
				audit.setValidValue(FREEZE);
				dao.updateById(audit);
			}
		}

		// 如果视频编号不为空
		if (authCourseVideoUpdateBO.getVideoLocalNumber() != null) {
			// 根据视频编号课时编号查找课时视频信息
			CourseVideo courseVideo = dao.getByVideoLocalNumberAndLessonId(authCourseVideoUpdateBO.getVideoLocalNumber(), authCourseVideoUpdateBO.getLessonId());
			if (ObjectUtil.isNotNull(courseVideo)) {
				// 如果存在则更新为可用状态
				courseVideo.setValidValue(ValidValueEnum.YES.getCode());
				dao.updateById(courseVideo);
				// 更新课程、章节、课时
				updateLesson(lessonApply, courseVideo);

			} else {
				// 根据视频编号查找可用的课时视频信息
				CourseVideo infoAudit = dao.getByVideoLocalNumberAndValidValue(authCourseVideoUpdateBO.getVideoLocalNumber(), ValidValueEnum.YES.getCode());
				CourseVideo audit = BeanUtil.copyProperties(infoAudit, CourseVideo.class);
				audit.setLessonId(authCourseVideoUpdateBO.getLessonId());
				dao.save(audit);
				// 更新课程、章节、课时
				updateLesson(lessonApply, audit);

			}
			return Result.success(1);
		} else {
			return Result.success(1);
		}
	}

	// 更新课程、章节、课时
	private void updateLesson(LessonApply lessonApply, CourseVideo courseVideo) {
		String videoLocalNumber = courseVideo.getVideoLocalNumber();
		String videoName = courseVideo.getVideoName();
		String videoLength = courseVideo.getVideoLength();

		// 更新课时审核信息为待审核状态
		lessonApply.setIsVideo(IsVideoEnum.YES.getCode());
		lessonApply.setVideoLocalNumber(videoLocalNumber);
		lessonApply.setVideoName(videoName);
		lessonApply.setVideoLength(videoLength);
		lessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		lessonAuditDao.updateById(lessonApply);
		// 更新章节审核信息为待审核状态
		ChapterApply chapterAudit = new ChapterApply();
		chapterAudit.setId(lessonApply.getChapterId());
		chapterAudit.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		chapterAuditDao.updateById(chapterAudit);
		// 更新课程审核信息为待审核状态
		CourseApply courseApply = new CourseApply();
		courseApply.setId(lessonApply.getCourseId());
		courseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		courseApplyDao.updateById(courseApply);
	}

	/**
	 * 视频删除
	 * 
	 * @param bo
	 * 
	 */
	@Transactional
	public Result<Integer> udpateById(AuthCourseVideoDeleteBO authCourseVideoDeleteBO) {
		if (authCourseVideoDeleteBO.getId() == null) {
			return Result.error("id不能为空");
		}
		if (authCourseVideoDeleteBO.getUserNo() == null) {
			return Result.error("teacherUserNo不能为空");
		}
		CourseVideo courseVideo = dao.getById(authCourseVideoDeleteBO.getId());
		if (ObjectUtil.isNull(courseVideo)) {
			return Result.error("找不到该视频");
		}
		CourseApply course = courseApplyDao.getById(courseVideo.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!authCourseVideoDeleteBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}
		//SysVO sys = bossSysBiz.getSys();
		//if (ObjectUtil.isNull(sys)) {
		//	return Result.error("未找到系统配置信息");
		//}
		if (StringUtils.isEmpty("sys.getPolyvUseid()") || StringUtils.isEmpty("sys.getPolyvSecretkey()")) {
			return Result.error("useid或secretkey未配置");
		}
		// 查找该视频是否被使用
		List<CourseVideo> courseVideoList = dao.listByVideoLocalNumberAndNotEqualLessonIdAndValidValue(courseVideo.getVideoLocalNumber(), "0", ValidValueEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(courseVideoList) && courseVideoList.size() >= 2) {
			return Result.error("该视频已被使用，请先删除");
		}
		// 更改为冻结状态
		courseVideo.setValidValue(FREEZE);
		int result = dao.updateById(courseVideo);
		if (result > 0) {
			// 删除保利威视的视频
			if (!courseVideo.getLessonId().equals("0")) {
				LessonApply lessonInfoAudit = lessonAuditDao.getById(courseVideo.getLessonId());
				lessonInfoAudit.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
				lessonAuditDao.updateById(lessonInfoAudit);
			}
			ChapterApply chapterInfo = chapterAuditDao.getById(courseVideo.getChapterId());
			chapterInfo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			chapterAuditDao.updateById(chapterInfo);

			CourseApply courseApply = new CourseApply();
			courseApply.setId(chapterInfo.getCourseId());
			courseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			courseApplyDao.updateById(courseApply);
			return Result.success(result);
		}

		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}



}
