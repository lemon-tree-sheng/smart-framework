package org.smart4j.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author shengxingyue, created on 2018/3/3
 */
public class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> aClass : controllerClassSet) {
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Action.class)) {
                        Action action = method.getAnnotation(Action.class);
                        String mapping = action.value();
                        if (mapping.matches("\\w+:/\\w*")) {
                            String[] mappings = mapping.split(":");
                            String requestMethod = mappings[0];
                            String requestPath = mappings[1];
                            Request request = new Request(requestMethod, requestPath);
                            Handler handler = new Handler(aClass, method);
                            ACTION_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
