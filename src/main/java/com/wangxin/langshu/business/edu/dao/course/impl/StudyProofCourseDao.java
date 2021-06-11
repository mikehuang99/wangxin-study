package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseQ;
import com.wangxin.langshu.business.edu.vo.user.studyProofCourse.StudyProofCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class StudyProofCourseDao extends BaseDao {

	public Page<StudyProofCourseVo> queryStudyProofCourses(StudyProofCourseQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from study_proof_course q ");
        sb.append(" where 1=1 ");
        
        if(StringUtils.isNotEmpty(q.getStudyProofId())){
    		sb.append(" and q.study_proof_id = :study_proof_id ");
    	    paramMap.put("study_proof_id", q.getStudyProofId());
    	}
    	
    	
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<StudyProofCourseVo> list = new ArrayList<StudyProofCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudyProofCourseVo vo = new StudyProofCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudyProofCourseVo> pg = new Page<StudyProofCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	

}
