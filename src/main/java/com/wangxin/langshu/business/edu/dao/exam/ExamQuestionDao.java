package com.wangxin.langshu.business.edu.dao.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.StringUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.entity.exam.ExamQuestion;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionQ;
import com.wangxin.langshu.business.edu.vo.exam.examQuestion.ExamQuestionVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class ExamQuestionDao extends BaseDao {

/**
    
    public List<Map<String, Object>> queryUser(){
    	 List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from exam_question");
    	 return list;
     }
    
    public Page queryUser2(){
   	 	Page pageList = this.queryPageForMySQL("select u.name,r.role_name from user u left join role r on u.role_id = r.id ", new String [0], 1, 10, null);
    	return pageList;
    }
    
    public Page<ExamQuestion> queryQuestions(){
    	
    	//查询语句
        String sql = "select * from exam_question q where INSTR(q.question_title, :question_title)>0 and question_type = :question_type";
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //这里的ids 必须和sql语句里的参数ids 名称一致
        
        
        paramMap.put("question_title", "i");
        paramMap.put("question_type", "2");

   
        //这里改成用 NamedParameterJdbcTemplate 进行查询
        //NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        //执行sql 并循环遍历每一行的结果，逻辑自行操作
        //List<Map<String, Object>> li = jdbc.queryForList(sql, paramMap);
        Page<ExamQuestion> pg = this.queryPageForMySQL(sql, paramMap, 1, 10, null);
       //Page pg2 = this.queryPageByMapParamsForMySQL(sql, paramMap, 1, 2, Question.class);
  
    	
    	
    	//List<String> list = new ArrayList<String>();
    	//list.add("i");
    	//Object[] objects = new Object[list.size()];

    	//objects[0] = list.get(0);
   	 	//Page<Question> pageList = this.queryByPageForMySQL("select * from question q where q.question_title=:question_title and question_type = :question_type", new String[]{"看iky","2"}, 1, 3, null);
    	//
    	return pg;
    }**/
    
    public Page<ExamQuestionVo> queryExamQuestions(ExamQuestionQ examQuestionQ){
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select cn1.subject_name subject_name1, cn2.subject_name subject_name2,cn3.subject_name subject_name3 ");
        sb.append(",q.* from exam_question q ");
        sb.append("left join course_subject  cn1 on q.subject_id1=cn1.id ");
        sb.append("left join course_subject  cn2 on q.subject_id2=cn2.id ");
        sb.append("left join course_subject  cn3 on q.subject_id3=cn3.id ");
        sb.append("  where 1=1  ");
    	

    	if(StringUtils.isNotEmpty(examQuestionQ.getQuestionTitle())){
    		sb.append(" and INSTR(q.question_title, :question_title)>0 ");
    	    paramMap.put("question_title", examQuestionQ.getQuestionTitle());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getQuestionType())){
    		sb.append(" and q.question_type = :question_type ");
    		paramMap.put("question_type", examQuestionQ.getQuestionType());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getSubjectId1())){
    		sb.append(" and q.subject_id1 = :subject_id1 ");
    		paramMap.put("subject_id1", examQuestionQ.getSubjectId1());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getSubjectId2())){
    		sb.append(" and q.subject_id2 = :subject_id2 ");
    		paramMap.put("subject_id2", examQuestionQ.getSubjectId2());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getSubjectId3())){
    		sb.append(" and q.subject_id3 = :subject_id3 ");
    		paramMap.put("subject_id3", examQuestionQ.getSubjectId3());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getLevel())){
    		sb.append(" and q.level = :level ");
    		paramMap.put("level", examQuestionQ.getLevel());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", examQuestionQ.getValidValue());
    	}
    	if(StringUtils.isNotEmpty(examQuestionQ.getCustomLabel())){
    		sb.append(" and INSTR(q.custom_label, :custom_label)>0 ");
    		paramMap.put("custom_label", examQuestionQ.getCustomLabel());
    	}
    	
    	if(StringUtils.isNotEmpty(examQuestionQ.getUserNo()+"")){
    		sb.append(" and q.user_no = :user_no ");
    		paramMap.put("user_no", examQuestionQ.getUserNo());
    	}
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, examQuestionQ.getPageCurrent(), examQuestionQ.getPageSize(), null);
        
        List<ExamQuestionVo> list = new ArrayList<ExamQuestionVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	ExamQuestionVo examQuestion = new ExamQuestionVo();
        	ObjectMapUtil2.mapToObjModle(examQuestion,mapHump);
        	list.add(examQuestion);
        }
        
        Page<ExamQuestionVo> pg = new Page<ExamQuestionVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
    
    /**
     * 全表查询全部试题ID
     * @return 全部试题ID集合
     */
	   public List<String> queryAllExamQuestionIds(){
		   StringBuffer sb = new StringBuffer();
		   sb.append("select  id from exam_question");
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
 
}
