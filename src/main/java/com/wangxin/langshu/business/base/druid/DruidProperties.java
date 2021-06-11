package com.wangxin.langshu.business.base.druid;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpListVo;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.vo.CourseCcpVo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {

	private String name;
	private String url;
	private String username;
	private String password;
	private String driverClass;

	private int initialSize = 0;
	private int maxActive = 8;
	private int minIdle = 8;
	private int maxWait;

	private Boolean poolPreparedStatements = false;
	private int maxPoolPreparedStatementPerConnectionSize = -1;

	private String validationQuery;
	private Boolean testOnBorrow = true;
	private Boolean testOnReturn = false;
	private Boolean testWhileIdle = false;

	private String filters;
	private String connectionProperties;

	private int timeBetweenEvictionRunsMillis = 1;
	private int minEvictableIdleTimeMillis;

	

}
