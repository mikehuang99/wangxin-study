package com.wangxin.langshu.business.util.alipay.trade.model;


import com.wangxin.langshu.business.util.alipay.trade.utils.Utils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liuyangkly on 15/6/26.
 * 商品明细
 */
public class GoodsDetail {
    // 商品编号(国标)
    @SerializedName("goods_id")
    private String goodsId;

    @SerializedName("alipay_goods_id")
    private String alipayGoodsId;

    // 商品名称
    @SerializedName("goods_name")
    private String goodsName;

    // 商品数量
    private int quantity;

    // 商品价格，此处单位为元，精确到小数点后2位
    private String price;

    // 商品类别
    @SerializedName("goods_category")
    private String goodsCategory;

    // 商品详情
    private String body;

    // 创建一个商品信息，参数含义分别为商品id（使用国标）、商品名称、商品价格（单位为分）、商品数量
    public static GoodsDetail newInstance(String goodsId, String goodsName, long price, int quantity) {
        GoodsDetail info = new GoodsDetail();
        info.setGoodsId(goodsId);
        info.setGoodsName(goodsName);
        info.setPrice(price);
        info.setQuantity(quantity);
        return info;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GoodsDetail{");
        sb.append("goodsId='").append(goodsId).append('\'');
        sb.append(", alipayGoodsId='").append(alipayGoodsId).append('\'');
        sb.append(", goodsName='").append(goodsName).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", price='").append(price).append('\'');
        sb.append(", goodsCategory='").append(goodsCategory).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public GoodsDetail setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getAlipayGoodsId() {
        return alipayGoodsId;
    }

    public GoodsDetail setAlipayGoodsId(String alipayGoodsId) {
        this.alipayGoodsId = alipayGoodsId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsDetail setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public GoodsDetail setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public GoodsDetail setPrice(long price) {
        this.price = Utils.toAmount(price);
        return this;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public GoodsDetail setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
        return this;
    }

    public String getBody() {
        return body;
    }

    public GoodsDetail setBody(String body) {
        this.body = body;
        return this;
    }
}
