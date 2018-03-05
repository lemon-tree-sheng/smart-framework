package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shengxingyue, created on 2018/3/3
 */
public final class ClassHelper {
    private static Set<Class<?>> CLASS_SET = null;

    static {
        CLASS_SET = ClassUtil.getClassSet(ConfigConstant.APP_BASE_PACKAGE);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getControllerClassSet() {
        Set<Class<?>> result = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Controller.class)) {
                result.add(cls);
            }
        }
        return result;
    }

    public static Set<Class<?>> getServiceClassSet() {
        Set<Class<?>> result = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(Service.class)) {
                result.add(cls);
            }
        }
        return result;
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> result = new HashSet<>();
        result.addAll(getControllerClassSet());
        result.addAll(getServiceClassSet());
        return result;
    }

    public static Set<Class<?>> getClassSetBySuper(Class<?> cls) {
        Set<Class<?>> result = new HashSet<>();
        for (Class<?> aClass : CLASS_SET) {
            if (cls.isAssignableFrom(aClass) && !aClass.equals(cls)) {
                result.add(aClass);
            }
        }
        return result;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
        Set<Class<?>> result = new HashSet<>();
        for (Class<?> aClass : CLASS_SET) {
            if (aClass.isAnnotationPresent(annotationClass)) {
                result.add(aClass);
            }
        }
        return result;
    }
}
