package com.weecoding.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.weecoding.common.form.MultipartFileWrapper;
import com.weecoding.common.model.Entity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 所以service接口
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:10
 */
public interface IBaseService<T extends Entity> extends IService<T> {
    /**
     * 更新实体
     * @param entity
     * @return  T
     */
    boolean updateEntity(T entity) throws Exception;

    /**
     * 上传单个文件处理
     * @param wrapper
     * @param entity  文件关联的类
     * @param <F>
     */
    <F extends MultipartFile> void uploadSingleFileWithEntity(MultipartFileWrapper<F> wrapper, T entity) throws Exception;
}
