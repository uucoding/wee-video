package com.weecoding.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.weecoding.common.enumerate.ErrorEnum;
import com.weecoding.common.exception.DefaultException;
import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.common.util.SecretUtil;
import com.weecoding.common.util.V;
import com.weecoding.common.util.bean.BeanUtils;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.mapper.UserMapper;
import com.weecoding.service.model.User;
import com.weecoding.service.service.UserService;


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
    @Transactional(rollbackFor = Exception.class)
    public void register(UserForm userForm) throws Exception {
        //1、校验入参
        if (V.isEmpty(userForm.getUsername()) || V.isEmpty(userForm.getPassword()) || V.isEmpty(userForm.getRePassword())) {
            throw new DefaultException(UserResultEnum.USER_PARAMS_IS_EMPTY);
        }
        //2、校验两次密码是否一致
        if (!V.equals(userForm.getPassword(), userForm.getRePassword())) {
            throw new DefaultException(UserResultEnum.PASSWORD_RE_PASSWORD_DIFFERENCE);
        }
        //3、校验数据是否存在数据库: 根据用户名查找
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, userForm.getUsername());
        if (V.notEmpty(super.getOne(lambdaQueryWrapper))) {
            throw new DefaultException(UserResultEnum.DB_EXIST_USER);
        }
        //4、补全其他信息
//        userForm.
        User user = BeanUtils.copyBean(userForm, User.class);
        user.setPassword(SecretUtil.encryptMD5(    user.getPassword()));
        user.setNickname(user.getUsername());
        user.setFaceImage(".png");
        if (!super.save(user)) {
            throw new DefaultException(ErrorEnum.ERROR);
        }
    }

    @Override
    public User login(UserForm userForm) throws Exception {
        //1、校验入参
        if (V.isEmpty(userForm.getUsername()) || V.isEmpty(userForm.getPassword())) {
            throw new DefaultException(UserResultEnum.USER_PARAMS_IS_EMPTY);
        }
        //2、校验数据是否存在数据库: 根据用户名查找
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, userForm.getUsername());
        User dbUser = super.getOne(lambdaQueryWrapper);
        //用户不存在，抛出异常
        if (V.isEmpty(dbUser)) {
            throw new DefaultException(UserResultEnum.DB_NOT_EXIST_USER);
        }
        //3、比较密码是否相同：
        if (!V.equals(SecretUtil.encryptMD5(userForm.getPassword()), dbUser.getPassword())) {
            throw new DefaultException(UserResultEnum.PASSWORD_ERROR);
        }
        return dbUser;
    }


}
