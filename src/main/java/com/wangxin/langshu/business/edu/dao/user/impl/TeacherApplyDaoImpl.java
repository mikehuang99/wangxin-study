package com.wangxin.langshu.business.edu.dao.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.user.TeacherApplyDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.TeacherApplyMapper;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherApply;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherApplyExample;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class TeacherApplyDaoImpl implements TeacherApplyDao {
	@Autowired
	private TeacherApplyMapper teacherApplyMapper;

	@Override
	public int save(TeacherApply record) {
		record.setId(IdWorker.getId());
		return this.teacherApplyMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.teacherApplyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(TeacherApply record) {
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.teacherApplyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TeacherApply getById(String id) {
		return this.teacherApplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<TeacherApply> listForPage(int pageCurrent, int pageSize, TeacherApplyExample example) {
		int count = this.teacherApplyMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<TeacherApply>(count, totalPage, pageCurrent, pageSize, this.teacherApplyMapper.selectByExample(example));
	}

	@Override
	public TeacherApply getByTeacherUserNo(String teacherUserNo) {
		TeacherApplyExample example = new TeacherApplyExample();
		example.createCriteria().andTeacherUserNoEqualTo(teacherUserNo);
		List<TeacherApply> list = this.teacherApplyMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}