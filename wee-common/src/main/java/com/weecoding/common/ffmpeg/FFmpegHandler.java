package com.weecoding.common.ffmpeg;

import com.weecoding.common.properties.GlobalProperties;
import com.weecoding.common.util.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 常用的ffmpeg命令
 * @author : wee
 * @version : v1.0
 * @Date 2019-08-12  10:25
 */
@Component
public class FFmpegHandler {

    @Autowired
    private GlobalProperties globalProperties;

    /**
     * 将视频文件从一个格式转化到另一个格式
     * 命令：ffmpeg -i input.avi output.mp4
     * @param fromAbsFilePath 源格式文件绝对路径
     * @param toAbsFilePath 目标格式文件绝对路径
     * @return
     */
    public boolean convertVideo(String fromAbsFilePath, String toAbsFilePath) {


        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsFilePath)
                .addCommand(toAbsFilePath)
                .addCommand(FFmpegCommand.Y)
                .build()
                .execute();
    }

    /**
     * 取出视频的音频文件: 使用与输入流相同的编解码器，<br/>
     * 如果需要强制指定编解码器请使用{@link FFmpegHandler#fromVideoGetAudio(String, String, String)}<br/>
     * 命令：ffmpeg -i input.mp4 -acodec copy -vn output.aac <br/>
     * @param fromAbsVideoFilePath 视频文件的绝对路径
     * @param toAudioAbsFilePath 目标音频文件的绝对路径
     * @return
     */
    public boolean fromVideoGetAudio(String fromAbsVideoFilePath, String toAudioAbsFilePath) {
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsVideoFilePath)
                .addCommand(FFmpegCommand.ACODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(FFmpegCommand.VN)
                .addCommand(FFmpegCommand.Y)
                .addCommand(toAudioAbsFilePath)
                .build()
                .execute();
    }

    /**
     * 取出视频的音频文件：可以强制指定编码格式，如mp3、aac等
     * 转化成mp3的命令：ffmpeg -i input.mp4 -acodec mp3 -vn output.mp3
     * @param fromAbsVideoFilePath 视频文件的绝对路径
     * @param toAudioAbsFilePath 目标音频文件的绝对路径
     * @param encoder 编解码器：mp3/aac等
     * @return
     */
    public boolean fromVideoGetAudio(String fromAbsVideoFilePath, String toAudioAbsFilePath, String encoder) {
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsVideoFilePath)
                .addCommand(FFmpegCommand.ACODEC)
                .addCommand(encoder)
                .addCommand(FFmpegCommand.VN)
                .addCommand(FFmpegCommand.Y)
                .addCommand(toAudioAbsFilePath)
                .build()
                .execute();
    }

    /**
     * 取出视频的视频文件（不包含音频）: 使用与输入流相同的编解码器，<br/>
     * 如果需要强制指定编解码器请使用{@link FFmpegHandler#fromVideoGetAudio(String, String, String)}<br/>
     * 命令：ffmpeg -i input.mp4 -vcodec copy -an output.aac <br/>
     * @param fromAbsVideoFilePath 视频文件的绝对路径
     * @param toVideoAbsFilePath 目标音频文件的绝对路径
     * @return
     */
    public boolean fromVideoGetVideo(String fromAbsVideoFilePath, String toVideoAbsFilePath) {
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsVideoFilePath)
                .addCommand(FFmpegCommand.VCODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(FFmpegCommand.AN)
                .addCommand(FFmpegCommand.Y)
                .addCommand(toVideoAbsFilePath)
                .build()
                .execute();
    }

    /**
     * 合并视频和音频<br>
     * ffmpeg -i out.h264 -i out.aac -vcodec copy -acodec copy out.mp4
     * @param fromAbsVideoFilePath  视屏源的地址
     * @param fromAbsAudioFilePath  音频源的地址
     * @param toAbsVideoFilePath    合并之后的视频地址
     * @return
     */
    public boolean mergeVideoAndAudio(String fromAbsVideoFilePath, String fromAbsAudioFilePath, String toAbsVideoFilePath) {

        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsVideoFilePath)
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsAudioFilePath)
                .addCommand(FFmpegCommand.VCODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(FFmpegCommand.ACODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(FFmpegCommand.Y)
                .addCommand(toAbsVideoFilePath)
                .build()
                .execute();
    }

    /**
     * 合并视频和音频(指定合并时长)<br>
     * ffmpeg -i out.h264 -i out.aac -vcodec copy -acodec copy out.mp4
     * @param fromAbsVideoFilePath  视屏源的地址
     * @param fromAbsAudioFilePath  音频源的地址
     * @param toAbsVideoFilePath    合并之后的视频地址
     * @return
     */
    public boolean mergeVideoAndAudio(String fromAbsVideoFilePath, String fromAbsAudioFilePath, String toAbsVideoFilePath, int seconds) {

        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsVideoFilePath)
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsAudioFilePath)
                .addCommand(FFmpegCommand.T)
                .addCommand(Integer.valueOf(seconds).toString())
                .addCommand(FFmpegCommand.Y)
                .addCommand(toAbsVideoFilePath)
                .build()
                .execute();
    }

    /**
     * 裁剪视频
     * 命令 ffmpeg -ss 00:00:15 -t 00:00:05 -i input.mp4 -vcodec copy -acodec copy -y output.mp4
     * @param fromAbsFilePath 源格式文件绝对路径
     * @param toAbsFilePath 目标格式文件绝对路径
     * @param offset        偏移量（从多少秒位置开始）
     * @param duration      截取多少秒
     * @return
     */
    public boolean cutVideo(String fromAbsFilePath, String toAbsFilePath, int offset, int duration) {
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.SS)
                .addCommand(calculateTime(offset))
                .addCommand(FFmpegCommand.T)
                .addCommand(calculateTime(duration))
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsFilePath)
                .addCommand(FFmpegCommand.VCODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(FFmpegCommand.ACODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(FFmpegCommand.Y)
                .addCommand(toAbsFilePath)
                .build()
                .execute();
    }

    /**
     * 截取视频中的图片<br>
     *
     * 使用命令（使用视频的宽高）： ffmpeg -y -i input.mp4 -ss 2 -vframes 1 -f image2 img.png<br>
     * 未使用（设置图片高度宽度）：ffmpeg -y -i input.mp4 -ss 2 -vframes 1 -f image2 -s 352x240 img.png<br>
     * @param fromAbsFilePath 源格式文件绝对路径
     * @param toAbsPictureFilePath 目标格式文件绝对路径
     * @param offset        偏移量（秒）
     * @return
     */
    public boolean fromVideoToStaticPicture(String fromAbsFilePath, String toAbsPictureFilePath, int offset) {
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.Y)
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsFilePath)
                .addCommand(FFmpegCommand.SS)
                .addCommand(calculateTime(offset))
                .addCommand(FFmpegCommand.VFRAMES)
                .addCommand("1")
                .addCommand(FFmpegCommand.F)
                .addCommand(FFmpegCommand.F_TYPE_ENUM.IMAGE2.getType())
                .addCommand(toAbsPictureFilePath)
                .build()
                .execute();
    }

    /**
     * 将视频变成动态图片
     *
     * ffmpeg -i out.mp4 -ss 00:00:00 -t 10 out.gif
     * @param fromAbsFilePath  视频路径
     * @param toAbsPictureFilePath  图片路径
     * @param offset    截取偏移量（从多少秒位置开始）
     * @param duration  持续时间
     * @return
     */
    public boolean fromVideoToGifPicture(String fromAbsFilePath, String toAbsPictureFilePath, int offset, int duration) {
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.Y)
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsFilePath)
                .addCommand(FFmpegCommand.SS)
                .addCommand(calculateTime(offset))
                .addCommand(FFmpegCommand.T)
                .addCommand(Integer.valueOf(duration).toString())
                .addCommand(FFmpegCommand.F)
                .addCommand(FFmpegCommand.F_TYPE_ENUM.GIF.getType())
                .addCommand(toAbsPictureFilePath)
                .build()
                .execute();
    }

    /**
     * <h3>给视频添加水印</h3>
     *
     * {@code ffmpeg -i input.mp4 -vf "movie=logo.png[watermark];[in][watermark] overlay=10:10[out] " output.mp4}
     * <br>
     * 注意：命令中加双引号的部分，在代码拼接中不需要双引号，否则报错：No such filter: '"movie'
     * @param fromAbsVideoFilePath  源视频
     * @param watermarkPicture      水印图片
     * @param toAbsVideoFilePath      目标视频
     * @param x      水印右上角x坐标
     * @param y      水印右上角y坐标
     * @return
     */
    public boolean settingWatermark(String fromAbsVideoFilePath, String watermarkPicture, String toAbsVideoFilePath,int x, int y) {
        String watermark = S.join(new String[]{"movie=", watermarkPicture, "[watermark];[in][watermark] overlay=",
                                            Integer.valueOf(x).toString(), ":", Integer.valueOf(y).toString(), " [out]"},"");
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.Y)
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsVideoFilePath)
                .addCommand(FFmpegCommand.VF)
                .addCommand(watermark)
                .addCommand(toAbsVideoFilePath)
                .build()
                .execute();
    }

    /**
     * <h3>祛除水印</h3>
     * {@code ffmpeg.exe -i input.mp4 -vf "delogo=x=10:y=10:w=30:h=30" -c:a copy output.mp4"}
     * <br>
     * 等价于
     * <br>
     * {@code ffmpeg.exe -i input.mp4 -vf "delogo=x=10:10=y:w=30:h=30" -acodec copy output.mp4"}
     * @param fromAbsFilePath   源视频地址
     * @param toAbsFilePath     目标视频地址
     * @param x         水印图片右上角的x坐标
     * @param y         水印图片右上角的y坐标
     * @param width     水印图片的宽度
     * @param height    水印图片的高度
     * @return
     */
    public boolean clearWatermark(String fromAbsFilePath, String toAbsFilePath, int x, int y, int width, int height) {
        String clearWatermark = S.join(new String[]{"delogo=x=", Integer.valueOf(x).toString(), ":y=", Integer.valueOf(y).toString(),
                                                      ":w=", Integer.valueOf(width).toString(), ":h=", Integer.valueOf(height).toString()},"");
        return new FFmpeg.FFmpegBulider(globalProperties.getFile().getFfmpegPath())
                .addCommand(FFmpegCommand.Y)
                .addCommand(FFmpegCommand.I)
                .addCommand(fromAbsFilePath)
                .addCommand(FFmpegCommand.MAX_MUXING_QUEUE_SIZE)
                .addCommand("1024")
                .addCommand(FFmpegCommand.VF)
                .addCommand(clearWatermark)
                .addCommand(FFmpegCommand.ACODEC)
                .addCommand(FFmpegCommand.COPY)
                .addCommand(toAbsFilePath)
                .build()
                .execute();
    }

    /**
     * 将秒转化为时间： 60s => "00:01:00"<br>
     * 将秒转化为时间： 61s => "00:01:01"<br>
     * 将秒转化为时间： 3600s => "01:00:00"<br>
     * @param seconds
     */
    private String calculateTime(int seconds) {
        //获取偏移总分钟数（可能大于60）
        Integer actualMinute= seconds / 60;

        //获取偏移小时数据
        Integer actualHour = actualMinute / 60;
        String actualHourStr = actualHour < 10 ? "0" + actualHour : actualHour.toString();

        //获取具体偏移分钟数（获取具体偏移分钟）
        actualMinute %= 60;
        String actualMinuteStr = actualMinute < 10 ? "0" + actualMinute : actualMinute.toString();

        //获取具体偏移秒数
        Integer actualSeconds = seconds % 60;
        String actualSecondsStr = actualSeconds < 10 ? "0" + actualSeconds : actualSeconds.toString();

        return S.join(new String[]{actualHourStr, actualMinuteStr, actualSecondsStr}, ":");
    }
//
//    public static void main(String[] args) {
//        FFmpegHandler fFmpegHandler = new FFmpegHandler();
//
//        fFmpegHandler.cutVideo("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/test.mp4",
//                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/36s.mp4",0,36);
////        fFmpegHandler.fromVideoGetVideo("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/111.mp4",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/video.avi");
////        fFmpegHandler.fromVideoGetAudio("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/111.mp4",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/audio.mp3", "mp3");
//
////        fFmpegHandler.mergeVideoAndAudio("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/video.avi",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/audio.mp3",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/zzz.mp4",
////                10);
////        System.out.println(fFmpegHandler.calculateTime(650));
//
////        fFmpegHandler.cutVideo("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/zzz.mp4"
////        ,"/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/sss.mp4",
////        5, 20);
////        fFmpegHandler.fromVideoToGifPicture("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/111.mp4",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/gif2.gif",
////                30, 20);
////        fFmpegHandler.settingWatermark("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/111.mp4",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/video.png",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/watermark.mp4",
////                10, 10);
////        fFmpegHandler.clearWatermark("/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/watermark.mp4",
////                "/Users/wuy/IdeaProjects/weecoding/wee-video/wee-upload/clearWatermark.mp4",
////                10, 10, 100, 100);
//
//    }
}
