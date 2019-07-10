package com.weecoding.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举为{@link com.weecoding.common.util.response.JsonResult}提供状态数据
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  15:57
 */
@Getter
@AllArgsConstructor
public enum JsonStatusEnum {
    ok(0, "操作成功！"),
    /**
     * 自定义错误内容
     */
    errorMsg(6000, null);

    private Integer code;
    private String msg;
}
