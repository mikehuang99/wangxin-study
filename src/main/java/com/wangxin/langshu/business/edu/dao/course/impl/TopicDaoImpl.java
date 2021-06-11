package com.wangxin.langshu.business.edu.dao.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.Topic;
import com.wangxin.langshu.business.edu.entity.course.old.TopicExample;
import com.wangxin.langshu.business.edu.dao.course.TopicDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.TopicMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class TopicDaoImpl implements TopicDao {
    @Autowired
    private TopicMapper topicMapper;

    public int save(Topic record) {
        record.setId(IdWorker.getId());
        return this.topicMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.topicMapper.deleteByPrimaryKey(id);
    }

    public int updateById(Topic record) {
        return this.topicMapper.updateByPrimaryKeySelective(record);
    }

    public Topic getById(String id) {
        return this.topicMapper.selectByPrimaryKey(id);
    }

    public Page<Topic> listForPage(int pageCurrent, int pageSize, TopicExample example) {
        int count = this.topicMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Topic>(count, totalPage, pageCurrent, pageSize, this.topicMapper.selectByExample(example));
    }

	@Override
	public List<Topic> listAllTopic() {
		TopicExample example = new TopicExample();
		List<Topic> list= this.topicMapper.selectByExample(example);
		return list;
	}
}