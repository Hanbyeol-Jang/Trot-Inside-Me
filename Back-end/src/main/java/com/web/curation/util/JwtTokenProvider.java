package com.web.curation.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.web.curation.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenProvider {
	
	@Value("${token.secret.key}")
	private String secretKey;
	
	@Value("${token.vaild.time}")
    private long tokenValidTime;
    
    public String createToken(UserDto dto) {
//        Claims claims = Jwts.claims().setSubject(map);
        Claims claims = Jwts.claims().setSubject(dto.getU_email());

        Date now = new Date();
        Date validity = new Date(now.getTime()
                + tokenValidTime);

        return Jwts.builder()
                .setClaims(claims)
                .claim("u_name", dto.getU_name())
                .claim("u_profileImg", dto.getU_profileImg())
                .claim("u_isAdmin", dto.getU_isAdmin())
                .claim("u_hasVote ", dto.getU_hasVote())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
         String token = request.getHeader("token");
         System.out.println("token ::::: "+token);
         return token;
    }
    
    // 카카오 로그인 시 헤더에서 access Token 얻어옴
    public String getAccessToken(HttpServletRequest request) {
    	String access_token = request.getHeader("access_token");
    	return access_token;
    }
    
    public boolean validateToken(String token) {
    	try {
    		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    		return !claims.getBody().getExpiration().before(new Date());
    	} catch (Exception e) {
    		return false;
    	}
    }


	public UserDto getInfo(HttpServletRequest request) {
		UserDto dto = new UserDto();
		String token = resolveToken(request);
		if(validateToken(token)) {
			 Claims cl=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			 
			 dto.setU_email(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
			 dto.setU_name(cl.get("u_name").toString());
			 dto.setU_profileImg(cl.get("u_profileImg").toString());
			 dto.setU_isAdmin(Integer.parseInt(cl.get("u_isAdmin").toString()));
			 dto.setU_hasVote(Integer.parseInt(cl.get("u_hasVote ").toString()));
		}else {
			System.out.println("만료됨 새 토큰 발행받아야함.");
			dto.setU_name("F");
		}
		return dto;
	}
}
