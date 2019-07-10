package com.weecoding.service.model;

import lombok.Data;

/**
 * 用户举报表
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:55
 */
@Data
public class UserReport extends BaseEntity {
    private static final long serialVersionUID = -6044979765165683741L;

    /**
     * 举报人id
     */
    private String fromUserId;

    /**
     * 被举报人id
     */
    private String toUserId;

    /**
     * 被举报人id
     */
    private String toVideoId;

    /**
     * 类型标题(枚举)
     */
    private String title;

    /**
     * 内容
     */
    private String content;
}
