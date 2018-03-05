package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author shengxingyue, created on 2018/3/4
 */
public class ProxyManager {

    public static <T> T createProxy(final Class<T> tClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(tClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(tClass, o, method, methodProxy, objects, proxyList).doProxyChain();
            }
        });
    }
}
