package com.weecoding.common.exception;

import com.weecoding.common.enumerate.IResultCode;
import lombok.Data;

/**
 * 全局默认异常
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-10  22:03
 */
@Data
public class DefaultException extends RuntimeException {

    private IResultCode resultCode;

    public DefaultException(IResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
