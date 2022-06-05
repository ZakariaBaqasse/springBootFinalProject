package com.example.finalproject.service;


import com.example.finalproject.dao.RoleDao;
import com.example.finalproject.dao.UserDao;
import com.example.finalproject.entity.Role;
import com.example.finalproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setPassword(getEncodedPassword(user.getPassword()));

        return  userDao.save(user);
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
