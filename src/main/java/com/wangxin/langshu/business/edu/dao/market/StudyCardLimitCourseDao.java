package com.wangxin.langshu.business.edu.dao.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.studyCardLimitCourse.StudyCardLimitCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class StudyCardLimitCourseDao extends BaseDao {
	
	public Page<StudyCardLimitCourseVo> queryStudyCardLimitCourses(StudyCardLimitCourseQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from study_card_limit_course q ");
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    		paramMap.put("course_id", q.getCourseId());
    	}
        
        /*
    	if(StringUtils.isNotEmpty(examManagementQ.getTitle())){
    		sb.append(" and INSTR(q.title, :title)>0 ");
    	    paramMap.put("title", examManagementQ.getTitle());
    	}
    	if(StringUtils.isNotEmpty(examManagementQ.getExamType())){
    		sb.append(" and q.exam_type = :exam_type ");
    		paramMap.put("exam_type", examManagementQ.getExamType());
    	}
    	if(StringUtils.isNotEmpty(examManagementQ.getStartTime())){
    		sb.append(" and q.start_time >= :start_time ");
    		paramMap.put("start_time", examManagementQ.getStartTime());
    	}
    	
    	//必须时段考试才起效
    	if(StringUtils.isNotEmpty(examManagementQ.getExamType())&&"2".equals(examManagementQ.getExamType())&&StringUtils.isNotEmpty(examManagementQ.getEndTime())){
    		sb.append(" and q.end_time =< :end_time ");
    		paramMap.put("end_time", examManagementQ.getEndTime());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", examManagementQ.getValidValue());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getUserNo()+"")){
    		sb.append(" and q.user_no = :user_no ");
    		paramMap.put("user_no", examManagementQ.getUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getExamPaperTitle())){
    		sb.append(" and INSTR(ep.title, :exam_paper_title)>0 ");
    	    paramMap.put("exam_paper_title", examManagementQ.getExamPaperTitle());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getCourseId())){
    		sb.append(" and INSTR(ep.course_id, :course_id)>0 ");
    	    paramMap.put("course_id", examManagementQ.getCourseId());
    	}
    	*/
    	//sb.append(" ORDER BY diffTime ASC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<StudyCardLimitCourseVo> list = new ArrayList<StudyCardLimitCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudyCardLimitCourseVo vo = new StudyCardLimitCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudyCardLimitCourseVo> pg = new Page<StudyCardLimitCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public Page<StudyCardLimitCourseVo> queryAllStudyCardLimitCourses(String studyCardId) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from study_card_limit_course q ");
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(studyCardId)){
    		sb.append(" and q.study_card_id = :study_card_id ");
    		paramMap.put("study_card_id", studyCardId);
    	}
        
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, -1, -1, null);
        
        List<StudyCardLimitCourseVo> list = new ArrayList<StudyCardLimitCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudyCardLimitCourseVo vo = new StudyCardLimitCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudyCardLimitCourseVo> pg = new Page<StudyCardLimitCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public int deleteStudyCardLimitCoursesByStudyCardId(String studyCardId){
		if(StringUtils.isEmpty(studyCardId)){
			return 0;
		}
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		StringBuffer sb = new StringBuffer();
		sb.append("delete from study_card_limit_course  where study_card_id = :study_card_id");
		parameters.addValue("study_card_id", studyCardId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
		int count = namedParameterJdbcTemplate.update(sb.toString(),parameters);
		return count;
	}
	
	
	   

}
