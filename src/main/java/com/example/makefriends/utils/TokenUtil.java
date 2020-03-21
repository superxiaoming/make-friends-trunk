package com.example.makefriends.utils;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-20 15:03
 **/

public class TokenUtil {

    public static String getTokenUserId() {
        String token = getRequest().getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static HttpServletRequest getRequest() {
      ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
              .getRequestAttributes();
      return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
