package com.wangxin.langshu.business.util.alipay.trade.model;

/**
 * Created by liuyangkly on 15/8/29.
 */
public enum TradeStatus {
    SUCCESS  // 业务交易明确成功，比如支付成功、退货成功

    ,FAILED  // 业务交易明确失败，比如支付明确失败、退货明确失败

    ,UNKNOWN // 业务交易状态未知，此时不清楚该业务是否成功或者失败，需要商户自行确认
}
