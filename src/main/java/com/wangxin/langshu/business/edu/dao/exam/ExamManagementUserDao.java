package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamManagementUserDao extends BaseDao {
	
	public Page<ExamManagementUserVo> queryExamManagementUsers(ExamManagementUserQ examManagementUserQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from exam_management_user q ");
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(examManagementUserQ.getExamManagementId())){
    		sb.append(" and q.exam_management_id = :exam_management_id ");
    		paramMap.put("exam_management_id", examManagementUserQ.getExamManagementId());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementUserQ.getExamPaperId())){
    		sb.append(" and q.exam_paper_id = :exam_paper_id ");
    		paramMap.put("exam_paper_id", examManagementUserQ.getExamPaperId());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementUserQ.getExamUserNo())){
    		sb.append(" and q.exam_user_no = :exam_user_no ");
    		paramMap.put("exam_user_no", examManagementUserQ.getExamUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementUserQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", examManagementUserQ.getValidValue());
    	}
    	
    	sb.append(" ORDER BY q.sort,q.create_time desc ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examManagementUserQ.getPageCurrent(), examManagementUserQ.getPageSize(), null);
        
        List<ExamManagementUserVo> list = new ArrayList<ExamManagementUserVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamManagementUserVo vo = new ExamManagementUserVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	
        	list.add(vo);
        }
        
        Page<ExamManagementUserVo> pg = new Page<ExamManagementUserVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
