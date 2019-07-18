package com.weecoding.common.wrapper;

import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * 链式操作集合相关
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  00:30
 */
@AllArgsConstructor
public class CollectionWrapper<T> {


    private Collection<T> collection;

    /**
     * 链式添加一个对象
     * @param t
     * @return
     */
    public CollectionWrapper add(T t) {
        this.collection.add(t);
        return this;
    }
    /**
     * 链式添加多个对象
     * @param t
     * @return
     */
    public CollectionWrapper addAll(Collection<T> t) {
        this.collection.addAll(t);
        return this;
    }

    public <E extends Collection<T>> E build() {
        return (E)this.collection;
    }
}
