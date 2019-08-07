package com.weecoding.common.util.response.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件相关枚举
 * @author : wee
 * @version v1.0
 * @Date 2019-07-31  00:53
 */
@Getter
@AllArgsConstructor
public enum FileEnum implements IResultCode {

    PARAM_FILE_NOT_EXIST(101, "上传文件不存在！"),

    ;

    private Integer code;
    private String msg;
}
