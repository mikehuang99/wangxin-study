package com.wangxin.langshu.business.util;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 自动补充插入或更新时的值
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
 
 
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(),metaObject);
        this.setFieldValByName("modifiedTime", new Date(),metaObject);
     
    }
 
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifiedTime", new Date(),metaObject);
    }
}
