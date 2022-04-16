package com.ShopTechnological.Application.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ShopTechnological.Application.service.MemoriesJwtService;
import com.ShopTechnological.Application.service.RefreshToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class FilterEntryResource extends OncePerRequestFilter{
	@Value("${key.authentication}")
	private String key;
	@Autowired
	private RefreshToken refreshToken;
	@Autowired
	private MemoriesJwtService memoriesJwtService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		String jwt=request.getHeader(HttpHeaders.AUTHORIZATION);
		if (jwt != null && !jwt.equals("") && jwt.startsWith("Bearer ")) {
			String jwtToToken=jwt.substring(jwt.indexOf("Bearer ")+("Bearer ").length()-1);
			Algorithm algorithm=Algorithm.HMAC256(key);
			Base64 base64=new Base64();
			String token=new String(base64.decode(jwtToToken));
			log.info(token);
			try {
				log.info("1");
				try {
					// ---- check token exist.this is not exist
					if (memoriesJwtService.findMemory(token) == null) {
						log.info("2");
						SecurityContextHolder.getContext().setAuthentication(null);
					}else {
						// --- check token exist.this is exist
						JWTVerifier jwtVerifier=JWT.require(algorithm).build();
						DecodedJWT decodedJWT=jwtVerifier.verify(token);
						String username = decodedJWT.getSubject();
						List<String> list=decodedJWT.getClaim("authors").asList(String.class);
						Collection<SimpleGrantedAuthority> authors=new ArrayList<SimpleGrantedAuthority>();
						for (String role : list) {
							authors.add(new SimpleGrantedAuthority(role));
						}
						log.info("3");
						
						Authentication authentication=new TokenUsernamePassword(username, null, authors);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				} catch (TokenExpiredException e) {
					// ----- refresh token
					refreshToken.refresh(response, token,request);
				}
			} catch (Exception e) {
				response.setStatus(403);
			}
		}
		filterChain.doFilter(request, response);
		
	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().matches("/ShopTechnological/[a-z]{1,10}");
	}
}
