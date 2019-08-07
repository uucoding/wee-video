package com.weecoding.common.handle;

import com.weecoding.common.exception.GlobalException;
import com.weecoding.common.util.V;
import com.weecoding.common.util.response.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一捕获类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  22:04
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

    /**
     * 捕获所有Exception异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult adviceException(Exception e) {
        log.error("【exception】<== 异常：", e);
        return JsonResult.errorMsg("系统异常！");
    }

    /**
     * 捕获抛出的全局异常
     *
     * @param de
     * @return
     */
    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public JsonResult adviceDefaultException(GlobalException de) {
        log.error("【exception】<==异常：错误信息：{}", de.getMessage());
        return V.notEmpty(de.getResultCode()) ? JsonResult.iResultCode(de.getResultCode())
                                              : JsonResult.errorMsg(de.getMessage());
    }
}
