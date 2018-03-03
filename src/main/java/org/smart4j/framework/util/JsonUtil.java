package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@Slf4j
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object object) {
        String target;
        try {
            target = OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("obj to json error...obj:{}", object);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        T target;
        try {
            target = OBJECT_MAPPER.readValue(json, tClass);
        } catch (IOException e) {
            log.error("json to obj error...json:{} | class:{}", json, tClass);
            throw new RuntimeException(e);
        }
        return target;
    }
}
