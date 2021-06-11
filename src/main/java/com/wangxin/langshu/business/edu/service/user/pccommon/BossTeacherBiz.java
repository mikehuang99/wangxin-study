package com.wangxin.langshu.business.edu.service.user.pccommon;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wangxin.langshu.business.edu.dao.user.TeacherApplyDao;
import com.wangxin.langshu.business.edu.dao.user.TeacherDao;
import com.wangxin.langshu.business.edu.entity.user.old.Teacher;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherApply;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherExample;
import com.wangxin.langshu.business.edu.entity.user.old.TeacherExample.Criteria;
import com.wangxin.langshu.business.edu.iservice.user.pccommon.IBossTeacherBiz;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo.TeacherQO;
import com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo.TeacherVO;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.PageUtil;
import com.wangxin.langshu.business.util.enums.ValidValueEnum;
import com.wangxin.langshu.business.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 教师信息
 *
 *  
 */
@Component
public class BossTeacherBiz implements IBossTeacherBiz{

	@Autowired
	private TeacherDao dao;
	@Autowired
	private TeacherApplyDao teacherApplyDao;


	public Page<TeacherVO> listForPage(TeacherQO qo) {
		TeacherExample example = new TeacherExample();
		Criteria c = example.createCriteria();
		Page<Teacher> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, TeacherVO.class);
	}

	public int save(TeacherQO qo) {
		Teacher record = BeanUtil.copyProperties(qo, Teacher.class);
		return dao.save(record);
	}

	public int deleteById(String id) {
		return dao.deleteById(id);
	}

	public TeacherVO getById(String id) {
		Teacher record = dao.getById(id);
		TeacherVO vo = BeanUtil.copyProperties(record, TeacherVO.class);
		// 扩展信息
		/*
		TeacherExt teacherExt = teacherExtDao.getByTeacherUserNo(vo.getTeacherUserNo());
		vo.setTeacherExtVO(BeanUtil.copyProperties(teacherExt, TeacherExtVO.class));
		*/
		return vo;
	}

	@Transactional
	public int updateById(TeacherQO qo) {
		Teacher record = BeanUtil.copyProperties(qo, Teacher.class);
		
		int teacherNum = dao.updateById(record);
		if (teacherNum < 1) {
			throw new BaseException("教师信息表更新失败");
		}
		return teacherApplyDao.updateById(BeanUtil.copyProperties(record, TeacherApply.class));
	}

	/**
	 * 根据教师用户编号查找教师信息
	 * 
	 * @param teacherUserNo
	 * @return
	 */
	public TeacherVO getByTeacherUserNo(String teacherUserNo) {
		if (teacherUserNo == null) {
			throw new BaseException("传入的教师用户编号不能为空");
		}
		Teacher record = dao.getByTeacherUserNo(teacherUserNo);
		if (ObjectUtil.isNull(record)) {
			throw new BaseException("找不到教师信息");
		}
		TeacherVO vo = BeanUtil.copyProperties(record, TeacherVO.class);
		/*
		TeacherExt teacherExt = teacherExtDao.getByTeacherUserNo(record.getTeacherUserNo());
		TeacherExtVO teacherExtVO = BeanUtil.copyProperties(teacherExt, TeacherExtVO.class);
		vo.setTeacherExtVO(teacherExtVO);
		*/
		return vo;
	}

	/**
	 * 列出所有教师信息
	 * 
	 * 
	 */
	public List<TeacherVO> listAllForTeacher() {
		List<Teacher> teacherList = dao.listByValidValue(ValidValueEnum.YES.getCode());
		return PageUtil.copyList(teacherList, TeacherVO.class);
	}
}
