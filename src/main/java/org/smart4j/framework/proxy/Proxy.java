package org.smart4j.framework.proxy;

/**
 * @author shengxingyue, created on 2018/3/4
 */
public interface Proxy {

    /**
     * 执行链式代理
     *
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
