package com.example.makefriends.service;

import com.example.makefriends.dao.TopicDao;
import com.example.makefriends.entity.database.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: makefriends
 * @description:
 * @author: YinShm
 * @date: 2020-03-23 20:30
 **/

@Service
public class TopicService {
    
    @Autowired
    TopicDao topicDao;
    
    /**
     * @description: 根据话题类型获取话题
     * @Author: yinshm
     * @Date: 20:33 2020-03-23
     */
    public List<Topic> getTopicsByContentType(int contentType){
        return topicDao.findTopicsByContentType(contentType);
    }

    /**
     * @description: 根据话题类型和创建者获取话题
     * @Author: yinshm
     * @Date: 20:34 2020-03-23
     */
    public List<Topic> getTopicsByContentTypeAndCreatorId(int creatorId, int contentType){
        return topicDao.findTopicsByCreatorIdAndContentType(creatorId, contentType);
    }
    
    /**
     * @description: 新增topic
     * @Author: yinshm
     * @Date: 20:44 2020-03-23
     */
    public void addtopic(Topic topic){ topicDao.save(topic); }
}