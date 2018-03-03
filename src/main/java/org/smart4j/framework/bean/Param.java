package org.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.smart4j.framework.util.CastUtil;

import java.util.Map;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@AllArgsConstructor
@Getter
public class Param {
    private Map<String, Object> paramMap;

    public long getLong(String paramKey) {
        return CastUtil.castLong(paramMap.get(paramKey));
    }
}
