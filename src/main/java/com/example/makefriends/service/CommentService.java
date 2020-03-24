package com.example.makefriends.service;

import com.example.makefriends.dao.CommentDao;
import com.example.makefriends.entity.database.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 18:34
 **/

@Service
public class CommentService {

    @Autowired
    CommentDao commentDao;

    public List<Comment> getCommentsByTopicId(int topicId){
        return commentDao.getCommentsByTopicId(topicId);
    }

    public void addComment(Comment comment){
        commentDao.save(comment);
    }
}
