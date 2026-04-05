package com.example.shoppy.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String email = null;
		String token = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			email = jwtUtils.extractMail(token);
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			Map<String, Object> usr = jwtUtils.extractAllClaims(token);

			if (!usr.isEmpty() && jwtUtils.isTokenValid(token, email)) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usr, null,
						new ArrayList<>());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		}

		filterChain.doFilter(request, response);

	}

}
