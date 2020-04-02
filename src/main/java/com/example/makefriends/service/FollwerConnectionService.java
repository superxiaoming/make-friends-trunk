package com.example.makefriends.service;

import com.example.makefriends.dao.FollowerConnectionDao;
import com.example.makefriends.entity.database.FollowerConnection;
import com.example.makefriends.entity.response.BeWatchedUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<BeWatchedUserInfo> getBeWatchedUserInfo (int followerId){
        return followerConnectionDao.getBeWatchedUserInfo(followerId);
    }

    public void saveFollower(FollowerConnection followerConnection){
        followerConnectionDao.save(followerConnection);
    }

    public List<Integer> getBewatchedIdByFollowerId (int followerId){
        return followerConnectionDao.getBewatchedIdByFollowerId(followerId);
    }

    public void deleteConnection(int followerId, int beWatchedId){
        followerConnectionDao.deleteFollowerConnectionByFollowerIdAndBewatchedId(followerId, beWatchedId);
    }
}
