package com.example.finalproject.service;


import com.example.finalproject.dao.UserDao;
import com.example.finalproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User registerNewUser(User user){
        return  userDao.save(user);
    }
}
