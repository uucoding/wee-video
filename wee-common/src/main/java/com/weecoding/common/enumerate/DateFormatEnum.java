package com.weecoding.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间日期的格式枚举
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-05  00:54
 */

@Getter
@AllArgsConstructor
public enum DateFormatEnum {
    yyMM("yyMM"),
    yyMMdd("yyMMdd"),
    yyyy("yyyy"),
    yyyyMMdd("yyyyMMdd"),
    yyMMddhhmmss("yyMMddmmss"),
    yyyy_MM_dd("yyyy-MM-dd"),
    HH_mm("HH:mm"),
    HH_mm_ss("HH:mm:ss"),
    yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"),
    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
            ;
    private String format;
}
