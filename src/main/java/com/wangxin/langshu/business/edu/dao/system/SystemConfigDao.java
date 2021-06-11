package com.wangxin.langshu.business.edu.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsVo;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.systemConfig.SystemConfigVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class SystemConfigDao extends BaseDao {
	
	
	
	
	
	
	public Page<SystemConfigVo> queryEntities(SystemConfigQ paperQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from system_config q ");
        sb.append("  where 1=1  ");
    	
        if(paperQ==null){
        	paperQ = new SystemConfigQ();
        	paperQ.setPageCurrent(-1);
        	paperQ.setPageSize(-1);
        }
       
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, paperQ.getPageCurrent(), paperQ.getPageSize(), null);
        
        List<SystemConfigVo> list = new ArrayList<SystemConfigVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	SystemConfigVo vo = new SystemConfigVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<SystemConfigVo> pg = new Page<SystemConfigVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
