package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.Topic;
import com.example.makefriends.entity.response.TopicInfoWithUser;
import com.example.makefriends.service.TopicService;
import com.example.makefriends.utils.FileUtils;
import com.example.makefriends.utils.ResponseCode;
import com.example.makefriends.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/getTopicsByContentType")
    public Object getTopicByContentType(@RequestParam int contentType){
        List<TopicInfoWithUser> topics = topicService.getTopicsByContentType(contentType);
        ResponseUtil responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                ResponseCode.SUCCESS_CODE.getCodeMessage(), topics);
        return responseUtil;
    }

    @RequestMapping(value = "/getTopicsByContentTypeAndCreatorId")
    public Object getTopicsByContentTypeAndCreatorId(@RequestParam int creatorId, @RequestParam int contentType){
        List<TopicInfoWithUser> topics = topicService.getTopicsByContentTypeAndCreatorId(creatorId, contentType);
        ResponseUtil responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                ResponseCode.SUCCESS_CODE.getCodeMessage(), topics);
        return responseUtil;
    }

    @RequestMapping(value = "/addTopic")
    public Object addTopic(HttpServletRequest request, MultipartFile pictures){
        ResponseUtil responseUtil;
        Topic topic = new Topic();

        if(pictures != null){
            // 文件存储并获取新文件名
            String newFileName = FileUtils.upload(pictures, pictures.getOriginalFilename());
            if(newFileName.equals("")){
                responseUtil = new ResponseUtil(ResponseCode.SYSTEM_ERROR.getCodeNumber(), ResponseCode.SYSTEM_ERROR.getCodeMessage());
                return responseUtil;
            }
            // 生成文件访问地址
            String picAddress = FileUtils.getPicAddress(request, newFileName);
            topic.setPicAddress(picAddress);
        }

        int creatorId = Integer.parseInt(request.getParameter("creatorId"));
        String contents = request.getParameter("contents");
        int contentType = Integer.parseInt(request.getParameter("contentType"));

        topic.setCreatorId(creatorId);
        topic.setContents(contents);
        topic.setContentType(contentType);
        topic.setCreateTime(new Date());
        topic.setLikes(0);

        try{
            topicService.addtopic(topic);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                    ResponseCode.SUCCESS_CODE.getCodeMessage());
            return responseUtil;
        } catch (Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(),
                    ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }

    @RequestMapping(value = "/addLikes")
    public Object addLikes(@RequestParam int topicId){
        ResponseUtil responseUtil;
        Topic topic = topicService.getLikes(topicId);
        if(topic == null){
            responseUtil = new ResponseUtil(ResponseCode.TOPIC_NOT_EXIST.getCodeNumber(),
                    ResponseCode.TOPIC_NOT_EXIST.getCodeMessage());
            return responseUtil;
        }
        try{
            int likes = topic.getLikes() + 1;
            topicService.addLikes(topicId, likes);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                    ResponseCode.SUCCESS_CODE.getCodeMessage(), likes);
            return responseUtil;
        }catch (Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(),
                    ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }
}
