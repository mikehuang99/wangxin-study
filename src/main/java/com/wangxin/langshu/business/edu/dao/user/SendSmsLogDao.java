package com.wangxin.langshu.business.edu.dao.user;

import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLog;
import com.wangxin.langshu.business.edu.entity.user.old.SendSmsLogExample;
import com.wangxin.langshu.business.util.base.Page;

public interface SendSmsLogDao {
    int save(SendSmsLog record);

    int deleteById(String id);

    int updateById(SendSmsLog record);

    SendSmsLog getById(String id);

    Page<SendSmsLog> listForPage(int pageCurrent, int pageSize, SendSmsLogExample example);
}