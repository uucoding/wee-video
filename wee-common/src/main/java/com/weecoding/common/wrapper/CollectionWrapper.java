package com.weecoding.common.wrapper;

import lombok.AllArgsConstructor;

import java.util.Collection;

/**
 * 链式操作集合相关
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  00:30
 */
@AllArgsConstructor
public class CollectionWrapper<T, E extends Collection> {


    private E collection;

    /**
     * 链式添加一个对象
     *
     * @param t
     * @return {@link CollectionWrapper}
     */
    public CollectionWrapper<T, E> add(T t) {
        this.collection.add(t);
        return this;
    }

    /**
     * 链式添加多个对象
     *
     * @param e
     * @return {@link CollectionWrapper}
     */
    public CollectionWrapper<T, E> addAll(E e) {
        this.collection.addAll(e);
        return this;
    }

    /**
     * 删除对象
     *
     * @param t
     * @return {@link CollectionWrapper}
     */
    public CollectionWrapper<T, E> remove(T t) {
        this.collection.remove(t);
        return this;
    }

    /**
     * 构建出具体集合对象
     *
     * @return {@link E}
     */
    public E build() {
        return this.collection;
    }
}
