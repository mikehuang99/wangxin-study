package com.wangxin.langshu.business.edu.dao.course.impl.fresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.chapter.FreshChapterVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class FreshChapterDao extends BaseDao {

	public Page<FreshChapterVo> queryChapters(FreshChapterQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        //sb.append("select q.*,u.mobile bind_user_mobile from study_card q left join user u on q.bind_user_no=u.user_no where 1=1 ");
        //sb.append("select q.* from course q  where 1=1 ");
        
        sb.append("select q.* from chapter q ");
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
    	
        /*
    	if(q.getIsQueryByIds()!=null&&"1".equals(q.getIsQueryByIds())){
    		List<String> idStrings = q.getIds();
    		List<String> ids = new ArrayList<String>();
    		if(idStrings==null||idStrings.size()==0){
    			idStrings = new ArrayList<String>();
    		}
    		for(int i=0;i<idStrings.size();i++){
    			if(StringUtils.isNotEmpty(idStrings.get(i))){
    				ids.add(idStrings.get(i));
    			}
    		}
    		if(ids.size()==0){
    			ids.add("-0");
    		}
    		sb.append(" and q.id in (:ids) ");
   		 	paramMap.put("ids",ids);
    	}
    	
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
        
        List<FreshChapterVo> list = new ArrayList<FreshChapterVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshChapterVo vo = new FreshChapterVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshChapterVo> pg = new Page<FreshChapterVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	public Page<FreshChapterVo> queryAllChaptersByCourseId(FreshChapterQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        //sb.append("select q.*,u.mobile bind_user_mobile from study_card q left join user u on q.bind_user_no=u.user_no where 1=1 ");
        //sb.append("select q.* from course q  where 1=1 ");
        
        sb.append("select q.* from chapter q ");
        sb.append(" where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    	    paramMap.put("course_id", q.getCourseId());
    	}
    	
       
    	
       
    	sb.append(" ORDER BY q.sort DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshChapterVo> list = new ArrayList<FreshChapterVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshChapterVo vo = new FreshChapterVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshChapterVo> pg = new Page<FreshChapterVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }

}
