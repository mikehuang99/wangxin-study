package com.wangxin.langshu.business.base.dao;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.util.base.Page;
import com.xiaoleilu.hutool.util.CollectionUtil;


public class BaseDao {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	/**
     * 只有一行查询结果的数据,只查询一列数据类型对象
     * @param sql sql
     * @param params 参数
     * @param cla 实体对象类型 可以填写 Integer.class,Float.class,Double.Class,Long.class,Boolean.class,Char.class,Byte.class,Short.class
     * @return
     */
    public Object queryOneColumnForSingleRow(String sql,Object[] params,Class cla){
        Object result=null;
        try{
            if(params==null||params.length>0){
                result=jdbcTemplate.queryForObject(sql,params,cla);
            }else{
                result=jdbcTemplate.queryForObject(sql,cla);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    

    /**
     * 查询返回实体对象集合
     * @param sql    sql语句
     * @param params 参数
     * @param cla    实体对象类型
     * @return
     */
    public List queryForObjectList(String sql,Object[] params,final Class cla){
        final List list=new ArrayList();
        try{
            jdbcTemplate.query(sql, params, new RowCallbackHandler(){
                public void processRow(ResultSet resultSet) {
                    try{
                    	ArrayList<String> columnNames=new ArrayList<String>();
                        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
                        int columnCount=resultSetMetaData.getColumnCount();
                        for(int i=0;i<columnCount;i++){
                            columnNames.add(resultSetMetaData.getColumnLabel(i+1).toLowerCase().trim());
                        }
                        Method[] methods=cla.getMethods();
                        ArrayList<String> fields=new ArrayList<String>();
                        for(int i=0;i<methods.length;i++){
                            if(StringUtils.isNotEmpty(methods[i].getName())&&methods[i].getName().trim().startsWith("set")){
                                String fe=methods[i].getName().trim().substring(3);
                                fe=(fe.charAt(0)+"").toLowerCase().trim()+fe.substring(1);
                                fields.add(fe);
                            }
                        }
                        do{
                            Object obj=null;
                            try{
                                obj=cla.getConstructor().newInstance();
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                            for(int i=0;i<columnCount;i++){
                                Object objectValue=resultSet.getObject(i+1);
                                for(int n=0;n<fields.size();n++){
                                    String fieldName=fields.get(n).trim();
                                    if(columnNames.get(i).equals(fieldName.toLowerCase().trim())){
                                        BeanUtils.copyProperty(obj, fieldName, objectValue);
                                        break;
                                    }
                                }
                            }
                            list.add(obj);
                        }while(resultSet.next());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        return list;
    }

    /**
     * 查询返回List<Map<String,Object>>格式数据。每一个Map就是一行数据，数据库的列名为key
     * @param sql  sql语句
     * @param params 参数
     * @return
     */
    public List<Map<String,Object>> queryForMaps(String sql,Object[] params){
        try{
            if(params!=null&&params.length>0){
                return jdbcTemplate.queryForList(sql, params);
            }
            return jdbcTemplate.queryForList(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询分页（只能适用MySQL数据库）
     * @param sql     SQL语句
     * @param params  参数
     * @param page    页数
     * @param pagerow 每页的条数
     * @param cla     对象类型
     * @return        Page对象
     */
    public Page queryPageForMySQL(String sql, Object[] params, int currentPage, int pageSize,Class cla) {
        String rowsql="select count(*) from ("+sql+") wangxin_langshu_tabs";   //查询总行数sql
        int pages = 0;   //总页数
        int rows=(Integer)queryOneColumnForSingleRow(rowsql, params, Integer.class);  //查询总行数
        //判断页数,如果是页大小的整数倍就为rows/pageRow如果不是整数倍就为rows/pageRow+1
        if (rows % pageSize == 0) {
            pages = rows / pageSize;
        } else {
            pages = rows / pageSize + 1;
        }
        //查询第page页的数据sql语句
        if(currentPage<=1){
            sql+=" limit 0,"+pageSize;
        }else{
            sql+=" limit "+((currentPage-1)*pageSize)+","+pageSize;
        }
        //查询第page页数据
        List list=null;
        if(cla!=null){
            list=queryForObjectList(sql, params, cla);
        }else{
            list=queryForMaps(sql, params);
        }

        //返回分页格式数据
        Page pl =new Page();
        pl.setPageCurrent(currentPage); //设置显示的当前页数
        pl.setTotalPage(pages); //设置总页数
        pl.setList(list);   //设置当前页数据
        pl.setTotalCount(rows);  //设置总记录数
        return pl;
    }
    
    /**
     * 查询分页（只适用MySQL数据库）
     * @param sql     SQL语句
     * @param params  参数
     * @param currentPage    页数
     * @param pageSize 每页=条数
     * @param cla     对象
     * @return        Page
     */
    public Page queryPageForMySQL(String sql, Map<String, Object> paramMap, int currentPage, int pageSize,Class cla) {
        //这里改成用 NamedParameterJdbcTemplate 进行查询
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        
        String rowsql="select count(*) from ("+sql+") wangxin_langshu_tabs";   //查询总行数sql
        int pages = 0;   //总页数
        int rows = (Integer)jdbc.queryForObject(rowsql, paramMap, Integer.class); //查询总行数
        //判断页数,如果是页大小的整数倍就为rows/pageRow如果不是整数倍就为rows/pageRow+1
        if (rows % pageSize == 0) {
            pages = rows / pageSize;
        } else {
            pages = rows / pageSize + 1;
        }
        
        if(currentPage==0){
        	currentPage=-1;
        }
        if(pageSize==0){
        	pageSize=-1;
        }
        //查询第page页的数据sql语句
        if(!(currentPage==-1&&pageSize==-1)){
	        if(currentPage<=1){
	            sql+=" limit 0,"+pageSize;
	        }else{
	            sql+=" limit "+((currentPage-1)*pageSize)+","+pageSize;
	        }
        }
        //查询第page页数据
        List list=null;
        if(cla!=null){
            list=jdbc.queryForList(sql, paramMap, cla);

        }else{
        	list = jdbc.queryForList(sql, paramMap);

        }

        //返回分页格式数据
        Page pl =new Page();
        pl.setPageCurrent(currentPage); //设置显示的当前页数
        pl.setTotalPage(pages); //设置总页数
        pl.setList(list);   //设置当前页数据
        pl.setTotalCount(rows);  //设置总记录数
        return pl;
    }
    
    public int count(String sql , Map<String, Object> paramMap){
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
   	 	String rowsql="select count(*) from ("+sql+") wangxin_langshu_tabs";   //查询总行数sql
        int rows = (Integer)jdbc.queryForObject(rowsql, paramMap, Integer.class); //查询总行数
    	return rows;
    }
  
   
    
    


    
}
