package com.acmsoft.checkgo.security.service;

import com.acmsoft.checkgo.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-token.expiration}")
    private long jwtExpiration;
    @Value("${jwt.refresh-token.expiration}")
    private long refreshExpiration;

    // Generar Access Token
    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // Generar el Refresh Token
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    //Construye el token
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        String subject = userDetails.getUsername();

        if (userDetails instanceof CustomUserDetails customUserDetails) {
            subject = customUserDetails.getPublicId().toString();
        }
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();
    }

    //extrae el public Id del token
    public String extractPublicUserId(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //extrae los roles del token
    public List<SimpleGrantedAuthority> extractRoles(String token){
        Claims claims = extractAllClaims(token);
        List<?> roles = claims.get("roles", List.class);
        if (roles != null) {
            return roles.stream()
                    .map(r -> new SimpleGrantedAuthority(r.toString()))
                    .toList();
        }
        return Collections.emptyList();
    }

    //Verifica si el token es valido
    public boolean isTokenValid(String token){
        try{
            return !isTokenValid(token);
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    //Verifica si el token expiro
    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    //Metodo generico para obtener un dato exacto del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //llama a todo el payload
    private Claims extractAllClaims(String token) {
        return Jwts.parser()           // Se usa parserBuilder() en v0.11.x
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //Convierte el secretkey en un objeto clave criptografico
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
