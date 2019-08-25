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
     * @throws Exception
     */
    boolean updateEntity(T entity) throws Exception;

    /**
     * 上传单个文件处理
     * @param wrapper
     * @param key 上传标记的key，用于做文件上层的目录，一般为用户的id
     * @param <F>  可以上传多种途径
     * @return  返回数据库的文件的路径
     * @throws Exception
     */
    <F extends MultipartFile> String uploadSingleFile(MultipartFileWrapper<F> wrapper, String key) throws Exception;
}
