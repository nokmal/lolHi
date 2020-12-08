package com.sbs.example.lolHi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMvcConfig implements WebMvcConfigurer {
	// beforeActionInterceptor 불러오기
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	@Autowired
	@Qualifier("needToLoginInterceptor")
	HandlerInterceptor needToLoginInterceptor;

	// 인터셉터 적용하는 함수
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**")
				.excludePathPatterns("/error");

// 로그인 없이도 접속할 수 있는 URI 전부 기술
		registry.addInterceptor(needToLoginInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**")
				.excludePathPatterns("/").excludePathPatterns("/usr/home/main").excludePathPatterns("/usr/member/login")
				.excludePathPatterns("/usr/member/doLogin").excludePathPatterns("/usr/member/join")
				.excludePathPatterns("/usr/member/doJoin").excludePathPatterns("/usr/article/list")
				.excludePathPatterns("/usr/member/doJoin").excludePathPatterns("/usr/article-*/list")
				.excludePathPatterns("/usr/article/detail").excludePathPatterns("/error");
	}
}
