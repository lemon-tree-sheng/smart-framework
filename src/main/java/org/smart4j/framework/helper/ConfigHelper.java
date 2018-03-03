package org.smart4j.framework.helper;

import org.smart4j.framework.ConfigConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Properties;

import static org.smart4j.framework.util.PropsUtil.getString;

/**
 * @author shengxingyue, created on 2018/3/2
 */
public class ConfigHelper {
    private static final Properties props = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getJdbcDriver() {
        return getString(props, ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return getString(props, ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUsername() {
        return getString(props, ConfigConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return getString(props, ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePackage() {
        return getString(props, ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath() {
        return getString(props, ConfigConstant.APP_JSP_PATH, "/WEB-INF/views/");
    }

    public static String getAppAssetPath() {
        return getString(props, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }

}
