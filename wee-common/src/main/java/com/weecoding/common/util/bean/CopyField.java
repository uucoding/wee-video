package com.weecoding.common.util.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * 该注解应用于类属性上，主要为了设置属性别名，适用于不同属性拷贝
 * @author : wee
 * @version v1.0
 * @Date 2019-07-10  16:28
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CopyField {
    /**
     * 在即将被拷贝的属性上面，设置目标属性名
     * @return
     */
    String targetName() default "";

    /**
     * 在即将拷贝至改属性上面，设置源属性名
     * @return
     */
    String originName() default "";
}
