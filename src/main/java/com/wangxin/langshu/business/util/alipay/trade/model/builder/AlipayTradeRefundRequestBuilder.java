package com.wangxin.langshu.business.util.alipay.trade.model.builder;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

/**
 * Created by liuyangkly on 16/3/3.
 */
public class AlipayTradeRefundRequestBuilder extends RequestBuilder {
    private BizContent bizContent = new BizContent();

    @Override
    public BizContent getBizContent() {
        return bizContent;
    }

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(bizContent.outTradeNo) &&
                StringUtils.isEmpty(bizContent.tradeNo)) {
            throw new NullPointerException("out_trade_no and trade_no should not both be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.refundAmount)) {
            throw new NullPointerException("refund_amount should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.refundReason)) {
            throw new NullPointerException("refund_reson should not be NULL!");
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayTradeRefundRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AlipayTradeRefundRequestBuilder setAppAuthToken(String appAuthToken) {
        return (AlipayTradeRefundRequestBuilder) super.setAppAuthToken(appAuthToken);
    }

    @Override
    public AlipayTradeRefundRequestBuilder setNotifyUrl(String notifyUrl) {
        return (AlipayTradeRefundRequestBuilder) super.setNotifyUrl(notifyUrl);
    }

    public String getOutTradeNo() {
        return bizContent.outTradeNo;
    }

    public AlipayTradeRefundRequestBuilder setOutTradeNo(String outTradeNo) {
        bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setTradeNo(String tradeNo) {
        bizContent.tradeNo = tradeNo;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setRefundAmount(String refundAmount) {
        bizContent.refundAmount = refundAmount;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setOutRequestNo(String outRequestNo) {
        bizContent.outRequestNo = outRequestNo;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setRefundReason(String refundReason) {
        bizContent.refundReason = refundReason;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setStoreId(String storeId) {
        bizContent.storeId = storeId;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setAlipayStoreId(String alipayStoreId) {
        bizContent.alipayStoreId = alipayStoreId;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setTerminalId(String terminalId) {
        bizContent.terminalId = terminalId;
        return this;
    }

    public String getTradeNo() {
        return bizContent.tradeNo;
    }

    public String getRefundAmount() {
        return bizContent.refundAmount;
    }

    public String getOutRequestNo() {
        return bizContent.outRequestNo;
    }

    public String getRefundReason() {
        return bizContent.refundReason;
    }

    public String getStoreId() {
        return bizContent.storeId;
    }

    public String getAlipayStoreId() {
        return bizContent.alipayStoreId;
    }

    public String getTerminalId() {
        return bizContent.terminalId;
    }

    public static class BizContent {
        // 支付宝交易号，当面付支付成功后支付宝会返回给商户系统。通过此支付宝交易号进行交易退款
        @SerializedName("trade_no")
        private String tradeNo;

        // (推荐) 外部订单号，可通过外部订单号申请退款，推荐使用
        @SerializedName("out_trade_no")
        private String outTradeNo;

        // 退款金额，该金额必须小于等于订单的支付金额，此处单位为元，精确到小数点后2位
        @SerializedName("refund_amount")
        private String refundAmount;

        // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
        // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
        @SerializedName("out_request_no")
        private String outRequestNo;

        // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
        @SerializedName("refund_reason")
        private String refundReason;

        // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
        @SerializedName("store_id")
        private String storeId;

        // 支付宝商家平台中配置的商户门店号，详询支付宝技术支持
        @SerializedName("alipay_store_id")
        private String alipayStoreId;

        // 商户机具终端编号，当以机具方式接入支付宝时必传，详询支付宝技术支持
        @SerializedName("terminal_id")
        private String terminalId;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("tradeNo='").append(tradeNo).append('\'');
            sb.append(", outTradeNo='").append(outTradeNo).append('\'');
            sb.append(", refundAmount='").append(refundAmount).append('\'');
            sb.append(", outRequestNo='").append(outRequestNo).append('\'');
            sb.append(", refundReason='").append(refundReason).append('\'');
            sb.append(", storeId='").append(storeId).append('\'');
            sb.append(", alipayStoreId='").append(alipayStoreId).append('\'');
            sb.append(", terminalId='").append(terminalId).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
