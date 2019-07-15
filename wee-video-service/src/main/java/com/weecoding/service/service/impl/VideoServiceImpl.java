package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.CommentMapper;
import com.weecoding.service.mapper.VideoMapper;
import com.weecoding.service.model.Comment;
import com.weecoding.service.model.Video;
import com.weecoding.service.service.CommentService;
import com.weecoding.service.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * 视频service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:07
 */
@Service
public class VideoServiceImpl extends BaseServiceImpl<VideoMapper, Video> implements VideoService {
}
