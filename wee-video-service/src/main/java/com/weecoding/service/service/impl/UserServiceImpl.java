package com.weecoding.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.weecoding.common.constant.RedisConstants;
import com.weecoding.common.enumerate.SecurityCodeEnum;
import com.weecoding.common.exception.GlobalException;
import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.common.util.JSON;
import com.weecoding.common.util.S;
import com.weecoding.common.util.SecretUtil;
import com.weecoding.common.util.V;
import com.weecoding.common.util.bean.BeanUtils;
import com.weecoding.common.util.response.enumerate.ErrorEnum;
import com.weecoding.common.wrapper.DataWrappers;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.mapper.UserMapper;
import com.weecoding.service.model.User;
import com.weecoding.service.service.UserService;
import com.weecoding.service.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 用户service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:22
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserForm userForm) throws Exception {
        //1、校验入参
        if (V.isEmpty(userForm.getUsername()) || V.isEmpty(userForm.getPassword()) || V.isEmpty(userForm.getRePassword())) {
            throw new GlobalException(UserResultEnum.USER_PARAMS_IS_EMPTY);
        }
        //2、校验两次密码是否一致
        if (!V.equals(userForm.getPassword(), userForm.getRePassword())) {
            throw new GlobalException(UserResultEnum.PASSWORD_RE_PASSWORD_DIFFERENCE);
        }
        //3、校验数据是否存在数据库: 根据用户名查找
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, userForm.getUsername());
        if (V.notEmpty(super.getOne(lambdaQueryWrapper))) {
            throw new GlobalException(UserResultEnum.DB_EXIST_USER);
        }
        //4、补全其他信息
//        userForm.
        User user = BeanUtils.copyBean(userForm, User.class);
        user.setPassword(SecretUtil.encryptMD5(user.getPassword()));
        user.setNickname(user.getUsername());
        user.setFaceImage(".png");
        if (!super.save(user)) {
            throw new GlobalException(ErrorEnum.ERROR);
        }
    }

    @Override
    public Map<String, Object> login(UserForm userForm) throws Exception {
        //1、校验入参
        if (V.isEmpty(userForm.getUsername()) || V.isEmpty(userForm.getPassword())) {
            throw new GlobalException(UserResultEnum.USER_PARAMS_IS_EMPTY);
        }
        //2、校验数据是否存在数据库: 根据用户名查找
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, userForm.getUsername());
        User dbUser = super.getOne(lambdaQueryWrapper);
        //用户不存在，抛出异常
        if (V.isEmpty(dbUser)) {
            throw new GlobalException(UserResultEnum.DB_NOT_EXIST_USER);
        }
        //3、比较密码是否相同：
        if (!V.equals(SecretUtil.encryptMD5(userForm.getPassword()), dbUser.getPassword())) {
            throw new GlobalException(UserResultEnum.PASSWORD_ERROR);
        }
        //生成token
        String token = S.newRandom(32, SecurityCodeEnum.MIX_LOWER_LETTER_AND_NUMBER);
        //设置数据进redis缓存
        stringRedisTemplate.opsForValue().set(String.format(RedisConstants.TOKEN_KEY_TEMPLATE, token),
                JSON.object2jsonString(dbUser),
                RedisConstants.EXPIRED_TIME,
                TimeUnit.SECONDS);

        return DataWrappers.<String, Object>createHashMap()
                .put("token", token)
                .put("user", BeanUtils.copyBean(dbUser, UserVO.class))
                .build();
    }

    @Override
    public User changePassword(UserForm userForm) throws Exception {
        //1、检查参数是否完整
        if (V.isEmpty(userForm.getOldPassword()) || V.isEmpty(userForm.getPassword()) || V.isEmpty(userForm.getRePassword())) {
            throw new GlobalException(UserResultEnum.CHANGE_PASSWORD_PARAMS_IS_EMPTY);
        }
        //2、检查新密码和重复密码是否相同
        if (!V.equals(userForm.getPassword(), userForm.getRePassword())) {
            throw new GlobalException(UserResultEnum.PASSWORD_ERROR);
        }
        //3、检查老密码是否正确
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getId, userForm.getId())
                .eq(User::getPassword, SecretUtil.encryptMD5(userForm.getOldPassword()));
        User dbUser = super.getOne(lambdaQueryWrapper);
        if (V.isEmpty(dbUser)) {
            throw new GlobalException(UserResultEnum.CHANGE_PASSWORD_OLD_IS_ERROR);
        }
        boolean success = super.updateEntity(userForm);
        if (!success) {
            throw new GlobalException(ErrorEnum.ERROR);
        }
        return null;
    }

    @Override
    protected void setFilePath(String uploadPath, User entity) {
        entity.setFaceImage(uploadPath);
    }
}
