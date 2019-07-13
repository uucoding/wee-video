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
     * 如果失败抛出异常{@link com.weecoding.common.exception.DefaultException}<br/>
     * 成功返回数据库对象
     * <ul>
     *     <li>1、检查提交内容：用户名、密码、重复密码是否全部填写,不存在抛出异常{@link UserResultEnum#USER_PARAMS_IS_EMPTY}</li>
     *     <li>2、检查密码和重复密码：是否一致,不一致抛出异常{@link UserResultEnum#PASSWORD_RE_PASSWORD_DIFFERENCE}</li>
     *     <li>3、检查用户是否已经存在数据库：用户名唯一，查数据库，如果存在抛出异常{@link UserResultEnum#DB_EXIST_USER}</li>
     *     <li>4、转化补全数据：将{@link UserForm} => {@link User} 并设置一些默认属性：包括加密密码</li>
     *     <li>5、注册用户：注册失败抛出异常{@link UserResultEnum#DB_NOT_EXIST_USER}</li>
     * </ul>
     * @param userForm
     * @return
     * @throws Exception
     */
    void register(UserForm userForm) throws Exception;

    /**
     * <h3>注册用户</h3>
     * 如果失败抛出异常{@link com.weecoding.common.exception.DefaultException}<br/>
     * 成功返回数据库对象
     * <ul>
     *     <li>1、检查提交内容：用户名、密码,不存在抛出异常{@link UserResultEnum#USER_PARAMS_IS_EMPTY}</li>
     *     <li>2、检查用户是否已经存在数据库：用户名唯一，查数据库，如果不存在抛出异常：{@link UserResultEnum#DB_NOT_EXIST_USER}</li>
     *     <li>3、判断登陆密码和数据库密码是否一致： 不一致抛出异常：{@link UserResultEnum#PASSWORD_ERROR}</li>
     *     <li>4、返回用户</li>
     * </ul>
     * @param userForm
     * @return {@link User}
     * @throws Exception
     */
    User login(UserForm userForm) throws Exception;
}
