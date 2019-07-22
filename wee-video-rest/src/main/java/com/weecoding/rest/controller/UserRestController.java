package com.weecoding.rest.controller;

import com.weecoding.common.constant.RedisConstants;
import com.weecoding.common.controller.BaseController;
import com.weecoding.common.util.V;
import com.weecoding.common.util.response.JsonResult;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户api
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  16:26
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController extends BaseController<UserService> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 退出登陆， 依据token删除
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public JsonResult logout(@RequestHeader("token") String token) {
        String key = String.format(RedisConstants.TOKEN_KEY_TEMPLATE, token);
        Boolean delete = stringRedisTemplate.delete(key);
        if (V.notEmpty(delete) && delete) {
            return JsonResult.ok();
        } else {
            return JsonResult.iResultCode(UserResultEnum.REDIS_CACHE_NOT_EXIST);
        }
    }

    public static void main(String[] args) {
        String property = System.getProperty("user.dir");

        System.out.println(property);
    }
}
