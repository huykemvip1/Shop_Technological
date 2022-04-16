package com.ShopTechnological.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ShopTechnological.Application.config"
		,"com.ShopTechnological.Application.service","com.ShopTechnological.Application.repository"
		,"com.ShopTechnological.Application.resource","com.ShopTechnological.Application.domain"
		,"com.ShopTechnological.Application.security"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
