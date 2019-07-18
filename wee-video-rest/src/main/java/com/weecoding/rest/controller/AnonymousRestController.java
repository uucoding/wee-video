package com.weecoding.rest.controller;

import com.weecoding.common.util.response.JsonResult;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 匿名请求api
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  17:56
 */
@RestController
@RequestMapping("/api/v1/anonymous")
public class AnonymousRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public JsonResult get() {
        return JsonResult.ok();
    }

    /***
     * 注册用户
     * @param entity
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody UserForm entity) throws Exception {
        //注册用户
        userService.register(entity);
        //注册完毕，直接登陆
        return login(entity);
    }

    /**
     * 登陆用户
     *
     * @param entity
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserForm entity) throws Exception {
        String token = userService.login(entity);
        return JsonResult.ok(token);
    }
}
