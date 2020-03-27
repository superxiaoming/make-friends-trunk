package com.example.makefriends.dao;

import com.example.makefriends.entity.database.FollowerConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-26 23:45
 **/

public interface FollowerConnectionDao extends JpaRepository<FollowerConnection, Integer> {

    int countByFollowerId(int followerId);
}
