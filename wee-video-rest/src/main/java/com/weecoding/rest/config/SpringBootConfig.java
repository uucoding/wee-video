package com.weecoding.rest.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring相关配置
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-09  13:13
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass=true)
public class SpringBootConfig implements WebMvcConfigurer {


}
