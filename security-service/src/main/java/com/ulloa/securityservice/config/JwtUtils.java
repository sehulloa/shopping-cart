package com.ulloa.securityservice.config;

import com.ulloa.securityservice.dto.UserDto;
import com.ulloa.securityservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtUtils {

    private final Key signingKey;

    public JwtUtils() {
        String SECRET_KEY = "mysecretkey123456dePruebaparaShoppin6s3rvic3";
        this.signingKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }

    // Generar token
    public String generateToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(signingKey, SignatureAlgorithm.HS256) // Usar `Key`
                .compact();
    }

    // Extraer token del encabezado HTTP
    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Validar token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey) // Usar `Key`
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener detalles del usuario desde el token
    public UserDetails getUserDetails(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey) // Usar `Key`
                .build()
                .parseClaimsJws(token)
                .getBody();
        return new User(claims.getSubject(), "", Collections.emptyList().toString());
    }
}
