package com.wangxin.langshu.business.edu.iservice.course.pcweb;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyDeleteBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplySaveBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplySortBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyUpdateBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.auth.AuthChapterApplyViewBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyListDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplySaveDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyUpdateDTO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth.AuthChapterApplyViewDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 章节信息-审核
 *
 */

public interface IPcWebChapterApplyBiz  {

	/**
	 * 章节查看接口
	 * 
	 * @param id
	 * @return
	 */
	public Result<AuthChapterApplyViewDTO> view(AuthChapterApplyViewBO authChapterApplyViewBO) ;

	/**
	 * 章节删除接口
	 * 
	 * @param id
	 * @return
	 */
	public Result<Integer> delete(AuthChapterApplyDeleteBO authChapterApplyDeleteBO) ;
	/**
	 * 章节列出接口
	 * 
	 * @param courseId
	 * @return
	 */
	public Result<AuthChapterApplyListDTO> listByCourseNo(AuthChapterApplyBO authChapterApplyBO) ;

	/**
	 * 章节添加接口
	 * 
	 * @param bo
	 * @return
	 */
	
	public Result<AuthChapterApplySaveDTO> save(AuthChapterApplySaveBO authChapterApplySaveBO) ;

	/**
	 * 章节更新接口
	 * 
	 * @param bo
	 * @return
	 */
	public Result<AuthChapterApplyUpdateDTO> update(AuthChapterApplyUpdateBO authChapterApplyUpdateBO) ;

	/**
	 * 更新章节排序接口
	 * 
	 * @param bo
	 * @return
	 */
	
	public Result<Integer> sort(AuthChapterApplySortBO authChapterApplySortBO) ;
}
