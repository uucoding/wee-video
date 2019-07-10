package com.weecoding.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Spring表单自动绑定到Java属性时的日期格式转换
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String dateString) {
        return D.fuzzyConvert(dateString);
    }
}