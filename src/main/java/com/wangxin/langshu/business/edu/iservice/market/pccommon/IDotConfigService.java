package com.wangxin.langshu.business.edu.iservice.market.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.DotConfig;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigQ;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigSaveVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigViewVo;
import com.wangxin.langshu.business.edu.vo.market.dotConfig.DotConfigVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.enums.DotGainRuleEnum;


public interface IDotConfigService  extends IService<DotConfig> {
	

	public Page<DotConfigVo> queryEntities(DotConfigQ q) ;

	public boolean saveEntity(DotConfigSaveVo vo) ;

	public boolean updateEntity(DotConfigUpdateVo vo) ;

	
	public boolean deleteEntity(DotConfigDeleteVo vo) ;
	
	public boolean deleteEntityById(String id) ;


	
	public DotConfigViewVo viewEntity(DotConfigViewVo vo) ;	
	
	public DotConfigViewVo view();

}
