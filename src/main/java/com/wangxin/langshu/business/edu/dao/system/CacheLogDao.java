package com.wangxin.langshu.business.edu.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.cache.CacheLogVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class CacheLogDao extends BaseDao {
	
	
	
	
	
	/**
	 * 查询前面20条缓存操作历史
	 * @param cacheLogVo
	 * @return
	 */
	public Page<CacheLogVo> queryEntities(CacheLogQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from cache_log q ");
        sb.append("  where 1=1  ");
        
    	if(StringUtils.isNotEmpty(q.getCacheStatus())){
    		sb.append(" and q.cache_status = :cache_status ");
    		paramMap.put("cache_status", q.getCacheStatus());
    	}

    	sb.append(" order by q.create_time desc");
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap,q.getPageCurrent(), q.getPageSize(), null);
        
        List<CacheLogVo> list = new ArrayList<CacheLogVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	CacheLogVo vo = new CacheLogVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<CacheLogVo> pg = new Page<CacheLogVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	/**
	 * 查询是否有正在执行的缓存任务
	 * @return
	 */
	public boolean checkIsRunningCacheTask() {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.id from cache_log q ");
        sb.append("  where 1=1 and q.cache_status = 1 ");

        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap,1, 1, null);
        
        if(pge.getList()!=null&&pge.getList().size()!=0){
        	return true;
        }else{
        	return false;
        }

    }
	
	
	   

}
