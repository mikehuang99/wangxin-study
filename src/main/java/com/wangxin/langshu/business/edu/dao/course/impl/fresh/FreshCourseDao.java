package com.wangxin.langshu.business.edu.dao.course.impl.fresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseQ;
import com.wangxin.langshu.business.edu.vo.course.fresh.course.FreshCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class FreshCourseDao extends BaseDao {

	public Page<FreshCourseVo> queryCourses(FreshCourseQ q) {
		
		
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        //sb.append("select q.*,u.mobile bind_user_mobile from study_card q left join user u on q.bind_user_no=u.user_no where 1=1 ");
        //sb.append("select q.* from course q  where 1=1 ");
        
        sb.append("select q.*,teacher.teacher_name teacher_name,teacher.teacher_mobile teacher_mobile, ");
        sb.append("cs1.subject_name as subject_name1,");
        sb.append("cs2.subject_name as subject_name2,");
        sb.append("cs3.subject_name as subject_name3 from course q ");
        sb.append("left join course_subject cs1 on q.subject_id1=cs1.id ");
        sb.append("left join course_subject cs2 on q.subject_id2=cs2.id ");
        sb.append("left join course_subject cs3 on q.subject_id3=cs3.id ");
        
        sb.append(" left join teacher teacher on q.teacher_user_no = teacher.teacher_user_no ");
        
        sb.append(" where 1=1 ");
        
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
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    	    paramMap.put("valid_value", q.getValidValue());
    	}
    	if(StringUtils.isNotEmpty(q.getIsFree())){
    		sb.append(" and q.is_free = :is_free ");
    	    paramMap.put("is_free", q.getIsFree());
    	}
    	if(StringUtils.isNotEmpty(q.getIsPutaway())){
    		sb.append(" and q.is_putaway = :is_putaway ");
    	    paramMap.put("is_putaway", q.getIsPutaway());
    	}
    	
    	
    	if(StringUtils.isNotEmpty(q.getTeacherName())){
    		sb.append(" and INSTR(teacher.teacher_name, :teacher_name)>0 ");
    	    paramMap.put("teacher_name", q.getTeacherName());
    	}
    	if(StringUtils.isNotEmpty(q.getTeacherMobile())){
    		sb.append(" and teacher.teacher_mobile = :teacher_mobile ");
    	    paramMap.put("teacher_mobile", q.getTeacherMobile());
    	}
       
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshCourseVo> list = new ArrayList<FreshCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshCourseVo vo = new FreshCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshCourseVo> pg = new Page<FreshCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	public Page<FreshCourseVo> queryCommonCourses(FreshCourseQ q) {
		
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.*,");
        sb.append("cs1.subject_name as subject_name1,");
        sb.append("cs2.subject_name as subject_name2,");
        sb.append("cs3.subject_name as subject_name3 from course q ");
        sb.append("left join course_subject cs1 on q.subject_id1=cs1.id ");
        sb.append("left join course_subject cs2 on q.subject_id2=cs2.id ");
        sb.append("left join course_subject cs3 on q.subject_id3=cs3.id ");
        
        sb.append(" where 1=1 and q.is_putaway = 1 and q.valid_value = 1 ");
       
    	if(StringUtils.isNotEmpty(q.getSubjectId1())){
    		sb.append(" and q.subject_id1 = :subject_id1 ");
    	    paramMap.put("subject_id1", q.getSubjectId1());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getSubjectId2())){
    		sb.append(" and q.subject_id2 = :subject_id2 ");
    	    paramMap.put("subject_id2", q.getSubjectId2());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getSubjectId3())){
    		sb.append(" and q.subject_id3 = :subject_id3 ");
    	    paramMap.put("subject_id3", q.getSubjectId3());
    	}
    	
    	
    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(q.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getIsFree())){
    		sb.append(" and q.is_free = :is_free ");
    	    paramMap.put("is_free", q.getIsFree());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCourseType())){
    		sb.append(" and q.course_type = :course_type ");
    	    paramMap.put("course_type", q.getCourseType());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getTeacherUserNo())){
    		sb.append(" and q.teacher_user_no = :teacher_user_no ");
    	    paramMap.put("teacher_user_no", q.getTeacherUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    	    paramMap.put("valid_value", q.getValidValue());
    	}
    	
    	/**
    	 *     private String sortType;
    	 * 排序类型数值
    	 * 1、智能排序（按预设sort排序）
    		2、学习数量
    		3、购买数量
    		4、原价最高
    		5、原价最低
    		6、折扣价后最高
    		7、折扣价后最低
    	 */
    	if(StringUtils.isNotEmpty(q.getSortType())) {
    		switch (q.getSortType()) {
			case "1":
		    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
				break;
			case "2":
		    	sb.append(" ORDER BY q.study_count desc  ");
				break;
			case "3":
		    	sb.append(" ORDER BY q.buy_count desc  ");
				break;
			case "4":
		    	sb.append(" ORDER BY q.course_original desc ");
				break;
			case "5":
		    	sb.append(" ORDER BY q.course_original ASC ");
				break;
			case "6":
		    	sb.append(" ORDER BY q.course_discount DESC ");
				break;
			case "7":
		    	sb.append(" ORDER BY q.course_discount ASC ");
				break;
			default:
		    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
				break;
			}
    	}else {
	    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	}
   
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(),q.getPageSize(), null);
        
        List<FreshCourseVo> list = new ArrayList<FreshCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshCourseVo vo = new FreshCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshCourseVo> pg = new Page<FreshCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	

}
