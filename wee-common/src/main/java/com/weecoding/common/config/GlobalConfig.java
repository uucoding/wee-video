package com.weecoding.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.weecoding.common.enumerate.DateFormatEnum;
import com.weecoding.common.intercept.GlobalInterceptor;
import com.weecoding.common.properties.GlobalProperties;
import com.weecoding.common.util.DConverter;
import com.weecoding.common.util.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局配置文件
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-18  17:17
 */
@Configuration
@EnableConfigurationProperties(GlobalProperties.class)
public class GlobalConfig implements WebMvcConfigurer {

    @Autowired
    private GlobalProperties globalProperties;

    @Bean
    public GlobalInterceptor globalInterceptor() {
        return new GlobalInterceptor();
    }

    /**
     * 配置全局拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/v1/anonymous/**");
    }

    /**
     * 配置资源映射：本地图片映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本地图片路径 注意：路径最后需要以"/"结尾否则无法访问
        String localImagePath = S.join("file:", globalProperties.getFile().getAbsoluteStoragePathPrefix(), File.separator);
        //资源映射项目所有资源
        registry.addResourceHandler("/**")
                //映射本地地缘
                .addResourceLocations(localImagePath);
    }

    /**
     * JSON转换组件替换为fastJson
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(fastMediaTypes);
        // 配置转换格式
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.BrowserCompatible);
        fastJsonConfig.setDateFormat(DateFormatEnum.yyyy_MM_dd_HH_mm.getFormat());
        converter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> httpMsgConverter = converter;
        return new HttpMessageConverters(httpMsgConverter);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DConverter());
    }
}
