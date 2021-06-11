package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagement.ExamManagementVo;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserQ;
import com.wangxin.langshu.business.edu.vo.exam.examManagementUser.ExamManagementUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamManagementDao extends BaseDao {
	
	public Page<ExamManagementVo> queryExamManagements(ExamManagementQ examManagementQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.*,");
        if(StringUtils.isNotEmpty(examManagementQ.getExamUserNo())) {
        	sb.append("emu.is_finish_user_total_score,emu.user_total_score,");
        }
        sb.append("ep.title exam_paper_title,ep.limit_time limit_time,c.course_name course_name,ABS(NOW() - start_time)  AS diffTime from exam_management q ");
        sb.append("left join exam_paper ep on ep.id = q.exam_paper_id ");
        if(StringUtils.isNotEmpty(examManagementQ.getExamUserNo())) {
            sb.append(" left join exam_management_user emu on emu.exam_management_id = q.id and emu.exam_user_no = :exam_user_no ");
            paramMap.put("exam_user_no", examManagementQ.getExamUserNo());
        }
        sb.append("left join course c on c.id = ep.course_id ");
        
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(examManagementQ.getTitle())){
    		sb.append(" and INSTR(q.title, :title)>0 ");
    	    paramMap.put("title", examManagementQ.getTitle());
    	}
    	if(StringUtils.isNotEmpty(examManagementQ.getExamType())){
    		sb.append(" and q.exam_type = :exam_type ");
    		paramMap.put("exam_type", examManagementQ.getExamType());
    	}
    	if(StringUtils.isNotEmpty(examManagementQ.getStartTime())){
    		sb.append(" and q.start_time >= :start_time ");
    		paramMap.put("start_time", examManagementQ.getStartTime());
    	}
    	
    	//必须时段考试才起效
    	if(StringUtils.isNotEmpty(examManagementQ.getExamType())&&"2".equals(examManagementQ.getExamType())&&StringUtils.isNotEmpty(examManagementQ.getEndTime())){
    		sb.append(" and q.end_time =< :end_time ");
    		paramMap.put("end_time", examManagementQ.getEndTime());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", examManagementQ.getValidValue());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getTeacherUserNo())){
    		sb.append(" and q.teacher_user_no = :teacher_user_no ");
    		paramMap.put("teacher_user_no", examManagementQ.getTeacherUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getExamPaperTitle())){
    		sb.append(" and INSTR(ep.title, :exam_paper_title)>0 ");
    	    paramMap.put("exam_paper_title", examManagementQ.getExamPaperTitle());
    	}
    	
    	if(StringUtils.isNotEmpty(examManagementQ.getCourseId())){
    		sb.append(" and INSTR(ep.course_id, :course_id)>0 ");
    	    paramMap.put("course_id", examManagementQ.getCourseId());
    	}
    	
    	sb.append(" ORDER BY q.sort desc,diffTime ASC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examManagementQ.getPageCurrent(), examManagementQ.getPageSize(), null);
        
        List<ExamManagementVo> list = new ArrayList<ExamManagementVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamManagementVo vo = new ExamManagementVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	
        	if(1==vo.getExamType()){
        		vo.setEndTime(new Date(vo.getStartTime().getTime()+vo.getLimitTime()*60*1000L));
        	}
        	
        	list.add(vo);
        }
        
        Page<ExamManagementVo> pg = new Page<ExamManagementVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	/**
	 * 根据对象类型和考试ID查询所有考试对象
	 * @param examManagementQ
	 * @return
	 */
	public Page<ExamManagementUserVo> queryExamObjectsByExamObjecTypeAndExamManagementId(ExamManagementUserQ examManagementUserQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        if("1".equals(examManagementUserQ.getExamObjectType())) {
            sb.append("select s.user_no exam_user_no,emu.exam_paper_id exam_paper_id,emu.is_finish_user_total_score is_finish_user_total_score,emu.user_total_score user_total_score,s.real_name real_name from student s ");
            sb.append(" left join exam_management_user emu on s.user_no = emu.exam_user_no ");
            sb.append(" where 1=1  ");
            if(StringUtils.isNotEmpty(examManagementUserQ.getIsFinishUserTotalScore())) {
        		if("0".equals(examManagementUserQ.getIsFinishUserTotalScore())) {
        			sb.append(" and emu.is_finish_user_total_score is null ");//因为不会有0的数据
        		}else {
            		sb.append(" and emu.is_finish_user_total_score=:is_finish_user_total_score ");
                	paramMap.put("is_finish_user_total_score", examManagementUserQ.getIsFinishUserTotalScore());

        		}
        	}
            if(StringUtils.isNotEmpty(examManagementUserQ.getRealName())){
        		sb.append(" and INSTR(s.real_name, :real_name)>0 ");
        	    paramMap.put("real_name", examManagementUserQ.getRealName());
        	}
            if(StringUtils.isNotEmpty(examManagementUserQ.getExamUserNo())){
        		sb.append(" and INSTR(s.user_no, :exam_user_no)>0 ");
        	    paramMap.put("exam_user_no", examManagementUserQ.getExamUserNo());
        	}
            
            sb.append("and emu.exam_management_id=:exam_management_id "); 
            
        	paramMap.put("exam_management_id", examManagementUserQ.getExamManagementId());
        	
        }
        if("2".equals(examManagementUserQ.getExamObjectType())) {
        	sb.append("select DISTINCT s.user_no exam_user_no,emu.exam_paper_id exam_paper_id,emu.is_finish_user_total_score is_finish_user_total_score,emu.user_total_score user_total_score,s.real_name real_name from student s ");
        	sb.append("left join exam_management_user emu on s.user_no = emu.exam_user_no ");
        	sb.append(" where 1=1  ");
        	if(StringUtils.isNotEmpty(examManagementUserQ.getIsFinishUserTotalScore())) {
        		if("0".equals(examManagementUserQ.getIsFinishUserTotalScore())) {
        			sb.append(" and emu.is_finish_user_total_score is null ");//因为不会有0的数据
        		}else {
            		sb.append(" and emu.is_finish_user_total_score=:is_finish_user_total_score ");
                	paramMap.put("is_finish_user_total_score", examManagementUserQ.getIsFinishUserTotalScore());

        		}
        	}
        	if(StringUtils.isNotEmpty(examManagementUserQ.getRealName())){
        		sb.append(" and INSTR(s.real_name, :real_name)>0 ");
        	    paramMap.put("real_name", examManagementUserQ.getRealName());
        	}
            if(StringUtils.isNotEmpty(examManagementUserQ.getExamUserNo())){
        		sb.append(" and INSTR(s.user_no, :exam_user_no)>0 ");
        	    paramMap.put("exam_user_no", examManagementUserQ.getExamUserNo());
        	}
        	sb.append(" and s.user_no in( ");
        	sb.append("select ssgr.user_no_for_group from student_student_group_relate ssgr where ssgr.student_group_id in ");
        	sb.append("(select sg.id from student_group sg where sg.id in ( select emsgr.student_group_id ");
        	sb.append("from exam_management_student_group_relate emsgr ");
        	sb.append("where emsgr.exam_management_id=:exam_management_id))) "); 
        
        	paramMap.put("exam_management_id", examManagementUserQ.getExamManagementId());
        }
        if("4".equals(examManagementUserQ.getExamObjectType())) {
        	sb.append("select DISTINCT cuin.bind_user_no exam_user_no,emu.exam_paper_id exam_paper_id,emu.is_finish_user_total_score is_finish_user_total_score,emu.user_total_score user_total_score,ss.real_name real_name from ");
        	sb.append("(select cu.* from course_user cu inner join exam_management em on cu.course_id = em.course_id) cuin ");
        	sb.append("left join exam_management_user emu on cuin.bind_user_no = emu.exam_user_no and emu.exam_management_id=:exam_management_id  ");
        	sb.append("left join student ss on ss.user_no = cuin.bind_user_no ");
        	sb.append("where 1=1 ");
        	if(StringUtils.isNotEmpty(examManagementUserQ.getIsFinishUserTotalScore())) {
        		if("0".equals(examManagementUserQ.getIsFinishUserTotalScore())) {
        			sb.append(" and emu.is_finish_user_total_score is null ");//因为不会有0的数据
        		}else {
            		sb.append(" and emu.is_finish_user_total_score=:is_finish_user_total_score ");
                	paramMap.put("is_finish_user_total_score", examManagementUserQ.getIsFinishUserTotalScore());

        		}
        	}
        	if(StringUtils.isNotEmpty(examManagementUserQ.getRealName())){
        		sb.append(" and INSTR(ss.real_name, :real_name)>0 ");
        	    paramMap.put("real_name", examManagementUserQ.getRealName());
        	}
            if(StringUtils.isNotEmpty(examManagementUserQ.getExamUserNo())){
        		sb.append(" and INSTR(cuin.bind_user_no, :exam_user_no)>0 ");
        	    paramMap.put("exam_user_no", examManagementUserQ.getExamUserNo());
        	}
        	sb.append(" and cuin.bind_user_no in ");
        	sb.append("(select DISTINCT s.user_no from student s  ");
        	sb.append("where s.user_no in( ");
        	sb.append("select ssgr.user_no_for_group from student_student_group_relate ssgr where ssgr.student_group_id in ");
        	sb.append("(select sg.id from student_group sg where sg.id in ( select emsgr.student_group_id ");
        	sb.append("from exam_management_student_group_relate emsgr ");
        	sb.append("where emsgr.exam_management_id=:exam_management_id))))");

        	paramMap.put("exam_management_id", examManagementUserQ.getExamManagementId());
        }
        if("3".equals(examManagementUserQ.getExamObjectType())) {
        	sb.append("select cuin.bind_user_no exam_user_no,emu.exam_paper_id exam_paper_id,emu.is_finish_user_total_score is_finish_user_total_score,emu.user_total_score user_total_score,ss.real_name real_name from  ");
        	sb.append("(select cu.* from course_user cu inner join exam_management em on cu.course_id = em.course_id where em.id=:exam_management_id) cuin ");
        	sb.append("left join exam_management_user emu on cuin.bind_user_no = emu.exam_user_no and emu.exam_management_id=:exam_management_id ");
        	sb.append("left join student ss on ss.user_no = cuin.bind_user_no ");
        	sb.append("where 1=1 ");
        	if(StringUtils.isNotEmpty(examManagementUserQ.getIsFinishUserTotalScore())) {
        		if("0".equals(examManagementUserQ.getIsFinishUserTotalScore())) {
        			sb.append(" and emu.is_finish_user_total_score is null ");//因为不会有0的数据
        		}else {
            		sb.append(" and emu.is_finish_user_total_score=:is_finish_user_total_score ");
                	paramMap.put("is_finish_user_total_score", examManagementUserQ.getIsFinishUserTotalScore());

        		}
        	}
        	if(StringUtils.isNotEmpty(examManagementUserQ.getRealName())){
        		sb.append(" and INSTR(ss.real_name, :real_name)>0 ");
        	    paramMap.put("real_name", examManagementUserQ.getRealName());
        	}
            if(StringUtils.isNotEmpty(examManagementUserQ.getExamUserNo())){
        		sb.append(" and INSTR(cuin.bind_user_no, :exam_user_no)>0 ");
        	    paramMap.put("exam_user_no", examManagementUserQ.getExamUserNo());
        	}
        	paramMap.put("exam_management_id", examManagementUserQ.getExamManagementId());
        }
        
      
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
