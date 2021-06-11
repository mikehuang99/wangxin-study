package com.wangxin.langshu.business.edu.dao.course.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpQ;

@Repository
public class CourseApplyCcpDao extends BaseDao{

	
	public List<Map<String, Object>> queryCcp(CourseCcpQ courseCcpQ){
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append(" select c.id course_id,cc.id chapter_id,ccp.id lesson_id ,c.course_name ,cc.chapter_name,ccp.lesson_name from course_apply c  ");
        sb.append("left join chapter_apply cc on c.id = cc.course_id  ");
        sb.append("left join lesson_apply ccp on cc.id = ccp.chapter_id ");
        sb.append(" where 1=1 ");
    	
        /*
    	if(StringUtils.isNotEmpty(courseCcpQ.getUserNo()+"")){
    		sb.append(" and c.teacher_user_no = :user_no ");
    		paramMap.put("user_no", courseCcpQ.getUserNo());
    	}*/
    	
    	return this.jdbcTemplate.queryForList(sb.toString());
	}
}
