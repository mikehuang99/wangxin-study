package com.wangxin.langshu.business.edu.dao.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupQ;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentGroupVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class StudentGroupDao extends BaseDao {
	
   	public Page<StudentGroupVo> queryStudentGroups(StudentGroupQ studentGroupQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from student_group q ");
        sb.append("  where 1=1  ");
    
    	if(StringUtils.isNotEmpty(studentGroupQ.getGroupName())){
    		sb.append(" and INSTR(q.group_name, :group_name)>0 ");
    	    paramMap.put("group_name", studentGroupQ.getGroupName());
    	}
    	
    	if(StringUtils.isNotEmpty(studentGroupQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", studentGroupQ.getValidValue());
    	}
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, studentGroupQ.getPageCurrent(), studentGroupQ.getPageSize(), null);
        
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
	
	   

}
