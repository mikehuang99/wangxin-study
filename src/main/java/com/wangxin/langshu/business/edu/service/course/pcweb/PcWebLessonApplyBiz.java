package com.wangxin.langshu.business.edu.service.course.pcweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplySortBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.Lesson;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.dao.course.ChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.dao.course.LessonDao;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebLessonApplyBiz;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsDocEnum;
import com.wangxin.langshu.business.util.enums.IsVideoEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.ArrayListUtil;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课时信息-审核
 *
 *  
 */
@Service
public class PcWebLessonApplyBiz extends BaseBiz implements IPcWebLessonApplyBiz{

	@Autowired
	private LessonDao lessonDao;
	@Autowired
	private CourseApplyDao courseApplyDao;
	@Autowired
	private ChapterApplyDao chapterAuditDao;
	@Autowired
	private LessonApplyDao lessonAuditDao;

	/**
	 * 课时列出接口
	 * 
	 * @param authLessonApplyBO
	 * @return
	 * 
	 */
	public Result<AuthLessonAuditListDTO> listByChapterId(AuthLessonApplyBO authLessonApplyBO) {
		if (authLessonApplyBO.getChapterId() == null) {
			return Result.error("章节ID不能为空");
		}

		ChapterApply chapter = chapterAuditDao.getById(authLessonApplyBO.getChapterId());
		if (ObjectUtil.isNull(chapter)) {
			return Result.error("找不到章节信息");
		}

		// 根据章节ID查询课时审核信息表
		List<LessonApply> lessonAuditList = lessonAuditDao.listByChapterIdAndValidValue(authLessonApplyBO.getChapterId(), ValidValueEnum.YES.getCode());
		AuthLessonAuditListDTO dto = new AuthLessonAuditListDTO();
		if (CollectionUtil.isNotEmpty(lessonAuditList)) {
			List<AuthLessonAuditDTO> lessonAuditDTOList = ArrayListUtil.copy(lessonAuditList, AuthLessonAuditDTO.class);
			dto.setUserLessonAuditList(lessonAuditDTOList);
			for (LessonApply lessonApply : lessonAuditList) {
				// 根据课时ID、是否存在视频(1存在，0否)查询课时审核信息
				List<LessonApply> isVideoList = lessonAuditDao.listByIsVideoAndLessonId(lessonApply.getIsVideo(), lessonApply.getId());
				if (lessonApply.getIsVideo().equals(IsVideoEnum.YES.getCode())) {
					dto.setVideoNum(isVideoList.size());
				}
				// 根据课时ID、是否存在文档(1存在，0否)查询课时审核信息
				List<LessonApply> isDocList = lessonAuditDao.listByIsDocAndLessonId(lessonApply.getIsDoc(), lessonApply.getId());
				if (lessonApply.getIsDoc().equals(IsDocEnum.YES.getCode())) {
					dto.setDocNum(isDocList.size());
				}
			}
		}
		return Result.success(dto);
	}

	/**
	 * 课时查看接口
	 * 
	 * @param authLessonApplyViewBO
	 * @return
	 * 
	 */
	public Result<AuthLessonAuditViewDTO> view(AuthLessonApplyViewBO authLessonApplyViewBO) {

		if (authLessonApplyViewBO.getId() == null) {
			return Result.error("课时ID不能为空");
		}
		LessonApply lessonAudit = lessonAuditDao.getById(authLessonApplyViewBO.getId());
		if (ObjectUtil.isNull(lessonAudit)) {
			return Result.error("找不到课时信息");
		}
		AuthLessonAuditViewDTO dto = BeanUtil.copyProperties(lessonAudit, AuthLessonAuditViewDTO.class);
		return Result.success(dto);
	}

