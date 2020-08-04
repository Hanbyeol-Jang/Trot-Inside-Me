package com.web.curation.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtTokenProvider {
	private String secretKey = "webfirewood";
    private long tokenValidTime = 30 * 60 * 1000L; //30분
    
    public String createToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);

        Date now = new Date();
        Date validity = new Date(now.getTime()
                + tokenValidTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
         String token = request.getHeader("token");
         return token;
    }
    
    public boolean validateToken(Jws<Claims> claims) {
        try {
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

	public String getInfo(HttpServletRequest request) {
		String token = resolveToken(request);
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		String useremail="";
		if(validateToken(claims)) {
			 useremail = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		}else {
			System.out.println("만료됨 새 토큰 발행받아야함.");
			useremail="F";
		}
		return useremail;
	}
}