package com.wangxin.langshu.business.edu.service.course.pccommon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangxin.langshu.business.edu.entity.course.old.FileStorage;
import com.wangxin.langshu.business.edu.entity.course.old.FileStorageExample;
import com.wangxin.langshu.business.edu.dao.course.FileStorageDao;
import com.wangxin.langshu.business.edu.iservice.course.pccommon.IFileStorageBiz;
import com.wangxin.langshu.business.util.base.Page;

@Service
public class FileStorageBiz implements IFileStorageBiz{

	@Autowired
	private FileStorageDao fileStorageDao;
	
	@Override
	public int save(FileStorage record) {
		return fileStorageDao.save(record);
	}

	@Override
	public int deleteById(String id) {
		return fileStorageDao.deleteById(id);
	}

	@Override
	public int updateById(FileStorage record) {
		return fileStorageDao.updateById(record);
	}

	@Override
	public FileStorage getById(String id) {
		return fileStorageDao.getById(id);
	}

	@Override
	public Page<FileStorage> listForPage(int pageCurrent, int pageSize, FileStorageExample example) {
		return fileStorageDao.listForPage(pageCurrent, pageSize, example);
	}

}
