package com.wangxin.langshu.business.util.alipay.trade.model.builder;

import com.wangxin.langshu.business.util.alipay.trade.model.ExtendParams;
import com.wangxin.langshu.business.util.alipay.trade.model.GoodsDetail;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by liuyangkly on 16/3/3.
 */
public class AlipayTradePrecreateRequestBuilder extends RequestBuilder {

    private BizContent bizContent = new BizContent();

    @Override
    public BizContent getBizContent() {
        return bizContent;
    }

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(bizContent.outTradeNo)) {
            throw new NullPointerException("out_trade_no should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.totalAmount)) {
            throw new NullPointerException("total_amount should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.subject)) {
            throw new NullPointerException("subject should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.storeId)) {
            throw new NullPointerException("store_id should not be NULL!");
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayTradePrecreateRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AlipayTradePrecreateRequestBuilder setAppAuthToken(String appAuthToken) {
        return (AlipayTradePrecreateRequestBuilder) super.setAppAuthToken(appAuthToken);
    }

    @Override
    public AlipayTradePrecreateRequestBuilder setNotifyUrl(String notifyUrl) {
        return (AlipayTradePrecreateRequestBuilder) super.setNotifyUrl(notifyUrl);
    }

    public String getOutTradeNo() {
        return bizContent.outTradeNo;
    }

    public AlipayTradePrecreateRequestBuilder setOutTradeNo(String outTradeNo) {
        bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public String getSellerId() {
        return bizContent.sellerId;
    }

    public AlipayTradePrecreateRequestBuilder setSellerId(String sellerId) {
        bizContent.sellerId = sellerId;
        return this;
    }

    public String getTotalAmount() {
        return bizContent.totalAmount;
    }

    public AlipayTradePrecreateRequestBuilder setTotalAmount(String totalAmount) {
        bizContent.totalAmount = totalAmount;
        return this;
    }

    public String getDiscountableAmount() {
        return bizContent.discountableAmount;
    }

    public AlipayTradePrecreateRequestBuilder setDiscountableAmount(String discountableAmount) {
        bizContent.discountableAmount = discountableAmount;
        return this;
    }

    public String getUndiscountableAmount() {
        return bizContent.undiscountableAmount;
    }

    public AlipayTradePrecreateRequestBuilder setUndiscountableAmount(String undiscountableAmount) {
        bizContent.undiscountableAmount = undiscountableAmount;
        return this;
    }

    public String getSubject() {
        return bizContent.subject;
    }

    public AlipayTradePrecreateRequestBuilder setSubject(String subject) {
        bizContent.subject = subject;
        return this;
    }

    public String getBody() {
        return bizContent.body;
    }

    public AlipayTradePrecreateRequestBuilder setBody(String body) {
        bizContent.body = body;
        return this;
    }

    public List<GoodsDetail> getGoodsDetailList() {
        return bizContent.goodsDetailList;
    }

    public AlipayTradePrecreateRequestBuilder setGoodsDetailList(List<GoodsDetail> goodsDetailList) {
        bizContent.goodsDetailList = goodsDetailList;
        return this;
    }

    public String getOperatorId() {
        return bizContent.operatorId;
    }

    public AlipayTradePrecreateRequestBuilder setOperatorId(String operatorId) {
        bizContent.operatorId = operatorId;
        return this;
    }

    public String getStoreId() {
        return bizContent.storeId;
    }

    public AlipayTradePrecreateRequestBuilder setStoreId(String storeId) {
        bizContent.storeId = storeId;
        return this;
    }

    public String getAlipayStoreId() {
        return bizContent.alipayStoreId;
    }

    public AlipayTradePrecreateRequestBuilder setAlipayStoreId(String alipayStoreId) {
        bizContent.alipayStoreId = alipayStoreId;
        return this;
    }

    public String getTerminalId() {
        return bizContent.terminalId;
    }

    public AlipayTradePrecreateRequestBuilder setTerminalId(String terminalId) {
        bizContent.terminalId = terminalId;
        return this;
    }

    public ExtendParams getExtendParams() {
        return bizContent.extendParams;
    }

    public AlipayTradePrecreateRequestBuilder setExtendParams(ExtendParams extendParams) {
        bizContent.extendParams = extendParams;
        return this;
    }

    public String getTimeoutExpress() {
        return bizContent.timeoutExpress;
    }

    public AlipayTradePrecreateRequestBuilder setTimeoutExpress(String timeoutExpress) {
        bizContent.timeoutExpress = timeoutExpress;
        return this;
    }

    public static class BizContent {
        // 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        @SerializedName("out_trade_no")
        private String outTradeNo;

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        @SerializedName("seller_id")
        private String sellerId;

        // 订单总金额，整形，此处单位为元，精确到小数点后2位，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        @SerializedName("total_amount")
        private String totalAmount;

        // 订单可打折金额，此处单位为元，精确到小数点后2位
        // 可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
        // 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
        @SerializedName("discountable_amount")
        private String discountableAmount;

        // 订单不可打折金额，此处单位为元，精确到小数点后2位，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        @SerializedName("undiscountable_amount")
        private String undiscountableAmount;

        // 订单标题，粗略描述用户的支付目的。如“喜士多（浦东店）消费”
        private String subject;

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        private String body;

        // 商品明细列表，需填写购买商品详细信息，
        @SerializedName("goods_detail")
        private List<GoodsDetail> goodsDetailList;

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        @SerializedName("operator_id")
        private String operatorId;

        // 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        @SerializedName("store_id")
        private String storeId;

        // 支付宝商家平台中配置的商户门店号，详询支付宝技术支持
        @SerializedName("alipay_store_id")
        private String alipayStoreId;

        // 商户机具终端编号，当以机具方式接入支付宝时必传，详询支付宝技术支持
        @SerializedName("terminal_id")
        private String terminalId;

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        @SerializedName("extend_params")
        private ExtendParams extendParams;

        // (推荐使用，相对时间) 支付超时时间，5m 5分钟
        @SerializedName("timeout_express")
        private String timeoutExpress;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("outTradeNo='").append(outTradeNo).append('\'');
            sb.append(", sellerId='").append(sellerId).append('\'');
            sb.append(", totalAmount='").append(totalAmount).append('\'');
            sb.append(", discountableAmount='").append(discountableAmount).append('\'');
            sb.append(", undiscountableAmount='").append(undiscountableAmount).append('\'');
            sb.append(", subject='").append(subject).append('\'');
            sb.append(", body='").append(body).append('\'');
            sb.append(", goodsDetailList=").append(goodsDetailList);
            sb.append(", operatorId='").append(operatorId).append('\'');
            sb.append(", storeId='").append(storeId).append('\'');
            sb.append(", alipayStoreId='").append(alipayStoreId).append('\'');
            sb.append(", terminalId='").append(terminalId).append('\'');
            sb.append(", extendParams=").append(extendParams);
            sb.append(", timeoutExpress='").append(timeoutExpress).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
