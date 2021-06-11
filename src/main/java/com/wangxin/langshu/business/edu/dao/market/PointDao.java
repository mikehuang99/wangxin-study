package com.wangxin.langshu.business.edu.dao.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.point.PointQ;
import com.wangxin.langshu.business.edu.vo.market.point.PointVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class PointDao extends BaseDao {
	
	public Page<PointVo> queryPoints(PointQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from point q  where 1=1 ");
        
    	if(StringUtils.isNotEmpty(q.getBindUserNo())){
    		sb.append(" and q.bind_user_no = :bind_user_no ");
    		paramMap.put("bind_user_no", q.getBindUserNo());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}
        
       
    	sb.append(" ORDER BY q.sort desc,q.create_time DESC ");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<PointVo> list = new ArrayList<PointVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	PointVo vo = new PointVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<PointVo> pg = new Page<PointVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
