package com.example.makefriends.dao;

import com.example.makefriends.entity.database.FollowerConnection;
import com.example.makefriends.entity.response.BeWatchedUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-26 23:45
 **/

public interface FollowerConnectionDao extends JpaRepository<FollowerConnection, Integer> {

    int countByFollowerId(int followerId);

    @Query(value = "select new com.example.makefriends.entity.response.BeWatchedUserInfo(t1.id, t1.followerId, " +
            "t1.bewatchedId, t2.school, t2.college, t2.major, t2.tags, t2.nickname, t2.sign, t2.headpic, " +
            "t2.age, t2.sex) from " +
            "follower_connection as t1, sys_user as t2 where t1.followerId = :followerId and t1.bewatchedId = " +
            "t2.id")
    List<BeWatchedUserInfo> getBeWatchedUserInfo(int followerId);

    @Query(value = "select bewatchedId from follower_connection where followerId = :followerId")
    List<Integer> getBewatchedIdByFollowerId(int followerId);

    @Transactional
    void deleteFollowerConnectionByFollowerIdAndBewatchedId(int followerId, int bewatchedId);
}
