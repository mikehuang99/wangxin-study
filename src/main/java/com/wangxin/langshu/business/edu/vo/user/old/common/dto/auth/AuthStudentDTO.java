package com.wangxin.langshu.business.edu.vo.user.old.common.dto.auth;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthStudentDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 5636118707419950417L;
	/**
	 * 主键
	 */
    @ApiModelProperty(value = "主键")
	@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    /**
   	 * 用户编号
   	 */
   	@ApiModelProperty(value = "用户编号")
   	@JsonSerialize(using = ToStringSerializer.class)
    private String userNo;
    /**
   	 * 用户类型
   	 */
   	@ApiModelProperty(value = "用户类型")
    private Integer userType;
    /**
   	 * 手机号码
   	 */
   	@ApiModelProperty(value = "手机号码")
    private String mobile;
    /**
   	 * 性别
   	 */
   	@ApiModelProperty(value = "性别")
    private Integer sex;
    /**
   	 * 年龄
   	 */
   	@ApiModelProperty(value = "年龄")
    private Integer age;
    /**
   	 * 昵称
   	 */
   	@ApiModelProperty(value = "昵称")
    private String nickname;
    /**
   	 * 头像
   	 */
   	@ApiModelProperty(value = "头像")
    private String headImgUrl;
    /**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
    private String remark;
	
    /**
   	 * 真实姓名
   	 */
   	@ApiModelProperty(value = "真实姓名")
    private String realName;
    /**
     * 身份证号码
     */
	@ApiModelProperty(value = "身份证号码")
    private String identityCardNo;//身份证号码
}
