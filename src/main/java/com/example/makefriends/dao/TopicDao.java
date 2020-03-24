package com.example.makefriends.dao;

import com.example.makefriends.entity.database.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-23 20:25
 **/

public interface TopicDao extends JpaRepository<Topic, Integer> {

    /**
     * @description: 根据话题类型查找话题
     * @Author: yinshm
     * @Date: 20:27 2020-03-23
     */
    List<Topic> findTopicsByContentType(int contentType);

    /**
     * @description: 根据用户
     * @Author: yinshm
     * @Date: 20:28 2020-03-23
     */
    List<Topic> findTopicsByCreatorIdAndContentType(int creatorId, int contentType);
}
