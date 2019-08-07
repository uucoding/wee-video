package com.weecoding.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weecoding.common.model.Entity;

/**
 * 基础mapper继承自{@link BaseMapper}
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:17
 */
public interface IBaseMapper<T extends Entity> extends BaseMapper<T> {
}
