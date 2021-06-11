package com.wangxin.langshu.business.edu.iservice.user.pcweb;

import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bo.auth.AuthStudentViewBO;
import com.wangxin.langshu.business.edu.vo.user.old.common.dto.auth.AuthStudentDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 用户教育信息
 *
 *  
 */

public interface IPcWebStudentBiz {

	/**
	 * 用户信息查看接口
	 * 
	 * @param userNo
	 * 
	 */
	public Result<AuthStudentDTO> view(AuthStudentViewBO authStudentViewBO) ;

	/**
	 * 用户信息更新接口
	 * 
	 * @param authStudentBO
	 * 
	 */
	public Result<AuthStudentDTO> update(AuthStudentBO authStudentBO) ;
	
	
	/**
	 * 用户注销接口
	 * 
	 * @param userNo
	 * 
	 */
	public Result<Integer> logout(AuthStudentViewBO authStudentViewBO) ;
}
