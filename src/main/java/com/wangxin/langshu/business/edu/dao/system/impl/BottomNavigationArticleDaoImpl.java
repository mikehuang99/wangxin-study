package com.wangxin.langshu.business.edu.dao.system.impl;

import com.wangxin.langshu.business.edu.dao.system.BottomNavigationArticleDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.BottomNavigationArticleMapper;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticle;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticleExample;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticleExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BottomNavigationArticleDaoImpl implements BottomNavigationArticleDao {
    @Autowired
    private BottomNavigationArticleMapper bottomNavigationArticleMapper;

    public int save(BottomNavigationArticle record) {
        record.setId(IdWorker.getId());
        return this.bottomNavigationArticleMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.bottomNavigationArticleMapper.deleteByPrimaryKey(id);
    }

    public int updateById(BottomNavigationArticle record) {
        return this.bottomNavigationArticleMapper.updateByPrimaryKeySelective(record);
    }

    public BottomNavigationArticle getById(String id) {
        return this.bottomNavigationArticleMapper.selectByPrimaryKey(id);
    }

    public Page<BottomNavigationArticle> listForPage(int pageCurrent, int pageSize, BottomNavigationArticleExample example) {
        int count = this.bottomNavigationArticleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<BottomNavigationArticle>(count, totalPage, pageCurrent, pageSize, this.bottomNavigationArticleMapper.selectByExample(example));
    }
    
    @Override
	public BottomNavigationArticle getByNavigationId(String navigationId) {
		BottomNavigationArticleExample example = new BottomNavigationArticleExample();
		example.createCriteria().andNavigationIdEqualTo(navigationId);
		List<BottomNavigationArticle> list = this.bottomNavigationArticleMapper.selectByExampleWithBLOBs(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
    
    @Override
	public BottomNavigationArticle getByNavigationIdAndValidValue(String navigationId, Integer validValue) {
		BottomNavigationArticleExample example = new BottomNavigationArticleExample();
		Criteria C = example.createCriteria();
		C.andNavigationIdEqualTo(navigationId);
		C.andValidValueEqualTo(validValue);
		List<BottomNavigationArticle> list = this.bottomNavigationArticleMapper.selectByExampleWithBLOBs(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}