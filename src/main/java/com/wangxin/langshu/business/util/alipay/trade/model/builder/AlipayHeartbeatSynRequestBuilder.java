package com.wangxin.langshu.business.util.alipay.trade.model.builder;

import com.wangxin.langshu.business.util.alipay.trade.model.hb.*;
import com.wangxin.langshu.business.util.alipay.trade.utils.Utils;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyangkly on 16/3/2.
 */
public class AlipayHeartbeatSynRequestBuilder extends RequestBuilder {

    private BizContent bizContent = new BizContent();

    @Override
    public BizContent getBizContent() {
        return bizContent;
    }

    @Override
    public boolean validate() {
        if (bizContent.product == null) {
            throw new NullPointerException("product should not be NULL!");
        }
        if (bizContent.type == null) {
            throw new NullPointerException("type should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.equipmentId)) {
            throw new NullPointerException("equipment_id should not be NULL!");
        }
        if (bizContent.equipmentStatus == null) {
            throw new NullPointerException("equipment_status should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.time)) {
            throw new NullPointerException("time should not be NULL!");
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayHeartbeatSynRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AlipayHeartbeatSynRequestBuilder setAppAuthToken(String appAuthToken) {
        return (AlipayHeartbeatSynRequestBuilder) super.setAppAuthToken(appAuthToken);
    }

    @Override
    public AlipayHeartbeatSynRequestBuilder setNotifyUrl(String notifyUrl) {
        return (AlipayHeartbeatSynRequestBuilder) super.setNotifyUrl(notifyUrl);
    }

    public String getMac() {
        return bizContent.mac;
    }

    public AlipayHeartbeatSynRequestBuilder setMac(String mac) {
        bizContent.mac = mac;
        return this;
    }

    public String getNetworkType() {
        return bizContent.networkType;
    }

    public AlipayHeartbeatSynRequestBuilder setNetworkType(String networkType) {
        bizContent.networkType = networkType;
        return this;
    }

    public String getBattery() {
        return bizContent.battery;
    }

    public AlipayHeartbeatSynRequestBuilder setBattery(String battery) {
        bizContent.battery = battery;
        return this;
    }

    public String getWifiMac() {
        return bizContent.wifiMac;
    }

    public AlipayHeartbeatSynRequestBuilder setWifiMac(String wifiMac) {
        bizContent.wifiMac = wifiMac;
        return this;
    }

    public String getWifiName() {
        return bizContent.wifiName;
    }

    public AlipayHeartbeatSynRequestBuilder setWifiName(String wifiName) {
        bizContent.wifiName = wifiName;
        return this;
    }

    public String getNetworkStatus() {
        return bizContent.networkStatus;
    }

    public AlipayHeartbeatSynRequestBuilder setNetworkStatus(String networkStatus) {
        bizContent.networkStatus = networkStatus;
        return this;
    }

    public String getBbsPosition() {
        return bizContent.bbsPosition;
    }

    public AlipayHeartbeatSynRequestBuilder setBbsPosition(String bbsPosition) {
        bizContent.bbsPosition = bbsPosition;
        return this;
    }

    public String getManufacturerPid() {
        return bizContent.manufacturerPid;
    }

    public AlipayHeartbeatSynRequestBuilder setManufacturerPid(String manufacturerPid) {
        bizContent.manufacturerPid = manufacturerPid;
        return this;
    }

    public String getProviderId() {
        return bizContent.providerId;
    }

    public AlipayHeartbeatSynRequestBuilder setProviderId(String providerId) {
        bizContent.providerId = providerId;
        return this;
    }

    public String getEquipmentId() {
        return bizContent.equipmentId;
    }

    public AlipayHeartbeatSynRequestBuilder setEquipmentId(String equipmentId) {
        bizContent.equipmentId = equipmentId;
        return this;
    }

    public String getEquipmentPosition() {
        return bizContent.equipmentPosition;
    }

    public AlipayHeartbeatSynRequestBuilder setEquipmentPosition(String equipmentPosition) {
        bizContent.equipmentPosition = equipmentPosition;
        return this;
    }

    public EquipStatus getEquipmentStatus() {
        return bizContent.equipmentStatus;
    }

    public AlipayHeartbeatSynRequestBuilder setEquipmentStatus(EquipStatus equipmentStatus) {
        bizContent.equipmentStatus = equipmentStatus;
        return this;
    }

    public List<ExceptionInfo> getExceptionInfoList() {
        return bizContent.exceptionInfoList;
    }

    public AlipayHeartbeatSynRequestBuilder setExceptionInfoList(List<ExceptionInfo> exceptionInfoList) {
        bizContent.exceptionInfoList = exceptionInfoList;
        return this;
    }

    public Map<String, Object> getExtendInfo() {
        return bizContent.extendInfo;
    }

    public AlipayHeartbeatSynRequestBuilder setExtendInfo(Map<String, Object> extendInfo) {
        bizContent.extendInfo = extendInfo;
        return this;
    }

    public String getIp() {
        return bizContent.ip;
    }

    public AlipayHeartbeatSynRequestBuilder setIp(String ip) {
        bizContent.ip = ip;
        return this;
    }

    public Product getProduct() {
        return bizContent.product;
    }

    public AlipayHeartbeatSynRequestBuilder setProduct(Product product) {
        bizContent.product = product;
        return this;
    }

    public String getStoreId() {
        return bizContent.storeId;
    }

    public AlipayHeartbeatSynRequestBuilder setStoreId(String storeId) {
        bizContent.storeId = storeId;
        return this;
    }

    public String getTime() {
        return bizContent.time;
    }

    public AlipayHeartbeatSynRequestBuilder setTime(String time) {
        bizContent.time = time;
        return this;
    }

    public List<TradeInfo> getTradeInfoList() {
        return bizContent.tradeInfoList;
    }

    public AlipayHeartbeatSynRequestBuilder setSysTradeInfoList(List<SysTradeInfo> sysTradeInfoList) {
        if (Utils.isListNotEmpty(sysTradeInfoList)) {
            bizContent.tradeInfoList = new ArrayList<TradeInfo>(sysTradeInfoList);
        }
        return this;
    }

    public AlipayHeartbeatSynRequestBuilder setPosTradeInfoList(List<PosTradeInfo> posTradeInfoList) {
        if (Utils.isListNotEmpty(posTradeInfoList)) {
            bizContent.tradeInfoList = new ArrayList<TradeInfo>(posTradeInfoList);
        }
        return this;
    }

    public Type getType() {
        return bizContent.type;
    }

    public AlipayHeartbeatSynRequestBuilder setType(Type type) {
        bizContent.type = type;
        return this;
    }

    public static class BizContent {
        private Product product;

        private Type type;

        @SerializedName("equipment_id")
        private String equipmentId;

        @SerializedName("equipment_status")
        private EquipStatus equipmentStatus;

        private String time;

        @SerializedName("manufacturer_app_id")
        private String manufacturerPid;

        @SerializedName("sys_service_provider_id")
        private String providerId;

        @SerializedName("store_id")
        private String storeId;

        @SerializedName("equipment_position")
        private String equipmentPosition;

        @SerializedName("bbs_position")
        private String bbsPosition;

        @SerializedName("network_status")
        private String networkStatus;

        @SerializedName("network_type")
        private String networkType;

        private String battery;

        @SerializedName("wifi_mac")
        private String wifiMac;

        @SerializedName("wifi_name")
        private String wifiName;

        private String ip;

        private String mac;

        @SerializedName("trade_info")
        private List<TradeInfo> tradeInfoList;

        @SerializedName("exception_info")
        private List<ExceptionInfo> exceptionInfoList;

        @SerializedName("extend_info")
        private Map<String, Object> extendInfo;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("product=").append(product);
            sb.append(", type=").append(type);
            sb.append(", equipmentId='").append(equipmentId).append('\'');
            sb.append(", equipmentStatus=").append(equipmentStatus);
            sb.append(", time='").append(time).append('\'');
            sb.append(", manufacturerPid='").append(manufacturerPid).append('\'');
            sb.append(", providerId='").append(providerId).append('\'');
            sb.append(", storeId='").append(storeId).append('\'');
            sb.append(", equipmentPosition='").append(equipmentPosition).append('\'');
            sb.append(", bbsPosition='").append(bbsPosition).append('\'');
            sb.append(", networkStatus='").append(networkStatus).append('\'');
            sb.append(", networkType='").append(networkType).append('\'');
            sb.append(", battery='").append(battery).append('\'');
            sb.append(", wifiMac='").append(wifiMac).append('\'');
            sb.append(", wifiName='").append(wifiName).append('\'');
            sb.append(", ip='").append(ip).append('\'');
            sb.append(", mac='").append(mac).append('\'');
            sb.append(", tradeInfoList=").append(tradeInfoList);
            sb.append(", exceptionInfoList=").append(exceptionInfoList);
            sb.append(", extendInfo=").append(extendInfo);
            sb.append('}');
            return sb.toString();
        }
    }
}
