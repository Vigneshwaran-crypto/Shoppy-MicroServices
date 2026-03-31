package com.example.shoppy.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.shoppy.entity.User;
import com.example.shoppy.repository.UsersRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UsersRepo usrRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		String token = null;
		String email = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			email = jwtUtils.extractMail(token);
			System.out.println("doFilterInternal email : " + email);
			System.out.println("doFilterInternal request : " + request.getRequestURI());
		}

		if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			User usr = usrRepo.findByEmail(email);

			if (usr != null && jwtUtils.isTokenValid(token, email)) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usr, null,
						new ArrayList<>());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		}

		filterChain.doFilter(request, response);
	}

}
