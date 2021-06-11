package com.wangxin.langshu.business.edu.dao.system.impl;

import com.wangxin.langshu.business.edu.dao.system.BottomNavigationDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.BottomNavigationMapper;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigation;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationExample;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BottomNavigationDaoImpl implements BottomNavigationDao {
    @Autowired
    private BottomNavigationMapper bottomNavigationMapper;

    public int save(BottomNavigation record) {
        record.setId(IdWorker.getId());
        return this.bottomNavigationMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.bottomNavigationMapper.deleteByPrimaryKey(id);
    }

    public int updateById(BottomNavigation record) {
        return this.bottomNavigationMapper.updateByPrimaryKeySelective(record);
    }

    public BottomNavigation getById(String id) {
        return this.bottomNavigationMapper.selectByPrimaryKey(id);
    }

    public Page<BottomNavigation> listForPage(int pageCurrent, int pageSize, BottomNavigationExample example) {
        int count = this.bottomNavigationMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<BottomNavigation>(count, totalPage, pageCurrent, pageSize, this.bottomNavigationMapper.selectByExample(example));
    }
    
    @Override
	public List<BottomNavigation> listByParentId(String parentId) {
		BottomNavigationExample example = new BottomNavigationExample();
		Criteria C = example.createCriteria();
		C.andParentIdEqualTo(parentId);
		return this.bottomNavigationMapper.selectByExample(example);
	}
    
    @Override
	public List<BottomNavigation> listByValidValueAndParentId(Integer validValue, String parentId) {
		BottomNavigationExample example = new BottomNavigationExample();
		Criteria C = example.createCriteria();
		C.andValidValueEqualTo(validValue);
		C.andParentIdEqualTo(parentId);
		example.setOrderByClause("valid_value desc, sort desc, id desc");
		return this.bottomNavigationMapper.selectByExample(example);
	}

}