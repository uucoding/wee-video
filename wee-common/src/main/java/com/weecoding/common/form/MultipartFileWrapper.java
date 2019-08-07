package com.weecoding.common.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 文件上传的包装类
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-30  19:04
 */
@Data
public class MultipartFileWrapper<F extends MultipartFile> implements Serializable {
    private static final long serialVersionUID = -2489197830851586733L;
    /**
     * 上传单个文件
     */
    private F file;

    /**
     * 上传多个文件
     */
    private F[] files;
}
