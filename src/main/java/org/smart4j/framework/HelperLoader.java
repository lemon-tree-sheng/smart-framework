package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * @author shengxingyue, created on 2018/3/3
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] clss = {ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class};
        for (Class<?> aClass : clss) {
            ClassUtil.loadClass(aClass.getName());
        }
    }
}
