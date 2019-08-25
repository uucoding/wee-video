package com.weecoding.common.ffmpeg;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ffmpeg常见命令
 * @author : wee
 * @version : v1.0
 * @Date 2019-08-12  10:34
 */
public interface FFmpegCommand {

    /**
     * 目标命令
     */
    String FFMPEG = "ffmpeg";
    //============主要参数================
    /**
     * 输入
     */
    String I = "-i";

    /**
     * 设定输出格式
     */
    String F = "-f";

    /**
     * 开始时间
     */
    String SS = "-ss";

    /**
     * 持续时间
     */
    String T = "-t";

    /**
     * 直接覆盖已经存在的输出文件：如果已经存在，不加的清空会存在锁住不执行的情况
     */
    String Y = "-y";

    /**
     * 指明只拷贝，不做编解码
     */
    String COPY = "copy";

    /**
     * 指定截取的帧数
     */
    String VFRAMES = "-vframes";

    /**
     * 容器封装队列大小，：有些视频数据有问题，导致视频处理过快，容器封装时队列溢出可以增加此数据的值
     */
    String MAX_MUXING_QUEUE_SIZE = "-max_muxing_queue_size";

    //============视频参数===============

    /**
     * 设定视频流量，默认为200Kbit/s
     */
    String B = "-b";

    /**
     * 设定帧速率，默认为25
     */
    String R = "-r";

    /**
     * 设定画面的宽与高
     */
    String S = "-s";

    /**
     * 过滤视频
     */
    String VF = "-vf";

    /**
     * 设定画面的比例
     */
    String ASPECT = "-aspect";

    /**
     * 不处理视频
     */
    String VN = "-vn";

    /**
     * 设定视频编解码器，未设定时则使用与输入流相同的编解码器 等同于 -c:v
     */
    String VCODEC = "-vcodec";

    //==============音频参数=============

    /**
     * 设定采样率
     */
    String AR = "-ar";

    /**
     * 设定声音的Channel数
     */
    String AC = "-ac";

    /**
     * 不处理音频
     */
    String AN = "-an";

    /**
     * 设置声音编码 -c:a
     */
    String ACODEC = "-acodec";

    //=================图片相关===========

    /**
     * 图片格式： image2, mjpeg, gif
     */
    @Getter
    @AllArgsConstructor
    enum F_TYPE_ENUM{
        IMAGE2("image2"),
        MJPEG("mjpeg"),
        GIF("gif");
        private String type;
    }
}
