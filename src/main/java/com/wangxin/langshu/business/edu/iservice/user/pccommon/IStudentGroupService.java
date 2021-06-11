package com.wangxin.langshu.business.edu.iservice.user.pccommon;

import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupSaveVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupViewVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupVo;
import com.wangxin.langshu.business.util.base.Page;

/**
 * 用户组
 */

public interface IStudentGroupService {

	public Page<StudentGroupVo> queryStudentGroups(StudentGroupQ studentGroupQ) ;

	public boolean saveStudentGroup(StudentGroupSaveVo studentGroupSaveVo) ;

	public boolean updateStudentGroup(StudentGroupUpdateVo studentGroupUpdateVo) ;

	
	public boolean deleteStudentGroup(StudentGroupDeleteVo studentGroupDeleteVo) ;

	
	public StudentGroupViewVo viewStudentGroup(StudentGroupViewVo studentGroupViewVo) ;
	


}
