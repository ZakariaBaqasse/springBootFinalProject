package com.example.finalproject.configuration;


import com.example.finalproject.service.JwtService;
import com.example.finalproject.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       final String header = request.getHeader("Authorization");
       String jwtToken = null;
       String username = null;
        System.out.println(header);
       if(header != null ) {
         if(header.startsWith("Bearer ")){
           jwtToken = header.substring(7);

           try {
               username = jwtUtil.getUsernameFromToken(jwtToken);
           } catch (IllegalArgumentException ie) {
               System.out.println("Unable to get Jwt Token");
           } catch (ExpiredJwtException ej) {
               System.out.println("Jwt Token is Expired");
           }}else {
             System.out.println("Jwt Token does not starts with Bearer");
         }
       }else {
           System.out.println("Header is null");
       }
       if(username != null && SecurityContextHolder.getContext().getAuthentication() ==null){
           UserDetails userDetails = jwtService.loadUserByUsername(username);
           if(jwtUtil.validateToken(jwtToken,userDetails)){
             UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
             usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
       }
       filterChain.doFilter(request,response);
    }
}
