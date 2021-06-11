package com.wangxin.langshu.business.edu.dao.course.impl.fresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.courseVideo.FreshCourseVideoVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class FreshCourseVideoDao extends BaseDao {

	public Page<FreshCourseVideoVo> queryCourseVideos(FreshCourseVideoQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
       
        
        sb.append("select q.* from course_video q ");
        sb.append(" where 1=1 ");
        
        
        
    	if(StringUtils.isNotEmpty(q.getChapterId())){
    		sb.append(" and q.chapter_id = :chapter_id ");
    	    paramMap.put("chapter_id", q.getChapterId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getLessonId())){
    		sb.append(" and q.lesson_id = :lesson_id ");
    	    paramMap.put("lesson_id", q.getLessonId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getVideoLocalNumber())){
    		sb.append(" and q.video_local_number = :video_local_number ");
    	    paramMap.put("video_local_number", q.getVideoLocalNumber());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		if(!"4".equals(q.getValidValue())){
	    		sb.append(" and q.valid_value = :valid_value ");
	    	    paramMap.put("valid_value", q.getValidValue());
    		}
    	}else{
    		sb.append(" and q.valid_value <> 3 ");
    	}
        
        /*
    	
    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(q.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getApplyStatus())){
    		sb.append(" and q.apply_status = :apply_status ");
    	    paramMap.put("apply_status", q.getApplyStatus());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getTeacherUserNo())){
    		sb.append(" and q.teacher_user_no = :teacher_user_no ");
    	    paramMap.put("teacher_user_no", q.getTeacherUserNo());
    	}
    	*/
    	
       
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshCourseVideoVo> list = new ArrayList<FreshCourseVideoVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshCourseVideoVo vo = new FreshCourseVideoVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshCourseVideoVo> pg = new Page<FreshCourseVideoVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	public int deleteCourseVideoByLessonId(String lessonId){
		if(StringUtils.isEmpty(lessonId)){
			return 0;
		}
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		StringBuffer sb = new StringBuffer();
		sb.append("delete from course_video  where lesson_id = :lesson_id");
		parameters.addValue("lesson_id", lessonId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
		int count = namedParameterJdbcTemplate.update(sb.toString(),parameters);
		return count;
	}
	

}
