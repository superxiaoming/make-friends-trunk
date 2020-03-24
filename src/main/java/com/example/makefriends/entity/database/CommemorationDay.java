package com.example.makefriends.entity.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-23 20:14
 **/

@Getter
@Setter
@Entity(name = "commemoration_day")
public class CommemorationDay {

    /** 主键id, GeneratedValue代表主键自动生成，strategy代表生成规则 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer creator_id;

    private String content;

    private Date create_time;

    private Date memory_day;
}
