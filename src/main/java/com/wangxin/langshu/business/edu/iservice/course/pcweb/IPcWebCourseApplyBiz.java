package com.wangxin.langshu.business.edu.iservice.course.pcweb;

import org.springframework.transaction.annotation.Transactional;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyListBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyStandBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthCourseApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplyListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthCourseApplyViewDTO;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课程信息-审核
 *
 */
public interface IPcWebCourseApplyBiz{

	
	/**
	 * 教师课程分页接口
	 * 
	 * @param authCourseApplyListBO
	 * @return
	 */
	public Result<Page<AuthCourseApplyListDTO>> listForPage(AuthCourseApplyListBO authCourseApplyListBO);

	/**
	 * 教师课程详情接口
	 * 
	 * @param id
	 * @return
	 */
	public Result<AuthCourseApplyViewDTO> view(AuthCourseApplyViewBO authCourseApplyViewBO) ;
	/**
	 * 教师课程保存接口
	 * 
	 * @param authCourseApplySaveBO
	 * @return
	 */
	@Transactional
	public Result<AuthCourseApplySaveDTO> save(AuthCourseApplySaveBO authCourseApplySaveBO) ;

	/**
	 * 教师课程更新接口
	 * 
	 * @param authCourseApplyUpdateBO
	 * @return
	 */
	@Transactional
	public Result<Integer> update(AuthCourseApplyUpdateBO authCourseApplyUpdateBO) ;

	/**
	 * 教师课程上下架接口
	 * 
	 * @param authCourseApplyStandBO
	 * @return
	 */
	public Result<Integer> stand(AuthCourseApplyStandBO authCourseApplyStandBO);

	/**
	 * 教师课程删除接口
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public Result<Integer> delete(AuthCourseApplyDeleteBO authCourseApplyDeleteBO) ;
}
