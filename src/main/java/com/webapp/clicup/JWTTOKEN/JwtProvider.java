package com.webapp.clicup.JWTTOKEN;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {


    @Value("${spring.jwt.token.secretKey}")
    private String Secretkeys;

    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .claims(claims)
                .subject(userName)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] key = Decoders.BASE64.decode(Secretkeys);
        return Keys.hmacShaKeyFor(key);
    }


    /*
     * bu ikki pastdagi funksiya json web token uchun
     * shifrlangan kalitni qaytarish uchun ishlatiladi
     * yangi versiyalarda kalitni shifrlab berish talab qilinadi
     *
     * SecretKey secretKey() {
     *    return Jwts.SIG.HS512.key().build();
     *}
     *
     *SecretKey key() {
     *   byte[] bytes = Decoders.BASE64.decode("sefe");
     *    return Keys.hmacShaKeyFor(bytes);
     *}
     * */

    public String extractUsername(String token) {
        return ExctractClaim(token, Claims::getSubject);
    }

    private <T> T ExctractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = ExctractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims ExctractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractEXpiration(token).before(new Date());
    }

    private Date extractEXpiration(String token) {
        return ExctractClaim(token, Claims::getExpiration);
    }
}