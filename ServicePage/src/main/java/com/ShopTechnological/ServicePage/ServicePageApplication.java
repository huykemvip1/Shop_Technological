package com.ShopTechnological.ServicePage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ShopTechnological.ServicePage.reponsitory.SanPhamBanRepository;

@SpringBootApplication
public class ServicePageApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ServicePageApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
	}

}
