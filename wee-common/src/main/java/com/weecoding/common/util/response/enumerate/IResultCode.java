package com.weecoding.common.util.response.enumerate;

/**
 * @author : wee
 * @Description: 枚举顶级code
 * @Date 2019-07-10  17:28
 */
public interface IResultCode {
    /**
     * 获取Code
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取提示信息
     *
     * @return
     */
    String getMsg();
}
