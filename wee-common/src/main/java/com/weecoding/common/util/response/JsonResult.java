package com.weecoding.common.util.response;

import com.weecoding.common.util.response.enumerate.IResultCode;
import com.weecoding.common.util.response.enumerate.JsonStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

/**
 * 响应数据
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  15:58
 */
@Data
@NoArgsConstructor
public class JsonResult<T> extends JsonAccept<T> {

    /**
     * <h3>成功，无数据返回，默认返回空数组</h3>
     * <p>
     * 返回值格式:<br/>
     * {code:0,msg:"操作成功!",data:[]}
     * </p>
     *
     * @return
     */
    public static JsonResult ok() {
        return enumBuild(JsonStatusEnum.ok, Collections.emptyList());
    }

    /**
     * <h3>成功，有返回值</h3>
     * <p>
     * 返回值格式:<br/>
     * {code:0,msg:"操作成功!",data:{@link Object}}
     * </p>
     *
     * @param data
     * @return
     */
    public static <T> JsonResult ok(T data) {
        return enumBuild(JsonStatusEnum.ok, data);
    }

    /**
     * <h3>自定义信息，无数据返回，默认返回空数组</h3>
     * <p>
     * 返回值格式:<br/>
     * {code:6000,msg:"自定义内容!",data:[]}
     * </p>
     *
     * @return
     */
    public static JsonResult errorMsg(String msg) {
        return build(JsonStatusEnum.errorMsg.getCode(), msg, Collections.emptyList());
    }

    /**
     * <h3>自定义信息，有返回值</h3>
     * <p>
     * 返回值格式:<br/>
     * {code:6000,msg:"自定义内容!",data:{@link Object}}
     * </p>
     *
     * @param data
     * @return
     */
    public static <T> JsonResult errorMsg(String msg, T data) {
        return build(JsonStatusEnum.errorMsg.getCode(), msg, data);
    }
//============================IResultCode返回码==========================================

    /**
     * <h3>设置返回码</h3>
     *
     * @param iResultCode {@link IResultCode}
     * @return
     */
    public static JsonResult iResultCode(IResultCode iResultCode) {
        return new JsonResult(iResultCode, Collections.emptyList());
    }

    /**
     * <h3>设置返回码，有返回值</h3>
     *
     * @param iResultCode {@link IResultCode}
     * @return
     */
    public static <T> JsonResult iResultCode(IResultCode iResultCode, T data) {
        return new JsonResult(iResultCode, data);
    }


    /**
     * 普通构造
     */
    private JsonResult(Integer code, String msg, T data) {
        super(code, msg, data);
    }

    /**
     * @param iResultCode
     * @param data
     */
    private JsonResult(IResultCode iResultCode, T data) {
        super(iResultCode.getCode(), iResultCode.getMsg(), data);
    }

    /**
     * <h3>默认提供的状态码</h3>
     * 可以通过{@link JsonResult}提供的内部枚举{@link JsonStatusEnum}返回指定状态<br/>
     * 还可以自定义枚举继承{@link IResultCode}，然后调用{@link }
     *
     * @param status
     * @param data
     * @return
     */
    private static JsonResult enumBuild(JsonStatusEnum status, Object data) {
        return new JsonResult(status.getCode(), status.getMsg(), data);
    }

    /**
     * <h3>自定义状态码</h3>
     * 当{@link JsonResult#enumBuild(JsonStatusEnum, Object)}所提供的默认状态码无法满足当前系统需求的时候，可以使用当前方法自定义
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    private static <T> JsonResult build(Integer code, String msg, T data) {
        return new JsonResult(code, msg, data);
    }
}

