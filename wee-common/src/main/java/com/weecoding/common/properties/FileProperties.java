package com.weecoding.common.properties;

import com.weecoding.common.util.S;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 文件全局配置
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-31  01:24
 */
@Data
@Component
public class FileProperties {
    /**
     * 文件根路径：默认项目root根目录 /xxx/xxx/wee-video
     */
    private String rootPath = System.getProperty("user.dir");
    /**
     * 文件存储主目录(默认值为upload)：设置后默认存在项目的根目录创建一个文件夹
     */
    private String fileMainDirectory = "upload";

    /**
     * ffmpeg的命令工具
     * 默认使用ffmpeg （MAC OS 环境）
     * 如果未配置全局变量，那么自行设置路径
     */
    private String ffmpegPath = "ffmpeg";

    /**
     * 获取文件存储的后的绝对路径前缀
     * {@link FileProperties#rootPath}/{@link FileProperties#fileMainDirectory}
     * @return
     */
    public String getAbsoluteStoragePathPrefix() {
        return S.duplicateJoin(new String[]{rootPath, fileMainDirectory}, File.separator);
    }
}
