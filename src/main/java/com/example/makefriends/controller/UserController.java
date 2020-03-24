package com.example.makefriends.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.User;
import com.example.makefriends.service.TokenService;
import com.example.makefriends.service.UserService;
import com.example.makefriends.utils.FileUtils;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @PassToken
    @RequestMapping(value = "/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        ResponseUtil responseUtil;
        User user = userService.getUserByUsername(username);
        if(!password.equals(user.getPassword())){
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
            userService.addUser(user);
        } catch (Exception e){
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
        return responseUtil;
    }

    @PassToken
    @PostMapping(value = "/editUser")
    public Object editUser(MultipartFile headPic, HttpServletRequest httpServletRequest){
        ResponseUtil responseUtil;
        // 文件存储并获取新文件名
        String newFileName = FileUtils.upload(headPic, headPic.getOriginalFilename());
        if(newFileName.equals("")){
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
        int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
        String nickname = httpServletRequest.getParameter("nickname");
        String school = httpServletRequest.getParameter("school");
        int age = Integer.parseInt(httpServletRequest.getParameter("age"));
        String college = httpServletRequest.getParameter("college");
        String major = httpServletRequest.getParameter("major");
        String tags = httpServletRequest.getParameter("tags");
        String sign = httpServletRequest.getParameter("sign");
        // 生成文件访问地址
        String picAddress = FileUtils.getPicAddress(httpServletRequest, newFileName);

        try{
            userService.editUserInfo(userId, nickname, school, age, college, major, tags, sign, picAddress);
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

    @PassToken
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
}
