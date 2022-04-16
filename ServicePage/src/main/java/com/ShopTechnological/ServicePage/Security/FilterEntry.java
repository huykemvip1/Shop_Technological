package com.ShopTechnological.ServicePage.Security;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ShopTechnological.ServicePage.reponsitory.MemoriesJwtRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class FilterEntry extends OncePerRequestFilter{
	@Value("${key.authentication}")
	private String key;
	private final MemoriesJwtRepository memoriesJwtRepository;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		RestTemplate restTemplate=new RestTemplate();
		String jwt="";
		try {
			HttpHeaders headers= restTemplate.headForHeaders(
					new URI("http://localhost:8080/ShopTechnological/auth/token")
					);
			jwt=headers.getFirst(HttpHeaders.AUTHORIZATION);
		} catch (RestClientException | URISyntaxException e1) {
			e1.printStackTrace();
		}
		if (jwt != null && !jwt.equals("") && jwt.startsWith("Bearer ")) {
			String jwtToToken=jwt.substring(jwt.indexOf("Bearer ")+("Bearer ").length()-1);
			Algorithm algorithm=Algorithm.HMAC256(key);
			Base64 base64=new Base64();
			String token=new String(base64.decode(jwtToToken));
			log.info(token);
			try {
					// ---- check token exist.this is not exist
					if (memoriesJwtRepository.findByToken(token).isEmpty()) {
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
						Authentication authentication=new UsernamePasswordAuthenticationToken(username, null, authors);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
			} catch (Exception e) {
				response.setStatus(403);
			}
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().startsWith("/ShopTechnological/have/");
	}

}
