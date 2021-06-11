package com.wangxin.langshu.business.edu.dao.user.impl;

import com.wangxin.langshu.business.edu.dao.user.SendSmsLogDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.SendSmsLogMapper;
import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLog;
import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLogExample;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SendSmsLogDaoImpl implements SendSmsLogDao {
    @Autowired
    private SendSmsLogMapper sendSmsLogMapper;

    public int save(SendSmsLog record) {
        record.setId(IdWorker.getId());
        return this.sendSmsLogMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.sendSmsLogMapper.deleteByPrimaryKey(id);
    }

    public int updateById(SendSmsLog record) {
        return this.sendSmsLogMapper.updateByPrimaryKeySelective(record);
    }

    public SendSmsLog getById(String id) {
        return this.sendSmsLogMapper.selectByPrimaryKey(id);
    }

    public Page<SendSmsLog> listForPage(int pageCurrent, int pageSize, SendSmsLogExample example) {
        int count = this.sendSmsLogMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<SendSmsLog>(count, totalPage, pageCurrent, pageSize, this.sendSmsLogMapper.selectByExample(example));
    }
}