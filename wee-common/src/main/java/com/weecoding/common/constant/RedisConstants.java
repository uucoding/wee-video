package com.weecoding.common.constant;


/**
 * redis常量
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  16:28
 */
public interface RedisConstants {

    /**
     * token名称
     */
    String TOKEN = "token";
    /**
     * token的模版
     */
    String TOKEN_KEY_TEMPLATE = "token_%s";

    /**
     * 过期时间 （s）
     */
    Long EXPIRED_TIME = 7200L;

    /**
     * 默认剩余时间 （s）
     */
    Long REMAIN_TIME = 600L;
}
