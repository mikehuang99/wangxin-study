package com.wangxin.langshu.business.util.version;

import java.util.HashMap;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class VersionTool {
	
	
	
	private String authVersion;//0为免费版，1为普及版，2为专业版，3高级版，4为至尊版
	/*数据库数量限制
	版本名称	学员	   教师	 分类	课程	   试题	    试卷	   资讯
	免费版	60	    30	 100	300	   2000	     30	    1000
	普及版	2000	100	 200	800	   50000	1000	不限
	专业版	5000	300	 500	2000	100000	2000	不限
	高级版	10000	800	 1000	6000	200000	6000	不限
	至尊版	不限	   不限	 不限	不限	    不限	   不限	   不限
	*/
	
	public static String[][] studentCount = {{"0","60"},{"1","2000"},{"2","5000"},{"3","10000"},{"4","N"}};
	public static String[][] teacherCount = {{"0","30"},{"1","100"},{"2","300"},{"3","800"},{"4","N"}};
	public static String[][] subjectCount = {{"0","100"},{"1","200"},{"2","500"},{"3","1000"},{"4","N"}};
	public static String[][] courseCount = {{"0","300"},{"1","800"},{"2","2000"},{"3","6000"},{"4","N"}};
	public static String[][] examQuestionCount = {{"0","2000"},{"1","50000"},{"2","100000"},{"3","200000"},{"4","N"}};
	public static String[][] examManagementCount = {{"0","30"},{"1","1000"},{"2","2000"},{"3","6000"},{"4","N"}};
	public static String[][] newsCount = {{"0","1000"},{"1","N"},{"2","N"},{"3","N"},{"4","N"}};

	/**
	 * <key,value> = <版本号， 限制数量>
	 */
	public static HashMap<String,String> studentCountMap ;
	public static HashMap<String,String> teacherCountMap ;
	public static HashMap<String,String> subjectCountMap ;
	public static HashMap<String,String> courseCountMap ;
	public static HashMap<String,String> examQuestionCountMap ;
	public static HashMap<String,String> examManagementCountMap ;
	public static HashMap<String,String> newsCountMap ;
			
    private static void initAuthVersionParam() {
    	studentCountMap = new HashMap<String, String>();
    	studentCountMap.put("0", "60");  
    	studentCountMap.put("1", "2000"); 
    	studentCountMap.put("2", "5000");  
    	studentCountMap.put("3", "10000"); 
    	studentCountMap.put("4", "N");  
    	
    	teacherCountMap = new HashMap<String, String>();
    	teacherCountMap.put("0", "30");  
    	teacherCountMap.put("1", "100"); 
    	teacherCountMap.put("2", "300");  
    	teacherCountMap.put("3", "800"); 
    	teacherCountMap.put("4", "N");  
    	  
    	subjectCountMap = new HashMap<String, String>();
    	subjectCountMap.put("0", "100");  
    	subjectCountMap.put("1", "200"); 
    	subjectCountMap.put("2", "500");  
    	subjectCountMap.put("3", "1000"); 
    	subjectCountMap.put("4", "N");  
    	   
    	courseCountMap = new HashMap<String, String>();
    	courseCountMap.put("0", "300");  
    	courseCountMap.put("1", "800"); 
    	courseCountMap.put("2", "2000");  
    	courseCountMap.put("3", "6000"); 
    	courseCountMap.put("4", "N");  
    	    
    	examQuestionCountMap = new HashMap<String, String>();
    	examQuestionCountMap.put("0", "2000");  
    	examQuestionCountMap.put("1", "50000"); 
    	examQuestionCountMap.put("2", "100000");  
    	examQuestionCountMap.put("3", "200000"); 
    	examQuestionCountMap.put("4", "N");  
    	  
    	examManagementCountMap = new HashMap<String, String>();
    	examManagementCountMap.put("0", "30");  
    	examManagementCountMap.put("1", "1000"); 
    	examManagementCountMap.put("2", "2000");  
    	examManagementCountMap.put("3", "6000"); 
    	examManagementCountMap.put("4", "N");  
    	   
    	newsCountMap = new HashMap<String, String>();
    	newsCountMap.put("0", "1000");  
    	newsCountMap.put("1", "N"); 
    	newsCountMap.put("2", "N");  
    	newsCountMap.put("3", "N"); 
    	newsCountMap.put("4", "N");  
    }

    public static  String generalAuthVersionParamsString(String authVersion) {
    	initAuthVersionParam();
		Version version = new Version();
		version.setStudentCount(studentCountMap.get(authVersion));
		version.setTeacherCount(teacherCountMap.get(authVersion));
		version.setSubjectCount(subjectCountMap.get(authVersion));
		version.setCourseCount(courseCountMap.get(authVersion));
		version.setExamQuestionCount(examQuestionCountMap.get(authVersion));
		version.setExamManagementCount(examManagementCountMap.get(authVersion));
		version.setNewsCount(newsCountMap.get(authVersion));
		String jsonString  =  JSON.toJSONString(version);
		return jsonString;
	} 
	
	
	public static String free = "{\"studentCount\":100,\"teacherCount\":30,\"subjectCount\":100,\"courseCount\":300,\"examQuestionCount\":2000,\"examManagementCount\":30,\"newsCount\":1000}";

	public static void main(String args[]) {
		
		System.out.println(generalAuthVersionParamsString("3"));

		/*
		
		Version version2 = new Version();
		version2.studentCount = 6;
		version2.examManagementCount = 90;
		String string  =  JSON.toJSONString(version2);
		System.out.println("string:"+string);
		Version version = JSON.parseObject(free, Version.class);
		System.out.println("version:===>"+version);*/
		
	}
}
