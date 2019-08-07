package com.weecoding.common.controller;

import com.weecoding.common.service.IBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础controller，自动注入baseService, 传递的参数为当前主类的service
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-12  00:35
 */
@Slf4j
public class BaseController<T extends IBaseService> {

    @Autowired
    public T baseService;

    public T getBaseService() {
        return baseService;
    }
}
