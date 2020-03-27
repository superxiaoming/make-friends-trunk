package com.example.makefriends.service;

import com.example.makefriends.dao.FollowerConnectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-26 23:49
 **/

@Service
public class FollwerConnectionService {

    @Autowired
    FollowerConnectionDao followerConnectionDao;

    public int getWatchedCountByFollowerId(int followerId){
        return followerConnectionDao.countByFollowerId(followerId);
    }
}
