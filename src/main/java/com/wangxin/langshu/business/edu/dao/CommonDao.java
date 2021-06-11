package com.wangxin.langshu.business.edu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.base.dao.BaseDao;

@Repository
public class CommonDao extends BaseDao {

	
	
	public int deleteTableByField(String tableName,String fieldName,String fieldValue){
	    StringBuffer sb = new StringBuffer();
	    List<String> paramList = new ArrayList<>();
	    sb.append("delete from  ");
	    sb.append(tableName);
	    sb.append(" where ");
	    sb.append(fieldName);
	    sb.append("=?");
	    paramList.add(fieldValue);
	    int count  = this.jdbcTemplate.update(sb.toString(), paramList.toArray());
		return count;
	}
	
	public boolean isExistRecordTableByField(String tableName,String fieldName,String fieldValue){
	    StringBuffer sb = new StringBuffer();
	    List<String> paramList = new ArrayList<>();
	    sb.append("select id from  ");
	    sb.append(tableName);
	    sb.append(" where ");
	    sb.append(fieldName);
	    sb.append("=?");
	    paramList.add(fieldValue);
	    List<Map<String, Object>> list  = this.jdbcTemplate.queryForList(sb.toString(), paramList.toArray());
	    if(list!=null&&list.size()!=0) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	public int updateTableByField(String tableName,String updateFieldName,String updateFieldValue,String fieldName,String fieldValue){
	    StringBuffer sb = new StringBuffer();
	    List<String> paramList = new ArrayList<>();
	    sb.append("update   ");
	    sb.append(tableName);
	    sb.append(" set ");
	    sb.append(updateFieldName);
	    sb.append(" = ? ");
	    sb.append(" where ");
	    sb.append(fieldName);
	    sb.append("=?");
	    paramList.add(updateFieldValue);
	    paramList.add(fieldValue);
	    int count  = this.jdbcTemplate.update(sb.toString(), paramList.toArray());
		return count;
	}
}
