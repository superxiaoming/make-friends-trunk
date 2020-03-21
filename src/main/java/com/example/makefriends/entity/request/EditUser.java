package com.example.makefriends.entity.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-19 00:00
 **/

@Setter
@Getter
public class EditUser {

    private String school;
    private String college;
    private String major;
    private String tags;
    private String nickname;
    private String sign;
    private MultipartFile headPic;
    private int age;

    @Override
    public String toString(){
        return "EditUser is {" +
                " school = " + school +
                ", college = " + college +
                ", majot = " + major +
                ", tags = " + tags +
                ", nickname = " + nickname +
                ", sign = " + sign +
                ", age = " + age +
                "}";
    }
}
