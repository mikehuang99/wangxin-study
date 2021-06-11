package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementStudentGroupRelate.ExamManagementStudentGroupRelateVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamManagementStudentGroupRelateDao extends BaseDao {
	
	public Page<ExamManagementStudentGroupRelateVo> queryEntities(ExamManagementStudentGroupRelateQ examManagementStudentGroupRelateQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from exam_management_student_group_relate q ");
       
        sb.append("  where 1=1  ");
        
    	
    	if(StringUtils.isNotEmpty(examManagementStudentGroupRelateQ.getExamManagementId())){
    		sb.append(" and q.exam_management_id = :exam_management_id ");
    		paramMap.put("exam_management_id", examManagementStudentGroupRelateQ.getExamManagementId());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementStudentGroupRelateQ.getStudentGroupId())){
    		sb.append(" and q.student_group_id = :student_group_id ");
    		paramMap.put("student_group_id", examManagementStudentGroupRelateQ.getStudentGroupId());
    	}
    	
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examManagementStudentGroupRelateQ.getPageCurrent(), examManagementStudentGroupRelateQ.getPageSize(), null);
        
        List<ExamManagementStudentGroupRelateVo> list = new ArrayList<ExamManagementStudentGroupRelateVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamManagementStudentGroupRelateVo vo = new ExamManagementStudentGroupRelateVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<ExamManagementStudentGroupRelateVo> pg = new Page<ExamManagementStudentGroupRelateVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
