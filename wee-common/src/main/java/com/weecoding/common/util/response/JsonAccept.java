package com.weecoding.common.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>接受响应数据</h3>
 * 该类主要用于两个系统交互，其中A系统返回{@link JsonResult}, B系统使用此类做映射关系
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  15:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonAccept<T> {
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String msg;

    /**
     * 响应的数据
     */
    private T data;
}
