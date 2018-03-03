package org.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author shengxingyue, created on 2018/3/3
 */
@Data
@AllArgsConstructor
public class Handler {
    /**
     * controller class
     */
    private Class<?> controllerClass;

    /**
     * action method
     */
    private Method actionMethod;
}
