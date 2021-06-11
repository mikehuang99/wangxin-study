package com.wangxin.langshu.business.edu.dao.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.dao.user.StudentDao;
import com.wangxin.langshu.business.edu.dao.user.impl.mapper.StudentMapper;
import com.wangxin.langshu.business.edu.entity.user.old.Student;
import com.wangxin.langshu.business.edu.entity.user.old.StudentExample;
import com.wangxin.langshu.business.edu.entity.user.old.StudentExample.Criteria;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.IdWorker;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(Student record) {
		record.setId(IdWorker.getId());
		return this.studentMapper.insertSelective(record);
	}

	@Override
	public int deleteById(String id) {
		return this.studentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Student record) {
		return this.studentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Student getById(String id) {
		return this.studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Student> listForPage(int pageCurrent, int pageSize, StudentExample example) {
		int count = this.studentMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Student>(count, totalPage, pageCurrent, pageSize, this.studentMapper.selectByExample(example));
	}

	@Override
	public Student getByUserNo(String userNo) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNoEqualTo(userNo);
		List<Student> list = this.studentMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int updateByUserNo(Student record) {
		StudentExample example = new StudentExample();
		example.createCriteria().andUserNoEqualTo(record.getUserNo());
		return this.studentMapper.updateByExampleSelective(record, example);
	}

	@Override
	public Student getByMobile(String mobile) {
		StudentExample example = new StudentExample();
		Criteria c = example.createCriteria();
		c.andMobileEqualTo(mobile);
		List<Student> list = this.studentMapper.selectByExample(example);
		if (list.size() < 0 || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 获取用户注册量
	 * 
	 */
	@Override
	public Integer sumByCountOrders(String date) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) as count from student where");
		builder.append(" valid_value = 1 ");
		builder.append("and create_time >= '").append(date).append(" 00:00:00' ");
		builder.append("and create_time <= '").append(date).append(" 23:59:59'");
		String sql = builder.toString();
		Integer count = 0;
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		if (!StringUtils.isEmpty(map.get("count"))) {
			count = Integer.valueOf(String.valueOf(map.get("count")));
		}
		return count;
	}
}