package com.wangxin.langshu.business.edu.service.course.pcboss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.CourseRecommendViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseRecommendPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.CourseRecommendViewRESQ;
import com.wangxin.langshu.business.edu.entity.course.old.Course;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommend;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommendExample;
import com.wangxin.langshu.business.edu.entity.course.old.CourseRecommendExample.Criteria;
import com.wangxin.langshu.business.edu.entity.course.old.CourseSubject;
import com.wangxin.langshu.business.edu.dao.course.CourseDao;
import com.wangxin.langshu.business.edu.dao.course.CourseRecommendDao;
import com.wangxin.langshu.business.edu.dao.course.CourseSubjectDao;
import com.wangxin.langshu.business.edu.iservice.course.pcboss.IPcBossCourseRecommendBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.base.Result;
import com.wangxin.langshu.business.util.enums.ResultEnum;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 
 * 课程推荐
 *
 */
@Service
public class PcBossCourseRecommendBiz implements IPcBossCourseRecommendBiz{

	@Autowired
	private CourseSubjectDao courseSubjectDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseRecommendDao dao;

	public Result<Page<CourseRecommendPageRESQ>> listForPage(CourseRecommendPageREQ req) {
		CourseRecommendExample example = new CourseRecommendExample();
		Criteria c = example.createCriteria();
		if (req.getValidValue() != null) {
			c.andValidValueEqualTo(req.getValidValue());
		}
		c.andSubjectIdEqualTo(req.getSubjectId());
		example.setOrderByClause("valid_value desc, sort desc, id desc");
		Page<CourseRecommend> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseRecommendPageRESQ> listForPage = PageUtil.transform(page, CourseRecommendPageRESQ.class);
		for (CourseRecommendPageRESQ resq : listForPage.getList()) {
			Course course = courseDao.getById(resq.getCourseId());
			if (ObjectUtil.isNotNull(course)) {
				resq.setCourseName(course.getCourseName());
			}
		}
		return Result.success(listForPage);
	}

	/**
	 * 课程推荐添加
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(CourseRecommendSaveREQ req) {
		if (StringUtils.isEmpty(req.getSubjectId())) {
			return Result.error("分类ID不能为空");
		}
		if (StringUtils.isEmpty(req.getCourseId())) {
			return Result.error("课程ID不能为空");
		}
		// 查找推荐信息
		CourseSubject courseSubject = courseSubjectDao.getById(req.getSubjectId());
		if (ObjectUtil.isNull(courseSubject)) {
			return Result.error("找不到分类信息");
		}
		// 查找课程信息
		Course course = courseDao.getById(req.getCourseId());
		if (ObjectUtil.isNull(course) && !ValidValueEnum.YES.getCode().equals(course.getValidValue())) {
			return Result.error("找不到课程信息");
		}
		// 根据分类ID、课程ID获取推荐课程信息
		CourseRecommend courseRecommend = dao.getBySubjectIdAndCourseId(req.getSubjectId(), req.getCourseId());
		if (ObjectUtil.isNotNull(courseRecommend)) {
			return Result.error("课程已添加推荐");
		}

		// 校验分类下只能添加5课推荐课程
		List<CourseRecommend> list = dao.listBySubjectIdAndValidValue(req.getSubjectId(), ValidValueEnum.YES.getCode());
		if (list.size() >= 5) {
			return Result.error("课程只展示5个");
		}
		CourseRecommend record = BeanUtil.copyProperties(req, CourseRecommend.class);
		int results = dao.save(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_SAVE_FAIL);
	}

	/**
	 * 课程推荐删除
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(CourseRecommendDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		int results = dao.deleteById(req.getId());
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_DELETE_FAIL);
	}

	/**
	 * 课程推荐更新
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> update(CourseRecommendUpdateREQ req) {
		CourseRecommend record = BeanUtil.copyProperties(req, CourseRecommend.class);
		int results = dao.updateById(record);
		if (results > 0) {
			return Result.success(results);
		}
		return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
	}

	/**
	 * 课程推荐查看
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseRecommendViewRESQ> view(CourseRecommendViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		CourseRecommend record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到推荐信息");
		}
		return Result.success(BeanUtil.copyProperties(record, CourseRecommendViewRESQ.class));
	}

}
