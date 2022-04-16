package com.ShopTechnological.Application.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ShopTechnological.Application.domain.MemoriesJwt;
import com.ShopTechnological.Application.domain.Role;
import com.ShopTechnological.Application.service.MemoriesJwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FilterEntryLogin extends OncePerRequestFilter{
	@Autowired
	private CheckExistUsername checkExistUsername;
	@Autowired
	private CheckUsernamePassword checkUsernamePassword;
	@Value("${key.authentication}")
	private String key;
	@Autowired
	private MemoriesJwtService memoriesJwtService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//---------------delete old token in database
		memoriesJwtService.delete(username);
		// ------------
		UserDetails userDetails=checkExistUsername.loadUserByUsername(username);
		if (userDetails  == null) {
			response.setStatus(401);
		}else {
		Authentication authentication= checkUsernamePassword.authenticate(
				new TokenUsernamePassword(userDetails.getUsername(), password,userDetails.getAuthorities())
				);
		//----------Authetication Account
		if (authentication != null) {
			List<String> authors
			            = authentication.getAuthorities().stream()
			            .map(r->r.getAuthority())
			            .collect(Collectors.toList());
			Algorithm algorithm=Algorithm.HMAC256(key);
			String jwt=JWT.create()
					.withSubject(userDetails.getUsername())
					.withClaim("authors", authors)
					.withExpiresAt(new Date(new Date().getTime()+ (60*1000)))
					.withIssuer(request.getRequestURL().toString())
					.sign(algorithm);
			
			Base64 base64= new Base64();
			String token=new String(base64.encode(jwt.getBytes()));
			//------- save infor jwt into database // 
			MemoriesJwt memoriesJwt = new MemoriesJwt();
			memoriesJwt.setToken(jwt);
			memoriesJwt.setUrl(request.getRequestURL().toString());
			memoriesJwt.setUsername(authentication.getName());
			List<Role> roles=new ArrayList<Role>();
			for (String r : authors) {
				String name = r.substring("ROLE_".length());
				Role role=new Role();
				role.setName(name);
				log.info("  ====="+role);
				roles.add(role);
			}
			log.info("---"+authors);
			memoriesJwt.setRoles(roles);
			memoriesJwtService.save(memoriesJwt);
			log.info("save success");
			//------
			response.setHeader(HttpHeaders.AUTHORIZATION,token);
		}else {
			response.setStatus(401);
		}
		}
		filterChain.doFilter(request, response);
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}
}
