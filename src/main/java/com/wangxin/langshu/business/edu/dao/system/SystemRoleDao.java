package com.wangxin.langshu.business.edu.dao.system;

import com.wangxin.langshu.business.edu.entity.system.old.SystemRole;
import com.wangxin.langshu.business.edu.entity.system.old.SystemRoleExample;
import com.wangxin.langshu.business.util.base.Page;

public interface SystemRoleDao {
    int save(SystemRole record);

    int deleteById(String id);

    int updateById(SystemRole record);

    int updateByExampleSelective(SystemRole record, SystemRoleExample example);

    SystemRole getById(String id);

    Page<SystemRole> listForPage(int pageCurrent, int pageSize, SystemRoleExample example);
}