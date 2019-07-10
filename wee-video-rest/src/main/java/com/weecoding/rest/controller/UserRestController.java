package com.weecoding.rest.controller;

import com.weecoding.common.util.response.JsonResult;
import com.weecoding.rest.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class UserRestController {

    /***
     * 注册用户
     * @param entity
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody UserVO entity) {

        return JsonResult.ok();
    }


    /**
     * 登陆用户
     * @param entity
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserVO entity) {
        return JsonResult.ok();
    }

}
