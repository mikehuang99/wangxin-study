package com.wangxin.langshu.business.edu.iservice.user.pccommon;

import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateSaveVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateViewVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.Page;

/**
 * 用户与用户组关系
 */

public interface IStudentStudentGroupRelateService {

	public Page<StudentStudentGroupRelateVo> queryStudentStudentGroupRelates(StudentStudentGroupRelateQ studentStudentGroupRelateQ) ;

	public boolean saveStudentStudentGroupRelate(StudentStudentGroupRelateSaveVo studentStudentGroupRelateSaveVo) ;

	public boolean updateStudentStudentGroupRelate(StudentStudentGroupRelateUpdateVo studentStudentGroupRelateUpdateVo) ;

	
	public boolean deleteStudentStudentGroupRelate(StudentStudentGroupRelateDeleteVo studentStudentGroupRelateDeleteVo) ;

	
	public StudentStudentGroupRelateViewVo viewStudentStudentGroupRelate(StudentStudentGroupRelateViewVo studentStudentGroupRelateViewVo) ;
	


}
