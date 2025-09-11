package com.dharaneesh.trade_nest.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${spring.app.jwtExpiration}")
    public int jwtExpiration;
    @Value("${spring.app.jwtSecret}")
    public String jwtSecret;

    public String getJwtFromHeader(HttpServletRequest request)
    {
        String bearerToken=request.getHeader("Authorization");

        if(bearerToken!=null && bearerToken.startsWith("Bearer "))
        {
            return  bearerToken.substring(7);
        }
        return null;
    }

    public String generateTokenFromUserName(UserDetails userDetails)
    {
       String userName=userDetails.getUsername();

       return Jwts
               .builder()
               .subject(userName)
               .issuedAt(new Date())
               .expiration(new Date((new Date().getTime()+jwtExpiration)))
               .signWith(key())
               .compact();
    }

    public String gettingUserNameFromToken(String token)
    {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Key key()
    {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    public boolean validateJwt(String authToken)
    {

        try {
                      Jwts.parser()
                       .verifyWith((SecretKey) key())
                       .build()
                       .parseSignedClaims(authToken);
             return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;

    }
}
