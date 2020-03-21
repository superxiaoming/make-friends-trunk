package com.example.makefriends.utils;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.io.*;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/9.
 * 响应报文基类
 */
public class Result implements Serializable, Cloneable {
    private static final long serialVersionUID = 214978255619953793L;
    /**
     * 响应码
     */
    protected int code;
    /**
     * 响应信息
     */
    protected String msg;


    public Result() {
    }


    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResponseCode errorCode){
        this.code=errorCode.getCodeNumber();
        this.msg=errorCode.getCodeMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public Result clone() {
        //将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(this);
            //从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (Result) oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Result(code, msg);

    }

    private static class HoldResult{
        private static Result result=new Result();
    }

    private Object readResolve() throws ObjectStreamException {
        return HoldResult.result;
    }
    public static Result getResult(ResponseCode errorCode) {
        Result result= HoldResult.result;
        result.setCode(errorCode.getCodeNumber());
        result.setMsg(errorCode.getCodeMessage());
        return result;
    }

    public static Result getResultWithSuccessCode(ErrorCode errorCode) {
        Result result= HoldResult.result;
        result.setCode(ResponseCode.SUCCESS_CODE.getCodeNumber());
        result.setMsg(ResponseCode.SUCCESS_CODE.getCodeMessage());
        return result;
    }

    public JSONObject produceApiResult(Map<String,Object> resultMap){
        if(!resultMap.isEmpty()){
            resultMap.put("code",code);
            resultMap.put("msg",msg);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap,filter));
        return jsonObject;
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",code);
            jsonObject.put("msg",msg);
            return jsonObject;
        }
    }

    public JSONObject produceApiResult() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject;
    }


    private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object o, String s, Object v) {
            if(v==null){
                return "";
            }
            return v;
        }
    };
}
