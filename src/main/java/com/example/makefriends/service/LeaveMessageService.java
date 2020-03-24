package com.example.makefriends.service;

import com.example.makefriends.dao.LeaveMessageDao;
import com.example.makefriends.entity.database.LeaveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 21:52
 **/

@Service
public class LeaveMessageService {
    
    @Autowired
    LeaveMessageDao leaveMessageDao;
    
    /**
     * @description: 获取为某人创建的留言
     * @Author: yinshm
     * @Date: 21:54 2020-03-24
     */
    public List<LeaveMessage> getLeaveMessagesByCreateFor(int createFor){
        return leaveMessageDao.getLeaveMessagesByCreateFor(createFor);
    }

    public void saveLeaveMessage(LeaveMessage leaveMessage){
        leaveMessageDao.save(leaveMessage);
    }
}
