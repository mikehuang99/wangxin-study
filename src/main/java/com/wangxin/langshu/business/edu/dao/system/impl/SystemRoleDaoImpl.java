package com.wangxin.langshu.business.edu.dao.system.impl;

import com.wangxin.langshu.business.edu.dao.system.SystemRoleDao;
import com.wangxin.langshu.business.edu.dao.system.impl.mapper.SystemRoleMapper;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRole;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleExample;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SystemRoleDaoImpl implements SystemRoleDao {
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    public int save(SystemRole record) {
        record.setId(IdWorker.getId());
        return this.systemRoleMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.systemRoleMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SystemRole record) {
        return this.systemRoleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByExampleSelective(SystemRole record, SystemRoleExample example) {
        return this.systemRoleMapper.updateByExampleSelective(record, example);
    }

    public SystemRole getById(String id) {
        return this.systemRoleMapper.selectByPrimaryKey(id);
    }

    public Page<SystemRole> listForPage(int pageCurrent, int pageSize, SystemRoleExample example) {
        int count = this.systemRoleMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SystemRole>(count, totalPage, pageCurrent, pageSize, this.systemRoleMapper.selectByExample(example));
    }
}