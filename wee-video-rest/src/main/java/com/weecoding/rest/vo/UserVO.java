package com.weecoding.rest.vo;

import lombok.Data;

/**
 * 用户vo类
 *
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
