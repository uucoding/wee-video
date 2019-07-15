package com.weecoding.common.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weecoding.common.mapper.IBaseMapper;
import com.weecoding.common.model.Bean;

/**
 * 基础的service
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:13
 */
public class BaseServiceImpl<M extends IBaseMapper<T>, T extends Bean> extends ServiceImpl<M, T> implements IBaseService<T> {
}
