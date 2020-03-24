package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.Topic;
import com.example.makefriends.service.TopicService;
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
 * @description: 话题controller
 * @author: YinShm
 * @date: 2020-03-23 20:43
 **/

@RestController
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @PassToken
    @RequestMapping(value = "/getTopicsByContentType")
    public Object getTopicByContentType(@RequestParam int contentType){
        List<Topic> topics = topicService.getTopicsByContentType(contentType);
        ResponseUtil responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                ResponseCode.SUCCESS_CODE.getCodeMessage(), topics);
        return responseUtil;
    }

    @PassToken
    @RequestMapping(value = "/getTopicsByContentTypeAndCreatorId")
    public Object getTopicsByContentTypeAndCreatorId(@RequestParam int creatorId, @RequestParam int contentType){
        List<Topic> topics = topicService.getTopicsByContentTypeAndCreatorId(creatorId, contentType);
        ResponseUtil responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                ResponseCode.SUCCESS_CODE.getCodeMessage(), topics);
        return responseUtil;
    }

    @PassToken
    @RequestMapping(value = "/addTopic")
    public Object addTopic(@RequestParam int creatorId, @RequestParam String contents, @RequestParam int contentType){
        Topic topic = new Topic();
        topic.setCreatorId(creatorId);
        topic.setContents(contents);
        topic.setContentType(contentType);
        topic.setCreateTime(new Date());
        topic.setLikes(0);
        ResponseUtil responseUtil;
        try{
            topicService.addtopic(topic);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                    ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch (Exception e){
            System.out.println(e);
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(),
                    ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }
}
