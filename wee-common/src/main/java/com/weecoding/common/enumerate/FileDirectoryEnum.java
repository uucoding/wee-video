package com.weecoding.common.enumerate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件夹的类型：提供图片和视频两种
 * @author : wee
 * @version : v1.0
 * @Date 2019-08-08  15:27
 */
public enum FileDirectoryEnum {

    /**
     * 选择文件夹：目前包含images/videos/other
     */
    DIR{
        @Override
        public String choose(String fileName) {
            if (isImage(fileName)) {
                return "images";
            } else if (isVideo(fileName)) {
                return "videos";
            } else {
                return "other";
            }
        }
    },
    ;

    /**
     * 选择存放的文件夹
     * @param fileName  包含扩展名的文件名
     * @return
     */
    public abstract String choose(String fileName);

    /**
     * 判断是否是image类型
     * @param fileName
     * @return
     */
    protected boolean isImage(String fileName) {
        //忽略大小写匹配（?i）
        //判断是否以图片类型结尾
        String regex = "(?i)(\\.png|\\.jpeg|\\.jpg|\\.gif)$";
        if (find(regex, fileName)){
            return true;
        }
        return false;
    }

    /**
     * 判断是否是video类型
     * @param fileName
     * @return
     */
    protected boolean isVideo(String fileName) {
        //忽略大小写匹配（?i）
        //判断是否以视频类型结尾
        String regex = "(?i)(\\.mp4|\\.avi)$";
        if (find(regex, fileName)){
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否包含指定的正则
     * @param regex
     * @param input
     * @return
     */
    private boolean find(String regex, String input) {
        //构建正则表达式
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
