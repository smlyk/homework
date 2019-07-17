package com.smlyk.formator;

import com.alibaba.fastjson.JSON;

/**
 * @author yekai
 */
public class JsonFormatProcessor implements FormatProcessor {

    @Override
    public <T> String format(T obj) {
        return "JsonFormatProcessor: "+ JSON.toJSONString(obj);
    }
}
