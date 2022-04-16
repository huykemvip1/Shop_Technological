package com.ShopTechnological.ServicePage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigWebMvc implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.exposedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS,
			 HttpHeaders.AUTHORIZATION)
		.allowedMethods("GET","POST","DELETE","PUT")
		.maxAge(1800);
	}
}
