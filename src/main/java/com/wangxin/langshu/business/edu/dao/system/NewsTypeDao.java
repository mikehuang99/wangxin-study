package com.wangxin.langshu.business.edu.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.newsType.NewsTypeQ;
import com.wangxin.langshu.business.edu.vo.system.newsType.NewsTypeVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class NewsTypeDao extends BaseDao {
	
	
	
	
	
	
	public Page<NewsTypeVo> queryEntities(NewsTypeQ newsPaperQ) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.* from news_type q ");
        sb.append("  where 1=1  ");

    	if(StringUtils.isNotEmpty(newsPaperQ.getNewsType())){
    		sb.append(" and INSTR(q.news_type, :news_type)>0 ");
    	    paramMap.put("news_type", newsPaperQ.getNewsType());
    	}
    	
    	if(StringUtils.isNotEmpty(newsPaperQ.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", newsPaperQ.getValidValue());
    	}

    	sb.append(" order by q.sort desc,q.create_time desc");
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, newsPaperQ.getPageCurrent(), newsPaperQ.getPageSize(), null);
        
        List<NewsTypeVo> list = new ArrayList<NewsTypeVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	NewsTypeVo vo = new NewsTypeVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<NewsTypeVo> pg = new Page<NewsTypeVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	
	
	   

}
