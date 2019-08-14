package com.regularbbs.bbs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages= {"com.regularbbs.bbs.dao", "com.regularbbs.bbs.service"})
@Import({DBConfig.class})
@PropertySource("classpath:com/properties/bbs/config.properties")
public class ApplicationConfig
	{
		
	}
