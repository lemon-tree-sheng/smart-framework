package org.smart4j.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@Slf4j
public class StreamUtil {

    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.error("read inputstream error");
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
