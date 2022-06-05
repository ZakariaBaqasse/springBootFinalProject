package com.example.finalproject.service;


import com.example.finalproject.dao.UserDao;
import com.example.finalproject.entity.JwtRequest;
import com.example.finalproject.entity.JwtResponse;
import com.example.finalproject.entity.User;
import com.example.finalproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();
        authenticate(username,password);
        final UserDetails userDetails =loadUserByUsername(username);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        User user = userDao.findById(username).get();
        return new JwtResponse(user,newGeneratedToken);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findById(username).get();
        if(user != null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        }else{
           throw new UsernameNotFoundException("username not found");
        }
    }

    private Set getAuthorities(User user){
        Set authorities = new HashSet();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("Role_"+role.getRole()));
        });
        return authorities;
    }

    public void authenticate(String username, String password) throws Exception{
        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException e){
            throw new Exception("User disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }
    }

}
