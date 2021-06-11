package com.wangxin.langshu.business.edu.iservice.course.pcweb;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplySortBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthLessonApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthLessonAuditViewDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 课时信息-审核
 *
 *  
 */

public interface IPcWebLessonApplyBiz {


	/**
	 * 课时列出接口
	 * 
	 * @param authLessonApplyBO
	 * @return
	 * 
	 */
	public Result<AuthLessonAuditListDTO> listByChapterId(AuthLessonApplyBO authLessonApplyBO) ;

	/**
	 * 课时查看接口
	 * 
	 * @param authLessonApplyViewBO
	 * @return
	 * 
	 */
	public Result<AuthLessonAuditViewDTO> view(AuthLessonApplyViewBO authLessonApplyViewBO);

	/**
	 * 课时删除接口
	 * 
	 * @param authLessonApplyDeleteBO
	 * @return
	 * 
	 */
	public Result<Integer> delete(AuthLessonApplyDeleteBO authLessonApplyDeleteBO) ;

	/**
	 * 课时添加接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	public Result<AuthLessonApplySaveDTO> save(AuthLessonApplySaveBO authLessonApplySaveBO) ;
	/**
	 * 课时更新接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	public Result<Integer> update(AuthLessonApplyUpdateBO authLessonApplyUpdateBO);

	/**
	 * 更新课时排序接口
	 * 
	 * @param bo
	 * @return
	 * 
	 */
	public Result<Integer> sort(AuthLessonApplySortBO authLessonApplySortBO) ;
}
