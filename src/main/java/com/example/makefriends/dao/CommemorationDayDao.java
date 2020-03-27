package com.example.makefriends.dao;

import com.example.makefriends.entity.database.CommemorationDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-24 22:17
 **/

public interface CommemorationDayDao extends JpaRepository<CommemorationDay, Integer> {

    List<CommemorationDay> getCommemorationDaysByCreatorIdOrderByCreateTimeDesc(int creatorId);
}
