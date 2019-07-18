package com.weecoding.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 默认错误消息
 *
 * @author : wee
 * @version v1.0
 * @Date 2019-07-10  17:35
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum implements IResultCode {
    ERROR(4000, "操作失败"),
    LOGIN_VISIT(4003, "登陆后访问"),
    ;

    /**
     * code
     */
    private Integer code;

    /**
     * 描述
     */
    private String msg;
}
