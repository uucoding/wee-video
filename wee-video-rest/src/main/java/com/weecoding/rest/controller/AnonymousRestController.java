package com.weecoding.rest.controller;

import com.weecoding.common.controller.BaseController;
import com.weecoding.common.util.response.JsonResult;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 匿名请求api
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  17:56
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/anonymous")
public class AnonymousRestController extends BaseController<UserService> {

    /***
     * 注册用户
     * @param entity
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody UserForm entity) throws Exception {
        //注册用户
        getBaseService().register(entity);
        //注册完毕，直接登陆
        return login(entity);
    }

    /**
     * 登陆用户: 返回用户token 以及用户信息
     *
     * @param entity
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserForm entity) throws Exception {
        return JsonResult.ok(getBaseService().login(entity));
    }
}
