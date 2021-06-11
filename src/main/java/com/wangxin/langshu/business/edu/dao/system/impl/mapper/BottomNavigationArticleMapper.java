package com.wangxin.langshu.business.edu.dao.system.impl.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticle;
import com.wangxin.langshu.business.edu.entity.system.old.BottomNavigationArticleExample;

@Mapper
public interface BottomNavigationArticleMapper {
    int countByExample(BottomNavigationArticleExample example);

    int deleteByExample(BottomNavigationArticleExample example);

    int deleteByPrimaryKey(String id);

    int insert(BottomNavigationArticle record);

    int insertSelective(BottomNavigationArticle record);

    List<BottomNavigationArticle> selectByExampleWithBLOBs(BottomNavigationArticleExample example);

    List<BottomNavigationArticle> selectByExample(BottomNavigationArticleExample example);

    BottomNavigationArticle selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BottomNavigationArticle record, @Param("example") BottomNavigationArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") BottomNavigationArticle record, @Param("example") BottomNavigationArticleExample example);

    int updateByExample(@Param("record") BottomNavigationArticle record, @Param("example") BottomNavigationArticleExample example);

    int updateByPrimaryKeySelective(BottomNavigationArticle record);

    int updateByPrimaryKeyWithBLOBs(BottomNavigationArticle record);

    int updateByPrimaryKey(BottomNavigationArticle record);
}