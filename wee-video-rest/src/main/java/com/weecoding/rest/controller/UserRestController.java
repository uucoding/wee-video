package com.weecoding.rest.controller;

import com.weecoding.common.controller.BaseController;
import com.weecoding.common.util.bean.BeanUtils;
import com.weecoding.common.util.response.JsonResult;
import com.weecoding.rest.vo.UserVO;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.model.User;
import com.weecoding.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /***
     * 注册用户
     * @param entity
     * @return
     */
    @PostMapping("/register")
    public JsonResult register(@RequestBody UserForm entity) throws Exception{
        //注册用户
        getBaseService().register(entity);
        //注册完毕，直接登陆
        return login(entity);
    }

    /**
     * 登陆用户
     * @param entity
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserForm entity) throws Exception{
        User user = getBaseService().login(entity);
        return JsonResult.ok(BeanUtils.copyBean(user, UserVO.class));
    }

}
