package com.changyi.common.core.utils;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 基于jackson的json工具类
 *
 * @author ZhangHao
 * @date 2021.3.18
 */
@Slf4j
public final class JsonUtil {
    /**
     * 建立Jackson的ObjectMapper对象
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 建立Json操作中的日期格式
     */
    private static final String JSON_STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        //对象的所有字段全部列入
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        //取消默认转换timestamps形式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空Bean转json的错误
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //所有的日期格式都统一为以下的样式
        mapper.setDateFormat(new SimpleDateFormat(JSON_STANDARD_FORMAT));

        //反序列化
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 转化为json字符串
     *
     * @param obj obj
     * @return {@link String}
     */
    public static String toJSONStr(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json to String error." + obj, e);
            return null;
        }
    }


    /**
     * 解析为Bean
     *
     * @param json   json
     * @param tClass t类
     * @return {@link T}
     */
    public static <T> T toBean(String json, Class<T> tClass) {
        if (StrUtil.isEmpty(json)) {
            log.warn("Json string {} is empty!", tClass);
            return null;
        }

        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            log.error("json to bean error." + json, e);
            return null;
        }
    }

    /**
     * json转化为带泛型的对象
     *
     * @param jsonStr json字符串
     * @param typeReference 转化类型
     * @return 对象
     */
    public static <T> T toObj(String jsonStr, TypeReference<T> typeReference) {
        if (StrUtil.isBlank(jsonStr) || typeReference == null) {
            return null;
        }
        try {
            return mapper.readValue(jsonStr, typeReference);
        } catch (JsonProcessingException e) {
            log.error("json to Obj error.", e);
        }
        return null;
    }

    /**
     * 解析为List
     *
     * @param json   json
     * @param eClass e类
     * @return {@link List<E>}
     */
    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            log.error("json to List error." + json, e);
            return Collections.emptyList();
        }
    }


    /**
     * 解析为Map
     *
     * @param json   json
     * @param kClass k类
     * @param vClass v类
     * @return {@link Map<K, V>}
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            log.error("json to Map error." + json, e);
            return null;
        }
    }
}