	/**
	 * 课时删除接口
	 * 
	 * @param authLessonApplyDeleteBO
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> delete(AuthLessonApplyDeleteBO authLessonApplyDeleteBO) {
		if (authLessonApplyDeleteBO.getId() == null) {
			return Result.error("课时ID不能为空");
		}
		if (authLessonApplyDeleteBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		LessonApply lessonAudit = lessonAuditDao.getById(authLessonApplyDeleteBO.getId());
		if (ObjectUtil.isNull(lessonAudit)) {
			return Result.error("找不到课时信息");
		}
		CourseApply course = courseApplyDao.getById(lessonAudit.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!authLessonApplyDeleteBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		// 更新课时接口状态为已冻结，设置为未审核
		lessonAudit.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		lessonAudit.setValidValue(Constants.FREEZE);
		int result = lessonAuditDao.updateById(lessonAudit);
		if (result > 0) {
			// 更新章节信息审核表状态为未审核
			ChapterApply chapterAudit = new ChapterApply();
			chapterAudit.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			chapterAuditDao.updateById(chapterAudit);
			// 更新课程信息审核表状态为未审核
			CourseApply courseApply = new CourseApply();
			courseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			courseApplyDao.updateById(courseApply);
			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 课时添加接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<AuthLessonApplySaveDTO> save(AuthLessonApplySaveBO authLessonApplySaveBO) {
		if (authLessonApplySaveBO.getChapterId() == null) {
			return Result.error("chapterId不能为空");
		}
		if (StringUtils.isEmpty(authLessonApplySaveBO.getLessonName())) {
			return Result.error("课时名称不能为空");
		}
		if (authLessonApplySaveBO.getIsFree() == null) {
			return Result.error("isFree不能为空");
		}
		if (authLessonApplySaveBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		ChapterApply chapterAudit = chapterAuditDao.getById(authLessonApplySaveBO.getChapterId());
		if (ObjectUtils.isEmpty(chapterAudit)) {
			return Result.error("找不到章节信息");
		}
		CourseApply course = courseApplyDao.getById(chapterAudit.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!authLessonApplySaveBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		LessonApply record = BeanUtil.copyProperties(authLessonApplySaveBO, LessonApply.class);
		// 如果有存文档进来
		if (!StringUtils.isEmpty(authLessonApplySaveBO.getDocUrl())) {
			record.setDocUrl(authLessonApplySaveBO.getDocUrl());
			record.setDocName(authLessonApplySaveBO.getDocName());
			record.setIsDoc(IsDocEnum.YES.getCode());
		}
		// 设置剩余的数据
		record.setCourseId(chapterAudit.getCourseId());
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		// 新增成功
		if (lessonAuditDao.save(record) > 0) {
			// 更新课程审核表、章节审核表的审核状态为待审核
			chapterAuditDao.updateApplyStatusByChapterNo(ApplyStatusEnum.WAIT.getCode(), record.getChapterId());
			courseApplyDao.updateApplyStatusBycourseId(ApplyStatusEnum.WAIT.getCode(), record.getCourseId());
			// 再复制回dto进行返回
			AuthLessonApplySaveDTO dto = BeanUtil.copyProperties(record, AuthLessonApplySaveDTO.class);
			return Result.success(dto);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 课时更新接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> update(AuthLessonApplyUpdateBO authLessonApplyUpdateBO) {
		if (authLessonApplyUpdateBO.getId() == null) {
			return Result.error("课时ID不能为空");
		}
		if (StringUtils.isEmpty(authLessonApplyUpdateBO.getLessonName())) {
			return Result.error("课时名称不能为空");
		}
		if (authLessonApplyUpdateBO.getIsFree() == null) {
			return Result.error("isFree不能为空");
		}
		if (authLessonApplyUpdateBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		LessonApply lessonAudit = lessonAuditDao.getById(authLessonApplyUpdateBO.getId());
		if (ObjectUtil.isNull(lessonAudit)) {
			return Result.error("找不到课时信息");
		}
		CourseApply course = courseApplyDao.getById(lessonAudit.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!authLessonApplyUpdateBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		LessonApply lessonApply = BeanUtil.copyProperties(authLessonApplyUpdateBO, LessonApply.class);

		// 如果有文档进来就保存
		if (!StringUtils.isEmpty(authLessonApplyUpdateBO.getDocUrl())) {
			lessonApply.setDocUrl(authLessonApplyUpdateBO.getDocUrl());
			lessonApply.setDocName(authLessonApplyUpdateBO.getDocName());
			lessonApply.setIsDoc(IsDocEnum.YES.getCode());
			// 如果没有则为删除文档
		} else {
			lessonApply.setDocUrl("");
			lessonApply.setDocName("");
			lessonApply.setIsDoc(IsDocEnum.NO.getCode());
		}
		lessonApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		int result = lessonAuditDao.updateById(lessonApply);
		if (result > 0) {
			courseApplyDao.updateApplyStatusBycourseId(ApplyStatusEnum.WAIT.getCode(), lessonAudit.getCourseId());
			chapterAuditDao.updateApplyStatusByChapterNo(ApplyStatusEnum.WAIT.getCode(), lessonAudit.getChapterId());

			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 更新课时排序接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> sort(AuthLessonApplySortBO authLessonApplySortBO) {
		
		List<AuthLessonAuditViewDTO> lessonViewList = authLessonApplySortBO.getLessonViewList();
		if(CollectionUtil.isNotEmpty(lessonViewList)){
			List<String> lesseonIds = new ArrayList<String>();
			for(int i=lessonViewList.size()-1;i>=0;i--){
				lesseonIds.add(lessonViewList.get(i).getId());
			}
			
			authLessonApplySortBO.setLessonIdList(lesseonIds);
		}
		if (CollectionUtil.isEmpty(authLessonApplySortBO.getLessonIdList())) {
			return Result.error("课时信息不能为空");
		}
		if (authLessonApplySortBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		if (CollectionUtil.isNotEmpty(authLessonApplySortBO.getLessonIdList())) {
			int i = 1;
			for (String lessonId : authLessonApplySortBO.getLessonIdList()) {
				// 更新课时审核信息表的排序
				lessonAuditDao.updateSortByLessonId(i++, lessonId);
				LessonApply lessonAudit = lessonAuditDao.getById(lessonId);
				Lesson lesson = lessonDao.getById(lessonId);
				CourseApply course = courseApplyDao.getById(lessonAudit.getCourseId());
				if (ObjectUtil.isNull(course)) {
					return Result.error("找不到课程信息");
				}
				if (!authLessonApplySortBO.getUserNo().equals(course.getTeacherUserNo())) {
					return Result.error("传入的useNo与该课程的教师useNo不一致");
				}
				// 更新课时信息集合
				if (ObjectUtil.isNotNull(lesson)) {
					Lesson record = new Lesson();
					record.setId(lesson.getId());
					record.setSort(lessonAudit.getSort());
					lessonDao.updateById(record);
				}
			}
			return Result.success(i);
		}
		return Result.error("设置排序失败");
	}
}
