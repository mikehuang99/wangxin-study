package com.wangxin.langshu.business.edu.iservice.user.pccommon;

import java.util.List;

import com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo.TeacherQO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.Page;

/**
 * 教师信息
 *
 *  
 */

public interface IBossTeacherBiz {


	public Page<TeacherVO> listForPage(TeacherQO qo);

	public int save(TeacherQO qo);

	public int deleteById(String id);

	public TeacherVO getById(String id);

	public int updateById(TeacherQO qo) ;
	/**
	 * 根据教师用户编号查找教师信息
	 * 
	 * @param teacherUserNo
	 * @return
	 */
	public TeacherVO getByTeacherUserNo(String teacherUserNo);

	/**
	 * 列出所有教师信息
	 * 
	 * 
	 */
	public List<TeacherVO> listAllForTeacher() ;
}
