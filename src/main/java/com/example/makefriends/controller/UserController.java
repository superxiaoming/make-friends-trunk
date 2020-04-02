package com.example.makefriends.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.User;
import com.example.makefriends.service.*;
import com.example.makefriends.utils.FileUtils;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.ResponseUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: visualization
 * @description: UserController
 * @author: YinShm
 * @date: 2019-12-09 23:46
 **/

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    TopicService topicService;

    @Autowired
    FollwerConnectionService follwerConnectionService;

    @Autowired
    LeaveMessageService leaveMessageService;

    @Autowired
    StudyGoalService studyGoalService;

    @PassToken
    @RequestMapping(value = "/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        ResponseUtil responseUtil;
        User user = userService.getUserByUsername(username);
        if(user == null || !password.equals(user.getPassword())){
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
        String token = tokenService.getToken(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("userId", user.getId());
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), jsonObject);
        return responseUtil;
    }

    @PassToken
    @RequestMapping(value = "/register")
    public Object addUser(HttpServletRequest httpServletRequest){
        ResponseUtil responseUtil;
        try{
            String userName = httpServletRequest.getParameter("userName");
            String password = httpServletRequest.getParameter("password");
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            user.setNickname("无名氏");
            user.setAge(20);
            user.setSex(0);
            userService.addUser(user);
        } catch (Exception e){
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
        return responseUtil;
    }

    @PostMapping(value = "/editUser")
    public Object editUser(MultipartFile headPic, HttpServletRequest httpServletRequest){
        ResponseUtil responseUtil;
        String picAddress = null;
        if(headPic != null){
            String newFileName = FileUtils.upload(headPic, headPic.getOriginalFilename());
            if(newFileName.equals("")){
                responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
                return responseUtil;
            }
            picAddress = FileUtils.getPicAddress(httpServletRequest, newFileName);
        }
        int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
        String nickname = httpServletRequest.getParameter("nickname");
        String school = httpServletRequest.getParameter("school");
        int age = Integer.parseInt(httpServletRequest.getParameter("age"));
        String college = httpServletRequest.getParameter("college");
        String major = httpServletRequest.getParameter("major");
        String tags = httpServletRequest.getParameter("tags");
        String sign = httpServletRequest.getParameter("sign");
        int sex = Integer.parseInt(httpServletRequest.getParameter("sex"));

        try{
            userService.editUserInfo(userId, nickname, school, age, college, major, tags, sign, picAddress, sex);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch(Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
    }

    @RequestMapping(value = "/getUserInfoById")
    public Object getUserInfoById(@RequestParam int userId){
        User user = userService.getUserByUserId(userId);
        ResponseUtil responseUtil;
        if(user == null){
            responseUtil = new ResponseUtil(ResponseCode.USER_NOT_EXIST.getCodeNumber(), ResponseCode.USER_NOT_EXIST.getCodeMessage());
            return responseUtil;
        }
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), user);
        return responseUtil;
    }

    @RequestMapping(value = "/changePassword")
    public Object changePassword(@RequestParam int userId, @RequestParam String password, @RequestParam String newPassword){
        ResponseUtil responseUtil;
        User user = userService.getUserByUserId(userId);
        if(user == null){
            responseUtil = new ResponseUtil(ResponseCode.USER_NOT_EXIST.getCodeNumber(), ResponseCode.USER_NOT_EXIST.getCodeMessage());
            return responseUtil;
        }
        if(user.getPassword().equals(password)){
            userService.changePassword(userId, newPassword);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        }
        responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
        return responseUtil;
    }

    @RequestMapping(value = "/getBasicInfo")
    public Object getBasicInfo(@RequestParam int userId){
        ResponseUtil responseUtil;
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("publish", topicService.getTopicCountByCreatorId(userId));
            jsonObject.put("focus", follwerConnectionService.getWatchedCountByFollowerId(userId));
            jsonObject.put("message", leaveMessageService.getMessageCountByCreateFor(userId));
            jsonObject.put("goal", studyGoalService.getGoalCountByCreatorId(userId));
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), jsonObject);
            return responseUtil;
        } catch (Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }

    @PassToken
    @RequestMapping(value = "/getToken")
    public Object getToken(@RequestParam int userId){
        ResponseUtil responseUtil;
        User user = userService.getUserByUserId(userId);
        try{
            String token = tokenService.getToken(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), jsonObject);
            return responseUtil;
        } catch(Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }

    @RequestMapping(value = "/getUserByUsername")
    public Object getUserByUsername(@RequestParam String nickname){
        ResponseUtil responseUtil;
        List<User> users = userService.findUsersByNickname(nickname);
        if(users.isEmpty()){
            responseUtil = new ResponseUtil(ResponseCode.USER_NOT_EXIST.getCodeNumber(), ResponseCode.USER_NOT_EXIST.getCodeMessage());
            return responseUtil;
        }
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), users);
        return responseUtil;
    }
}
