package com.example.makefriends.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-25 03:18
 **/


@Setter
@Getter
public class StudyGoalWithUser {

    private Integer id;

    private Integer creatorId;

    private String content;

    private Integer repeatType;

    @JsonFormat(pattern="HH:mm:ss", timezone = "GMT+8")
    private Date repeatTime;

    private String nickname;

    private Integer age;

    private Integer sex;

    private String headpic;

    public StudyGoalWithUser() {}

    public StudyGoalWithUser(Integer id, Integer creatorId, String content, Integer repeatType, Date repeatTime,
                             String nickname, Integer age, Integer sex, String headpic) {
        this.id = id;
        this.creatorId = creatorId;
        this.content = content;
        this.repeatType = repeatType;
        this.repeatTime = repeatTime;
        this.nickname = nickname;
        this.age = age;
        this.sex = sex;
        this.headpic = headpic;
    }
}
