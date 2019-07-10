package com.weecoding.service.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 用户
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-04  21:52
 */
@Data
public class User extends BaseEntity {
    private static final long serialVersionUID = -5502165213852201693L;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 粉丝数量
     */
    private Long fansCount;

    /**
     * 关注总数
     */
    private Long followCount;

    /**
     * 接受到的点赞/收藏的数量
     */
    private Long receiveLikeCount;
}
