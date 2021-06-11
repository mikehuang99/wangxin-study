package com.wangxin.langshu.business.edu.iservice.exam.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.exam.ExamManagementStudentGroupRelate;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateDeleteVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateSaveVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateUpdateVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateViewVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.Page;


public interface IExamManagementStudentGroupRelateService  extends IService<ExamManagementStudentGroupRelate> {
	

	public Page<ExamManagementStudentGroupRelateVo> queryEntities(ExamManagementStudentGroupRelateQ q) ;

	public boolean saveEntity(ExamManagementStudentGroupRelateSaveVo vo) ;

	public boolean updateEntity(ExamManagementStudentGroupRelateUpdateVo vo) ;

	
	public boolean deleteEntity(ExamManagementStudentGroupRelateDeleteVo vo) ;
	
	public boolean deleteEntityByExamManagementId(String examManagementId) ;


	
	public ExamManagementStudentGroupRelateViewVo viewEntity(ExamManagementStudentGroupRelateViewVo vo) ;	
	
	
}
