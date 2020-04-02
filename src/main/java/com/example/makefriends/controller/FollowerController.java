package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.FollowerConnection;
import com.example.makefriends.entity.response.BeWatchedUserInfo;
import com.example.makefriends.service.FollwerConnectionService;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-30 17:03
 **/

@RestController
@RequestMapping(value = "/follower")
public class FollowerController {

    @Autowired
    FollwerConnectionService follwerConnectionService;

    @PassToken
    @RequestMapping(value = "/getBewatchedUserInfo")
    public Object getBewatchedUserInfo(@RequestParam int followerId){
        ResponseUtil responseUtil;
        List<BeWatchedUserInfo> beWatchedUserInfos = follwerConnectionService.getBeWatchedUserInfo(followerId);
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), beWatchedUserInfos);
        return responseUtil;
    }

    @PassToken
    @RequestMapping(value = "/addFollower")
    public Object addFollwer(@RequestParam int followerId, @RequestParam int beWatchedId){
        ResponseUtil responseUtil;
        if(followerId == beWatchedId){
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
        List<Integer> list = follwerConnectionService.getBewatchedIdByFollowerId(followerId);
        if(list.contains(beWatchedId)){
            responseUtil = new ResponseUtil(ResponseCode.ALREADY_INSWRT_CONNECTION.getCodeNumber(), ResponseCode.ALREADY_INSWRT_CONNECTION.getCodeMessage());
            return responseUtil;
        }
        FollowerConnection followerConnection = new FollowerConnection();
        followerConnection.setFollowerId(followerId);
        followerConnection.setBewatchedId(beWatchedId);
        try{
            follwerConnectionService.saveFollower(followerConnection);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch (Exception e){
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }

    @PassToken
    @RequestMapping(value = "/getConnection")
    public Object getList(@RequestParam int followerId, @RequestParam int beWatchedId){
        ResponseUtil responseUtil;
        List<Integer> list = follwerConnectionService.getBewatchedIdByFollowerId(followerId);
        if(list.contains(beWatchedId)){
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), true);
            return responseUtil;
        }
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), false);
        return responseUtil;
    }

    @PassToken
    @RequestMapping(value = "/deleteConnection")
    public Object deleteConnection(@RequestParam int followerId, @RequestParam int beWatchedId){
        ResponseUtil responseUtil;
        try{
            follwerConnectionService.deleteConnection(followerId, beWatchedId);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch(Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }
}
