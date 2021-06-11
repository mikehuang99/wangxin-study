package com.wangxin.langshu.business.edu.service.course.pcboss;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseSubjectViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseSubjectViewRESQ;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubjectExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubjectExample.Criteria;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseSubjectBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程分类
 *
 */
@Service
public class PcBossCourseSubjectBiz implements IPcBossCourseSubjectBiz{

	@Autowired
	private CourseSubjectDao dao;

	/**
	 * 课程分类-分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CourseSubjectPageRESQ>> listForPage(CourseSubjectPageREQ req) {
		CourseSubjectExample example = new CourseSubjectExample();
		Criteria c = example.createCriteria();
		if (req.getValidValue() != null) {
			c.andValidValueEqualTo(req.getValidValue());
		}
		if (req.getSubjectType() != null) {
			c.andSubjectTypeEqualTo(req.getSubjectType());
		}
		if (req.getParentId() != null) {
			c.andParentIdEqualTo(req.getParentId());
		}
		if (StringUtils.hasText(req.getSubjectName())) {
			c.andSubjectNameLike(PageUtil.rightLike(req.getSubjectName()));
		} else {
			c.andFloorEqualTo(1);
		}
		example.setOrderByClause(" valid_value desc, sort desc, id desc ");
		Page<CourseSubject> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseSubjectPageRESQ> listForPage = PageUtil.transform(page, CourseSubjectPageRESQ.class);
		for (CourseSubjectPageRESQ resq : listForPage.getList()) {
			resq.setChildren(recursionList(resq.getId()));
		}
		return Result.success(listForPage);
	}

	public Result<Integer> save(CourseSubjectSaveREQ req) {
		if (StringUtils.isEmpty(req.getParentId())) {
			return Result.error("父ID不能为空");
		}
		if (StringUtils.isEmpty(req.getFloor())) {
			return Result.error("层级不能为空");
		}
		if (StringUtils.isEmpty(req.getSubjectName())) {
			return Result.error("分类名称不能为空");
		}
		if (StringUtils.isEmpty(req.getSubjectType())) {
			return Result.error("分类类型不能为空");
		}

		if ("0".equals(req.getParentId()) && req.getFloor() == 1) {
			req.setFloor(1);
		} else {
			CourseSubject parentSubject = dao.getById(req.getParentId());
			if (ObjectUtil.isNull(parentSubject)) {
				return Result.error("找不到父分类信息");
			}
			req.setFloor(req.getFloor() + 1);
		}
		CourseSubject record = BeanUtil.copyProperties(req, CourseSubject.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	public Result<Integer> delete(CourseSubjectDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		List<CourseSubject> list = dao.listByParentId(req.getId());
		if (CollectionUtils.isNotEmpty(list)) {
			return Result.error("请先删除下级分类");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<Integer> update(CourseSubjectUpdateREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseSubject record = BeanUtil.copyProperties(req, CourseSubject.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	public Result<CourseSubjectViewRESQ> view(CourseSubjectViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseSubject parentSubject = dao.getById(req.getId());
		if (ObjectUtil.isNull(parentSubject)) {
			return Result.error("找不到父分类信息");
		}
		return Result.success(BeanUtil.copyProperties(parentSubject, CourseSubjectViewRESQ.class));
	}

	/**
	 * 递归展示分类
	 * 
	 * 
	 */
	private List<CourseSubjectPageRESQ> recursionList(String parentId) {
		List<CourseSubjectPageRESQ> list = new ArrayList<>();
		List<CourseSubject> CourseSubjectList = dao.listByParentId(parentId);
		if (CollectionUtils.isNotEmpty(CourseSubjectList)) {
			for (CourseSubject courseSubject : CourseSubjectList) {
				CourseSubjectPageRESQ resq = BeanUtil.copyProperties(courseSubject, CourseSubjectPageRESQ.class);
				resq.setChildren(recursionList(courseSubject.getId()));
				list.add(resq);
			}
		}
		return list;
	}
}
