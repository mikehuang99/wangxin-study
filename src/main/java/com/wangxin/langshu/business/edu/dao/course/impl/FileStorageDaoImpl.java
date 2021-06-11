package com.wangxin.langshu.business.edu.dao.course.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.entity.course.old.FileStorage;
import com.wangxin.langshu.business.edu.entity.course.old.FileStorageExample;
import com.wangxin.langshu.business.edu.dao.course.FileStorageDao;
import com.wangxin.langshu.business.edu.dao.course.impl.mapper.FileStorageMapper;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class FileStorageDaoImpl implements FileStorageDao {
    @Autowired
    private FileStorageMapper fileStorageMapper;

    public int save(FileStorage record) {
        record.setId(IdWorker.getId());
        return this.fileStorageMapper.insertSelective(record);
    }

    public int deleteById(String id) {
        return this.fileStorageMapper.deleteByPrimaryKey(id);
    }

    public int updateById(FileStorage record) {
        return this.fileStorageMapper.updateByPrimaryKeySelective(record);
    }

    public FileStorage getById(String id) {
        return this.fileStorageMapper.selectByPrimaryKey(id);
    }

    public Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example) {
        int count = this.fileStorageMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<FileStorage>(count, totalPage, pageCurrent, pageSize, this.fileStorageMapper.selectByExample(example));
    }
}