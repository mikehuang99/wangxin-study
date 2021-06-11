package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.ChapterBO;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperQ;
import com.wangxin.langshu.business.edu.vo.exam.examPaper.ExamPaperVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamPaperDao extends BaseDao {
	
	
	
	
	
	
	public Page<ExamPaperVo> queryExamPapers(ExamPaperQ examPaperQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.*,c.course_name course_name from exam_paper q ");
        sb.append("left join course c on q.course_id = c.id ");
        sb.append("  where 1=1  ");
    	
    	if(StringUtils.isNotEmpty(examPaperQ.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    		paramMap.put("course_id", examPaperQ.getCourseId());
    	}

    	if(StringUtils.isNotEmpty(examPaperQ.getTitle())){
    		sb.append(" and INSTR(q.title, :title)>0 ");
    	    paramMap.put("title", examPaperQ.getTitle());
    	}
    	if(StringUtils.isNotEmpty(examPaperQ.getSubtitle())){
    		sb.append(" and INSTR(q.subtitle, :subtitle)>0 ");
    	    paramMap.put("subtitle", examPaperQ.getSubtitle());
    	}
    	if(StringUtils.isNotEmpty(examPaperQ.getLevel())){
    		sb.append(" and q.level = :level ");
    		paramMap.put("level", examPaperQ.getLevel());
    	}
    	if(StringUtils.isNotEmpty(examPaperQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", examPaperQ.getValidValue());
    	}
    	
    	if(StringUtils.isNotEmpty(examPaperQ.getIntroduce())){
    		sb.append(" and INSTR(q.introduce, :introduce)>0 ");
    		paramMap.put("introduce", examPaperQ.getIntroduce());
    	}
    	if(StringUtils.isNotEmpty(examPaperQ.getAttentionPoints())){
    		sb.append(" and INSTR(q.attention_points, :attention_points)>0 ");
    		paramMap.put("attention_points", examPaperQ.getAttentionPoints());
    	}
    	if(StringUtils.isNotEmpty(examPaperQ.getTeacherUserNo()+"")){
    		sb.append(" and q.teacher_user_no = :teacher_user_no ");
    		paramMap.put("teacher_user_no", examPaperQ.getTeacherUserNo());
    	}
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examPaperQ.getPageCurrent(), examPaperQ.getPageSize(), null);
        
        List<ExamPaperVo> list = new ArrayList<ExamPaperVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamPaperVo examPaperVo = new ExamPaperVo();
        	ObjectMapUtil2.mapToObjModle(examPaperVo,mapHump);
        	list.add(examPaperVo);
        }
        
        Page<ExamPaperVo> pg = new Page<ExamPaperVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public List<ChapterBO> queryChaptersByCourseId(String courseId){
    	StringBuffer sb = new StringBuffer();
        sb.append("select cc.* from chapter cc where cc.course_id = ? and valid_value = 1 order by cc.sort asc");
		String strings[] = {courseId};
		List<Map<String,Object>> listMap = this.jdbcTemplate.queryForList(sb.toString(),strings );
		List<ChapterBO> list = new ArrayList<ChapterBO>();
        for(Map<String, Object> map:listMap){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ChapterBO obj = new ChapterBO();
        	ObjectMapUtil2.mapToObjModle(obj,mapHump);
        	list.add(obj);
        }
		return list;
	}
	
	/**
	 * 查询尚未生成实际试卷的试卷
	 * @param examPaperQ
	 * @return
	 */
	public Page<ExamPaperVo> queryNotYetBuildExamPapers() {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from exam_paper q ");
        sb.append("  where 1=1  and q.build_status = 0 ");

    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, -1, -1, null);
        
        List<ExamPaperVo> list = new ArrayList<ExamPaperVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamPaperVo examPaperVo = new ExamPaperVo();
        	ObjectMapUtil2.mapToObjModle(examPaperVo,mapHump);
        	list.add(examPaperVo);
        }
        
        Page<ExamPaperVo> pg = new Page<ExamPaperVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	   

}
