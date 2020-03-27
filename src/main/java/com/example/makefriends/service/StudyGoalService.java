package com.example.makefriends.service;

import com.example.makefriends.dao.StudyGoalDao;
import com.example.makefriends.entity.database.StudyGoal;
import com.example.makefriends.entity.response.StudyGoalWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-25 03:24
 **/

@Service
public class StudyGoalService {

    @Autowired
    StudyGoalDao studyGoalDao;

    public List<StudyGoalWithUser> getStudyGoals(){
        return studyGoalDao.getStudyGoals();
    }

    public List<StudyGoalWithUser> getStudyGoalsByCreatorId(int creatorId){
        return studyGoalDao.getStudyGoalsBycreatorId(creatorId);
    }

    public void addStudyGoal(StudyGoal studyGoal){ studyGoalDao.save(studyGoal); }

    public int getGoalCountByCreatorId(int creatorId){
        return studyGoalDao.countByCreatorId(creatorId);
    }
}
