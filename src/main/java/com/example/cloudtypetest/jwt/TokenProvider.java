package com.example.cloudtypetest.jwt;

import com.example.cloudtypetest.base.BaseException;
import com.example.cloudtypetest.domain.user.User;
import com.example.cloudtypetest.repository.UserRepository;
import com.example.cloudtypetest.service.user.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

import static com.example.cloudtypetest.jwt.JwtFilter.AUTHORIZATION_HEADER;


@Component
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private CustomUserDetailsService customUserDetailsService;

    private final UserRepository userRepository;


    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
    private final long tokenValidityInMilliseconds;

    private Key key;


    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
            UserRepository userRepository,
            CustomUserDetailsService customUserDetailsService) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.userRepository = userRepository;
        this.customUserDetailsService=customUserDetailsService;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Long userId) {
        Date now =new Date();

        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userId",userId)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public Authentication getAuthentication(String token)  {
        Jws<Claims> claims;

        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);



        Long userId=claims.getBody().get("userId",Long.class);

        Optional<User> users=userRepository.findById(userId);
        String userName = users.get().getUsername();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }


    public boolean validateToken(ServletRequest servletRequest, String token) {
        try {
            Jws<Claims> claims;
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            servletRequest.getParameter("userId");

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            servletRequest.setAttribute("exception","MalformedJwtException");
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            servletRequest.setAttribute("exception","ExpiredJwtException");
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            servletRequest.setAttribute("exception","UnsupportedJwtException");
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            servletRequest.setAttribute("exception","IllegalArgumentException");
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(AUTHORIZATION_HEADER);
    }
    /*
    JWT에서 userId 추출
    @return int
    @throws BaseException
     */
    public Long getUserIdx(){
        //1. JWT 추출
        String accessToken = getJwt();

        // 2. JWT parsing
        Jws<Claims> claims;
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(accessToken);


        // 3. userId 추출
        return claims.getBody().get("userId",Long.class);
    }

}