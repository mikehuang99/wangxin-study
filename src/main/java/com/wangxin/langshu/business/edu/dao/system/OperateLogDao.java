package com.wangxin.langshu.business.edu.dao.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.operateLog.OperateLogQ;
import com.wangxin.langshu.business.edu.vo.system.operateLog.OperateLogVo;
import com.wangxin.langshu.business.util.base.BaseException;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;
import com.xiaoleilu.hutool.util.ObjectUtil;

@Repository
public class OperateLogDao extends BaseDao {
	
	
	
	
	
	
	public Page<OperateLogVo> queryEntities(OperateLogQ q) {
    	StringBuffer sb = new StringBuffer();
        //设置参数 用map接收
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sb.append("select q.*,u.mobile mobile from operate_log q left join user u on q.operate_user_no = u.user_no ");
        sb.append("  where 1=1  ");

        if(StringUtils.isNotEmpty(q.getOperateUserNo())){
    		sb.append(" and INSTR(q.operate_user_no, :operate_user_no)>0 ");
    	    paramMap.put("operate_user_no", q.getOperateUserNo());
    	}
        
        
        if(StringUtils.isNotEmpty(q.getMobile())){
    		sb.append(" and INSTR(u.mobile, :mobile)>0 ");
    	    paramMap.put("mobile", q.getMobile());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getValidValue())){
    		sb.append(" and q.valid_value = :valid_value ");
    		paramMap.put("valid_value", q.getValidValue());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getBeginDate())){
    		sb.append(" and q.create_time >= :begin_date ");
    		paramMap.put("begin_date", q.getBeginDate());
    	}
    	
    	if(StringUtils.isNotEmpty(q.getEndDate())){
    		sb.append(" and q.create_time <= :end_date ");
    		paramMap.put("end_date", q.getEndDate());
    	}

    	sb.append(" order by q.sort desc,q.create_time desc");
        Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, q.getPageCurrent(), q.getPageSize(), null);
        
        List<OperateLogVo> list = new ArrayList<OperateLogVo>();
        for(Map<String, Object> map:pge.getList()){
        	Map<String,Object> mapHump = new HashMap<String, Object>();
        	for (Map.Entry<String, Object> entry : map.entrySet()) {
        		  mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
        	}
        	OperateLogVo vo = new OperateLogVo();
        	ObjectMapUtil2.mapToObjModle(vo,mapHump);
        	list.add(vo);
        }
        
        Page<OperateLogVo> pg = new Page<OperateLogVo>();
        pg.setPageCurrent(pge.getPageCurrent()); //设置显示的当前页数
        pg.setTotalPage(pge.getTotalPage()); //设置总页数
        pg.setList(list);   //设置当前页数据
        pg.setPageSize(pge.getPageSize());
        pg.setTotalCount(pge.getTotalCount());  //设置总记录数
        
    	return pg;
    }
	
	public void deleteOperateLogLessThanDate(Date date){
		if(ObjectUtil.isNull(date)){
			throw new BaseException("缺少日期，参数异常");
		}
		StringBuffer sb = new StringBuffer();
	     Object[] objects = new Object[1];
        sb.append("delete from operate_log ");
        sb.append("  where operate_log.create_time < ? ");
    	objects[0]= date;
    	
        this.jdbcTemplate.update(sb.toString(), objects);  

	}
	
	   

}
