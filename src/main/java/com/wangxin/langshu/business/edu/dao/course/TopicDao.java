package com.wangxin.langshu.business.edu.dao.course;

import java.util.List;

import com.wangxin.langshu.business.edu.entity.course.old.Topic;
import com.wangxin.langshu.business.edu.entity.course.old.TopicExample;
import com.wangxin.langshu.business.util.base.Page;

public interface TopicDao {
    int save(Topic record);

    int deleteById(String id);

    int updateById(Topic record);

    Topic getById(String id);

    Page<Topic> listForPage(int pageCurrent, int pageSize, TopicExample example);

	List<Topic> listAllTopic();
}