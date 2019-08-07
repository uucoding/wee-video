package com.weecoding.common.exception;

import com.weecoding.common.util.response.enumerate.IResultCode;
import lombok.Data;

/**
 * 全局默认异常
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  22:03
 */
@Data
public class GlobalException extends RuntimeException {

    private IResultCode resultCode;

    public GlobalException(IResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public GlobalException(String msg) {
        super(msg);
    }
}
