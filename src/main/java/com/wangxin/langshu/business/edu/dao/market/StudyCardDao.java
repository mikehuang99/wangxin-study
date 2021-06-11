package com.wangxin.langshu.business.edu.dao.market;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardQ;
import com.wangxin.langshu.business.edu.vo.market.studyCard.StudyCardVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class StudyCardDao extends BaseDao {
	
	public Page<StudyCardVo> queryStudyCards(StudyCardQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.*,u.mobile bind_user_mobile from study_card q left join user u on q.bind_user_no=u.user_no where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCardTitle())){
    		sb.append(" and INSTR(q.card_title, :card_title)>0 ");
    	    paramMap.put("card_title", q.getCardTitle());
    	}
        
    	if(StringUtils.isNotEmpty(q.getCardNo())){
    		sb.append(" and INSTR(q.card_no, :card_no)>0 ");
    	    paramMap.put("card_no", q.getCardNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}
        
    	if(StringUtils.isNotEmpty(q.getStudyCardTimeBeginBegin())){
    		sb.append(" and q.study_card_time_begin >= :study_card_time_begin_begin ");
    		paramMap.put("study_card_time_begin_begin", q.getStudyCardTimeBeginBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getStudyCardTimeBeginEnd())){
    		sb.append(" and q.study_card_time_begin <= :study_card_time_begin_end ");
    		paramMap.put("study_card_time_begin_end", q.getStudyCardTimeBeginEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getStudyCardTimeEndBegin())){
    		sb.append(" and q.study_card_time_end >= :study_card_time_end_begin ");
    		paramMap.put("study_card_time_end_begin", q.getStudyCardTimeEndBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getStudyCardTimeBeginEnd())){
    		sb.append(" and q.study_card_time_end <= :study_card_time_end_end ");
    		paramMap.put("study_card_time_end_end", q.getStudyCardTimeEndEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getBindUserNo())){
    		sb.append(" and INSTR(q.bind_user_no, :bind_user_no)>0 ");
    	    paramMap.put("bind_user_no", q.getBindUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getBindUserMobile())){
    		sb.append(" and INSTR(u.mobile, :bind_user_mobile)>0 ");
    	    paramMap.put("bind_user_mobile", q.getBindUserMobile());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getBindUserTimeBegin())){
    		sb.append(" and q.bind_user_time >= :bind_user_time_begin ");
    		paramMap.put("bind_user_time_begin", q.getBindUserTimeBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getBindUserTimeEnd())){
    		sb.append(" and q.bind_user_time <= :bind_user_time_end ");
    		paramMap.put("bind_user_time_end", q.getBindUserTimeEnd());
    	}
        
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<StudyCardVo> list = new ArrayList<StudyCardVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	StudyCardVo vo = new StudyCardVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<StudyCardVo> pg = new Page<StudyCardVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	/**
	 * 查看学习卡是否存在，如果存在返回true，否则返回false
	 * @param cardNo
	 * @param cardPsw
	 * @return
	 */
	   public boolean isExist(String cardNo,String cardPsw){
			MapSqlParameterSource parameters = new MapSqlParameterSource(); 

		   StringBuffer sb = new StringBuffer();
		   sb.append("select q.id from study_card q where q.card_no=:card_no and q.card_psw=:card_psw ");
		   parameters.addValue("card_no",cardNo);
		   parameters.addValue("card_psw",cardPsw);
		   NamedParameterJdbcTemplate namedParameterJdbcTemplate =   
				    new NamedParameterJdbcTemplate(jdbcTemplate);  
		 
			List<Map<String,Object>> list = namedParameterJdbcTemplate.queryForList(sb.toString(),parameters);
			
			if(list==null||list.size()==0){
				return false;
			}else{
				return true;
			}

	    }
	
	   
		public Page<StudyCardVo> queryPcWebStudyCards(StudyCardQ q) {
	    	StringBuffer sb = new StringBuffer();
	        //设置参数 用map接收
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        
	        sb.append("select q.* from study_card q where 1=1 ");
	        
	    	
	    	if(StringUtils.isNotEmpty(q.getCardNo())){
	    		sb.append(" and q.card_no = :card_no ");
	    	    paramMap.put("card_no", q.getCardNo());
	    	}
	    	
	    	if(StringUtils.isNotEmpty(q.getCardPsw())){
	    		sb.append(" and q.card_psw = :card_psw ");
	    	    paramMap.put("card_psw", q.getCardPsw());
	    	}
	    	if(StringUtils.isNotEmpty(q.getValidValue())){
	    		sb.append(" and q.valid_value = :valid_value ");
	    		paramMap.put("valid_value", q.getValidValue());
	    	}
	        
	    	
	    	if(StringUtils.isNotEmpty(q.getBindUserNo())){
	    		sb.append(" and q.bind_user_no = :bind_user_no");
	    	    paramMap.put("bind_user_no", q.getBindUserNo());
	    	}
	    	
	    	if(StringUtils.isNotEmpty(q.getIsStudyCardTimeEndBeforeToday())&&"TRUE".equals(q.getIsStudyCardTimeEndBeforeToday().toUpperCase())){
	    		Date date = new Date();
	    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String todayStr = simpleDateFormat.format(date);
	    		sb.append(" and q.study_card_time_end >= :today_str");
	    	    paramMap.put("today_str", todayStr);
	    	}

	    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
	    	
	        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
	        
	        List<StudyCardVo> list = new ArrayList<StudyCardVo>();
	        for(Map<String, Object> map:pge.getList()){
	        	Map<String,Object> mapHump = new HashMap<String, Object>();
	        	for (Map.Entry<String, Object> entry : map.entrySet()) {
	        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
	        	}
	        	StudyCardVo vo = new StudyCardVo();
	        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
	        	list.add(vo);
	        }
	        
	        Page<StudyCardVo> pg = new Page<StudyCardVo>();
	        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
	        pg.setTotalPage(pge.getTotalPage()); //设置总页数
	        pg.setList(list);   //设置当前页数据
	        pg.setPageSize(pge.getPageSize());
	        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
	        
	    	return pg;
	    }
	
	   

}
