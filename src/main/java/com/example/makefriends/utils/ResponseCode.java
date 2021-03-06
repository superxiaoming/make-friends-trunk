package com.example.makefriends.utils;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-18 23:15
 **/

public enum ResponseCode {

    SUCCESS_CODE(10000,"成功"),
    FAILED_CODE(10001,"失败"),
    NO_TOKEN(10002, "缺失token或token无效"),
    SYSTEM_ERROR(10003,"系统错误"),
    USER_NOT_EXIST(10004,"用户不存在");

    private int codeNumber;
    private String codeMessage;

    private ResponseCode(int codeNumber, String codeMessage){
        this.codeMessage = codeMessage;
        this.codeNumber = codeNumber;
    }

    public String getCodeMessage(){
        return codeMessage;
    }

    public int getCodeNumber(){
        return codeNumber;
    }
}
