package com.wangxin.langshu.business.edu.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogQ;
import com.wangxin.langshu.business.edu.vo.user.userStudyLog.UserStudyLogVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class UserStudyLogDao extends BaseDao {
	
   	public Page<UserStudyLogVo> queryUserStudyLogs(UserStudyLogQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        
        sb.append("select q.*,c.course_name,ch.chapter_name,l.lesson_name from user_study_log q  ");
        
        sb.append(" left join course c on q.course_id = c.id ");
        sb.append(" left join chapter ch on q.chapter_id = ch.id ");
        sb.append(" left join lesson l on q.lesson_id = l.id ");
        
        sb.append(" where 1=1  ");

       
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    	    paramMap.put("course_id", q.getCourseId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(c.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getChapterId())){
    		sb.append(" and q.chapter_id = :chapter_id ");
    	    paramMap.put("chapter_id", q.getChapterId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getChapterName())){
    		sb.append(" and INSTR(c.chapter_name, :chapter_name)>0 ");
    	    paramMap.put("chapter_name", q.getChapterName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getLessonId())){
    		sb.append(" and q.lesson_id = :lesson_id ");
    	    paramMap.put("lesson_id", q.getLessonId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getLessonName())){
    		sb.append(" and INSTR(l.lesson_name, :lesson_name)>0 ");
    	    paramMap.put("lesson_name", q.getLessonName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getStudyProgressGreaterThan())){
    		sb.append(" and q.study_progress >= :study_progress ");
    	    paramMap.put("study_progress", q.getStudyProgressGreaterThan().trim());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getStudyProgressLessThan())){
    		sb.append(" and q.study_progress <= :study_progress ");
    	    paramMap.put("study_progress", q.getStudyProgressLessThan().trim());
    	}
    	
    	
    	if(StringUtils.isNotEmpty(q.getStudyUserNo())){
    		sb.append(" and q.study_user_no = :study_user_no ");
    	    paramMap.put("study_user_no", q.getStudyUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getTeacherUserNo())){
    		sb.append(" and c.teacher_user_no = :teacher_user_no ");
    	    paramMap.put("teacher_user_no", q.getTeacherUserNo());
    	}
    	
    	
    	
    	
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<UserStudyLogVo> list = new ArrayList<UserStudyLogVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	UserStudyLogVo vo = new UserStudyLogVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<UserStudyLogVo> pg = new Page<UserStudyLogVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public Page<FreshCourseVo> queryCommonCourses(FreshCourseQ q) {
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from course q  ");
        sb.append(" where 1=1 ");

    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(q.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	if(StringUtils.isNotEmpty(q.getTeacherUserNo())){
    		sb.append(" and q.teacher_user_no = :teacher_user_no ");
    	    paramMap.put("teacher_user_no", q.getTeacherUserNo());
    	}

    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    	    paramMap.put("valid_value", q.getValidValue());
    	}
    	
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshCourseVo> list = new ArrayList<FreshCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshCourseVo vo = new FreshCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshCourseVo> pg = new Page<FreshCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }   

}
