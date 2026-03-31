package com.example.shoppy.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.secret}")
	private String SECRET;

	private final long EXPIRY = 1000 * 60 * 60 * 24;

	public String generateToken(String email, Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setSubject(email).setId(claims.get("id").toString())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRY))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractMail(String token) {
		return extractAllClaims(token).getSubject();
	}

	public boolean isTokenValid(String token, String email) {
		String mail = extractMail(token);
		return mail.equals(mail) && !isTokenExpired(token);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString(SECRET.getBytes()));
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
