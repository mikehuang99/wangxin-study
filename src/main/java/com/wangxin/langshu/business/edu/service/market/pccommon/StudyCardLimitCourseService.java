package com.wangxin.langshu.business.edu.service.market.pccommon;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.StudyCardLimitCourseDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.StudyCardLimitCourseMapper;
import com.wangxin.langshu.business.edu.entity.market.StudyCardLimitCourse;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.IStudyCardLimitCourseService;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class StudyCardLimitCourseService  extends ServiceImpl<StudyCardLimitCourseMapper, StudyCardLimitCourse> implements IStudyCardLimitCourseService {

	@Autowired
	public StudyCardLimitCourseDao studyCardLimitCourseDao;
	
	@Autowired
    private StudyCardLimitCourseMapper studyCardLimitCourseMapper;

	@Override
	public Page<StudyCardLimitCourseVo> queryStudyCardLimitCourses(StudyCardLimitCourseQ q) {
		return studyCardLimitCourseDao.queryStudyCardLimitCourses(q);
	}

	@Override
	public boolean saveStudyCardLimitCourse(StudyCardLimitCourseSaveVo vo) {
		String id = UUID.randomUUID().toString().replace("-", "");
		StudyCardLimitCourse studyCardLimitCourse = new StudyCardLimitCourse();
		
		BeanUtils.copyProperties(vo, studyCardLimitCourse);
		studyCardLimitCourse.setId(id);
		this.save(studyCardLimitCourse);
		return true;
	}

	@Override
	public boolean updateStudyCardLimitCourse(StudyCardLimitCourseUpdateVo vo) {
		StudyCardLimitCourse studyCardLimitCourse = new StudyCardLimitCourse();
		BeanUtils.copyProperties(vo, studyCardLimitCourse);
		this.updateById(studyCardLimitCourse);

		return true;
	}

	@Override
	public boolean deleteStudyCardLimitCourse(StudyCardLimitCourseDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
		}

		return true;
	}

	@Override
	public StudyCardLimitCourseViewVo viewStudyCardLimitCourse(StudyCardLimitCourseViewVo viewVo) {
		StudyCardLimitCourse vvvo = this.getById(viewVo.getId());
		BeanUtils.copyProperties(vvvo, viewVo);

		


		return viewVo;
	}
	
	@Override
	public String queryAllStudyCardLimitCoursesString(String studyCardId){
		Page<StudyCardLimitCourseVo> page = studyCardLimitCourseDao.queryAllStudyCardLimitCourses(studyCardId);
		String selectedCourseIdsString = "";
		List<StudyCardLimitCourseVo> list = page.getList();
		if(list!=null&&list.size()!=0){
			for(int i=0;i<list.size();i++){
				selectedCourseIdsString = selectedCourseIdsString + list.get(i).getCourseId() + ",";
			}
		}
		return selectedCourseIdsString;
	}

	@Override
	public int deleteStudyCardLimitCoursesByStudyCardId(String studyCardId) {
		return studyCardLimitCourseDao.deleteStudyCardLimitCoursesByStudyCardId(studyCardId);
	}
	
	
	


	
	
}
