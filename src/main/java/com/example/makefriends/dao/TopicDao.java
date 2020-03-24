package com.example.makefriends.dao;

import com.example.makefriends.entity.database.Topic;
import com.example.makefriends.entity.response.TopicInfoWithUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
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
    @Query(value = "select new com.example.makefriends.entity.response.TopicInfoWithUser(t1.id, t1.creatorId, " +
            "t1.contents, t1.contentType, t1.likes, t1.picAddress, t2.nickname, t2.age, t2.sex, t2.headpic) " +
            "from topic as t1, sys_user as t2 where t1.creatorId = t2.id and t1.contentType = :contentType")
    List<TopicInfoWithUser> getTopicsByContentType(int contentType);

    /**
     * @description: 根据用户和话题类型查找话题
     * @Author: yinshm
     * @Date: 20:28 2020-03-23
     */
    @Query(value = "select new com.example.makefriends.entity.response.TopicInfoWithUser(t1.id, t1.creatorId, " +
            "t1.contents, t1.contentType, t1.likes, t1.picAddress, t2.nickname, t2.age, t2.sex, t2.headpic) " +
            "from topic as t1, sys_user as t2 where t1.creatorId = t2.id and t1.contentType = :contentType and t1.creatorId " +
            "= :creatorId")
    List<TopicInfoWithUser> findTopicsByCreatorIdAndContentType(int creatorId, int contentType);

    Topic findTopicById(int topicId);

    @Modifying
    @Transactional
    @Query("update topic set likes = :likes where id = :topicId")
    void addLikes(@Param("topicId")int topicId, @Param("likes") int likes);
}
