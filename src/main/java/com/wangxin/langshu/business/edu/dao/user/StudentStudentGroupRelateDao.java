package com.wangxin.langshu.business.edu.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class StudentStudentGroupRelateDao extends BaseDao {
	
   	public Page<StudentStudentGroupRelateVo> queryStudentStudentGroupRelates(StudentStudentGroupRelateQ studentStudentGroupRelateQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        
        sb.append("select q.* from student q where q.user_no ");
       
     
        
    	if(StringUtils.isNotEmpty(studentStudentGroupRelateQ.getIsRelate())){
    		if("0".equals(studentStudentGroupRelateQ.getIsRelate())){
    			sb.append("not ");
    		}
    	}
    	sb.append("in (select ur.user_no_for_group from student_student_group_relate ur where ur.user_no_for_group = q.user_no ");
    	sb.append("and ur.student_group_id = :student_group_id ");
    	sb.append(") ");
	    paramMap.put("student_group_id", studentStudentGroupRelateQ.getStudentGroupId());

    	
    	if(StringUtils.isNotEmpty(studentStudentGroupRelateQ.getUserNoForGroup())){
    		sb.append(" and ur.user_no_for_group = :user_no_for_group ");
    	    paramMap.put("user_no_for_group", studentStudentGroupRelateQ.getUserNoForGroup());
    	}
    	
    	if(StringUtils.isNotEmpty(studentStudentGroupRelateQ.getMobile())){
    		sb.append(" and INSTR(q.mobile, :mobile)>0 ");
    	    paramMap.put("mobile", studentStudentGroupRelateQ.getMobile());
    	}
    	
    	if(StringUtils.isNotEmpty(studentStudentGroupRelateQ.getRealName())){
    		sb.append(" and INSTR(q.real_name, :real_name)>0 ");
    	    paramMap.put("real_name", studentStudentGroupRelateQ.getRealName());
    	}
    	
    	if(StringUtils.isNotEmpty(studentStudentGroupRelateQ.getNickName())){
    		sb.append(" and INSTR(q.nick_name, :nick_name)>0 ");
    	    paramMap.put("nick_name", studentStudentGroupRelateQ.getNickName());
    	}
    	
    	/*
    	if(StringUtils.isNotEmpty(studentStudentGroupRelateQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", studentStudentGroupRelateQ.getValidValue());
    	}*/
    	
    	
    	
    	
    	


       //sb.append("select q.* from student_student_group_relate q ");
        //sb.append("  where 1=1  ");
    
        /*
    	if(StringUtils.isNotEmpty(StudentStudentGroupRelateQ.getGroupName())){
    		sb.append(" and INSTR(q.group_name, :group_name)>0 ");
    	    paramMap.put("group_name", StudentStudentGroupRelateQ.getGroupName());
    	}*/
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, studentStudentGroupRelateQ.getPageCurrent(), studentStudentGroupRelateQ.getPageSize(), null);
        
        List<StudentStudentGroupRelateVo> list = new ArrayList<StudentStudentGroupRelateVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudentStudentGroupRelateVo vo = new StudentStudentGroupRelateVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudentStudentGroupRelateVo> pg = new Page<StudentStudentGroupRelateVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	   

}
