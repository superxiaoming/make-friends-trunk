package com.example.makefriends.entity.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-25 01:25
 **/

@Setter
@Getter
public class TopicInfoWithUser {

    private Integer id;

    private Integer creatorId;

    private String contents;

    private Integer contentType;

    private Integer likes;

    private String picAddress;

    private String nickname;

    private Integer age;

    private Integer sex;

    private String headpic;

    public TopicInfoWithUser(){}

    public TopicInfoWithUser(Integer id, Integer creatorId, String contents, Integer contentType,
                             Integer likes, String picAddress, String nickname, Integer age, Integer sex, String headpic) {
        this.id = id;
        this.creatorId = creatorId;
        this.contents = contents;
        this.contentType = contentType;
        this.likes = likes;
        this.picAddress = picAddress;
        this.nickname = nickname;
        this.age = age;
        this.sex = sex;
        this.headpic = headpic;
    }
}
