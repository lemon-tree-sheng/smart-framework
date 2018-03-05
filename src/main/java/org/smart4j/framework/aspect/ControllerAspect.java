package org.smart4j.framework.aspect;

import lombok.extern.slf4j.Slf4j;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@Slf4j
public class ControllerAspect extends AspectProxy {
    private long accessTime;

    @Override
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
        log.info("开始访问 {} 的 {} 方法，参数：{}", cls.getName(), method.getName(), params);
        accessTime = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
        log.info("结束访问 {} 的 {} 方法，参数：{}", cls.getName(), method.getName(), params);
        long time = System.currentTimeMillis() - accessTime;
        log.info("方法处理时间：{}ms", time);
    }
}
