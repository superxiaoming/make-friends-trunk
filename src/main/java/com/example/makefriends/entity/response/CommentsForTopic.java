package com.example.makefriends.entity.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-25 00:14
 **/

@Setter
@Getter
public class CommentsForTopic {

    private int id;

    private String comment;

    private int commentatorId;

    private String headpic;

    private String nickname;

    public CommentsForTopic(){}

    public CommentsForTopic(int id, String comment, int commentatorId, String headpic, String nickname){
        this.id = id;
        this.comment = comment;
        this.commentatorId = commentatorId;
        this.headpic = headpic;
        this.nickname = nickname;
    }
}
