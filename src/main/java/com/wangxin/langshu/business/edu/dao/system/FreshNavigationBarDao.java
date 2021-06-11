package com.wangxin.langshu.business.edu.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.fresh.navigationBar.FreshNavigationBarQ;
import com.wangxin.langshu.business.edu.vo.system.fresh.navigationBar.FreshNavigationBarVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class FreshNavigationBarDao extends BaseDao {
	
	public Page<FreshNavigationBarVo> queryEntities(FreshNavigationBarQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from navigation_bar q  ");
        sb.append("  where 1=1  ");
    	
    	
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}

    	sb.append(" order by q.sort desc,q.create_time desc");
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<FreshNavigationBarVo> list = new ArrayList<FreshNavigationBarVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	FreshNavigationBarVo vo = new FreshNavigationBarVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<FreshNavigationBarVo> pg = new Page<FreshNavigationBarVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
