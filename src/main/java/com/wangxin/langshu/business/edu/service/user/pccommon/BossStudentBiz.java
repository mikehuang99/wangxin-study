package com.wangxin.langshu.business.edu.service.user.pccommon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wangxin.langshu.business.edu.dao.user.StudentDao;
import com.wangxin.langshu.business.edu.dao.user.UserDao;
import com.wangxin.langshu.business.edu.entity.user.old.Student;
import com.wangxin.langshu.business.edu.entity.user.old.StudentExample;
import com.wangxin.langshu.business.edu.entity.user.old.StudentExample.Criteria;
import com.wangxin.langshu.business.edu.vo.user.old.common.CacheRedis;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo.StudentEchartsQO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo.StudentQO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.UserEchartsVO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.StudentVO;
import com.wangxin.langshu.business.util.base.BaseBiz;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.wangxin.langshu.business.util.tools.DateUtil;

/**
 * 用户教育信息
 *
 *  
 */
@Component
public class BossStudentBiz extends BaseBiz   {

	@Autowired
	private StudentDao dao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private CacheRedis cacheRedis;

	public Page<StudentVO> listForPage(StudentQO qo) {
		StudentExample example = new StudentExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(qo.getMobile())) {
			c.andMobileLike(PageUtil.rightLike(qo.getMobile()));
		}
		if (!StringUtils.isEmpty(qo.getUserNo())) {
			c.andUserNoEqualTo(qo.getUserNo());
		}
		if (StringUtils.hasText(qo.getBeginCreateTime())) {
			c.andCreateTimeGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginCreateTime(), "yyyy-MM-dd"));
		}
		if (StringUtils.hasText(qo.getEndCreateTime())) {
			c.andCreateTimeLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndCreateTime(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" valid_value desc, id desc ");
		Page<Student> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, StudentVO.class);
	}

	public int save(StudentQO qo) {
		Student record = BeanUtil.copyProperties(qo, Student.class);
		return dao.save(record);
	}

	public int deleteById(String id) {
		return dao.deleteById(id);
	}

	public StudentVO getById(String id) {
		Student record = dao.getById(id);
		return BeanUtil.copyProperties(record, StudentVO.class);
	}

	public int updateById(StudentQO qo) {
		Student record = BeanUtil.copyProperties(qo, Student.class);
		return dao.updateById(record);
	}

	public StudentVO getByUserNo(String userNo) {
		Student record = dao.getByUserNo(userNo);
		return BeanUtil.copyProperties(record, StudentVO.class);
	}

	/**
	 * 获取用户注册量
	 * 
	 * @param studentEchartsQO
	 * @return
	 * 
	 */
	public List<UserEchartsVO> sumByCounts(StudentEchartsQO studentEchartsQO) {
		List<UserEchartsVO> list = new ArrayList<>();
		List<Integer> countOrders = new ArrayList<>();
		UserEchartsVO vo = new UserEchartsVO();
		for (String date : studentEchartsQO.getDateList()) {
			Integer sum = dao.sumByCountOrders(date);
			countOrders.add(sum);
		}
		vo.setCount(countOrders);
		list.add(vo);
		return list;
	}


}
