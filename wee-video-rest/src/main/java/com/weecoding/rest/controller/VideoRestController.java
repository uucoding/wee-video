package com.weecoding.rest.controller;

import com.weecoding.common.controller.BaseController;
import com.weecoding.common.form.MultipartFileWrapper;
import com.weecoding.common.util.response.JsonResult;
import com.weecoding.service.form.VideoForm;
import com.weecoding.service.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 视频API
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-08-13  15:59
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/video")
public class VideoRestController extends BaseController<VideoService> {

    /**
     * 获取下一个视频
     *
     * 注：每次取出一个视频后，并将其标记为已经看过，之后这个视频将不会被取出
     * @return
     */
    public JsonResult next() {
        return JsonResult.ok();
    }

    /**
     * 视频上传
     *
     * @param fileWrapper
     * @param entity
     * @return
     */
    @PostMapping("/upload")
    public JsonResult uploadVideo(MultipartFileWrapper<MultipartFile> fileWrapper, VideoForm entity) {
        return JsonResult.ok();
    }
}
