package com.wangxin.langshu.business.edu.iservice.course.pcboss;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertDeleteREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertPageREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertSaveREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertUpdateREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.req.AdvertViewREQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertPageRESQ;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.resq.AdvertViewRESQ;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 广告信息
 *
 */

public interface IPcBossAdvertBiz {

	

	public Result<Page<AdvertPageRESQ>> list(AdvertPageREQ req);

	/**
	 * 
	 * 添加轮播广告
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> save(AdvertSaveREQ req) ;

	/**
	 * 更新广告信息
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> update(AdvertUpdateREQ req) ;
	/**
	 * 删除广告信息
	 * 
	 * @param req
	 * @return
	 */
	public Result<Integer> delete(AdvertDeleteREQ req) ;

	/**
	 * 广告信息查看
	 * 
	 * @param req
	 * @return
	 */
	public Result<AdvertViewRESQ> view(AdvertViewREQ req) ;

}
