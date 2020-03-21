package com.example.makefriends.entity.database;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * @program: visualization
 * @description: 用户实体类
 * @author: YinShm
 * @date: 2019-12-10 02:48
 **/

@Getter
@Setter
@Entity(name = "sys_user")
public class User {
    /** 主键id, GeneratedValue代表主键自动生成，strategy代表生成规则 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 用户名（默认情况下，生成的表中字段的名称就是属性的名称，可以通过Column注解定制生成的表字段的属性 */
    private String username;

    /** 用户密码 */
    private String password;

    /** 学校 */
    private String school;

    /** 学院 */
    private String college;

    /** 专业 */
    private String major;

    /** 标签 */
    private String tags;

    /** 姓名 */
    private String nickname;

    /** 签名 */
    private String sign;

    /** 头像地址 */
    private String headpic;

    /** 年龄 */
    private Integer age;
}
