package com.weecoding.common.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weecoding.common.exception.GlobalException;
import com.weecoding.common.form.MultipartFileWrapper;
import com.weecoding.common.mapper.IBaseMapper;
import com.weecoding.common.model.Entity;
import com.weecoding.common.properties.GlobalProperties;
import com.weecoding.common.util.JSON;
import com.weecoding.common.util.S;
import com.weecoding.common.util.V;
import com.weecoding.common.util.bean.BeanUtils;
import com.weecoding.common.util.response.enumerate.ErrorEnum;
import com.weecoding.common.util.response.enumerate.FileEnum;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 基础的service
 *
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-06  19:13
 */
public class BaseServiceImpl<M extends IBaseMapper<T>, T extends Entity> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    protected GlobalProperties globalProperties;

    @Override
    public boolean updateEntity(T entity) throws Exception {
        return super.getBaseMapper().updateById(entity) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <F extends MultipartFile> void uploadSingleFileWithEntity(MultipartFileWrapper<F> wrapper, T entity) throws Exception {
        F fileStream = wrapper.getFile();
        if (V.isEmpty(fileStream)) {
            throw new GlobalException(FileEnum.PARAM_FILE_NOT_EXIST);
        }
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            //获取统一配置的配置的文件路径
            String absoluteStoragePathPrefix = globalProperties.getFile().getAbsoluteStoragePathPrefix();
            //获取文件名
            String fileName = fileStream.getOriginalFilename();
            //构建最终的文件路径
            String fullPath = S.duplicateJoin(new String[]{absoluteStoragePathPrefix, entity.getKey().toString(), fileName}, File.separator);
            String dbStoragePath = S.join(new String[]{entity.getKey().toString(), fileName}, File.separator);
            //如果文件夹不存在，创建文件夹
            File file = new File(fullPath);
            if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }
            fileOutputStream = new FileOutputStream(file);
            inputStream = fileStream.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);

            setFilePath(dbStoragePath, entity);
            //保存文件到数据库
            baseMapper.updateById(entity);
        } catch (Exception e) {
            log.error("【上传文件】<== 异常：", e);
            throw new GlobalException(ErrorEnum.ERROR);
        } finally {
            if (V.notEmpty(fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            if (V.notEmpty(inputStream)) {
                inputStream.close();
            }
        }
    }

    /**
     * 上传后得到路径，设置到entity中
     *
     * @param uploadPath
     * @param entity
     */
    protected void setFilePath(String uploadPath, T entity) {
        log.warn("【上传文件】<==后续操作，由子类覆盖实现");
    }
}
