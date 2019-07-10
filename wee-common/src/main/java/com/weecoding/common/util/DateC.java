package com.weecoding.common.util;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 时间转化
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:10
 */
public class DateC implements Converter<String, Date> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Date convert(String source) {
        return D.fuzzyConvert(source);
    }
}
