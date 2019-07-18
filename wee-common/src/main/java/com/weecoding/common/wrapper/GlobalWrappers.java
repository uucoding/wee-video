package com.weecoding.common.wrapper;

import com.weecoding.common.constant.BaseConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 集合帮助类
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  00:20
 */
public class GlobalWrappers {

    /**
     * 创建Map
     * @param <K>
     * @param <V>
     * @param size map长度，默认长度为16
     * @return
     */
    public static <K, V> MapWrapper<K, V> createHashMap(int ... size) {
        int value = BaseConstants.MAP_INIT_SIZE;
        if (size !=  null && size.length > 0) {
            value = size[0];
        }
        return new MapWrapper<>(new HashMap<K, V>(value));
    }

    /**
     * 创建ArrayList集合
     * @param <T>
     * @return
     */
    public static <T >CollectionWrapper<T> createArrayList() {
        return new CollectionWrapper<T>(new ArrayList<>());
    }

    /**
     * 创建HashSet集合
     * @param <T>
     * @return
     */
    public static <T>CollectionWrapper<T> createHashSet() {
        return new CollectionWrapper<T>(new HashSet<>());
    }

}
