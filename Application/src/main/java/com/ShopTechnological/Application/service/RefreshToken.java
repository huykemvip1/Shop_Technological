package com.ShopTechnological.Application.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ShopTechnological.Application.domain.MemoriesJwt;
import com.ShopTechnological.Application.domain.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


@Component
public class RefreshToken {
	@Autowired
	private MemoriesJwtService memoriesJwtService;
	
	@Value("${key.authentication}")
	private String key;
	@Autowired
	private RestTemplate restTemplate;
	public void refresh(HttpServletResponse response,String token,HttpServletRequest request)
			throws IOException {
		MemoriesJwt memoriesJwt=memoriesJwtService.findMemory(token);
		// refresh token if information of token exist 
		if (memoriesJwt != null) {
			List<String> authors=new ArrayList<String>();
			for (Role r:memoriesJwt.getRoles()) {
				authors.add("ROLE_"+r.getName());
			}
        Algorithm algorithm=Algorithm.HMAC256(key);
        String jwt=JWT.create()
		     .withSubject(memoriesJwt.getUsername())
		     .withClaim("authors", authors)
		     .withExpiresAt(new Date(new Date().getTime()+ (60*1000)))
		     .withIssuer(memoriesJwt.getUrl())
		     .sign(algorithm);
        Base64 base64= new Base64();
        String refreshToken=new String(base64.encode(jwt.getBytes()));
        memoriesJwt.setToken(token);
        memoriesJwtService.update(memoriesJwt);
        Map<String, String> map=new HashMap<String, String>();
        map.put(HttpHeaders.AUTHORIZATION, refreshToken);
        restTemplate.headForHeaders(request.getServletPath(), map);
		}else {
			// don't refresh token
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
}
