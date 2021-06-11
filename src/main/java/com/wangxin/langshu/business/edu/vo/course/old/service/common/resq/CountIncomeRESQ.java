/**
 *  广州旺鑫计算机科技有限公司
 */
package com.wangxin.langshu.business.edu.vo.course.old.service.common.resq;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 收入统计
 * 
 */
@Data
@Accessors(chain = true)
public class CountIncomeRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 总收入
	 */
	private BigDecimal totalProfit = BigDecimal.valueOf(0);

	/**
	 * 平台收入
	 */
	private BigDecimal platformProfit = BigDecimal.valueOf(0);


}
