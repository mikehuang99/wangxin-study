package com.wangxin.langshu.business.edu.dao.course.impl.fresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapterApply.FreshChapterApplyVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class FreshChapterApplyDao extends BaseDao {

	public Page<FreshChapterApplyVo> queryChapterApplys(FreshChapterApplyQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
      
        
        sb.append("select q.* from chapter_apply q ");
        sb.append(" where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    	    paramMap.put("course_id", q.getCourseId());
    	}
    	

       	if(StringUtils.isNotEmpty(q.getValidValue())){
       		if(!"4".equals(q.getValidValue())){
	    		sb.append(" and q.valid_value = :valid_value ");
	    	    paramMap.put("valid_value", q.getValidValue());
       		}
    	}else{
    		sb.append(" and q.valid_value <> 3 ");
    	}
    	
       
       
    	sb.append(" ORDER BY q.sort DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshChapterApplyVo> list = new ArrayList<FreshChapterApplyVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshChapterApplyVo vo = new FreshChapterApplyVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshChapterApplyVo> pg = new Page<FreshChapterApplyVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	public Page<FreshChapterApplyVo> queryAllChapterApplysByCourseId(FreshChapterApplyQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
      
        
        sb.append("select q.* from chapter_apply q ");
        sb.append(" where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    	    paramMap.put("course_id", q.getCourseId());
    	}
    	

    	
       
       
    	sb.append(" ORDER BY q.sort DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshChapterApplyVo> list = new ArrayList<FreshChapterApplyVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshChapterApplyVo vo = new FreshChapterApplyVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshChapterApplyVo> pg = new Page<FreshChapterApplyVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }

}
