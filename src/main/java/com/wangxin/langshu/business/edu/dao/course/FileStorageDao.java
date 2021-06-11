package com.wangxin.langshu.business.edu.dao.course;

import com.wangxin.langshu.business.edu.entity.course.old.FileStorage;
import com.wangxin.langshu.business.edu.entity.course.old.FileStorageExample;
import com.wangxin.langshu.business.util.base.Page;

public interface FileStorageDao {
    int save(FileStorage record);

    int deleteById(String id);

    int updateById(FileStorage record);

    FileStorage getById(String id);

    Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example);
}