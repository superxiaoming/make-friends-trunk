package com.example.makefriends.dao;

import com.example.makefriends.entity.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-18 14:01
 **/

public interface UserDao extends JpaRepository<User, Integer> {

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

    /**
     * @description: 修改密码
     * @Author: yinshm
     * @Date: 20:04 2020-03-22
     */
    @Modifying
    @Transactional
    @Query("update sys_user set password = :newPassword where id = :userId")
    void changePassword(@Param("userId")int userId, @Param("newPassword") String newPassword);

    @Modifying
    @Transactional
    @Query("update sys_user set nickname = :nickname, school = :school, age = :age," +
            "college = :college, major = :major, tags = :tags, sign = :sign," +
            "headpic = :picAddress, sex = :sex where id = :userId")
    void editUserInfo(@Param("userId")int userId, @Param("nickname") String nickname, @Param("school") String school,
                      @Param("age") int age, @Param("college") String college, @Param("major") String major,
                      @Param("tags") String tags, @Param("sign") String sign, @Param("picAddress") String picAddress,
                      @Param("sex") int sex);
}
