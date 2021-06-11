package com.wangxin.langshu.business.edu.dao.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordQ;
import com.wangxin.langshu.business.edu.vo.market.dotOperateRecord.DotOperateRecordVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class DotOperateRecordDao extends BaseDao {
	
	public Page<DotOperateRecordVo> queryDotOperateRecords(DotOperateRecordQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from dot_operate_record q  where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getDotChangeReson())){
    		sb.append(" and q.dot_change_reson = :dot_change_reson ");
    		paramMap.put("dot_change_reson", q.getDotChangeReson());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getOperateType())){
    		sb.append(" and q.operate_type = :operate_type ");
    		paramMap.put("operate_type", q.getOperateType());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getOperateDate())){
    		sb.append(" and q.operate_date = :operate_date ");
    		paramMap.put("operate_date", q.getOperateDate());
    	}
    	
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<DotOperateRecordVo> list = new ArrayList<DotOperateRecordVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	DotOperateRecordVo vo = new DotOperateRecordVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<DotOperateRecordVo> pg = new Page<DotOperateRecordVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
