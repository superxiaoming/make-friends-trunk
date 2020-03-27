package com.example.makefriends.service;

import com.example.makefriends.dao.CommemorationDayDao;
import com.example.makefriends.entity.database.CommemorationDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 22:18
 **/

@Service
public class CommemorationDayService {

    @Autowired
    CommemorationDayDao commemorationDayDao;

    public List<CommemorationDay> getCommemorationDaysByCreatorId(int cretorId){
        return commemorationDayDao.getCommemorationDaysByCreatorIdOrderByCreateTimeDesc(cretorId);
    }

    public void addCommemorationDay(CommemorationDay commemorationDay){
        commemorationDayDao.save(commemorationDay);
    }
}
