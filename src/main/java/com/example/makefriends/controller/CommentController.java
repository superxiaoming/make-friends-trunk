package com.example.makefriends.controller;

import com.example.makefriends.annotation.PassToken;
import com.example.makefriends.entity.database.Comment;
import com.example.makefriends.entity.response.CommentsForTopic;
import com.example.makefriends.service.CommentService;
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
 * @date: 2020-03-24 18:35
 **/

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PassToken
    @RequestMapping(value = "/getCommentsByTopicId")
    public Object getCommentsByTopicId(@RequestParam int topicId){
        ResponseUtil responseUtil;
        try{
            List<CommentsForTopic> comments = commentService.getCommentsByTopicId(topicId);
            responseUtil = new ResponseUtil(ResponseCode.SUCCESS_CODE.getCodeNumber(),
                    ResponseCode.SUCCESS_CODE.getCodeMessage(), comments);
            return responseUtil;
        } catch (Exception e){
            e.printStackTrace();
            responseUtil = new ResponseUtil(ResponseCode.FAILED_CODE.getCodeNumber(),
                    ResponseCode.FAILED_CODE.getCodeMessage());
            return responseUtil;
        }
    }

    @PassToken
    @RequestMapping(value = "/addComment")
    public Object addComment(@RequestParam int topicId, @RequestParam int commentatorId, @RequestParam String comment){
        ResponseUtil responseUtil;
        Comment comment1 = new Comment();
        comment1.setTopicId(topicId);
        comment1.setCommentatorId(commentatorId);
        comment1.setComment(comment);
        comment1.setCreateTime(new Date());
        try{
            commentService.addComment(comment1);
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
}
