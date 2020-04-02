package com.example.makefriends.entity.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-30 16:47
 **/

@Setter
@Getter
public class BeWatchedUserInfo {
    private Integer id;

    private Integer followerId;

    private Integer bewatchedId;

    private String school;

    private String college;

    private String major;

    private String tags;

    private String nickname;

    private String sign;

    private String headpic;

    private Integer age;

    private Integer sex;

    public BeWatchedUserInfo() {
    }

    public BeWatchedUserInfo(Integer id, Integer followerId, Integer bewatchedId, String school, String college, String major, String tags, String nickname, String sign, String headpic, Integer age, Integer sex) {
        this.id = id;
        this.followerId = followerId;
        this.bewatchedId = bewatchedId;
        this.school = school;
        this.college = college;
        this.major = major;
        this.tags = tags;
        this.nickname = nickname;
        this.sign = sign;
        this.headpic = headpic;
        this.age = age;
        this.sex = sex;
    }
}
