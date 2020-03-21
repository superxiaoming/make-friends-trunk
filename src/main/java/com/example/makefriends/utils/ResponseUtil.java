package com.example.makefriends.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-18 18:24
 **/

@Getter
@Setter
public class ResponseUtil implements Serializable {

    protected int resCode;
    protected String resMsg;
    protected Object data;

    public ResponseUtil(){}

    public ResponseUtil(int resCode, String resMsg, Object data){
        this.resCode = resCode;
        this.resMsg = resMsg;
        this.data = data;
    }

    public ResponseUtil(int resCode, String resMsg){
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    @Override
    public String toString(){
        return "Result{" +
                "resCode=" + resCode +
                ", resMsg=" + resMsg +
                "}";
    }
}
