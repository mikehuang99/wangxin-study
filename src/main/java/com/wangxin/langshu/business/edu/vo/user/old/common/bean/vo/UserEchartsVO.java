package com.wangxin.langshu.business.edu.vo.user.old.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserEchartsVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6512874122942426733L;

	private List<BigDecimal> counts;

	private List<Integer> count;
	private String createTime;

}
