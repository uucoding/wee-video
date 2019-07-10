package com.weecoding.rest.controller;

import com.weecoding.common.enumerate.IResultCode;
import com.weecoding.common.handle.EnumHandler;
import com.weecoding.common.util.V;
import com.weecoding.common.util.response.JsonResult;
import com.weecoding.rest.vo.UserVO;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    /***
     * 注册用户
     * @param entity
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody UserForm entity) throws Exception{
        IResultCode resultCode = userService.register(entity);
        //获取返回的code值
        UserResultEnum userResultEnum = EnumHandler.findEnumByCode(resultCode.getCode(), UserResultEnum.class);
        if (V.equals(userResultEnum, UserResultEnum.STORAGE_USER_SUCCESS)) {
            return login(entity);
        }
        return JsonResult.iResultCode(resultCode);
    }


    /**
     * 登陆用户
     * @param entity
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserForm entity) {

        return JsonResult.ok();
    }

}
