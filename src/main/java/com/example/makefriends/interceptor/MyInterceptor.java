package com.example.makefriends.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.User;
import com.example.makefriends.service.UserService;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: visualization
 * @description:
 * @author: YinShm
 * @date: 2019-12-10 22:12
 **/

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    /**
     * @description: token拦截验证
     * @Author: yinshm
     * @Date: 18:14 2020-03-20
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        if (token == null) {
            returnData(httpServletResponse, Result.getResult(ResponseCode.NO_TOKEN).produceApiResult());
            return false;
        }
        int userId;
        try {
            userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        } catch (JWTDecodeException j) {
            returnData(httpServletResponse, Result.getResult(ResponseCode.SYSTEM_ERROR).produceApiResult());
            return false;
        }
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            returnData(httpServletResponse, Result.getResult(ResponseCode.USER_NOT_EXIST).produceApiResult());
            return false;
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            returnData(httpServletResponse, Result.getResult(ResponseCode.TOKEN_ERROR).produceApiResult());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object handler,
                           ModelAndView modelAndView){
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object handler,
                                Exception ex){
    }

    private void returnData(HttpServletResponse response, JSONObject errorResult) {
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(errorResult.toJSONString());
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
