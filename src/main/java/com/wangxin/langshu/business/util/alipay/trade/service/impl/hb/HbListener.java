package com.wangxin.langshu.business.util.alipay.trade.service.impl.hb;

import com.wangxin.langshu.business.util.alipay.trade.model.hb.HbStatus;
import com.wangxin.langshu.business.util.alipay.trade.model.hb.SysTradeInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by liuyangkly on 15/10/28.
 */
public class HbListener implements TradeListener {
    private static Log log = LogFactory.getLog(HbListener.class);

    private void offerTradeInfo(String outTradeNo, long beforeCall, HbStatus status) {
        // 获取当前毫秒数
        long afterCall = System.currentTimeMillis();

        // 通过交易请求发生时毫秒数计算得到交易耗时
        double timeConsume = ((double) (afterCall - beforeCall)) / 1000.0;

        log.debug(outTradeNo + " offer " + status + ": " + timeConsume);

        // 创建系统商trade_info结构，添加进队列
        HbQueue.offer(SysTradeInfo.newInstance(outTradeNo, timeConsume, status));
    }

    @Override
    public void onPayTradeSuccess(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.S);
    }

    @Override
    public void onPayInProgress(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.I);
    }

    @Override
    public void onPayFailed(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.F);
    }

    @Override
    public void onConnectException(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.X);
    }

    @Override
    public void onSendException(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.Y);
    }

    @Override
    public void onReceiveException(String outTradeNo, long beforeCall) {
        offerTradeInfo(outTradeNo, beforeCall, HbStatus.Z);
    }
}
