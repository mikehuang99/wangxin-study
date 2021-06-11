package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaperContent.ExamPaperContentVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamPaperContentDao extends BaseDao {
	
	
	
	
	
	
	public Page<ExamPaperContentVo> queryExamPaperContents(ExamPaperContentQ examPaperQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from exam_paper_content q ");
        sb.append("  where 1=1  ");
    	
    	if(StringUtils.isNotEmpty(examPaperQ.getExamPaperId())){
    		sb.append(" and q.exam_paper_id = :exam_paper_id ");
    		paramMap.put("exam_paper_id", examPaperQ.getExamPaperId());
    	}

    	if(StringUtils.isNotEmpty(examPaperQ.getTeacherUserNo())){
    		sb.append(" and q.teacher_user_no = :teacher_user_no ");
    		paramMap.put("teacher_user_no", examPaperQ.getTeacherUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(examPaperQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", examPaperQ.getValidValue());
    	}
    	
    	sb.append(" order by q.create_time desc");
    	
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examPaperQ.getPageCurrent(), examPaperQ.getPageSize(), null);
        
        List<ExamPaperContentVo> list = new ArrayList<ExamPaperContentVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamPaperContentVo examPaperVo = new ExamPaperContentVo();
        	ObjectMapUtil2.mapToObjModle(examPaperVo,mapHump);
        	list.add(examPaperVo);
        }
        
        Page<ExamPaperContentVo> pg = new Page<ExamPaperContentVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	

	
	
	
	   

}
