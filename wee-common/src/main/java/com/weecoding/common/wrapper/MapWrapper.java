package com.weecoding.common.wrapper;

import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * map包装：允许链式调用
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  00:30
 */
@AllArgsConstructor
public class MapWrapper<K, V> {

    private Map<K, V> map;

    /**
     * 链式添加map的数据
     *
     * @param k     key
     * @param v     value
     * @return {@link MapWrapper}
     */
    public MapWrapper<K, V> put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    /**
     * 链式添加map的数据
     *
     * @param k     key
     * @return {@link MapWrapper}
     */
    public MapWrapper<K, V> put(K k) {
        this.map.remove(k);
        return this;
    }
    /**
     * 构建出具体Map对象
     *
     * @return {@link Map<K, V>}
     */
    public Map<K, V> build() {
        return this.map;
    }
}
