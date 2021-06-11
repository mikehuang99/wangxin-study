package com.wangxin.langshu.business.edu.dao.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseQ;
import com.wangxin.langshu.business.edu.vo.market.couponLimitCourse.CouponLimitCourseVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class CouponLimitCourseDao extends BaseDao {
	
	public Page<CouponLimitCourseVo> queryCouponLimitCourses(CouponLimitCourseQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from coupon_limit_course q ");
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and q.course_id = :course_id ");
    		paramMap.put("course_id", q.getCourseId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponId())){
    		sb.append(" and q.coupon_id = :coupon_id ");
    		paramMap.put("coupon_id", q.getCouponId());
    	}
        
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<CouponLimitCourseVo> list = new ArrayList<CouponLimitCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CouponLimitCourseVo vo = new CouponLimitCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CouponLimitCourseVo> pg = new Page<CouponLimitCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public Page<CouponLimitCourseVo> queryAllCouponLimitCourses(String couponId) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from coupon_limit_course q ");
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(couponId)){
    		sb.append(" and q.coupon_id = :coupon_id ");
    		paramMap.put("coupon_id", couponId);
    	}
        
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, -1, -1, null);
        
        List<CouponLimitCourseVo> list = new ArrayList<CouponLimitCourseVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CouponLimitCourseVo vo = new CouponLimitCourseVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CouponLimitCourseVo> pg = new Page<CouponLimitCourseVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public int deleteCouponLimitCoursesByCouponId(String couponId){
		if(StringUtils.isEmpty(couponId)){
			return 0;
		}
		MapSqlParameterSource parameters = new MapSqlParameterSource(); 
		StringBuffer sb = new StringBuffer();
		sb.append("delete from coupon_limit_course  where coupon_id = :coupon_id");
		parameters.addValue("coupon_id", couponId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);  
		int count = namedParameterJdbcTemplate.update(sb.toString(),parameters);
		return count;
	}
	
	
	   

}
