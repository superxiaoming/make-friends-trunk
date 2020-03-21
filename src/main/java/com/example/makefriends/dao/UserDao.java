package com.example.makefriends.dao;

import com.example.makefriends.entity.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-18 14:01
 **/

public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * @description: 通过用户名获取用户列表
     * @Author: yinshm
     * @Date: 00:46 2020-03-19
     */
    List<User> findByUsernameIs(String username);

    /**
     * @description: 通过用户名获取用户信息
     * @Author: yinshm
     * @Date: 00:46 2020-03-19
     */
    User findUserByUsername(String username);
    
    /**
     * @description: 通过用户id获取用户信息
     * @Author: yinshm
     * @Date: 14:50 2020-03-20
     */
    User findUserById(int id);
}
