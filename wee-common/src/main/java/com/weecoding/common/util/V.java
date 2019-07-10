package com.weecoding.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 校验类
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-05  00:18
 */
@Slf4j
public class V {

    /***
     * 字符串是否为空
     * @param value
     * @return
     */
    public static boolean isEmpty(String value){
        return S.isBlank(value);
    }

    /***
     * 字符串数组是否不为空
     * @param values
     * @return
     */
    public static boolean isEmpty(String[] values){
        return values == null || values.length == 0;
    }
    /***
     * 时间数组是否不为空
     * @param dates
     * @return
     */
    public static boolean isEmpty(Date[] dates){
        return dates == null || dates.length == 0;
    }

    /***
     * 集合为空
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    /***
     * Map为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Map obj){
        return obj == null || obj.isEmpty();
    }

    /***
     * 对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj instanceof String){
            return isEmpty((String)obj);
        }
        else if(obj instanceof Collection){
            return isEmpty((Collection)obj);
        }
        else if(obj instanceof Map){
            return isEmpty((Map)obj);
        }
        else if(obj instanceof String[]){
            return isEmpty((String[])obj);
        }
        else if(obj instanceof Date[]){
            return isEmpty((Date[])obj);
        }
        else{
            return obj == null;
        }
    }

    /***
     * 对象是否为空
     * @param obj
     * @return
     */
    public static boolean notEmpty(Object obj){
        return !isEmpty(obj);
    }

    /***
     * 字符串是否不为空
     * @param value
     * @return
     */
    public static boolean notEmpty(String value){
        return !isEmpty(value);
    }

    /***
     * 字符串数组是否不为空
     * @param values
     * @return
     */
    public static boolean notEmpty(String[] values){
        return !isEmpty(values);
    }

    /***
     * 集合不为空
     * @param list
     * @return
     */
    public static <T> boolean notEmpty(Collection<T> list) {
        return !isEmpty(list);
    }


    /***
     * 判定两个对象是否类型相同值相等
     * @param source
     * @param target
     * @return
     */
    public static boolean equals(Object source, Object target){
        return Objects.equals(source, target);
    }

    /***
     * 判定两个对象是否不同类型或不同值
     * @param source
     * @param target
     * @return
     */
    public static boolean notEquals(Object source, Object target){
        return !equals(source, target);
    }

}
