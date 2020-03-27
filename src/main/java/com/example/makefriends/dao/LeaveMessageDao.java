package com.example.makefriends.dao;

import com.example.makefriends.entity.database.LeaveMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 15:01
 **/

public interface LeaveMessageDao extends JpaRepository<LeaveMessage, Integer> {

    /**
     * @description: 获取为某人创建的留言
     * @Author: yinshm
     * @Date: 22:17 2020-03-24
     */
    List<LeaveMessage> getLeaveMessagesByCreateForOrderByCreateTimeDesc(int createFor);

    int countByCreateFor(int createFor);
}
