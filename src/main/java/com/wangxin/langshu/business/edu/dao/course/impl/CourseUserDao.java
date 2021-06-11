package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserQ;
import com.wangxin.langshu.business.edu.vo.course.courseUser.CourseUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;
import com.xiaoleilu.hutool.util.CollectionUtil;

@Repository
public class CourseUserDao extends BaseDao {

	public Page<CourseUserVo> queryCourseUsers(CourseUserQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.*,c.course_name,c.is_putaway,c.course_logo,s.real_name,s.mobile from course_user q left join course c on q.course_id = c.id ");
        sb.append(" left join student s on q.bind_user_no = s.user_no ");
        sb.append(" where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    	    paramMap.put("course_id", q.getCourseId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getBindUserNo())){
    		sb.append(" and q.bind_user_no = :bind_user_no ");
    	    paramMap.put("bind_user_no", q.getBindUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(c.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getTeacherUserNo())){
    		sb.append(" and c.teacher_user_no = :teacher_user_no ");
    	    paramMap.put("teacher_user_no", q.getTeacherUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getTotalStudyProgressGreaterThan())){
    		sb.append(" and q.total_study_progress >= :total_study_progress ");
    	    paramMap.put("total_study_progress", q.getTotalStudyProgressGreaterThan().trim());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getTotalStudyProgressLessThan())){
    		sb.append(" and q.total_study_progress <= :total_study_progress ");
    	    paramMap.put("total_study_progress", q.getTotalStudyProgressLessThan().trim());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    	    paramMap.put("valid_value", q.getValidValue());
    	}
    	
    	
    	
    	
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<CourseUserVo> list = new ArrayList<CourseUserVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CourseUserVo vo = new CourseUserVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CourseUserVo> pg = new Page<CourseUserVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public boolean queryCourseUserIsExist(String courseId,String userNo) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from course_user q ");
        sb.append(" where 1=1 ");
        
    		sb.append(" and q.course_id = :course_id ");
    	    paramMap.put("course_id", courseId);
    	
    	
    		sb.append(" and q.bind_user_no = :bind_user_no ");
    	    paramMap.put("bind_user_no", userNo);
    	
    	
    	
    	
    	
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, -1,-1, null);
        
       if(CollectionUtil.isEmpty(pge.getList())){
    	   return false;
       }else{
    	   return true;
       }
    }
	

}
