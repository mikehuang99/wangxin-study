package com.wangxin.langshu.business.edu.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.mobileMiddleNavigation.MobileMiddleNavigationVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class MobileMiddleNavigationDao extends BaseDao {
	
	public Page<MobileMiddleNavigationVo> queryMobileMiddleNavigations(MobileMiddleNavigationQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        sb.append("select q.* from mobile_middle_navigation q ");
        sb.append("  where 1=1  ");
        
        if(StringUtils.isNotEmpty(q.getMmnName())){
    		sb.append(" and INSTR(q.mmnName, :mmnName)>0 ");
    	    paramMap.put("mmnName", q.getMmnName());
    	}
        if(StringUtils.isNotEmpty(q.getMmnUrl())){
    		sb.append(" and INSTR(q.mmnUrl, :mmnUrl)>0 ");
    	    paramMap.put("mmnUrl", q.getMmnUrl());
    	}
        if(StringUtils.isNotEmpty(q.getMmnDesc())){
    		sb.append(" and INSTR(q.mmnDesc, :mmnDesc)>0 ");
    	    paramMap.put("mmnDesc", q.getMmnDesc());
    	}
 
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getMmnUrlType())){
    		sb.append(" and q.mmn_url_type = :mmn_url_type ");
    		paramMap.put("mmn_url_type", q.getMmnUrlType());
    	}
     
    	sb.append(" ORDER BY q.sort DESC");
    	
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<MobileMiddleNavigationVo> list = new ArrayList<MobileMiddleNavigationVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	MobileMiddleNavigationVo vo = new MobileMiddleNavigationVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<MobileMiddleNavigationVo> pg = new Page<MobileMiddleNavigationVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
