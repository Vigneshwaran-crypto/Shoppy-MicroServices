package com.example.shoppy.webSecurity;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements WebFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		String path = exchange.getRequest().getURI().getPath();
		
		if (path.startsWith("/api/auth/") || path.startsWith("/auth/")) {
		    return chain.filter(exchange);
		}
		
		String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
		
//		System.out.println("Request header :"+authHeader);
		
		String email = null;
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			email = jwtUtils.extractMail(token);
		}
		
		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			Map<String, Object> usr = jwtUtils.extractAllClaims(token);
			if (!usr.isEmpty() && jwtUtils.isTokenValid(token, email)) {
				
				
				System.out.println("Request User :"+usr);
				
				UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                usr,
                                null,
                                new ArrayList<>()
                        );
				
				SecurityContext context = new SecurityContextImpl(authentication);
				
				return chain.filter(exchange)
						.contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(context)));
				
			}
				
		}
		
		return chain.filter(exchange);
	}

}
