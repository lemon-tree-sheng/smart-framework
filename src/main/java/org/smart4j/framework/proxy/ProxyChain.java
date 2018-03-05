package org.smart4j.framework.proxy;

import lombok.Getter;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shengxingyue, created on 2018/3/4
 */
public class ProxyChain {
    @Getter
    private final Class<?> targetClass;
    private final Object targetObject;
    @Getter
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    @Getter
    private final Object[] methodParams;

    private List<Proxy> proxyList = new ArrayList<>();

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    private int proxyIndex = 0;

    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if (this.proxyIndex < this.proxyList.size()) {
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        } else {
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }

}
