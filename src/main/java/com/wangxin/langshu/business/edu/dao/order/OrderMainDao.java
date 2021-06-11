package com.wangxin.langshu.business.edu.dao.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.ChapterBO;
import com.wangxin.langshu.business.edu.entity.order.OrderMain;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainQ;
import com.wangxin.langshu.business.edu.vo.order.order.OrderMainVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class OrderMainDao extends BaseDao {
	
	
	public Page<OrderMainVo> queryOrders(OrderMainQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select od.*,c.course_name,c.course_logo,s.real_name,par.pay_type_str,par.pay_amount_str from order_main od ");
        sb.append("left join course c on od.course_id= c.id ");
        sb.append("left join student s on od.order_user_no = s.user_no ");
        sb.append("left join ");
        sb.append("(select GROUP_CONCAT(r.pay_type) pay_type_str, ");
        sb.append("GROUP_CONCAT(r.pay_amount) pay_amount_str,r.order_id order_id ");
        sb.append("from pay_amount_record r group by r.order_id ) par on od.id=par.order_id ");
        sb.append(" where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getCreateTimeBegin())){
    		sb.append(" and od.create_time >= :create_time_begin ");
    		paramMap.put("create_time_begin", q.getCreateTimeBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCreateTimeEnd())){
    		sb.append(" and od.create_time <= :create_time_end ");
    		paramMap.put("create_time_end", q.getCreateTimeEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getOrderNo())){
    		sb.append(" and INSTR(od.order_no, :order_no)>0 ");
    	    paramMap.put("order_no", q.getOrderNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(c.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCourseId())){
    		sb.append(" and INSTR(c.id, :course_id)>0 ");
    	    paramMap.put("course_id", q.getCourseId());
    	}
        
    	if(StringUtils.isNotEmpty(q.getRealName())){
    		sb.append(" and INSTR(s.real_name, :real_name)>0 ");
    	    paramMap.put("real_name", q.getRealName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getPayTimeBegin())){
    		sb.append(" and od.pay_time >= :pay_time_begin ");
    		paramMap.put("pay_time_begin", q.getPayTimeBegin());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getPayTimeEnd())){
    		sb.append(" and od.pay_time <= :pay_time_end ");
    		paramMap.put("pay_time_end", q.getPayTimeEnd());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getRealName())){
    		sb.append(" and INSTR(s.real_name, :real_name)>0 ");
    	    paramMap.put("real_name", q.getRealName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getOrderUserNo())){
    		sb.append(" and od.order_user_no = :order_user_no ");
    	    paramMap.put("order_user_no", q.getOrderUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getCourseName())){
    		sb.append(" and INSTR(c.course_name, :course_name)>0 ");
    	    paramMap.put("course_name", q.getCourseName());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getPayStatus())){
    		sb.append(" and od.pay_status = :pay_status ");
    	    paramMap.put("pay_status", q.getPayStatus());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getPayType())){
    		sb.append(" and INSTR(par.pay_type_str, :pay_type_str)>0 ");
    	    paramMap.put("pay_type_str", q.getPayType());
    	}
     
    	sb.append(" ORDER BY od.sort,od.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<OrderMainVo> list = new ArrayList<OrderMainVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	OrderMainVo vo = new OrderMainVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<OrderMainVo> pg = new Page<OrderMainVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	public OrderMain queryOrderByOrderNo(String orderNo){
    	StringBuffer sb = new StringBuffer();
        sb.append("select cc.* from order_main cc where cc.order_no = ?");
		String strings[] = {orderNo};
		List<Map<String,Object>> listMap = this.jdbcTemplate.queryForList(sb.toString(),strings );
		List<OrderMain> list = new ArrayList<OrderMain>();
        for(Map<String, Object> map:listMap){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	OrderMain obj = new OrderMain();
        	ObjectMapUtil2.mapToObjModle(obj,mapHump);
        	list.add(obj);
        }
        if(list.size()==0){
        	return null;
        }
        if(list.size()!=1){
        	throw new BaseException("无法查询到订单，数据异常");
        }
		return list.get(0);
	}
	   
	//查询在date之后的每日订单数量
	public List<Map<String,Object>> queryOrderAfterDate(String date){
    	StringBuffer sb = new StringBuffer();
        sb.append("select DATE_FORMAT(om.create_time,'%Y-%m-%d') date,count(om.id) count ");
        sb.append("from order_main om ");
        sb.append("WHERE DATE_FORMAT(om.create_time,'%Y-%m-%d')>=DATE_FORMAT(?,'%Y-%m-%d' ) and om.pay_status=1 ");
        sb.append("GROUP BY DATE_FORMAT(om.create_time,'%Y-%m-%d') ");
		String strings[] = {date};
		List<Map<String, Object>> listMap = this.jdbcTemplate.queryForList(sb.toString(),strings );

		return listMap;
	}

}
