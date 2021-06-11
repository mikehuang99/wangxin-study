package com.wangxin.langshu.business.edu.dao.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordQ;
import com.wangxin.langshu.business.edu.vo.order.payAmountRecord.PayAmountRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class PayAmountRecordDao extends BaseDao {
	
	
	public Page<PayAmountRecordVo> queryPayAmountRecords(PayAmountRecordQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from pay_amount_record q ");
        sb.append(" where 1=1 ");
        
        
    	if(StringUtils.isNotEmpty(q.getOrderId())){
    		sb.append(" and q.order_id = :order_id ");
    		paramMap.put("order_id", q.getOrderId());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    	    paramMap.put("valid_value", q.getValidValue());
    	}
       
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<PayAmountRecordVo> list = new ArrayList<PayAmountRecordVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	PayAmountRecordVo vo = new PayAmountRecordVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<PayAmountRecordVo> pg = new Page<PayAmountRecordVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
