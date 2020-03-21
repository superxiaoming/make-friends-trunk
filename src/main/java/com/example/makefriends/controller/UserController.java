package com.example.makefriends.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.User;
import com.example.makefriends.service.TokenService;
import com.example.makefriends.service.UserService;
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

    /**
     * @description: 登陆入口，无需token验证
     * @Author: yinshm
     * @Date: 18:10 2020-03-20
     */
    @PassToken
    @RequestMapping(value = "/login")
    public Object login(@RequestParam String userName, @RequestParam String password){
        ResponseUtil responseUtil;
        User user = userService.getUserByUsername(userName);
        if(!password.equals(user.getPassword())){
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(), ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
        String token = tokenService.getToken(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), jsonObject);
        return responseUtil;
    }

    /**
     * @description: 用户注册
     * @Author: yinshm
     * @Date: 18:10 2020-03-20
     */
    @RequestMapping(value = "/addUser")
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

    /**
     * @description: 编辑用户信息
     * @Author: yinshm
     * @Date: 18:13 2020-03-20
     */
    @PostMapping(value = "/editUser")
    public Object editUser(MultipartFile headPic, HttpServletRequest httpServletRequest){
        return null;
    }

    /**
     * @description: 根据用户id获取用户信息
     * @Author: yinshm
     * @Date: 18:13 2020-03-20
     */
    @RequestMapping(value = "getUserInfoById")
    public Object getUserInfoById(@RequestParam int userId){
        User user = userService.getUserByUserId(userId);
        ResponseUtil responseUtil;
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(), user);
        return responseUtil;
    }
}
