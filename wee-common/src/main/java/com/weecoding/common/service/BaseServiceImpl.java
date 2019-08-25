package com.weecoding.common.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weecoding.common.enumerate.FileDirectoryEnum;
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
    public <F extends MultipartFile> String uploadSingleFile(MultipartFileWrapper<F> wrapper, String key) throws Exception {
        F fileStream = wrapper.getFile();
        if (V.isEmpty(fileStream)) {
            throw new GlobalException(FileEnum.PARAM_FILE_NOT_EXIST);
        }
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            //1、获取统一配置的配置的文件路径
            String absoluteStoragePathPrefix = globalProperties.getFile().getAbsoluteStoragePathPrefix();
            //2、获取文件名
            String fileName = fileStream.getOriginalFilename();
            //3、获取存储的类型
            String dir = FileDirectoryEnum.DIR.choose(fileName);
            //4、数据库存储的文件路径
            String dbStoragePath = "/" + S.join(new String[]{dir, key, fileName}, File.separator);

            //5、构建文件全路径
            String fullPath = S.duplicateJoin(new String[]{absoluteStoragePathPrefix, dbStoragePath}, File.separator);

            //如果文件夹不存在，创建文件夹
            File file = new File(fullPath);
            if (file.getParentFile() != null || !file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }
            //输出到文件流
            fileOutputStream = new FileOutputStream(file);
            inputStream = fileStream.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
            return dbStoragePath;
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
}
