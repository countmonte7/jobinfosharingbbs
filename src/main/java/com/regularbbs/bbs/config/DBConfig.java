package com.regularbbs.bbs.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer
	{
		@Value("${db.driverClassName}")
		private String driverClassName;
		@Value("${db.url}")
		private String url;
		@Value("${db.username}")
		private String username;
		@Value("${db.password}")
		private String password;
		
		@Bean
		public DataSource dataSource() {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			return dataSource;
			
		}
		
		@Override public PlatformTransactionManager annotationDrivenTransactionManager() {
			return transactionManager();
		}
		
		@Bean
		public PlatformTransactionManager transactionManager() {
			return new DataSourceTransactionManager(dataSource());
		}
	}
