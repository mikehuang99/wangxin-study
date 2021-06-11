package com.wangxin.langshu.business.edu.dao.system.impl.fresh;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wangxin.langshu.business.base.dao.BaseDao;

@Repository
public class FreshSystemRoleUserDao extends BaseDao {

	   public int deleteSystemRoleUserByUserId(String userId){
	    	StringBuffer sb = new StringBuffer();
	    	List<String> paramList = new ArrayList<>();
	        sb.append("delete from system_role_user where user_id = ? ");
	        paramList.add(userId);
	    	int count  = this.jdbcTemplate.update(sb.toString(), paramList.toArray());
		   return count;
	   }

}