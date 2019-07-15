package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.CommentMapper;
import com.weecoding.service.mapper.UserLikeVideoMapper;
import com.weecoding.service.model.Comment;
import com.weecoding.service.model.UserLikeVideo;
import com.weecoding.service.service.CommentService;
import com.weecoding.service.service.UserLikeVideoService;
import org.springframework.stereotype.Service;

/**
 * 喜欢或收藏视频service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:07
 */
@Service
public class UserLikeVideoServiceImpl extends BaseServiceImpl<UserLikeVideoMapper, UserLikeVideo> implements UserLikeVideoService {
}
