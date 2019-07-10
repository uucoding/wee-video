package com.weecoding.service.service;

import com.weecoding.common.enumerate.IResultCode;
import com.weecoding.common.service.IBaseService;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.model.User;
import com.weecoding.service.enumerate.UserResultEnum;

/**
 * 用户service
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  22:05
 */
public interface UserService extends IBaseService<User> {

    /**
     * <h3>注册用户</h3>
     * <ul>
     *     <li>1、检查提交内容：用户名、密码、重复密码是否全部填写,不存返回{@link UserResultEnum#USERNAME_PASSWORD_RE_PASSWORD_IS_EMPTY}</li>
     *     <li>2、检查密码和重复密码：是否一致，不一致返回：{@link UserResultEnum#PASSWORD_RE_PASSWORD_DIFFERENCE}</li>
     *     <li>3、检查用户是否已经存在数据库：用户名唯一，查数据库，如果存在返回：{@link UserResultEnum#DB_EXIST_USER}</li>
     *     <li>4、转化补全数据：将{@link UserForm} => {@link User} 并设置一些默认属性 todo 密码暂时不加密
     *     </li>
     *     <li>5、注册用户</li>
     * </ul>
     * @param userForm
     * @return
     * @throws Exception
     */
    IResultCode register(UserForm userForm) throws Exception;
}
