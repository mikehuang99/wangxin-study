package com.wangxin.langshu.business.util.alipay.trade.model.hb;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by liuyangkly on 15/8/27.
 */
public class EquipStatusAdapter implements JsonSerializer<EquipStatus> {
    @Override
    public JsonElement serialize(EquipStatus equipStatus, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(equipStatus.getValue());
    }
}
