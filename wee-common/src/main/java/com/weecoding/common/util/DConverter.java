package com.weecoding.common.util;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Spring表单自动绑定到Java属性时的日期格式转换
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-15  09:12
 */
public class DConverter implements Converter<String, Date> {
    @Override
    public Date convert(String dateString) {
        return D.fuzzyConvert(dateString);
    }
}
