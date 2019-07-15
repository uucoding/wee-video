package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.CommentMapper;
import com.weecoding.service.mapper.UserReportMapper;
import com.weecoding.service.model.Comment;
import com.weecoding.service.model.UserReport;
import com.weecoding.service.service.CommentService;
import com.weecoding.service.service.UserReportService;
import org.springframework.stereotype.Service;

/**
 * 举报service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:07
 */
@Service
public class UserReportServiceImpl extends BaseServiceImpl<UserReportMapper, UserReport> implements UserReportService {
}
