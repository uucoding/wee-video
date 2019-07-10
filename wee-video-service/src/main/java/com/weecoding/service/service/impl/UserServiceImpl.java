package com.weecoding.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.weecoding.common.enumerate.ErrorEnum;
import com.weecoding.common.enumerate.IResultCode;
import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.common.util.V;
import com.weecoding.common.util.bean.BeanUtils;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.form.UserForm;
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

    @Override
    public IResultCode register(UserForm userForm) throws Exception {
        //1、校验入参
        if (V.isEmpty(userForm.getUsername()) || V.isEmpty(userForm.getPassword()) || V.isEmpty(userForm.getRePassword())) {
            return UserResultEnum.USERNAME_PASSWORD_RE_PASSWORD_IS_EMPTY;
        }
        //2、校验两次密码是否一致
        if (V.equals(userForm.getPassword(), userForm.getRePassword())) {
            return UserResultEnum.PASSWORD_RE_PASSWORD_DIFFERENCE;
        }
        //3、校验数据是否存在数据库: 根据用户名查找
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, userForm.getUsername());
        if (V.notEmpty(super.getOne(lambdaQueryWrapper))) {
            return UserResultEnum.DB_EXIST_USER;
        }
        //4、补全其他信息 todo 暂时密码明文存储
//        userForm.
        User user = BeanUtils.copyBean(userForm, User.class);
        user.setNickname(user.getUsername());
        user.setFaceImage("/us");
        if (super.save(user)) {
            return UserResultEnum.STORAGE_USER_SUCCESS;
        }
        return ErrorEnum.ERROR;
    }


}
