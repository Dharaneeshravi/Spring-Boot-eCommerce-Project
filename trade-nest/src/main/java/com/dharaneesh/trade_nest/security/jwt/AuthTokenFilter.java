package com.dharaneesh.trade_nest.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        try {
               String jwt=parseJwt(request);

               if(jwt!=null && jwtUtils.validateJwt(jwt))
               {
                   String userName=jwtUtils.gettingUserNameFromToken(jwt);
                   UserDetails userDetails =userDetailsService.loadUserByUsername(userName);
                   UsernamePasswordAuthenticationToken passwordAuthenticationToken=
                           new UsernamePasswordAuthenticationToken(userDetails ,null,userDetails.getAuthorities());
                   passwordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                   SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
               }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        filterChain.doFilter(request,response);

    }

    private String parseJwt(HttpServletRequest request) {

        return jwtUtils.getJwtFromHeader(request);
    }
}
