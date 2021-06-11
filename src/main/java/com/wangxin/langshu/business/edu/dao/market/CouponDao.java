package com.wangxin.langshu.business.edu.dao.market;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class CouponDao extends BaseDao {
	
	public Page<CouponVo> queryCoupons(CouponQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from coupon q  where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCouponName())){
    		sb.append(" and INSTR(q.coupon_name, :coupon_name)>0 ");
    	    paramMap.put("coupon_name", q.getCouponName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponCode())){
    		sb.append(" and INSTR(q.coupon_code, :coupon_code)>0 ");
    	    paramMap.put("coupon_code", q.getCouponCode());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginBegin())){
    		sb.append(" and q.coupon_time_begin >= :coupon_time_begin_begin ");
    		paramMap.put("coupon_time_begin_begin", q.getCouponTimeBeginBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginEnd())){
    		sb.append(" and q.coupon_time_begin <= :coupon_time_begin_end ");
    		paramMap.put("coupon_time_begin_end", q.getCouponTimeBeginEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeEndBegin())){
    		sb.append(" and q.coupon_time_end >= :coupon_time_end_begin ");
    		paramMap.put("coupon_time_end_begin", q.getCouponTimeEndBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginEnd())){
    		sb.append(" and q.coupon_time_end <= :coupon_time_end_end ");
    		paramMap.put("coupon_time_end_end", q.getCouponTimeEndEnd());
    	}
    	
    	
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeBeginBegin())){
    		sb.append(" and q.coupon_assign_time_begin >= :coupon_assign_time_begin_begin ");
    		paramMap.put("coupon_assign_time_begin_begin", q.getCouponAssignTimeBeginBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeBeginEnd())){
    		sb.append(" and q.coupon_assign_time_begin <= :coupon_assign_time_begin_end ");
    		paramMap.put("coupon_assign_time_begin_end", q.getCouponAssignTimeBeginEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeEndBegin())){
    		sb.append(" and q.coupon_assign_time_end >= :coupon_assign_time_end_begin ");
    		paramMap.put("coupon_assign_time_end_begin", q.getCouponAssignTimeEndBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeBeginEnd())){
    		sb.append(" and q.coupon_assign_time_end <= :coupon_assign_time_end_end ");
    		paramMap.put("coupon_assign_time_end_end", q.getCouponAssignTimeEndEnd());
    	}
       
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}
        
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<CouponVo> list = new ArrayList<CouponVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CouponVo vo = new CouponVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CouponVo> pg = new Page<CouponVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	public Page<CouponVo> queryPcWebCoupons(CouponQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from coupon q  where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCouponName())){
    		sb.append(" and INSTR(q.coupon_name, :coupon_name)>0 ");
    	    paramMap.put("coupon_name", q.getCouponName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponCode())){
    		sb.append(" and INSTR(q.coupon_code, :coupon_code)>0 ");
    	    paramMap.put("coupon_code", q.getCouponCode());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginBegin())){
    		sb.append(" and q.coupon_time_begin >= :coupon_time_begin_begin ");
    		paramMap.put("coupon_time_begin_begin", q.getCouponTimeBeginBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginEnd())){
    		sb.append(" and q.coupon_time_begin <= :coupon_time_begin_end ");
    		paramMap.put("coupon_time_begin_end", q.getCouponTimeBeginEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeEndBegin())){
    		sb.append(" and q.coupon_time_end >= :coupon_time_end_begin ");
    		paramMap.put("coupon_time_end_begin", q.getCouponTimeEndBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginEnd())){
    		sb.append(" and q.coupon_time_end <= :coupon_time_end_end ");
    		paramMap.put("coupon_time_end_end", q.getCouponTimeEndEnd());
    	}
    	
    	
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeBeginBegin())){
    		sb.append(" and q.coupon_assign_time_begin >= :coupon_assign_time_begin_begin ");
    		paramMap.put("coupon_assign_time_begin_begin", q.getCouponAssignTimeBeginBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeBeginEnd())){
    		sb.append(" and q.coupon_assign_time_begin <= :coupon_assign_time_begin_end ");
    		paramMap.put("coupon_assign_time_begin_end", q.getCouponAssignTimeBeginEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeEndBegin())){
    		sb.append(" and q.coupon_assign_time_end >= :coupon_assign_time_end_begin ");
    		paramMap.put("coupon_assign_time_end_begin", q.getCouponAssignTimeEndBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponAssignTimeEndEnd())){
    		sb.append(" and q.coupon_assign_time_end <= :coupon_assign_time_end_end ");
    		paramMap.put("coupon_assign_time_end_end", q.getCouponAssignTimeEndEnd());
    	}
    	
    	
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}
       
        
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<CouponVo> list = new ArrayList<CouponVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CouponVo vo = new CouponVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CouponVo> pg = new Page<CouponVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	   

}
