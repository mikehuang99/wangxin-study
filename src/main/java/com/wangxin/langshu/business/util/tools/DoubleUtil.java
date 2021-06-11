package com.wangxin.langshu.business.util.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtil {
	
	/**
	 * 当d为整数的时候，只是保留整数。当d为小数的时候，保留一位小数。例如d为2,输出2。d为2.18，输出2.1
	 * @param d
	 * @return
	 */
	public static String formatDoubleForOneBit(double d) {
	    BigDecimal bg = new BigDecimal(d).setScale(1, RoundingMode.UP);
	    double num = bg.doubleValue();
	    if (Math.round(num) - num == 0) {
	        return String.valueOf((long) num);
	    }
	    return String.valueOf(num);
	}

}
