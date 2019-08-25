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
 * 背景音乐API
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-08-13  15:59
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/backgroundMusic")
public class BackgroundMusicRestController extends BaseController<VideoService> {

    /**
     * 音乐列表(分页)
     * @param fileWrapper
     * @param entity
     * @return
     */
    @PostMapping("/list")
    public JsonResult list(MultipartFileWrapper<MultipartFile> fileWrapper, VideoForm entity) {
        return JsonResult.ok();
    }

    /**
     * 根据歌曲名字搜索
     * @param key
     * @return
     */
    @GetMapping("/search")
    public JsonResult search(@RequestParam("key") String key) {
        return JsonResult.ok();
    }
}
