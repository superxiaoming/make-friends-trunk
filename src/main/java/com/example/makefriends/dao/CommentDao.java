package com.example.makefriends.dao;

import com.example.makefriends.entity.database.Comment;
import com.example.makefriends.entity.response.CommentsForTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 18:30
 **/

public interface CommentDao extends JpaRepository<Comment, Integer> {

    @Query(value = "select new com.example.makefriends.entity.response.CommentsForTopic(t1.id, " +
            "t1.comment, t1.commentatorId, t2.headpic, t2.nickname) " +
            "from comment as t1, sys_user as t2 where t1.topicId = :topicId and " +
            "t1.commentatorId = t2.id")
    List<CommentsForTopic> getCommentsByTopicId(int topicId);
}
