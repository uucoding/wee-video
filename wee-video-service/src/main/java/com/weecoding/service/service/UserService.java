package com.weecoding.service.service;

import com.weecoding.common.exception.GlobalException;
import com.weecoding.common.form.MultipartFileWrapper;
import com.weecoding.common.service.IBaseService;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 用户service
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  22:05
 */
public interface UserService extends IBaseService<User> {

    /**
     * <h3>注册用户</h3>
     * 如果失败抛出异常{@link GlobalException}<br/>
     * 成功返回数据库对象
     * <ul>
     * <li>1、检查提交内容：用户名、密码、重复密码是否全部填写,不存在抛出异常{@link UserResultEnum#USER_PARAMS_IS_EMPTY}</li>
     * <li>2、检查密码和重复密码：是否一致,不一致抛出异常{@link UserResultEnum#PASSWORD_RE_PASSWORD_DIFFERENCE}</li>
     * <li>3、检查用户是否已经存在数据库：用户名唯一，查数据库，如果存在抛出异常{@link UserResultEnum#DB_EXIST_USER}</li>
     * <li>4、转化补全数据：将{@link UserForm} => {@link User} 并设置一些默认属性：包括加密密码</li>
     * <li>5、注册用户：注册失败抛出异常{@link UserResultEnum#DB_NOT_EXIST_USER}</li>
     * </ul>
     *
     * @param userForm
     * @return
     * @throws Exception
     */
    void register(UserForm userForm) throws Exception;

    /**
     * <h3>登陆用户</h3>
     * 如果失败抛出异常{@link GlobalException}<br/>
     * 成功返回数据库对象
     * <ul>
     * <li>1、检查提交内容：用户名、密码,不存在抛出异常{@link UserResultEnum#USER_PARAMS_IS_EMPTY}</li>
     * <li>2、检查用户是否已经存在数据库：用户名唯一，查数据库，如果不存在抛出异常：{@link UserResultEnum#DB_NOT_EXIST_USER}</li>
     * <li>3、判断登陆密码和数据库密码是否一致： 不一致抛出异常：{@link UserResultEnum#PASSWORD_ERROR}</li>
     * <li>4、将用户存储到redis，返回token + 用户信息</li>
     * </ul>
     *
     * @param userForm user表单提交
     * @return {@link String} token
     * @throws Exception
     */
    Map<String, Object> login(UserForm userForm) throws Exception;

    /**
     * <h3>更改密码</h3>
     * <ul>
     *     <li>1、检查老密码、新密码、重复密码，是否全部填写，,不存在抛出异常{@link UserResultEnum#CHANGE_PASSWORD_PARAMS_IS_EMPTY}</li>
     *     <li>2、检查密码和重复密码：是否一致,不一致抛出异常{@link UserResultEnum#PASSWORD_RE_PASSWORD_DIFFERENCE}</li>
     *     <li>2、检查老密码输入是否正确，不正确抛出异常{@link UserResultEnum#CHANGE_PASSWORD_OLD_IS_ERROR}</li>
     * </ul>
     * @param userForm
     * @return
     * @throws Exception
     */
    User changePassword(UserForm userForm) throws Exception;

    /**
     * 上传头像，并将头像保存到数据库的用户中
     * @param userForm
     * @param fileWrapper
     * @return
     */
    String updateEntityAndUploadFaceImage(UserForm userForm, MultipartFileWrapper<MultipartFile> fileWrapper) throws Exception;
}
