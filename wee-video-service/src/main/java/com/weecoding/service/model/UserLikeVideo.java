package com.weecoding.service.model;

import lombok.Data;

/**
 * 用户喜欢/点赞视频
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:54
 */
@Data
public class UserLikeVideo extends BaseEntity {
    private static final long serialVersionUID = -8877017561233355188L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 视频id
     */
    private String videoId;
}
