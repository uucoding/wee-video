package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.CommentMapper;
import com.weecoding.service.mapper.SearchRecordMapper;
import com.weecoding.service.model.Comment;
import com.weecoding.service.model.SearchRecord;
import com.weecoding.service.service.CommentService;
import com.weecoding.service.service.SearchRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 搜索记录service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:07
 */
@Service
@Slf4j
public class SearchRecordServiceImpl extends BaseServiceImpl<SearchRecordMapper, SearchRecord> implements SearchRecordService {
}
