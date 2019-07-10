package com.weecoding.service.model;

import lombok.Data;

import java.security.PrivateKey;

/**
 * 视频表
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:55
 */
@Data
public class Video extends BaseEntity {
    private static final long serialVersionUID = -2749188619464303944L;

    /**
     * 发布者id
     */
    private String userId;

    /**
     * 背景音乐
     */
    private String backgroundMusicId;

    /**
     * 视频存放的路径
     */
    private String videoPath;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 视频秒数
     */
    private Double videoSeconds;

    /**
     * 视频宽度
     */
    private Integer videoWidth;

    /**
     * 视频高度
     */
    private Integer videoHeight;

    /**
     * 视频封面图
     */
    private Integer coverPath;

    /**
     * 喜欢/点赞的数量
     */
    private Integer likeCount;

    /**
     * 视频状态：发布成功1，禁止播放0
     */
    private Integer status;

}
