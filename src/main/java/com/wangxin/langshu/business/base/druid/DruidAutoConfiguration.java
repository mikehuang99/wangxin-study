package com.wangxin.langshu.business.base.druid;

import java.sql.SQLException;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaoleilu.hutool.util.ObjectUtil;


@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class DruidAutoConfiguration {

	private final DruidProperties druidProperties;

	public DruidAutoConfiguration(DruidProperties druidProperties) {
		this.druidProperties = druidProperties;
	}

	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(druidProperties.getUrl());
		dataSource.setUsername(druidProperties.getUsername());
		dataSource.setPassword(druidProperties.getPassword());

		if (ObjectUtil.isNotNull(druidProperties.getName())) {
			dataSource.setName(druidProperties.getName());
		}
		if (ObjectUtil.isNotNull(druidProperties.getDriverClass())) {
			dataSource.setDriverClassName(druidProperties.getDriverClass());
		}
		if (druidProperties.getInitialSize() > 0) {
			dataSource.setInitialSize(druidProperties.getInitialSize());
		}
		if (druidProperties.getMinIdle() > 0) {
			dataSource.setMinIdle(druidProperties.getMinIdle());
		}
		if (druidProperties.getMaxActive() > 0) {
			dataSource.setMaxActive(druidProperties.getMaxActive());
		}
		if (druidProperties.getMaxWait() > 0) {
			dataSource.setMaxWait(druidProperties.getMaxWait());
		}
		if (ObjectUtil.isNotNull(druidProperties.getPoolPreparedStatements())) {
			dataSource.setPoolPreparedStatements(druidProperties.getPoolPreparedStatements());
		}
		if (druidProperties.getMaxPoolPreparedStatementPerConnectionSize() > 0) {
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
		}
		if (StringUtils.isNotEmpty(druidProperties.getValidationQuery())) {
			dataSource.setValidationQuery(druidProperties.getValidationQuery());
		}
		if (ObjectUtil.isNotNull(druidProperties.getTestOnBorrow())) {
			dataSource.setTestOnBorrow(druidProperties.getTestOnBorrow());
		}
		if (ObjectUtil.isNotNull(druidProperties.getTestWhileIdle())) {
			dataSource.setTestWhileIdle(druidProperties.getTestWhileIdle());
		}
		if (ObjectUtil.isNotNull(druidProperties.getTestOnReturn())) {
			dataSource.setTestOnReturn(druidProperties.getTestOnReturn());
		}
		if (ObjectUtil.isNotNull(druidProperties.getConnectionProperties())) {
			dataSource.setConnectionProperties(druidProperties.getConnectionProperties());
		}
		if (ObjectUtil.isNotNull(druidProperties.getFilters())) {
			try {
				dataSource.setFilters(druidProperties.getFilters());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (druidProperties.getMinEvictableIdleTimeMillis() > 0) {
			dataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
		}
		if (druidProperties.getTimeBetweenEvictionRunsMillis() > 0) {
			dataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
		}
		try {
			dataSource.init();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return dataSource;
	}

}
