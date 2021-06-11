package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestionCourse;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestionCourse.ExamQuestionCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamQuestionCourseDao extends BaseDao {
	
	   public int deleteExamQuestionId(String examQuestionId){
	    	StringBuffer sb = new StringBuffer();
	    	List<String> paramList = new ArrayList<>();
	        sb.append("delete from exam_question_course where question_id = ? ");
	        paramList.add(examQuestionId);
	    	int count  = this.jdbcTemplate.update(sb.toString(), paramList.toArray());
		   return count;
			
	   }
	   
	   public int deleteExamQuestionCourse(ExamQuestionCourse examQuestionCourse){
	    	StringBuffer sb = new StringBuffer();
	    	List<String> paramList = new ArrayList<>();
	        sb.append("delete from exam_question_course where question_id = ? ");
	        paramList.add(examQuestionCourse.getQuestionId());
	        sb.append(" and bind_type = ? ");
	        paramList.add(examQuestionCourse.getBindType()+"");
	        if("1".equals(examQuestionCourse.getBindType())){
	        	sb.append(" and course_id = ? ");
		        paramList.add(examQuestionCourse.getCourseId());
	        }
	        if("2".equals(examQuestionCourse.getBindType())){
	        	sb.append(" and course_id = ? ");
	        	sb.append(" and chapter_id = ? ");
		        paramList.add(examQuestionCourse.getCourseId());
		        paramList.add(examQuestionCourse.getChapterId());
	        }
	        if("3".equals(examQuestionCourse.getBindType())){
	        	sb.append(" and course_id = ? ");
	        	sb.append(" and chapter_id = ? ");
	        	sb.append(" and lesson_id = ? ");
		        paramList.add(examQuestionCourse.getCourseId());
		        paramList.add(examQuestionCourse.getChapterId());
		        paramList.add(examQuestionCourse.getLessonId());
	        }
	       
	    	int count  = this.jdbcTemplate.update(sb.toString(), paramList.toArray());
	    	

	    	
		   return count;
			
	   }
		
	   public Page<ExamQuestionCourseVo> queryExamQuestionCourses(ExamQuestionCourseQ examQuestionCourseQ){
	        //设置参数 用map接收
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	    	StringBuffer sb = generalExamQuestionCoursesSql(examQuestionCourseQ, paramMap);
	    	
	        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examQuestionCourseQ.getPageCurrent(), examQuestionCourseQ.getPageSize(), null);
	        
	        List<ExamQuestionCourseVo> list = new ArrayList<ExamQuestionCourseVo>();
	        for(Map<String, Object> map:pge.getList()){
	        	Map<String,Object> mapHump = new HashMap<String, Object>();
	        	for (Map.Entry<String, Object> entry : map.entrySet()) {
	        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
	        	}
	        	ExamQuestionCourseVo examQuestion = new ExamQuestionCourseVo();
	        	ObjectMapUtil2.mapToObjModle(examQuestion,mapHump);
	        	list.add(examQuestion);
	        }
	        
	        Page<ExamQuestionCourseVo> pg = new Page<ExamQuestionCourseVo>();
	        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
	        pg.setTotalPage(pge.getTotalPage()); //设置总页数
	        pg.setList(list);   //设置当前页数据
	        pg.setPageSize(pge.getPageSize());
	        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
	        
	    	return pg;
	    }

	   public int countExamQuestionCourses(ExamQuestionCourseQ examQuestionCourseQ){
		 //设置参数 用map接收
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	    	StringBuffer sb = generalExamQuestionCoursesSql(examQuestionCourseQ, paramMap);
	        Integer count = count(sb.toString(),paramMap);
	        return count;
	    }
	   
	private StringBuffer generalExamQuestionCoursesSql(ExamQuestionCourseQ examQuestionCourseQ,
			Map<String, Object> paramMap) {
		StringBuffer sb = new StringBuffer();

		sb.append("select cn1.subject_name subject_name1, cn2.subject_name subject_name2,cn3.subject_name subject_name3 ");
		sb.append(",q.* from exam_question q ");
		sb.append("left join course_subject  cn1 on q.subject_id1=cn1.id ");
		sb.append("left join course_subject  cn2 on q.subject_id2=cn2.id ");
		sb.append("left join course_subject  cn3 on q.subject_id3=cn3.id ");
		sb.append("  where 1=1  ");
		

		if(StringUtils.isNotEmpty(examQuestionCourseQ.getQuestionTitle())){
			sb.append(" and INSTR(q.question_title, :question_title)>0 ");
		    paramMap.put("question_title", examQuestionCourseQ.getQuestionTitle());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getQuestionType())){
			sb.append(" and q.question_type = :question_type ");
			paramMap.put("question_type", examQuestionCourseQ.getQuestionType());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getSubjectId1())){
			sb.append(" and q.subject_id1 = :subject_id1 ");
			paramMap.put("subject_id1", examQuestionCourseQ.getSubjectId1());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getSubjectId2())){
			sb.append(" and q.subject_id2 = :subject_id2 ");
			paramMap.put("subject_id2", examQuestionCourseQ.getSubjectId2());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getSubjectId3())){
			sb.append(" and q.subject_id3 = :subject_id3 ");
			paramMap.put("subject_id3", examQuestionCourseQ.getSubjectId3());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getLevel())){
			sb.append(" and q.level = :level ");
			paramMap.put("level", examQuestionCourseQ.getLevel());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getValidValue())){
			sb.append(" and q.valid_value = :valid_value ");
			paramMap.put("valid_value", examQuestionCourseQ.getValidValue());
		}
		
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getCustomLabel())){
			sb.append(" and INSTR(q.custom_label, :custom_label)>0 ");
			paramMap.put("custom_label", examQuestionCourseQ.getCustomLabel());
		}
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getUserNo()+"")){
			sb.append(" and q.user_no = :user_no ");
			paramMap.put("user_no", examQuestionCourseQ.getUserNo());
		}
		
		sb.append("and q.id ");
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getIsRelate())&&"0".equals(examQuestionCourseQ.getIsRelate())){
			sb.append(" not ");
		}
		
		sb.append(" in (select eqc.question_id from exam_question_course eqc where  ");
		
		
		if(StringUtils.isNotEmpty(examQuestionCourseQ.getBindType())){
			if("1".equals(examQuestionCourseQ.getBindType())){
				sb.append(" eqc.course_id=:course_id ");
				//paramMap.put("question_id", examQuestionCourseQ.getQuestionId());
				paramMap.put("course_id", examQuestionCourseQ.getCourseId());
				sb.append(" and eqc.bind_type = 1 )");
			}
			if("2".equals(examQuestionCourseQ.getBindType())){
				sb.append("  eqc.course_id=:course_id and eqc.chapter_id=:chapter_id ");
				//paramMap.put("question_id", examQuestionCourseQ.getQuestionId());
				paramMap.put("course_id", examQuestionCourseQ.getCourseId());
				paramMap.put("chapter_id", examQuestionCourseQ.getChapterId());
				sb.append(" and eqc.bind_type = 2 )");
			}
			if("3".equals(examQuestionCourseQ.getBindType())){
				sb.append(" eqc.course_id=:course_id and eqc.chapter_id=:chapter_id and eqc.lesson_id=:lesson_id  ");
				//paramMap.put("question_id", examQuestionCourseQ.getQuestionId());
				paramMap.put("course_id", examQuestionCourseQ.getCourseId());
				paramMap.put("chapter_id", examQuestionCourseQ.getChapterId());
				paramMap.put("lesson_id", examQuestionCourseQ.getLessonId());
				sb.append(" and eqc.bind_type = 3 )");

			}
			
			
			
			//只有章节号，使用4类型
			if("4".equals(examQuestionCourseQ.getBindType())){
				sb.append(" eqc.chapter_id=:chapter_id  ");
				paramMap.put("chapter_id", examQuestionCourseQ.getChapterId());
				sb.append(" and eqc.bind_type = 2 )");

			}
			
			//只有课时号，使用5类型
			if("5".equals(examQuestionCourseQ.getBindType())){
				sb.append(" eqc.lesson_id=:lesson_id  ");
				paramMap.put("lesson_id", examQuestionCourseQ.getLessonId());				
				sb.append(" and eqc.bind_type = 2 )");

			}
		}
		return sb;
	}
	
	   public List<Map<String, Object>> countExamQuestionCourses(List<String> ids,String bindType,String status){
		   StringBuffer sb = new StringBuffer();
		   if("1".equals(bindType)){
			   sb.append("select eqc.course_id id,count(eqc.question_id) count from exam_question_course eqc  GROUP BY eqc.course_id ");
		   }
		   if("4".equals(bindType)){
			   sb.append("select eqc.chapter_id id,count(eqc.question_id) count from exam_question_course eqc  GROUP BY eqc.chapter_id ");
		   }
		   if("5".equals(bindType)){
			   sb.append("select eqc.lesson_id id,count(eqc.question_id) count from exam_question_course eqc  GROUP BY eqc.lesson_id ");
		   }
		   sb.append(" HAVING id in (:ids) ");
		   NamedParameterJdbcTemplate namedParameterJdbcTemplate =   
				    new NamedParameterJdbcTemplate(jdbcTemplate);  
			MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		   //设置参数 用map接收
	       //Map<String, Object> paramMap = new HashMap<String, Object>();
	       //paramMap.put("ids", ids);
	       parameters.addValue("ids",ids);
	       //List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sb.toString(), paramMap);
	      // List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sb.toString(), paramMap);
			List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList(sb.toString(),parameters);

		   return list;
	    }
	   
	   /**
	    * 根据绑定类型查找有绑定关系的 课程、章节、课时 ID
	    * @param bindType 绑定类型 （课程1、章节2、课时3）
	    * @return
	    */
	   public List<String> queryExamQuestionCourseCcpIds(String bindType){
		   StringBuffer sb = new StringBuffer();
		   if("1".equals(bindType)){
			   sb.append("select course_id id from exam_question_course group by course_id,bind_type having bind_type='1'");
		   }
		   if("2".equals(bindType)){
			   sb.append("select chapter_id id from exam_question_course group by chapter_id,bind_type having bind_type='2'");
		   }
		   if("3".equals(bindType)){
			   sb.append("select lesson_id id from exam_question_course group by lesson_id,bind_type having bind_type='3'");
		   }
		   NamedParameterJdbcTemplate namedParameterJdbcTemplate =   
				    new NamedParameterJdbcTemplate(jdbcTemplate);  
		   MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		   //设置参数 用map接收
		   List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList(sb.toString(),parameters);
		   
		   List<String> ids = new ArrayList<String>();
		   if(list!=null){
			   for(Map<String,Object> map:list){
				   ids.add(String.valueOf(map.get("id")));
			   }
		   }
		   return ids;
	   }
	   
	   /**
	    * 根据ccpId 查找有绑定关系的 question_id
	    * @param ccpId 课程ID 章节ID 课时ID
	    * @param bindType 绑定类型 （课程1、章节2、课时3）
	    * @return
	    */
	   public List<String> queryExamQuestionCourseQuestionIdsByCcpId(String ccpId,String bindType){
		   StringBuffer sb = new StringBuffer();
		   if("1".equals(bindType)){
			   sb.append("select question_id question_id from exam_question_course where course_id=:ccpId and bind_type='1'");
		   }
		   if("2".equals(bindType)){
			   sb.append("select question_id question_id from exam_question_course where chapter_id=:ccpId and bind_type='2'");
		   }
		   if("3".equals(bindType)){
			   sb.append("select question_id question_id from exam_question_course where lesson_id=:ccpId and bind_type='3'");
		   }
		   NamedParameterJdbcTemplate namedParameterJdbcTemplate =   
				    new NamedParameterJdbcTemplate(jdbcTemplate);  
		   MapSqlParameterSource parameters = new MapSqlParameterSource(); 
	       parameters.addValue("ccpId",ccpId);
		   //设置参数 用map接收
		   List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList(sb.toString(),parameters);
		   
		   List<String> ids = new ArrayList<String>();
		   if(list!=null){
			   for(Map<String,Object> map:list){
				   ids.add(String.valueOf(map.get("question_id")));
			   }
		   }
		   return ids;
	   }

}
