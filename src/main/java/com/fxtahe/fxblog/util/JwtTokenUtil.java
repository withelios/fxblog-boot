package com.fxtahe.fxblog.util;

import com.fxtahe.fxblog.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author fxtahe
 * @description JWT 工具类
 * @date 2020/6/17
 */
@Component
public class JwtTokenUtil {

    private Clock clock = DefaultClock.INSTANCE;

    @Value("${token.header}")
    public String tokenHeader = "Authorization";

    @Value("${token.secret}")
    private String secret;

    @Value("${token.access-expiration}")
    private Long accessExpiration;

    @Value("${token.refresh-expiration}")
    private Long refreshExpiration;

    @Value("${token.roleClaims}")
    private String roleClaims;

    private String typeClaims = "tokenType";

    private String idClaims = "userId";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Integer getIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Integer) claims.get(idClaims);
    }


    public List<String> getRoleClaimsFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (List<String>) claims.get(roleClaims);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isAccessToken(String token) {
        return judgeToken(token, Const.ACCESS_TOKEN);
    }

    public Boolean isReFreshToken(String token) {
        return judgeToken(token, Const.REFRESH_TOKEN);
    }

    private Boolean judgeToken(String token, String tokenType) {
        Claims claims = getAllClaimsFromToken(token);
        System.out.println(claims.get(typeClaims).toString());
        return tokenType.equals(claims.get(typeClaims).toString());
    }

    public Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());

    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateAccessToken(Integer id,String username, List<String> role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(idClaims,id);
        claims.put(roleClaims, role);
        claims.put(typeClaims, Const.ACCESS_TOKEN);
        return doGenerateToken(claims, username, accessExpiration);
    }

    public String generateRefreshToken(Integer id,String username, List<String> role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(idClaims,id);
        claims.put(roleClaims, role);
        claims.put(typeClaims, Const.REFRESH_TOKEN);
        return doGenerateToken(claims, username, refreshExpiration);
    }

    public String resetRefreshToken(String token) {
        Claims allClaimsFromToken = getAllClaimsFromToken(token);
        allClaimsFromToken.put(typeClaims, Const.REFRESH_TOKEN);
        return refreshToken(allClaimsFromToken, refreshExpiration);
    }

    public String resetAccessToken(String token) {
        Claims allClaimsFromToken = getAllClaimsFromToken(token);
        allClaimsFromToken.put(typeClaims, Const.ACCESS_TOKEN);
        return refreshToken(allClaimsFromToken, accessExpiration);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, Long expiration) {
        final Date createdDate = clock.now();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(final Claims claims, Long expiration) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate, expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetailsImpl user = (UserDetailsImpl) userDetails;
        final String username = getUsernameFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
        );
    }

    private Date calculateExpirationDate(Date createdDate, Long expiration) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}
