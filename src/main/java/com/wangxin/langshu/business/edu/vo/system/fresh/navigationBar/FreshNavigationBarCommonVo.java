package com.wangxin.langshu.business.edu.vo.system.fresh.navigationBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FreshNavigationBarCommonVo  implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -991596448136593874L;
    /**
     * 导航集合
     */
    @ApiModelProperty(value = "导航集合")
    private List<FreshNavigationBarVo> list = new ArrayList<>();
	
	
	
}
