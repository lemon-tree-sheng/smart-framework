package org.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author shengxingyue, created on 2018/3/3
 */
@Data
@AllArgsConstructor
public class Request {
    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
