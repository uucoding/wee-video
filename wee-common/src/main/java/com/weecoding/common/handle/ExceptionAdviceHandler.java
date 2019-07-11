package com.weecoding.common.handle;

import com.weecoding.common.exception.DefaultException;
import com.weecoding.common.util.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * 异常统一捕获类
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  22:04
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

    /**
     * 捕获所有Exception异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult adviceException(Exception e) {
        log.error("【exception】<==异常：", e);
        return JsonResult.errorMsg("系统异常！");
    }

    /**
     * 捕获抛出的默认异常
     * @param de
     * @return
     */
    @ResponseBody
    @ExceptionHandler(DefaultException.class)
    public JsonResult adviceDefaultException(DefaultException de) {
        log.error("【exception】<==异常：错误信息：{}", de.getResultCode().getMsg());
        return JsonResult.iResultCode(de.getResultCode());
    }
}
