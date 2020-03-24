package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.CommemorationDay;
import com.example.makefriends.service.CommemorationDayService;
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
 * @date: 2020-03-24 22:21
 **/

@RestController
@RequestMapping(value = "/commemorationDay")
public class CommemorationDayController {

    @Autowired
    CommemorationDayService commemorationDayService;

    @PassToken
    @RequestMapping(value = "/getDays")
    public Object getDays(@RequestParam int creatorId){
        ResponseUtil responseUtil;
        List<CommemorationDay> commemorationDays = commemorationDayService.getCommemorationDaysByCreatorId(creatorId);
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(),
                commemorationDays);
        return responseUtil;
    }

    @PassToken
    @RequestMapping(value = "/addDays")
    public Object addDays(@RequestParam int creatorId, @RequestParam String content, @RequestParam String memoryDay){
        ResponseUtil responseUtil;
        CommemorationDay commemorationDay = new CommemorationDay();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try{
            date = format.parse(memoryDay);
        } catch (Exception e) {
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
        commemorationDay.setCreatorId(creatorId);
        commemorationDay.setContent(content);
        commemorationDay.setCreateTime(new Date());
        commemorationDay.setMemoryDay(date);
        try{
            commemorationDayService.addCommemorationDay(commemorationDay);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch (Exception e) {
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
    }
}
