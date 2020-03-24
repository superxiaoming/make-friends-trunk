package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.LeaveMessage;
import com.example.makefriends.service.LeaveMessageService;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 21:56
 **/

@RestController
@RequestMapping(value = "/leaveMessage")
public class LeaveMessageController {

    @Autowired
    LeaveMessageService leaveMessageService;

    /**
     * @description: 获取某人的留言板
     * @Author: yinshm
     * @Date: 22:07 2020-03-24
     */
    @PassToken
    @RequestMapping(value = "/getLeaveMessageByCreateFor")
    public Object getLeaveMessageByCreateFor(@RequestParam int createFor){
        ResponseUtil responseUtil;
        List<LeaveMessage> leaveMessages = leaveMessageService.getLeaveMessagesByCreateFor(createFor);
        responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage(),
                leaveMessages);
        return responseUtil;
    }

    /**
     * @description: 为某人新增留言
     * @Author: yinshm
     * @Date: 22:17 2020-03-24
     */
    @PassToken
    @RequestMapping(value = "/addLeaveMessage")
    public Object addLeaveMessage(@RequestParam int creatorId, @RequestParam String content, @RequestParam int createFor){
        ResponseUtil responseUtil;
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setCreatorId(creatorId);
        leaveMessage.setContent(content);
        leaveMessage.setCreateTime(new Date());
        leaveMessage.setCreateFor(createFor);
        try{
            leaveMessageService.saveLeaveMessage(leaveMessage);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(), ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch (Exception e) {
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
            return responseUtil;
        }
    }
}
