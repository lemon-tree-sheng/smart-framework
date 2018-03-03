package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author shengxingyue, created on 2018/3/3
 */
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> cls) {
        Object obj;
        try {
            obj = cls.newInstance();
        } catch (ReflectiveOperationException e) {
            LOGGER.error("new instance failure, className:{}, {}", cls.getName(), e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (ReflectiveOperationException e) {
            LOGGER.error("invoke method:{} failure,{}", method.getName(), e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object obj, Field field, Object arg) {
        try {
            field.setAccessible(true);
            field.set(obj, arg);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field:{} failure, {}", field.getName(), e);
            throw new RuntimeException(e);
        }
    }
}
