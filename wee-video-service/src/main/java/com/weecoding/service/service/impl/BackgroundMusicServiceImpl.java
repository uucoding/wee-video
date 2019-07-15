package com.weecoding.service.service.impl;

import com.weecoding.common.service.BaseServiceImpl;
import com.weecoding.service.mapper.BackgroundMusicMapper;
import com.weecoding.service.model.BackgroundMusic;
import com.weecoding.service.service.BackgroundMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 背景音乐service实现类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:07
 */
@Service
public class BackgroundMusicServiceImpl extends BaseServiceImpl<BackgroundMusicMapper, BackgroundMusic> implements BackgroundMusicService {
}
