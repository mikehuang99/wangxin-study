package com.wangxin.langshu.business.edu.service.course.pcweb;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyListBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyStandBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplyListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplyViewDTO;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApply;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseApplyExample.Criteria;
import com.wangxin.langshu.business.edu.entity.course.old.CourseIntroduceApply;
import com.wangxin.langshu.business.edu.dao.course.ChapterApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseApplyDao;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseIntroduceApplyDao;
import com.wangxin.langshu.business.edu.dao.course.LessonApplyDao;
import com.wangxin.langshu.business.edu.iservice.course.pcweb.IPcWebCourseApplyBiz;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ApplyStatusEnum;
import com.wangxin.langshu.business.util.enums.IsFreeEnum;
import com.wangxin.langshu.business.util.enums.IsPutawayEnum;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.Constants;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * ????????????-??????
 *
 *  
 */
@Service
public class PcWebCourseApplyBiz extends BaseBiz implements IPcWebCourseApplyBiz {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseApplyDao courseApplyDao;
	@Autowired
	private ChapterApplyDao chapterAduitDao;
	@Autowired
	private LessonApplyDao lessonApplyDao;
	@Autowired
	private CourseIntroduceApplyDao courseIntroduceApplyDao;

	/**
	 * ??????????????????
	 * 
	 * @param authCourseApplyListBO
	 * @return
	 * 
	 */
	public Result<Page<AuthCourseApplyListDTO>> listForPage(AuthCourseApplyListBO authCourseApplyListBO) {
		if (authCourseApplyListBO.getTeacherUserNo() == null) {
			return Result.error("teacherUserNo????????????");
		}
		CourseApplyExample example = new CourseApplyExample();
		Criteria c = example.createCriteria();
		c.andTeacherUserNoEqualTo(authCourseApplyListBO.getTeacherUserNo());
		c.andValidValueLessThan(Constants.FREEZE);
		if (authCourseApplyListBO.getApplyStatus() != null) {
			c.andApplyStatusEqualTo(authCourseApplyListBO.getApplyStatus());
		}
		example.setOrderByClause("apply_status asc, sort desc, id desc");
		Page<CourseApply> page = courseApplyDao.listForPage(authCourseApplyListBO.getPageCurrent(), authCourseApplyListBO.getPageSize(), example);
		Page<AuthCourseApplyListDTO> dtoPage = PageUtil.transform(page, AuthCourseApplyListDTO.class);
		for (AuthCourseApplyListDTO dto : dtoPage.getList()) {
			Course course = courseDao.getById(dto.getId());
			if (ObjectUtil.isNull(course)) {
				dto.setIsDelete(1);
			} else {
				dto.setIsDelete(0);
			}
		}
		return Result.success(dtoPage);
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public Result<AuthCourseApplyViewDTO> view(AuthCourseApplyViewBO authCourseApplyViewBO) {
		if (StringUtils.isEmpty(authCourseApplyViewBO.getId())) {
			return Result.error("??????id????????????");
		}

		// ??????????????????
		CourseApply courseApply = courseApplyDao.getById(authCourseApplyViewBO.getId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("???????????????????????????");
		}
		AuthCourseApplyViewDTO dto = BeanUtil.copyProperties(courseApply, AuthCourseApplyViewDTO.class);
		// ????????????
		CourseIntroduceApply courseIntroduceApply = courseIntroduceApplyDao.getById(courseApply.getIntroduceId());
		dto.setIntroduce(courseIntroduceApply.getIntroduce());

		return Result.success(dto);
	}

	/**
	 * ??????????????????
	 * 
	 * @param authCourseApplySaveBO
	 * @return
	 * 
	 */
	@Transactional
	public Result<AuthCourseApplySaveDTO> save(AuthCourseApplySaveBO authCourseApplySaveBO) {
		if (StringUtils.isEmpty(authCourseApplySaveBO.getUserNo())) {
			return Result.error("userNo????????????");
		}
		// ????????????0
		if (authCourseApplySaveBO.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			return Result.error("??????????????????0");
		}
		// ???????????????????????????
		if (IsFreeEnum.CHARGE.getCode().equals(authCourseApplySaveBO.getIsFree())) {
			if (authCourseApplySaveBO.getCourseOriginal() == null) {
				return Result.error("??????????????????");
			}
		}

		// ????????????
		CourseIntroduceApply courseIntroduceApply = new CourseIntroduceApply();
		courseIntroduceApply.setIntroduce(authCourseApplySaveBO.getIntroduce());
		courseIntroduceApplyDao.save(courseIntroduceApply);

		// ??????
		CourseApply record = BeanUtil.copyProperties(authCourseApplySaveBO, CourseApply.class);
		if (IsFreeEnum.FREE.getCode().equals(authCourseApplySaveBO.getIsFree())) {
			// ??????????????????????????????0(??????????????????)
			record.setCourseOriginal(BigDecimal.valueOf(0));
			record.setCourseDiscount(BigDecimal.valueOf(0));
		}
		record.setValidValue(ValidValueEnum.YES.getCode());
		record.setIsPutaway(IsPutawayEnum.YES.getCode());
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		record.setIntroduceId(courseIntroduceApply.getId());
		record.setCourseDiscount(authCourseApplySaveBO.getCourseOriginal());
		record.setTeacherUserNo(authCourseApplySaveBO.getUserNo());
		// ????????????????????????????????????
		if (courseApplyDao.save(record) > 0) {
			return Result.success(BeanUtil.copyProperties(record, AuthCourseApplySaveDTO.class));
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * ??????????????????
	 * 
	 * @param authCourseApplyUpdateBO
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> update(AuthCourseApplyUpdateBO authCourseApplyUpdateBO) {
		if (StringUtils.isEmpty(authCourseApplyUpdateBO.getUserNo())) {
			return Result.error("userNo????????????");
		}
		// ???????????????????????????
		if (IsFreeEnum.CHARGE.getCode().equals(authCourseApplyUpdateBO.getIsFree())) {
			if (authCourseApplyUpdateBO.getCourseOriginal() == null) {
				return Result.error("??????????????????");
			}
		}
		// ????????????0
		if (authCourseApplyUpdateBO.getCourseOriginal().compareTo(BigDecimal.valueOf(0)) == -1) {
			return Result.error("??????????????????0");
		}

		CourseApply courseApply = courseApplyDao.getById(authCourseApplyUpdateBO.getId());
		if (courseApply == null) {
			return Result.error("id?????????");
		}
		if (!authCourseApplyUpdateBO.getUserNo().equals(courseApply.getTeacherUserNo())) {
			return Result.error("?????????useNo?????????????????????useNo?????????");
		}

		// ??????????????????
		CourseIntroduceApply courseIntroduceApply = new CourseIntroduceApply();
		courseIntroduceApply.setId(courseApply.getIntroduceId());
		courseIntroduceApply.setIntroduce(authCourseApplyUpdateBO.getIntroduce());
		courseIntroduceApplyDao.updateById(courseIntroduceApply);

		// ????????????
		CourseApply record = BeanUtil.copyProperties(authCourseApplyUpdateBO, CourseApply.class);
		if (IsFreeEnum.FREE.getCode().equals(authCourseApplyUpdateBO.getIsFree())) {
			// ??????????????????????????????0(??????????????????)
			record.setCourseOriginal(BigDecimal.valueOf(0));
			record.setCourseDiscount(BigDecimal.valueOf(0));
		}
		if (authCourseApplyUpdateBO.getSubjectId1() == null) {
			record.setSubjectId1("0");
		}
		if (authCourseApplyUpdateBO.getSubjectId2() == null) {
			record.setSubjectId2("0");
		}
		if (authCourseApplyUpdateBO.getSubjectId3() == null) {
			record.setSubjectId3("0");
		}
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		record.setCourseDiscount(authCourseApplyUpdateBO.getCourseOriginal());
		record.setApplyResultDesc("");

		if (courseApplyDao.updateById(record) > 0) {
			return Result.success(1);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * ?????????????????????
	 * 
	 * @param authCourseApplyStandBO
	 * @return
	 * 
	 */
	public Result<Integer> stand(AuthCourseApplyStandBO authCourseApplyStandBO) {
		if (StringUtils.isEmpty(authCourseApplyStandBO.getId())) {
			return Result.error("??????ID????????????");
		}
		if (StringUtils.isEmpty(authCourseApplyStandBO.getUserNo())) {
			return Result.error("userNo????????????");
		}
		CourseApply courseApply = courseApplyDao.getById(authCourseApplyStandBO.getId());
		if (courseApply == null) {
			return Result.error("id?????????");
		}
		if (!authCourseApplyStandBO.getUserNo().equals(courseApply.getTeacherUserNo())) {
			return Result.error("?????????useNo?????????????????????useNo?????????");
		}
		CourseApply record = BeanUtil.copyProperties(authCourseApplyStandBO, CourseApply.class);
		record.setApplyStatus(ApplyStatusEnum.WAIT.getCode());
		int result = courseApplyDao.updateById(record);
		if (result > 0) {
			return Result.success(result);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @return
	 * 
	 */
	@Transactional
	public Result<Integer> delete(AuthCourseApplyDeleteBO authCourseApplyDeleteBO) {
		if (authCourseApplyDeleteBO.getId() == null) {
			return Result.error("id????????????");
		}
		if (StringUtils.isEmpty(authCourseApplyDeleteBO.getUserNo())) {
			return Result.error("userNo????????????");
		}
		CourseApply courseApply = courseApplyDao.getById(authCourseApplyDeleteBO.getId());
		if (ObjectUtil.isNull(courseApply)) {
			return Result.error("?????????????????????");
		}
		if (!authCourseApplyDeleteBO.getUserNo().equals(courseApply.getTeacherUserNo())) {
			return Result.error("?????????useNo?????????????????????useNo?????????");
		}
		// ?????????????????????????????????
		Course course = courseDao.getByCourseIdAndValidValue(courseApply.getId(), ValidValueEnum.YES.getCode());
		if (ObjectUtil.isNotNull(course)) {
			return Result.error("?????????????????????????????????");
		}

		// ????????????
		chapterAduitDao.deleteByCourseId(courseApply.getId());

		// ????????????
		lessonApplyDao.deleteByCourseId(courseApply.getId());

		return Result.success(courseApplyDao.deleteById(courseApply.getId()));
	}

}
