package com.example.makefriends.dao;

import com.example.makefriends.entity.database.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 18:30
 **/

public interface CommentDao extends JpaRepository<Comment, Integer> {

    List<Comment> getCommentsByTopicId(int topicId);
}
