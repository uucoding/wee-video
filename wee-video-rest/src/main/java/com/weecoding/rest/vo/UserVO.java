package com.weecoding.rest.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 用户vo类
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:33
 */
@Data
public class UserVO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码: 密码不回显到前端
     */
    @JsonIgnore
    private String password;

    /**
     * 头像
     */
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;
}
