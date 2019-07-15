package com.weecoding.common.handle;

import com.weecoding.common.enumerate.ErrorEnum;
import com.weecoding.common.enumerate.IResultCode;

import java.util.Arrays;

/**
 * 枚举统一助手类
 *
 * @author : wee
 * @version v1.0
 * @Description: 枚举工具类
 * @Date 2019-04-15  21:45
 */
public class EnumHandler {

    /**
     * <h3>该主要用于将枚举的msg返回到前端提示</h3>
     * 根据code和枚举类型查询对应的枚举对象<br/>
     * 用来响应设置自定义内容{@link com.weecoding.common.util.response.JsonResult}的值<br/>
     * 如果查询不到，默认返回{@link ErrorEnum}的提示
     *
     * @param code   需要枚举
     * @param tClass 枚举
     * @return 返回T类型的枚举
     */
    public static <T extends IResultCode> T findEnumByCode(Integer code, Class<T> tClass) {
        return Arrays.stream(tClass.getEnumConstants())
                .filter(codeEnum -> codeEnum.getCode().equals(code))
                .findFirst()
                //操作不成功，统一返回错误枚举
                .orElse((T) ErrorEnum.ERROR);
    }
}
