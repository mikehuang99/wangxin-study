package com.wangxin.langshu.business.util.alipay.trade.model.hb;

/**
 * Created by liuyangkly on 15/8/27.
 */
public enum HbStatus {
     S // 交易成功（包括支付宝返回“处理中”）

    ,I // 支付宝返回处理中

    ,F // 支付宝返回失败

    ,P // POSP返回失败，或商户系统失败

    ,X // 建立连接异常

    ,Y // 报文上送异常

    ,Z // 报文接收异常

    ,C // 收银员取消
}
