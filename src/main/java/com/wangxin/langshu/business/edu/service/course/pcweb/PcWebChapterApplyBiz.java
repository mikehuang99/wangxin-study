package com.wangxin.langshu.business.edu.service.course.pcweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplySortBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyUpdateDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyViewDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Chapter;
import com.wangxin.langshu.business.edu.entity.course.old.ChapterApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.LessonApply;
import com.wangxin.langshu.business.edu.dao.course.ChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.ChapterDao;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebChapterApplyBiz;
import com.wangxin.langshu.business.edu.service.user.pccommon.BossStudentBiz;
import com.wangxin.langshu.business.edu.service.user.pccommon.BossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentVO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.ArrayListUtil;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.Constants;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 章节信息-审核
 *
 *  
 */
@Service
public class PcWebChapterApplyBiz extends BaseBiz implements IPcWebChapterApplyBiz{

	@Autowired
	private CourseApplyDao courseApplyDao;
	@Autowired
	private ChapterApplyDao chapterAuditDao;
	@Autowired
	private LessonApplyDao lessonAuditDao;
	@Autowired
	private ChapterDao chapterDao;

	@Autowired
	private BossStudentBiz bossStudentBiz;

	@Autowired
	private BossTeacherBiz bossTeacherBiz;

	/**
	 * 章节查看接口
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public Result<AuthChapterApplyViewDTO> view(AuthChapterApplyViewBO authChapterApplyViewBO) {

		if (StringUtils.isEmpty(authChapterApplyViewBO.getId())) {
			return Result.error("章节id不能为空");
		}

		// 查询章节审核信息
		ChapterApply chapterAudit = chapterAuditDao.getById(authChapterApplyViewBO.getId());
		if (StringUtils.isEmpty(chapterAudit)) {
			return Result.error("找不到章节信息");
		}
		AuthChapterApplyViewDTO dto = BeanUtil.copyProperties(chapterAudit, AuthChapterApplyViewDTO.class);

		// 查询课时审核信息
		List<LessonApply> lessonAuditList = lessonAuditDao.listByChapterIdAndValidValue(authChapterApplyViewBO.getId(), ValidValueEnum.YES.getCode());
		// 写入课时信息集合
		if (CollectionUtil.isNotEmpty(lessonAuditList)) {
			List<AuthLessonAuditViewDTO> lessonAuditDTOList = ArrayListUtil.copy(lessonAuditList, AuthLessonAuditViewDTO.class);
			dto.setAuthLessonAuditView(lessonAuditDTOList);
		}
		return Result.success(dto);
	}

	/**
	 * 章节删除接口
	 * 
	 * @param id
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> delete(AuthChapterApplyDeleteBO authChapterApplyDeleteBO) {
		if (authChapterApplyDeleteBO.getId() == null) {
			return Result.error("章节id不能为空");
		}
		if (authChapterApplyDeleteBO.getUserNo() == null) {
			return Result.error("用户编号不能为空");
		}

		// 查询更新后的章节信息
		ChapterApply chapterAudit = chapterAuditDao.getById(authChapterApplyDeleteBO.getId());
		if (ObjectUtils.isEmpty(chapterAudit)) {
			return Result.error("找不到章节信息");
		}
		CourseApply course = courseApplyDao.getById(chapterAudit.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (!authChapterApplyDeleteBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		// 查询章节下是否存在课时信息
		List<LessonApply> lessonAuditList = lessonAuditDao.listByChapterIdAndValidValue(chapterAudit.getId(), ValidValueEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(lessonAuditList)) {
			return Result.error("请先删除章节下的所有课时信息");
		}

		// 将状态改为冻结状态
		ChapterApply record = new ChapterApply();
		record.setId(authChapterApplyDeleteBO.getId());
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		record.setValidValue(Constants.FREEZE);
		int result = chapterAuditDao.updateById(record);
		if (result > 0) {
			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 章节列出接口
	 * 
	 * @param courseId
	 * @return
	 * 
	 */
	public Result<AuthChapterApplyListDTO> listByCourseNo(AuthChapterApplyBO authChapterApplyBO) {

		if (StringUtils.isEmpty(authChapterApplyBO.getCourseId())) {
			return Result.error("课程ID不能为空");
		}

		AuthChapterApplyListDTO dto = new AuthChapterApplyListDTO();

		// 根据课程ID查询出章节审核信息
		List<ChapterApply> chapterAuditList = chapterAuditDao.listByCourseIdAndValidValue(authChapterApplyBO.getCourseId(), ValidValueEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(chapterAuditList)) {
			List<AuthChapterApplyDTO> chapterAuditDTOList = ArrayListUtil.copy(chapterAuditList, AuthChapterApplyDTO.class);

			// 根据章节ID查询出课时审核信息
			for (AuthChapterApplyDTO authChapterApplyDTO : chapterAuditDTOList) {
				// 根据章节编号设置课时数量
				List<LessonApply> lessonAuditList = lessonAuditDao.listByChapterIdAndValidValue(authChapterApplyDTO.getId(), ValidValueEnum.YES.getCode());
				authChapterApplyDTO.setLessonNum(lessonAuditList.size());
			}
			dto.setUserChapterAuditList(chapterAuditDTOList);
		}
		return Result.success(dto);
	}

