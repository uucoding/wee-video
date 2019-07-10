package com.weecoding.service.model;

import lombok.Data;

/**
 * 背景音乐
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:52
 */
@Data
public class BackgroundMusic extends BaseEntity {
    private static final long serialVersionUID = -7197326929035153265L;

    /**
     * 作者
     */
    private String author;

    /**
     * 名称
     */
    private String name;

    /**
     * 播放地址
     */
    private String path;
}
