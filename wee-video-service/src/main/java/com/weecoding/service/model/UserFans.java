package com.weecoding.service.model;

import lombok.Data;

/**
 * 用户粉丝
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:54
 */
@Data
public class UserFans extends BaseEntity {
    private static final long serialVersionUID = 6132526543239215869L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 粉丝id
     */
    private String fansId;
}
