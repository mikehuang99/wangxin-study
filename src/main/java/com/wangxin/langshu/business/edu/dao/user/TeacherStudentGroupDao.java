package com.wangxin.langshu.business.edu.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.user.teacherStudentGroup.TeacherStudentGroupQ;
import com.wangxin.langshu.business.edu.vo.user.teacherStudentGroup.TeacherStudentGroupVo;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class TeacherStudentGroupDao extends BaseDao {
	
   	
   	public Page<StudentGroupVo> queryHadBindedStudentGroups(TeacherStudentGroupQ teacherStudentGroupQ) {
   		if(StringUtils.isEmpty(teacherStudentGroupQ.getTeacherId())){
   			throw new BaseException("教师ID不能为空");
   		}
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select * from student_group sg where id  in (select tsg.student_group_id from teacher_student_group tsg where tsg.teacher_id= :teacher_id) ");
    	paramMap.put("teacher_id", teacherStudentGroupQ.getTeacherId());
    	
    	if(StringUtils.isNotEmpty(teacherStudentGroupQ.getGroupName())){
    		sb.append(" and INSTR(sg.group_name, :group_name)>0 ");
    	    paramMap.put("group_name", teacherStudentGroupQ.getGroupName());
    	}
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, teacherStudentGroupQ.getPageCurrent(), teacherStudentGroupQ.getPageSize(), null);
        
        List<StudentGroupVo> list = new ArrayList<StudentGroupVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudentGroupVo vo = new StudentGroupVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudentGroupVo> pg = new Page<StudentGroupVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
   	public Page<StudentGroupVo> queryNotYetBindedStudentGroups(TeacherStudentGroupQ teacherStudentGroupQ) {
   		if(StringUtils.isEmpty(teacherStudentGroupQ.getTeacherId())){
   			throw new BaseException("教师ID不能为空");
   		}
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select * from student_group sg where id  not in (select tsg.student_group_id from teacher_student_group tsg where tsg.teacher_id= :teacher_id) ");

        
    	paramMap.put("teacher_id", teacherStudentGroupQ.getTeacherId());

    	if(StringUtils.isNotEmpty(teacherStudentGroupQ.getGroupName())){
    		sb.append(" and INSTR(sg.group_name, :group_name)>0 ");
    	    paramMap.put("group_name", teacherStudentGroupQ.getGroupName());
    	}
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, teacherStudentGroupQ.getPageCurrent(), teacherStudentGroupQ.getPageSize(), null);
        
        List<StudentGroupVo> list = new ArrayList<StudentGroupVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudentGroupVo vo = new StudentGroupVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudentGroupVo> pg = new Page<StudentGroupVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	   
   	public Page<StudentGroupVo> queryStudentGroups(TeacherStudentGroupQ teacherStudentGroupQ) {
   		if(StringUtils.isEmpty(teacherStudentGroupQ.getTeacherId())){
   			throw new BaseException("教师ID不能为空");
   		}
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select * from student_group sg where 1=1 ");

    	if(StringUtils.isNotEmpty(teacherStudentGroupQ.getGroupName())){
    		sb.append(" and INSTR(sg.group_name, :group_name)>0 ");
    	    paramMap.put("group_name", teacherStudentGroupQ.getGroupName());
    	}

    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, teacherStudentGroupQ.getPageCurrent(), teacherStudentGroupQ.getPageSize(), null);
        
        List<StudentGroupVo> list = new ArrayList<StudentGroupVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudentGroupVo vo = new StudentGroupVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudentGroupVo> pg = new Page<StudentGroupVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
   	
   	public int deleteCouponLimitCoursesByCouponId(String couponId){
		if(StringUtils.isEmpty(couponId)){
			return 0;
		}
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		StringBuffer sb = new StringBuffer();
		sb.append("delete from coupon_limit_course  where coupon_id = :coupon_id");
		parameters.addValue("coupon_id", couponId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
		int count = namedParameterJdbcTemplate.update(sb.toString(),parameters);
		return count;
	}
   	
   	public int deleteTeacherStudentGroup(String teacherId,String studentGroupId) {
   		if(StringUtils.isEmpty(teacherId)){
   			throw new BaseException("教师ID不能为空");
   		}
   		if(StringUtils.isEmpty(studentGroupId)){
   			throw new BaseException("studentGroupId不能为空");
   		}
    	StringBuffer sb = new StringBuffer();
  
        sb.append("delete  from teacher_student_group  where teacher_id=:teacher_id and student_group_id=:student_group_id ");
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		parameters.addValue("teacher_id", teacherId);
		parameters.addValue("student_group_id", studentGroupId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
		int count = namedParameterJdbcTemplate.update(sb.toString(),parameters);
		return count;
    }
   	
   	public boolean isExistTeacherStudentGroup(String teacherId,String studentGroupId) {
   		if(StringUtils.isEmpty(teacherId)){
   			throw new BaseException("教师ID不能为空");
   		}
   		if(StringUtils.isEmpty(studentGroupId)){
   			throw new BaseException("studentGroupId不能为空");
   		}
    	StringBuffer sb = new StringBuffer();
  
        sb.append("select * from teacher_student_group  where teacher_id=:teacher_id and student_group_id=:student_group_id ");
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		parameters.addValue("teacher_id", teacherId);
		parameters.addValue("student_group_id", studentGroupId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
		List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sb.toString(),parameters);
		if(list!=null&&list.size()!=0){
			return true;
		}else{
			return false;
		}
    }
}
