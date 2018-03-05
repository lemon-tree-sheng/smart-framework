package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author shengxingyue, created on 2018/3/5
 */
public class AopHelper {

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> result = new HashSet<>();
        Class<? extends Annotation> targetCls = aspect.value();
        if (targetCls != null && !targetCls.equals(Aspect.class)) {
            result.addAll(ClassHelper.getClassSetByAnnotation(targetCls));
        }
        return result;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, Set<Class<?>>> result = new HashMap<>();
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> aClass : proxyClassSet) {
            if (aClass.isAnnotationPresent(Aspect.class)) {
                Aspect aspect = aClass.getAnnotation(Aspect.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                result.put(aClass, targetClassSet);
            }
        }
        return result;
    }

    // todo aopHelper 未写完，未添加到 helperLoader 当中
}
