package org.smart4j.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author shengxingyue, created on 2018/3/1
 */
@Slf4j
public class PropsUtil {

    public static Properties loadProps(String fileName) {
        Properties properties = null;
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new FileNotFoundException("file not found:" + fileName);
            }
            properties = new Properties();
            properties.load(is);
        } catch (Exception e) {
            log.error("load file failure");
        }
        return properties;
    }

    public static String getString(Properties properties, String key, String defaultValue) {
        String result = defaultValue;
        if (properties.containsKey(key)) {
            result = properties.getProperty(key);
        }
        return result;
    }

    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        int result = defaultValue;
        if (properties.containsKey(key)) {
            result = CastUtil.castInt(getString(properties, key));
        }
        return result;
    }

    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    public static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        boolean result = defaultValue;
        if (properties.containsKey(key)) {
            result = CastUtil.castBoolean(getString(properties, key));
        }
        return result;
    }

    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }
}
