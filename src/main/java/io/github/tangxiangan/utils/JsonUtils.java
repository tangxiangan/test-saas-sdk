package io.github.tangxiangan.utils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tangxiangan.pojo.ResultMap;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonUtils
 *
 * @author tangxiangan
 */
public class JsonUtils {
    private final static SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * getJsonString
     *
     * @param bean Can be a basic object or an array
     * @return String
     */
    public static String getJsonString(Object bean) {
        return getJsonString(bean, DEFAULT_FORMAT);
    }

    /**
     * Convert object to JSON string
     *
     * @param bean       Can be a basic object or an array
     * @param dateFormat convert format
     * @return String
     */
    public static String getJsonString(Object bean, DateFormat dateFormat) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(dateFormat);
        try {
            return mapper.writeValueAsString(bean);
        } catch (Exception e) {
            return null;
        }
    }

}
