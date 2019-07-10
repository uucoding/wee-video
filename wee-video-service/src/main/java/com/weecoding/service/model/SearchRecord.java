package com.weecoding.service.model;

import lombok.Data;

/**
 * 搜索记录表
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:53
 */
@Data
public class SearchRecord extends BaseEntity {
    private static final long serialVersionUID = 3855610447969039284L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 搜索内容
     */
    private String content;
}
