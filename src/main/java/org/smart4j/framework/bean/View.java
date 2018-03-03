package org.smart4j.framework.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@Data
@AllArgsConstructor
public class View {
    private String path;
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public void addModel(String key, Object value) {
        model.put(key, value);
    }
}
