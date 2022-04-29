package br.com.api.condomanager.condomanager.service;

import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    private static final long EXPIRTATION_TIME = 1800000;
    private final String KEY = String.format(String.valueOf(new Date()), new Random().doubles(999999999));
    

    public String generateToken(User user) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRTATION_TIME))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }
	
}
