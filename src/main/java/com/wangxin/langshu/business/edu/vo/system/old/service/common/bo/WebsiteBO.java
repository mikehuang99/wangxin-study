package com.wangxin.langshu.business.edu.vo.system.old.service.common.bo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点信息
 *
 * 
 */
@Data
@Accessors(chain = true)
public class WebsiteBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 状态(1有效, 0无效)
     */
    private Integer validValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 站点标题
     */
    private String websiteTitle;
    /**
     * 站点关键词
     */
    private String websiteKeyword;
    /**
     * 站点描述
     */
    private String websiteDesc;
    /**
     * 站点版权
     */
    private String copyright;
    /**
     * 站点微信
     */
    private String weixinQrUrl;
    /**
     * 站点微博
     */
    private String weibo;
    /**
     * 是否开启统计
     */
    private Integer isOpenExternalCode;
    /**
     * 统计代码
     */
    private String externalCode;
    /**
     * 是否显示客服信息
     */
    private Integer isOpenQqService;
    /**
     * 客服信息1
     */
    private String serviceQq1;
    /**
     * 客服信息2
     */
    private String serviceQq2;
}
