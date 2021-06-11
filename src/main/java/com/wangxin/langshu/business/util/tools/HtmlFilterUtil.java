package com.wangxin.langshu.business.util.tools;

import java.util.regex.PatternSyntaxException;

import org.apache.oro.text.perl.Perl5Util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class HtmlFilterUtil {

	/*
	public static void main(String args[]){
    	//String strings = "<p>填空题<span style=\"font-size: 12px;\">&nbsp;</span><span style=\"font-size: 12px;\">(_)</span><span style=\"font-size: 12px;\">&nbsp;</span><span style=\"font-size: 12px;\">1sdfsdfd</span></p>";
    	//String strings = "测试题干<table>888</table><a href=\"www.ddk.com\">ddd</a>万物为<d><div style=\"dd:kd\">wo de</div>水电费水电费水电费说的<p><p>填空题<span style=\"font-size: 12px;\">&nbsp;</span><span style=\"font-size: 12px;\">(_)</span><span style=\"font-size: 12px;\">&nbsp;</span><span style=\"font-size: 12px;\">1sdfsdfd</span></p><img src=\"https://atest888.oss-cn-hangzhou.aliyuncs.com/course/87f7cdf3681446758767e0b11e0f2d03.jpg\"><br></p>";
    	String strings = "<table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;wq</th><th>qweqw&nbsp;</th></tr><tr><td>&nbsp;qweqw</td><td>&nbsp;qweqweqw</td></tr></tbody></table><p><br>测试题干</p>";
    	//System.out.println("st:"+strings);
    	System.out.println(clean(strings));
    }*/
    

	
	
		
	/**过滤HTML里去除img等外的所有标签
	* @param str
	* @return
	* @throws PatternSyntaxException
	*/		
	public static String clean(String html){  
		if(StringUtils.isEmpty(html)){
			return html;
		}
        StringBuffer buffer = new StringBuffer();    
        Perl5Util preg = new Perl5Util();    
        preg.substitute(buffer,"s/<script[^>]*?>.*?<\\/script>//gmi",html);    
        html =buffer.toString();  
        buffer.setLength(0);  
          
        //preg.substitute(buffer,"s#<[/]*?(?!a|img|br|/a|table|/table|tr|/tr|td|/td)[^<>]*?>#abc#gmi",html);  
        preg.substitute(buffer,"s#<[/]*?(?!img|br|p|table|/table|tr|/tr|td|/td)[^<>]*?>##gmi",html);  

        //(?# a,/a,img/br ...标签之外,都删除)  
        html =buffer.toString();  
        buffer.setLength(0);  
        /* 
        preg.substitute(buffer,"s/([\r\n])[\\s]+//gmi",html);   
        html =buffer.toString(); 
        buffer.setLength(0); 
        */  
        return html;  
    }  
	
}
