package com.weecoding.rest.controller;

import com.weecoding.common.constant.RedisConstants;
import com.weecoding.common.controller.BaseController;
import com.weecoding.common.form.MultipartFileWrapper;
import com.weecoding.common.util.V;
import com.weecoding.common.util.bean.BeanUtils;
import com.weecoding.common.util.response.JsonResult;
import com.weecoding.service.enumerate.UserResultEnum;
import com.weecoding.service.form.UserForm;
import com.weecoding.service.service.UserService;
import com.weecoding.service.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 更新个人信息
     * @param entity
     * @return
     */
    @PostMapping("/update")
    public JsonResult update(UserForm entity) throws Exception{
        getBaseService().updateEntity(entity);
        return JsonResult.ok(BeanUtils.copyBean(entity, UserVO.class));
    }

    /**
     * 上传个人头像
     * @param fileWrapper
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadFaceImage")
    public JsonResult uploadFaceImage(MultipartFileWrapper<MultipartFile> fileWrapper, UserForm userForm) throws Exception {
        return JsonResult.ok(getBaseService().updateEntityAndUploadFaceImage(userForm, fileWrapper));
    }
    /**
     * 修改密码
     * @param entity
     * @return
     * @throws Exception
     */
    @PostMapping("/changePassword")
    public JsonResult changePassword(UserForm entity) throws Exception{
        getBaseService().changePassword(entity);
        return JsonResult.ok();
    }

    /**
     * 退出登陆，依据token删除
     *
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public JsonResult logout(@RequestHeader("token") String token) throws Exception {
        Boolean delete = stringRedisTemplate.delete(String.format(RedisConstants.TOKEN_KEY_TEMPLATE, token));
        return V.notEmpty(delete) && delete ? JsonResult.ok() : JsonResult.iResultCode(UserResultEnum.REDIS_CACHE_NOT_EXIST);
    }
}
