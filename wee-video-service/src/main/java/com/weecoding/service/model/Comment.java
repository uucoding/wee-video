package com.weecoding.service.model;

import lombok.Data;

/**
 * 评论表
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:52
 */
@Data
public class Comment extends BaseEntity {
    private static final long serialVersionUID = -3148144142720769743L;

    /**
     * 上一个commentId
     */
    private String parentId;

    /**
     * 视频id
     */
    private String videoId;

    /**
     * 留言者
     */
    private String fromUserId;

    /**
     * 被留言者
     */
    private String toUserId;

    /**
     * 评论内容
     */
    private String comment;
}
