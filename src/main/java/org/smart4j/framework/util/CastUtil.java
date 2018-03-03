package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author shengxingyue, created on 2018/3/2
 */
public class CastUtil {
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static String castString(Object obj) {
        return castString(obj, "");
    }

    public static int castInt(Object obj, int defaultValue) {
        int result = defaultValue;
        if (obj != null) {
            String tmpString = castString(obj);
            if (StringUtils.isNotEmpty(tmpString)) {
                try {
                    result = Integer.parseInt(tmpString);
                } catch (NumberFormatException e) {
                    return result;
                }
            }
        }
        return result;
    }

    public static int castInt(Object obj) {
        return castInt(obj, 0);
    }

    public static double castDouble(Object obj, double defaultValue) {
        double result = defaultValue;
        if (obj != null) {
            String tmpString = castString(obj);
            if (StringUtils.isNotEmpty(tmpString)) {
                try {
                    result = Double.parseDouble(tmpString);
                } catch (NumberFormatException e) {
                    return result;
                }
            }
        }
        return result;
    }

    public static double castDouble(Object obj) {
        return castDouble(obj, 0);
    }

    public static long castLong(Object obj, long defaultValue) {
        long result = defaultValue;
        if (obj != null) {
            String tmpString = castString(obj);
            if (StringUtils.isNotEmpty(tmpString)) {
                try {
                    result = Long.parseLong(tmpString);
                } catch (NumberFormatException e) {
                    return result;
                }
            }
        }
        return result;
    }

    public static long castLong(Object obj) {
        return castLong(obj, 0L);
    }

    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean result = defaultValue;
        if (obj != null) {
            String tmpString = castString(obj);
            if (StringUtils.isNotEmpty(tmpString)) {
                try {
                    result = Boolean.parseBoolean(tmpString);
                } catch (NumberFormatException e) {
                    return result;
                }
            }
        }
        return result;
    }

    public static boolean castBoolean(Object obj) {
        return castBoolean(obj, false);
    }

}
