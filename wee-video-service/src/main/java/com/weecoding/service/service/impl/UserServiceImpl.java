package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.UserMapper;
import com.weecoding.service.model.User;
import com.weecoding.service.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
