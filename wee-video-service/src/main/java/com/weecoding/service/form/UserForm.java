package com.weecoding.service.form;

import com.weecoding.service.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 用户的form表单
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  16:42
 */
@Data
public class UserForm extends User {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 重复密码
     */
    private String rePassword;

    private String password;

}
