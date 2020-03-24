package com.example.makefriends.entity.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: makefriends
 * @description: 话题实体类
 * @author: YinShm
 * @date: 2020-03-23 20:03
 **/

@Getter
@Setter
@Entity(name = "topic")
public class Topic {

    /** 主键id, GeneratedValue代表主键自动生成，strategy代表生成规则 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer creatorId;

    private String contents;

    private Integer contentType;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer likes;

    private String picAddress;
}
