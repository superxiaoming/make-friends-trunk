package com.example.makefriends.dao;

import com.example.makefriends.entity.database.StudyGoal;
import com.example.makefriends.entity.response.StudyGoalWithUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-25 03:16
 **/

public interface StudyGoalDao extends JpaRepository<StudyGoal, Integer> {

    @Query(value = "select new com.example.makefriends.entity.response.StudyGoalWithUser(t1.id, t1.creatorId, " +
            "t1.content, t1.repeatType, t1.repeatTime, t2.nickname, t2.age, t2.sex, t2.headpic) " +
            "from study_goal as t1, sys_user as t2 where t1.creatorId = t2.id " +
            "order by t1.createTime desc")
    List<StudyGoalWithUser> getStudyGoals();

    /**
     * @description: 根据用户和话题类型查找话题
     * @Author: yinshm
     * @Date: 20:28 2020-03-23
     */
    @Query(value = "select new com.example.makefriends.entity.response.StudyGoalWithUser(t1.id, t1.creatorId, " +
            "t1.content, t1.repeatType, t1.repeatTime, t2.nickname, t2.age, t2.sex, t2.headpic) " +
            "from study_goal as t1, sys_user as t2 where t1.creatorId = t2.id and t1.creatorId = :creatorId " +
            "order by t1.createTime desc")
    List<StudyGoalWithUser> getStudyGoalsBycreatorId(int creatorId);

    int countByCreatorId(int creatorId);
}
