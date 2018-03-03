package org.smart4j.framework.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author shengxingyue, created on 2018/3/3
 */
public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                Class<?> clazz = entry.getKey();
                Object obj = entry.getValue();
                Field[] fields = clazz.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(fields)) {
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            Class<?> objFieldClass = field.getType();
                            Object fieldObject = BeanHelper.getBean(objFieldClass);
                            if (fieldObject != null) {
                                ReflectionUtil.setField(obj, field, fieldObject);
                            }
                        }
                    }
                }
            }
        }
    }
}
