package com.wangxin.langshu.business.util.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *   下划线和驼峰转换器 
 *
 */
public class LineHumpConvert {

	 private static Pattern linePattern = Pattern.compile("_(\\w)");
	 
	 /** 下划线转驼峰 */
	  public static String lineToHump(String str) {
	        str = str.toLowerCase();
	     Matcher matcher = linePattern.matcher(str);
	      StringBuffer sb = new StringBuffer();
	      while (matcher.find()) {
	          matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
	     }
	       matcher.appendTail(sb);
	     return sb.toString();
	 }
	 
	  /** 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)}) */
	 public static String humpToLine(String str) {
	       return str.replaceAll("[A-Z]", "_$0").toLowerCase();
	    }
	 
	  private static Pattern humpPattern = Pattern.compile("[A-Z]");
	 
	    /** 驼峰转下划线,效率比上面高 */
	  public static String humpToLine2(String str) {
	      Matcher matcher = humpPattern.matcher(str);
	     StringBuffer sb = new StringBuffer();
	     while (matcher.find()) {
	          matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
	      }
	       matcher.appendTail(sb);
	     return sb.toString();
	 }
	  
}
