package br.com.api.condomanager.condomanager.autenticacao.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.sistema.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	/**
	 * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key
	 * here. Ideally, in a microservices environment, this key would be kept on a
	 * config-server.
	 */
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 1h

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private MyUserDetails myUserDetails;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String gerarToken(UserEntity user) {

		Claims claims = this.gerarClaims(user);

		Date validity = new Date(new Date().getTime() + validityInMilliseconds);
		Map<String, Object> header = new HashMap<>();
		header.put("api-key", apiKey);

		return Jwts.builder()
				.setHeader(header)
				.setClaims(claims)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidTokenException("Token inválido");
		}
	}
	
	private Claims gerarClaims(UserEntity user) {
		
		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("nome", user.getNome());
		claims.put("email", user.getEmail());
		claims.put("auth", user.getNivelAcesso());
		claims.put("data", new Date());
		
		return claims;
	}

}
