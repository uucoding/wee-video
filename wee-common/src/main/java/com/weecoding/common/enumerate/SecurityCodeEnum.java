package com.weecoding.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 验证码枚举
 *
 * @author : wee
 * @version v1.0
 * @Date 2019-07-04  23:30
 */
@Getter
@AllArgsConstructor
public enum SecurityCodeEnum {

    /**
     * 纯数字类型
     */
    NUMBER("纯数字类型"),
    /**
     * 纯小写字母类型
     */
    LOWER_LETTER("纯小写字母类型"),
    /**
     * 纯大写字母类型
     */
    UPPER_LETTER("纯大写字母类型"),
    /**
     * 大小写字母混合类型
     */
    MIX_LETTER("大小写字母混合类型"),
    /**
     * 小写字母和数字混合类型
     */
    MIX_LOWER_LETTER_AND_NUMBER("小写字母和数字混合类型"),
    /**
     * 大写字母和数字混合类型
     */
    MIX_UPPER_LETTER_AND_NUMBER("大写字母和数字混合类型"),
    /**
     * 数字和所有字母混合类型
     */
    MIX_ALL_LETTER_AND_NUMBER("大小写字母和数字混合类型"),
    ;
    /**
     * 描述
     */
    private String description;
}
