package com.wangxin.langshu.business.edu.service.market.pccommon;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangxin.langshu.business.edu.dao.market.CouponLimitCourseDao;
import com.wangxin.langshu.business.edu.dao.market.impl.mapper.CouponLimitCourseMapper;
import com.wangxin.langshu.business.edu.entity.market.CouponLimitCourse;
import com.wangxin.langshu.business.edu.iservice.market.pccommon.ICouponLimitCourseService;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseSaveVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseViewVo;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;


@Service
public class CouponLimitCourseService  extends ServiceImpl<CouponLimitCourseMapper, CouponLimitCourse> implements ICouponLimitCourseService {

	@Autowired
	public CouponLimitCourseDao couponLimitCourseDao;
	
	@Autowired
    private CouponLimitCourseMapper couponLimitCourseMapper;

	@Override
	public Page<CouponLimitCourseVo> queryCouponLimitCourses(CouponLimitCourseQ q) {
		return couponLimitCourseDao.queryCouponLimitCourses(q);
	}

	@Override
	public boolean saveCouponLimitCourse(CouponLimitCourseSaveVo vo) {
		String id = UUID.randomUUID().toString().replace("-", "");
		CouponLimitCourse couponLimitCourse = new CouponLimitCourse();
		
		BeanUtils.copyProperties(vo, couponLimitCourse);
		couponLimitCourse.setId(id);
		this.save(couponLimitCourse);
		return true;
	}

	@Override
	public boolean updateCouponLimitCourse(CouponLimitCourseUpdateVo vo) {
		CouponLimitCourse couponLimitCourse = new CouponLimitCourse();
		BeanUtils.copyProperties(vo, couponLimitCourse);
		this.updateById(couponLimitCourse);

		return true;
	}

	@Override
	public boolean deleteCouponLimitCourse(CouponLimitCourseDeleteVo vo) {
		if(vo.getIds().length<1){
    		throw new BaseException("删除ID不能为空");
		}
		for(int i=0;i<vo.getIds().length;i++){
			String id = vo.getIds()[i];
			this.removeById(id);
			
		}

		return true;
	}

	@Override
	public CouponLimitCourseViewVo viewCouponLimitCourse(CouponLimitCourseViewVo viewVo) {
		CouponLimitCourse vvvo = this.getById(viewVo.getId());
		BeanUtils.copyProperties(vvvo, viewVo);

		


		return viewVo;
	}
	
	@Override
	public String queryAllCouponLimitCoursesString(String couponId){
		Page<CouponLimitCourseVo> page = couponLimitCourseDao.queryAllCouponLimitCourses(couponId);
		String selectedCourseIdsString = "";
		List<CouponLimitCourseVo> list = page.getList();
		if(list!=null&&list.size()!=0){
			for(int i=0;i<list.size();i++){
				selectedCourseIdsString = selectedCourseIdsString + list.get(i).getCourseId() + ",";
			}
		}
		return selectedCourseIdsString;
	}

	@Override
	public int deleteCouponLimitCoursesByCouponId(String couponId) {
		return couponLimitCourseDao.deleteCouponLimitCoursesByCouponId(couponId);
	}
	
	
	


	
	
}
