package com.wangxin.langshu.business.util.alipay.trade.model.hb;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuyangkly on 15/8/27.
 */
public class SysTradeInfo implements TradeInfo {
    @SerializedName("OTN")
    private String outTradeNo;

    @SerializedName("TC")
    private double timeConsume;

    @SerializedName("STAT")
    private HbStatus status;

    private SysTradeInfo() {
        // no public constructor.
    }

    public static SysTradeInfo newInstance(String outTradeNo, double timeConsume, HbStatus status) {
        SysTradeInfo info = new SysTradeInfo();
        info.setOutTradeNo(outTradeNo);
        if (timeConsume > 99 || timeConsume < 0) {
            timeConsume = 99;
        }
        info.setTimeConsume(timeConsume);
        info.setStatus(status);
        return info;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public HbStatus getStatus() {
        return status;
    }

    public void setStatus(HbStatus status) {
        this.status = status;
    }

    @Override
    public double getTimeConsume() {
        return timeConsume;
    }

    public void setTimeConsume(double timeConsume) {
        this.timeConsume = timeConsume;
    }
}
