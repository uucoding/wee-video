package com.weecoding.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * 全局配置信息
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-31  00:22
 */
@Data
@Component
@ConfigurationProperties(GlobalProperties.PREFIX)
public class GlobalProperties {

    public final static String PREFIX = "weecoding.global";

    /**
     * 文件相关配置: @NestedConfigurationProperty json文件中可以单独分组,
     * //配置文件中不会出现weecoding.global.file的提示，只会提示file下的属性，
     * //否则会对file提示，会以为file可配置
     */
    @Autowired
    @NestedConfigurationProperty
    private FileProperties file;

    /**
     * 是否开启token认证
     */
    private boolean enableTokenFilter = true;
}
