/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.util.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  
 */
public final class JSONUtil {

	private JSONUtil() {
	}

	private static final ObjectMapper m = new ObjectMapper();

	public static String toJSONString(Object o) {
		try {
			m.setSerializationInclusion(Include.NON_EMPTY);
			return m.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	public static <T> T parseObject(String jsonString, Class<T> elementClasses) {
		m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			return m.readValue(jsonString, elementClasses);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> parseArray(String jsonString, Class<T> elementClasses) {
		m.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		JavaType javaType = m.getTypeFactory().constructParametricType(ArrayList.class, elementClasses);
		try {
			return m.readValue(jsonString, javaType);
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * json数组字符串里面的 转 长整型为字符串
	 * @param s
	 * @return
	 */
	/*
	public static String jsonArrayStringWithChangeLongToString(String s){
		com.alibaba.fastjson.JSONArray jsobjectArray = com.alibaba.fastjson.JSONObject.parseArray(s);
		//fastjson解析方法
		for(int i=0;i<jsobjectArray.size();i++){
			com.alibaba.fastjson.JSONObject jsobject = jsobjectArray.getJSONObject(i);

			for (Map.Entry<String, Object> entry : jsobject.entrySet()) {
				if(entry.getValue() instanceof Long){
					entry.setValue(entry.getValue()+"");
				}
			}
		}
		return com.alibaba.fastjson.JSONObject.toJSONString(jsobjectArray);
	}*/
	
	/**
	 * json字符串里面的 转 长整型为字符串
	 * @param s
	 * @return
	 */
	/*
	public static String jsonObjectStringWithChangeLongToString(String s){
		com.alibaba.fastjson.JSONObject jsobject = com.alibaba.fastjson.JSONObject.parseObject(s);
		//fastjson解析方法
		for (Map.Entry<String, Object> entry : jsobject.entrySet()) {
			if(entry.getValue() instanceof Long){
				entry.setValue(entry.getValue()+"");
			}
		}
		return com.alibaba.fastjson.JSONObject.toJSONString(jsobject);
	}*/

}
