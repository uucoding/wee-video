package com.weecoding.service.enumerate;

import com.weecoding.common.util.response.enumerate.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户相关返回码
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  17:22
 */
@Getter
@AllArgsConstructor
public enum UserResultEnum implements IResultCode {
    USER_PARAMS_IS_EMPTY(100, "用户名或密码为空!"),
    PASSWORD_RE_PASSWORD_DIFFERENCE(101, "两次密码不一致!"),
    DB_EXIST_USER(103, "用户已存在!"),
    DB_NOT_EXIST_USER(105, "用户不存在!"),
    PASSWORD_ERROR(106, "密码错误!"),
    REDIS_CACHE_NOT_EXIST(107, "用户已经退出!"),
    CHANGE_PASSWORD_PARAMS_IS_EMPTY(108, "密码不能为空!"),
    CHANGE_PASSWORD_OLD_IS_ERROR(109, "旧密码错误!"),
    ;

    private Integer code;

    private String msg;
}
