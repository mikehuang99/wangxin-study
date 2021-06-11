package com.wangxin.langshu.business.edu.iservice.user.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.user.StudyProof;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofDeleteVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofQ;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofSaveVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofUpdateVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofViewVo;
import com.wangxin.langshu.business.edu.vo.user.studyProof.StudyProofVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebStudyProofService  extends IService<StudyProof> {
	
	

	public Page<StudyProofVo> queryStudyProofs(StudyProofQ q) ;
	

	public Result<StudyProofSaveVo> saveStudyProof(StudyProofSaveVo vo) ;

	public Result<Integer> updateStudyProof(StudyProofUpdateVo vo) ;

	
	public boolean deleteStudyProof(StudyProofDeleteVo vo) ;

	
	public Result<StudyProofViewVo> viewStudyProof(StudyProofViewVo vo) ;
	
	
	
	
}
