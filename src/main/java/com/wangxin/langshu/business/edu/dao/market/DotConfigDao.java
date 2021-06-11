package com.wangxin.langshu.business.edu.dao.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigQ;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class DotConfigDao extends BaseDao {
	
	
	
	
	
	
	public Page<DotConfigVo> queryEntities(DotConfigQ paperQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from dot_config q ");
        sb.append("  where 1=1  ");
    	
        if(paperQ==null){
        	paperQ = new DotConfigQ();
        	paperQ.setPageCurrent(-1);
        	paperQ.setPageSize(-1);
        }
       
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, paperQ.getPageCurrent(), paperQ.getPageSize(), null);
        
        List<DotConfigVo> list = new ArrayList<DotConfigVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	DotConfigVo vo = new DotConfigVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<DotConfigVo> pg = new Page<DotConfigVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
