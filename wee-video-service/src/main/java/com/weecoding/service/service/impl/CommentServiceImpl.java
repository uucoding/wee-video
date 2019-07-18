package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.BackgroundMusicMapper;
import com.weecoding.service.mapper.CommentMapper;
import com.weecoding.service.model.BackgroundMusic;
import com.weecoding.service.model.Comment;
import com.weecoding.service.service.BackgroundMusicService;
import com.weecoding.service.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 评论service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:07
 */
@Service
@Slf4j
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {
}
