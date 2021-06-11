package com.wangxin.langshu.business.edu.dao.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.edu.dao.user.TeacherDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.TeacherMapper;
import com.wangxin.langshu.business.edu.entity.user.old.Teacher;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherExample;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;

@Repository
public class TeacherDaoImpl implements TeacherDao {
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public int save(Teacher record) {
		return this.teacherMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.teacherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Teacher record) {
		record.setCreateTime(null);
		record.setModifiedTime(null);
		return this.teacherMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Teacher getById(String id) {
		return this.teacherMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Teacher> listForPage(int pageCurrent, int pageSize, TeacherExample example) {
		int count = this.teacherMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Teacher>(count, totalPage, pageCurrent, pageSize, this.teacherMapper.selectByExample(example));
	}

	@Override
	public Teacher getByTeacherUserNo(String teacherUserNo) {
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andTeacherUserNoEqualTo(teacherUserNo);
		List<Teacher> resultList = this.teacherMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public List<Teacher> listByValidValue(Integer validValue) {
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andValidValueEqualTo(validValue);
		return this.teacherMapper.selectByExample(example);
	}

	@Override
	public List<Teacher> getByTeacherName(String TeacherName) {
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andTeacherNameLike(PageUtil.rightLike(TeacherName));
		List<Teacher> resultList = this.teacherMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList;
	}

	@Override
	public Teacher getByTeacherUserNoAndValidValue(String teacherUserNo, Integer validValue) {
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andTeacherUserNoEqualTo(teacherUserNo);
		criteria.andValidValueEqualTo(validValue);
		List<Teacher> resultList = this.teacherMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}
}