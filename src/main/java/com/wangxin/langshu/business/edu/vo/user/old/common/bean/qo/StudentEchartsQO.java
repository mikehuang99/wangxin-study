package com.wangxin.langshu.business.edu.vo.user.old.common.bean.qo;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StudentEchartsQO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8903000284366030711L;
	private List<String> dateList;
	
}
