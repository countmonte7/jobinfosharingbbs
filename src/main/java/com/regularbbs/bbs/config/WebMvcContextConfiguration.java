package com.regularbbs.bbs.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.mail.javamail.*;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.regularbbs.bbs.controller"})
public class WebMvcContextConfiguration implements WebMvcConfigurer
	{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("file:///C:/devr/").setCachePeriod(31556926);
	}
	
		@Override public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}
		
		@Override public void addViewControllers(ViewControllerRegistry registry) {
			System.out.println("출동");
			registry.addViewController("/").setViewName("main");
		}
		
		@Bean
		public InternalResourceViewResolver getInternalResouceViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/view/");
			resolver.setSuffix(".jsp");
			return resolver;
		}
		
		@Bean
		public MultipartResolver multipartResolver() {
			org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver =
					new org.springframework.web.multipart.commons.CommonsMultipartResolver();
			multipartResolver.setMaxUploadSize(10485760);
			return multipartResolver;
		}
		
		@Value("${smtp.host}")
		private String host;
		
		@Value("${smtp.port}")
		private int port;
		
		@Value("${smtp.username}")
		private String username;
		
		@Value("${smtp.password}")
		private String password;
		
		
		@Bean
		public JavaMailSender mailSender() {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			System.out.println(username);
			System.out.println(password);
			mailSender.setHost(host);
			mailSender.setPort(port);
			mailSender.setUsername(username);
			mailSender.setPassword(password);
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "true");
			return mailSender;
		}
		
	}