	/**
	 * 章节添加接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<AuthChapterApplySaveDTO> save(AuthChapterApplySaveBO authChapterApplySaveBO) {
		if (authChapterApplySaveBO.getCourseId() == null) {
			return Result.error("courseId不能为空");
		}
		if (StringUtils.isEmpty(authChapterApplySaveBO.getChapterName())) {
			return Result.error("chapterName不能为空");
		}
		if (authChapterApplySaveBO.getIsFree() == null) {
			return Result.error("isFree不能为空");
		}
		if (authChapterApplySaveBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		// 根据courseNo查找课程信息
		CourseApply courseApply = courseApplyDao.getById(authChapterApplySaveBO.getCourseId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("找不到该课程信息");
		}
		if (!authChapterApplySaveBO.getUserNo().equals(courseApply.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		ChapterApply chapterAudit = BeanUtil.copyProperties(authChapterApplySaveBO, ChapterApply.class);
		chapterAudit.setApplyStatus(ApplyStatusEnum.WAIT.getCode());

		// 修改成功将课程改为审核状态，查询已修改的章节审核信息回来
		if (chapterAuditDao.save(chapterAudit) > 0) {
			CourseApply record = new CourseApply();
			record.setId(chapterAudit.getCourseId());
			record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			courseApplyDao.updateById(record);

			AuthChapterApplySaveDTO dto = BeanUtil.copyProperties(chapterAudit, AuthChapterApplySaveDTO.class);
			return Result.success(dto);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 章节更新接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<AuthChapterApplyUpdateDTO> update(AuthChapterApplyUpdateBO authChapterApplyUpdateBO) {
		if (StringUtils.isEmpty(authChapterApplyUpdateBO.getId())) {
			return Result.error("章节ID不能为空");
		}
		if (StringUtils.isEmpty(authChapterApplyUpdateBO.getChapterName())) {
			return Result.error("chapterName不能为空");
		}
		if (authChapterApplyUpdateBO.getIsFree() == null) {
			return Result.error("isFree不能为空");
		}
		if (authChapterApplyUpdateBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		// 根据id查找章节信息
		ChapterApply chapterInfoAudit = chapterAuditDao.getById(authChapterApplyUpdateBO.getId());
		if (ObjectUtil.isNull(chapterInfoAudit)) {
			return Result.error("找不到章节信息");
		}
		CourseApply course = courseApplyDao.getById(chapterInfoAudit.getCourseId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到该课程信息");
		}
		if (!authChapterApplyUpdateBO.getUserNo().equals(course.getTeacherUserNo())) {
			return Result.error("传入的useNo与该课程的教师useNo不一致");
		}

		// 设置章节信息审核表为待审核状态
		ChapterApply chapterInfo = BeanUtil.copyProperties(authChapterApplyUpdateBO, ChapterApply.class);
		chapterInfo.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		int result = chapterAuditDao.updateById(chapterInfo);
		if (result > 0) {
			// 修改课程审核表为待审核状态
			CourseApply courseApply = new CourseApply();
			courseApply.setId(chapterInfoAudit.getCourseId());
			courseApply.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
			courseApplyDao.updateById(courseApply);
			AuthChapterApplyUpdateDTO dto = BeanUtil.copyProperties(chapterInfoAudit, AuthChapterApplyUpdateDTO.class);
			return Result.success(dto);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 更新章节排序接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> sort(AuthChapterApplySortBO authChapterApplySortBO) {
		if (CollectionUtil.isEmpty(authChapterApplySortBO.getChapterIdList())) {
			return Result.error("排序失败，请刷新重试");
		}
		if (authChapterApplySortBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		StudentVO studentVO = bossStudentBiz.getByUserNo(authChapterApplySortBO.getUserNo());
		if (ObjectUtils.isEmpty(studentVO)) {
			return Result.error("找不到用户信息");
		}
		TeacherVO vo = bossTeacherBiz.getByTeacherUserNo(studentVO.getUserNo());
		if (ObjectUtils.isEmpty(vo)) {
			return Result.error("请先申请成为教师");
		}
		// 章节ID集合不为空就遍历拿到ID
		if (CollectionUtil.isNotEmpty(authChapterApplySortBO.getChapterIdList())) {
			int i = 1;
			for (String chapterId : authChapterApplySortBO.getChapterIdList()) {
				// 更新章节审核表排序
				chapterAuditDao.updateSortByChapterId(i++, chapterId);
				// 查询更新后的章节审核信息
				ChapterApply chapterAudit = chapterAuditDao.getById(chapterId);
				CourseApply course = courseApplyDao.getById(chapterAudit.getCourseId());
				if (ObjectUtil.isNull(course)) {
					return Result.error("找不到该课程信息");
				}
				if (!authChapterApplySortBO.getUserNo().equals(course.getTeacherUserNo())) {
					return Result.error("传入的useNo与该课程的教师useNo不一致");
				}
				// 更新章节信息的排序
				Chapter chapter = chapterDao.getById(chapterId);
				if (ObjectUtil.isNotNull(chapter)) {
					Chapter chapterInfo = new Chapter();
					chapterInfo.setId(chapter.getId());
					chapterInfo.setSort(chapterAudit.getSort());
					chapterDao.updateById(chapterInfo);
				}
			}
			return Result.success(i);
		}
		return Result.error("更新排序失败");
	}
}
