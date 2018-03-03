package org.smart4j.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author shengxingyue, created on 2018/3/4
 */
@Slf4j
public class CodecUtil {

    public static String encodeUrl(String url) {
        String target;
        try {
            target = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("url encode error...url:{}", url);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeUrl(String url) {
        String target;
        try {
            target = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("url decode error...url:{}", url);
            throw new RuntimeException(e);
        }
        return target;
    }

}
