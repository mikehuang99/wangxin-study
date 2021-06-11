package com.wangxin.langshu.business.edu.dao.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wangxin.langshu.business.base.dao.BaseDao;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsQ;
import com.wangxin.langshu.business.edu.vo.system.old.common.bean.vo.news.NewsVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.tools.LineHumpConvert;
import com.wangxin.langshu.business.util.tools.ObjectMapUtil2;

@Repository
public class NewsDao extends BaseDao {

	public Page<NewsVo> queryEntities(NewsQ newsPaperQ) {
		StringBuffer sb = new StringBuffer();
		// 设置参数 用map接收
		Map<String, Object> paramMap = new HashMap<String, Object>();
		sb.append("select q.*,nt.news_type from news q left join news_type nt on q.news_type_id = nt.id ");
		sb.append("  where 1=1  ");

		if (StringUtils.isNotEmpty(newsPaperQ.getNewsTitle())) {
			sb.append(" and INSTR(q.news_title, :news_title)>0 ");
			paramMap.put("news_title", newsPaperQ.getNewsTitle());
		}

		if (StringUtils.isNotEmpty(newsPaperQ.getNewsContent())) {
			sb.append(" and INSTR(q.news_content, :news_content)>0 ");
			paramMap.put("news_content", newsPaperQ.getNewsContent());
		}

		if (StringUtils.isNotEmpty(newsPaperQ.getNewsTypeId())) {
			sb.append(" and q.news_type_id=:news_type_id ");
			paramMap.put("news_type_id", newsPaperQ.getNewsTypeId());
		}

		if (StringUtils.isNotEmpty(newsPaperQ.getPlatShow())) {
			sb.append(" and q.plat_show=:plat_show ");
			paramMap.put("plat_show", newsPaperQ.getPlatShow());
		}

		if (StringUtils.isNotEmpty(newsPaperQ.getValidValue())) {
			sb.append(" and q.valid_value = :valid_value ");
			paramMap.put("valid_value", newsPaperQ.getValidValue());
		}

		sb.append(" order by q.sort desc,q.create_time desc");
		Page<Map<String, Object>> pge = this.queryPageForMySQL(sb.toString(), paramMap, newsPaperQ.getPageCurrent(),
				newsPaperQ.getPageSize(), null);

		List<NewsVo> list = new ArrayList<NewsVo>();
		for (Map<String, Object> map : pge.getList()) {
			Map<String, Object> mapHump = new HashMap<String, Object>();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				mapHump.put(LineHumpConvert.lineToHump(entry.getKey()), entry.getValue());
			}
			NewsVo vo = new NewsVo();
			ObjectMapUtil2.mapToObjModle(vo, mapHump);
			list.add(vo);
		}

		Page<NewsVo> pg = new Page<NewsVo>();
		pg.setPageCurrent(pge.getPageCurrent()); // 设置显示的当前页数
		pg.setTotalPage(pge.getTotalPage()); // 设置总页数
		pg.setList(list); // 设置当前页数据
		pg.setPageSize(pge.getPageSize());
		pg.setTotalCount(pge.getTotalCount()); // 设置总记录数

		return pg;
	}

}
