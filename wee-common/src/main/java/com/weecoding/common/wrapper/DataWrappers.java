package com.weecoding.common.wrapper;

import com.weecoding.common.constant.BaseConstants;

import java.util.*;

/**
 * 数据帮助类
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  00:20
 */
public class DataWrappers {

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
        return new MapWrapper<K, V>(new HashMap<K, V>(value));
    }

    /**
     * 创建ArrayList集合
     * @param <T>
     * @return
     */
    public static <T>CollectionWrapper<T, List<T>> createArrayList() {
        return new CollectionWrapper<T, List<T>>(new ArrayList<T>());
    }

    /**
     * 创建ArrayList集合
     * @param <T>
     * @return
     */
    public static <T>CollectionWrapper<T, List<T>> createArrayList(List<T> defaultList) {
        return new CollectionWrapper<T, List<T>>(new ArrayList<T>(defaultList));
    }

    /**
     * 创建HashSet集合
     * @param <T>
     * @return {@link CollectionWrapper<T, Set<T>>}
     */
    public static <T>CollectionWrapper<T, Set<T>> createHashSet() {
        return new CollectionWrapper<T, Set<T>>(new HashSet<T>());
    }


}
