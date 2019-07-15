package com.weecoding.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weecoding.common.model.Bean;

/**
 * 所以service接口
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:10
 */
public interface IBaseService<T extends Bean> extends IService<T> {
}
