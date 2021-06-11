package com.wangxin.langshu.business.edu.dao.system.impl;

import com.wangxin.langshu.business.edu.dao.system.FriendLinkDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.FriendLinkMapper;
import com.wangxin.langshu.business.edu.entity.system.old.FriendLink;
import com.wangxin.langshu.business.edu.entity.system.old.FriendLinkExample;
import com.wangxin.langshu.business.edu.entity.system.old.FriendLinkExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendLinkDaoImpl implements FriendLinkDao {
    @Autowired
    private FriendLinkMapper friendLinkMapper;

    public int save(FriendLink record) {
        record.setId(IdWorker.getId());
        return this.friendLinkMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.friendLinkMapper.deleteByPrimaryKey(id);
    }

    public int updateById(FriendLink record) {
        return this.friendLinkMapper.updateByPrimaryKeySelective(record);
    }

    public FriendLink getById(String id) {
        return this.friendLinkMapper.selectByPrimaryKey(id);
    }

    public Page<FriendLink> listForPage(int pageCurrent, int pageSize, FriendLinkExample example) {
        int count = this.friendLinkMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<FriendLink>(count, totalPage, pageCurrent, pageSize, this.friendLinkMapper.selectByExample(example));
    }
    
    @Override
	public List<FriendLink> listByValidValue(Integer validValue) {
		FriendLinkExample example = new FriendLinkExample();
		Criteria c = example.createCriteria();
		c.andValidValueEqualTo(validValue);
		example.setOrderByClause(" sort desc, id desc ");
		return this.friendLinkMapper.selectByExample(example);
	}
    
}