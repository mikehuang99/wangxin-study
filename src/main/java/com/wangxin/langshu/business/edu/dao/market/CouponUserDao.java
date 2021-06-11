package com.wangxin.langshu.business.edu.dao.market;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserQ;
import com.wangxin.langshu.business.edu.vo.market.couponUser.CouponUserVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class CouponUserDao extends BaseDao {
	
	public Page<CouponUserVo> queryCouponUsers(CouponUserQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from coupon_user q  where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCouponId())){
    		sb.append(" and q.coupon_id = :coupon_id  ");
    	    paramMap.put("coupon_id", q.getCouponId());
    	}
        
    	if(StringUtils.isNotEmpty(q.getBindUserNo())){
    		sb.append(" and q.bind_user_no = :bind_user_no  ");
    	    paramMap.put("bind_user_no", q.getBindUserNo());
    	}
    	
      
        
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<CouponUserVo> list = new ArrayList<CouponUserVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CouponUserVo vo = new CouponUserVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CouponUserVo> pg = new Page<CouponUserVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public Page<CouponUserVo> queryCouponUserDetails(CouponUserQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        

        
        
        sb.append("select q.id id,q.coupon_id coupon_id,q.bind_user_no bind_user_no,q.is_used, ");
        sb.append(" c.valid_value valid_value,c.coupon_code,c.coupon_name,c.coupon_amount,c.is_limit_course, ");
        sb.append(" c.coupon_time_begin,c.coupon_time_end,c.coupon_assign_time_begin,c.coupon_assign_time_end,c.coupon_desc ");
        sb.append(" from coupon_user q  left join coupon c on q.coupon_id = c.id where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCouponId())){
    		sb.append(" and q.coupon_id = :coupon_id  ");
    	    paramMap.put("coupon_id", q.getCouponId());
    	}
        
    	if(StringUtils.isNotEmpty(q.getBindUserNo())){
    		sb.append(" and q.bind_user_no = :bind_user_no  ");
    	    paramMap.put("bind_user_no", q.getBindUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getIsUsed())){
    		sb.append(" and q.is_used = :is_used  ");
    	    paramMap.put("is_used", q.getIsUsed());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginBegin())){
    		sb.append(" and c.coupon_time_begin >= :coupon_time_begin_begin ");
    		paramMap.put("coupon_time_begin_begin", q.getCouponTimeBeginBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeBeginEnd())){
    		sb.append(" and c.coupon_time_begin <= :coupon_time_begin_end ");
    		paramMap.put("coupon_time_begin_end", q.getCouponTimeBeginEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeEndBegin())){
    		sb.append(" and c.coupon_time_end >= :coupon_time_end_begin ");
    		paramMap.put("coupon_time_end_begin", q.getCouponTimeEndBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCouponTimeEndEnd())){
    		sb.append(" and c.coupon_time_end <= :coupon_time_end_end ");
    		paramMap.put("coupon_time_end_end", q.getCouponTimeEndEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getIsCouponTimeEndBeforeToday())&&"TRUE".equals(q.getIsCouponTimeEndBeforeToday().toUpperCase())){
    		Date date = new Date();
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String todayStr = simpleDateFormat.format(date);
    		sb.append(" and c.coupon_time_end >= :today_str");
    	    paramMap.put("today_str", todayStr);
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and c.valid_value = :valid_value  ");
    	    paramMap.put("valid_value", q.getValidValue());
    	}
        
        
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<CouponUserVo> list = new ArrayList<CouponUserVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CouponUserVo vo = new CouponUserVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CouponUserVo> pg = new Page<CouponUserVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	   

}
