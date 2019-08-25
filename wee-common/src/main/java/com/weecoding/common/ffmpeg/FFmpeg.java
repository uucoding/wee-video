package com.weecoding.common.ffmpeg;

import com.weecoding.common.util.S;
import com.weecoding.common.util.V;
import com.weecoding.common.wrapper.DataWrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * @author : wee
 * @version : v1.0
 * @Date 2019-08-12  09:16
 */
@AllArgsConstructor
@Slf4j
public class FFmpeg {

    private List<String> commandList;

    public boolean execute() {
        Process process = null;
        try {
            process = new ProcessBuilder(this.commandList)
                    .start();
        } catch (IOException e) {
            log.error("【ffmpeg】执行转化异常：", e);
            return false;
        }
        //在执行命令时会在瞬间大量消耗CPU和内存等系统资源，需要手动清理
        try {
            InputStream errorStream = process.getErrorStream();
            InputStreamReader isr = new InputStreamReader(errorStream);
            BufferedReader br = new BufferedReader(isr);
            //释放资源
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            if (V.notEmpty(errorStream)) {
                errorStream.close();
            }
            if (V.notEmpty(isr)) {
                isr.close();
            }
            if (V.notEmpty(br)) {
                br.close();
            }
        } catch (IOException e) {
            log.error("【ffmpeg】释放资源异常：", e);
        }
        log.debug("【ffmpeg】执行命令: 【{}】结束!!!", S.join(commandList.toArray(), " "));
        return true;
    }

    public static class FFmpegBulider {

        private List<String> commandList;

        public FFmpegBulider() {
            commandList = DataWrappers.<String>createArrayList().build();
        }

        public FFmpegBulider(String ffmpeg) {
            commandList = DataWrappers.<String>createArrayList().build();
            commandList.add(ffmpeg);
        }

        public FFmpegBulider addCommand(String command) {
            this.commandList.add(command);
            return this;
        }

        public FFmpeg build() {
            System.out.println("【ffmpeg】当前正在执行命令:" + S.join(commandList.toArray(), " "));
            log.info("【ffmpeg】当前正在执行命令: {}", S.join(commandList.toArray(), " "));
            return new FFmpeg(commandList);
        }
    }
}
