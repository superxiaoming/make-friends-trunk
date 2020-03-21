package com.example.makefriends.service;

import com.example.makefriends.dao.UserDao;
import com.example.makefriends.entity.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-18 14:08
 **/
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(User user){
        userDao.save(user);
    }

    public User getUserByUsername(String username){
        return userDao.findUserByUsername(username);
    }

    public User getUserByUserId(int id){
        return userDao.findUserById(id);
    }
}
