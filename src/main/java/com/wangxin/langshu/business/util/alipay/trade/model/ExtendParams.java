package com.wangxin.langshu.business.util.alipay.trade.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuyangkly on 15/6/26.
 * 扩展信息
 */
public class ExtendParams {
    // 系统商编号
    @SerializedName("sys_service_provider_id")
    private String sysServiceProviderId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExtendParams{");
        sb.append("sysServiceProviderId='").append(sysServiceProviderId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getSysServiceProviderId() {
        return sysServiceProviderId;
    }

    public ExtendParams setSysServiceProviderId(String sysServiceProviderId) {
        this.sysServiceProviderId = sysServiceProviderId;
        return this;
    }
}
