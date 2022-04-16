package com.ShopTechnological.Application.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationToken {
	@GetMapping("/ShopTechnological/auth/token")
	public ResponseEntity<?> auth(HttpServletResponse response){
		if (response.getStatus() == 200 || response.getStatus() == 201) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(401).build();
		}
	}
}
