package com.wangxin.langshu.business.edu.iservice.course.pccommon;

import com.wangxin.langshu.business.edu.vo.course.old.service.common.bo.AdvertBO;
import com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.AdvertListDTO;
import com.wangxin.langshu.business.util.base.Result;

/**
 * 广告信息
 *
 *  
 */

public interface IApiAdvertBiz {


	public Result<AdvertListDTO> list(AdvertBO advertBO) ;

}
