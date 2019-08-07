package com.weecoding.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.weecoding.common.model.Entity;

import java.util.List;

/**
 * json处理
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  22:33
 */
public class JSON {

    /**
     * 将json对象转化成Object对象
     *
     * @param text
     * @return
     */
    public static Object toObject(String text) {
        return JSONObject.parseObject(text);
    }

    /**
     * 将json对象转化成bean对象
     *
     * @param text
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T extends Entity> T toBean(String text, Class<T> beanClass) {
        return JSONObject.parseObject(text, beanClass);
    }

    /**
     * 将json集合转化成指定Bean
     *
     * @param text
     * @param type 需要转化bean的类型
     * @param <T>  extends BaseBean
     * @return
     */
    public static <T extends Entity> List<T> toBeanList(String text, TypeReference<List<T>> type) {
        return JSONObject.parseObject(text, type);
    }

    /**
     * 将Object转化成json字符串
     *
     * @param object
     * @return
     */
    public static String object2jsonString(Object object) {
        return com.alibaba.fastjson.JSON.toJSONString(object);
    }
}
