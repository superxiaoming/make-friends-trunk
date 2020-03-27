package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.StudyGoal;
import com.example.makefriends.entity.response.StudyGoalWithUser;
import com.example.makefriends.service.StudyGoalService;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-25 03:27
 **/

@RestController
@RequestMapping(value = "/studyGoal")
public class StudyGoalController {

    @Autowired
    StudyGoalService studyGoalService;

    @RequestMapping(value = "/getStudyGoals")
    public Object getStudyGoals(){
        List<StudyGoalWithUser> studyGoals = studyGoalService.getStudyGoals();
        ResponseUtil responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                ResponseCode.SUCCESS_CODE.getCodeMessage(), studyGoals);
        return responseUtil;
    }

    @RequestMapping(value = "/getStudyGoalsByCreatorId")
    public Object getStudyGoals(@RequestParam int creatorId){
        List<StudyGoalWithUser> studyGoals = studyGoalService.getStudyGoalsByCreatorId(creatorId);
        ResponseUtil responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                ResponseCode.SUCCESS_CODE.getCodeMessage(), studyGoals);
        return responseUtil;
    }

    @RequestMapping("/addStudyGoal")
    public Object addStudyGoal(@RequestParam int creatorId, @RequestParam String content, @RequestParam int repeatType,
                               @RequestParam String repeatTime){
        ResponseUtil responseUtil;
        StudyGoal studyGoal = new StudyGoal();
        studyGoal.setCreatorId(creatorId);
        studyGoal.setContent(content);
        studyGoal.setRepeatType(repeatType);
        studyGoal.setRepeatTime(repeatTime);
        studyGoal.setCreateTime(new Date());
        try{
            studyGoalService.addStudyGoal(studyGoal);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch (Exception e) {
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
    }
}
