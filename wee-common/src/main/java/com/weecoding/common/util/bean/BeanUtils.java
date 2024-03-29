package com.weecoding.common.util.bean;

import lombok.extern.slf4j.Slf4j;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * bean拷贝帮助类：可以结合{@link CopyField}使用
 * @author : wee
 * @version v1.0
 * @Description: 常用bean相关方法
 */
@Slf4j
public class BeanUtils {

    /**
     * <h3>拷贝一个对象的属性至另一个对象</h3>
     * <p>
     * 支持两个对象之间不同属性名称进行拷贝，使用注解{@link CopyField}
     * </p>
     *
     * @param originBean 源对象 使用注解{@link CopyField#targetName()}
     * @param targetBean 目标对象 使用注解{@link CopyField#originName()}
     */
    public static void copyBean(Object originBean, Object targetBean) {
        Map<String, Object> originFieldKeyWithValueMap = new HashMap<>(16);
        PropertyDescriptor propertyDescriptor = null;
        //生成源bean的属性及其值的字典
        generateOriginFieldWithValue(propertyDescriptor, originBean, originFieldKeyWithValueMap, originBean.getClass());
        //设置目标bean的属性值
        settingTargetFieldWithValue(propertyDescriptor, targetBean, originFieldKeyWithValueMap, targetBean.getClass());
    }

    /**
     * <h3>拷贝一个对象的属性至另一个对象</h3>
     * <p>
     * 支持两个对象之间不同属性名称进行拷贝，使用注解{@link CopyField}
     * </p>
     *
     * @param originBean      源对象 使用注解{@link CopyField#targetName()}
     * @param targetBeanClass 目标对象class 使用注解{@link CopyField#originName()}
     * @param <T>
     * @return
     */
    public static <T> T copyBean(Object originBean, Class<T> targetBeanClass) {
        try {
            T targetBean = targetBeanClass.newInstance();
            copyBean(originBean, targetBean);
            return targetBean;
        } catch (Exception e) {
            log.error("【拷贝对象】<==失败，目标对象为：" + targetBeanClass.getSimpleName());
        }
        return null;
    }

    /**
     * <h3>拷贝一个对象的属性至另一个对象</h3>
     * <p>
     * 支持两个对象之间不同属性名称进行拷贝，使用注解{@link CopyField}
     * </p>
     *
     * @param originBeanList  源对象 使用注解{@link CopyField#targetName()}
     * @param targetBeanClass 目标对象class 使用注解{@link CopyField#originName()}
     * @param <T>
     * @return
     */
    public static <T> List<T> copyBeanList(List originBeanList, Class<T> targetBeanClass) {
        try {
            List<T> targetBeanList = new ArrayList<>();
            for (Object originBean : originBeanList) {
                T targetBean = targetBeanClass.newInstance();
                copyBean(originBean, targetBean);
                targetBeanList.add(targetBean);
            }
            return targetBeanList;
        } catch (Exception e) {
            log.error("【拷贝对象】<==失败，目标对象为：" + targetBeanClass.getSimpleName());
        }
        return null;
    }

    /**
     * 生成需要被拷贝的属性字典 属性-属性值<br/>
     * 递归取父类属性值
     *
     * @param propertyDescriptor         属性描述器，可以获取bean中的属性及方法
     * @param originBean                 待拷贝的bean
     * @param originFieldKeyWithValueMap 存放待拷贝的属性和属性值
     * @param beanClass                  待拷贝的class[可能是超类的class]
     */
    private static void generateOriginFieldWithValue(PropertyDescriptor propertyDescriptor, Object originBean, Map<String, Object> originFieldKeyWithValueMap, Class<?> beanClass) {
        /**如果不存在超类，那么跳出循环*/
        if (beanClass.getSuperclass() == null) {
            return;
        }
        Field[] originFieldList = beanClass.getDeclaredFields();
        for (Field field : originFieldList) {
            try {
                /*获取属性上的注解。如果不存在，使用属性名，如果存在使用注解名*/
                CopyField annotation = field.getAnnotation(CopyField.class);
                String targetName = "";
                if (annotation != null) {
                    targetName = annotation.targetName();
                } else {
                    targetName = field.getName();
                }
                //初始化
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                propertyDescriptor = new PropertyDescriptor(field.getName(), beanClass);
                //获取当前属性的get方法
                Method method = propertyDescriptor.getReadMethod();
                //设置值
                Object value = method.invoke(originBean);
                //设置值
                originFieldKeyWithValueMap.put(targetName, value);
            } catch (IntrospectionException e) {
                log.warn("【源对象】<==异常:" + field.getName() + "不存在对应的get方法，无法参与拷贝！");
            } catch (IllegalAccessException e) {
                log.warn("【源对象】<==异常:" + field.getName() + "的get方法执行失败！");
            } catch (InvocationTargetException e) {
                log.warn("【源对象】<==异常:" + field.getName() + "的get方法执行失败！");
            }
        }
        //生成超类 属性-value
        generateOriginFieldWithValue(propertyDescriptor, originBean, originFieldKeyWithValueMap, beanClass.getSuperclass());
    }

    /**
     * @param propertyDescriptor         属性描述器，获取当前传入属性的（getter/setter）方法
     * @param targetBean                 目标容器bean
     * @param originFieldKeyWithValueMap 待拷贝的属性和属性值
     * @param beanClass                  待设置的class[可能是超类的class]
     */
    private static void settingTargetFieldWithValue(PropertyDescriptor propertyDescriptor, Object targetBean, Map<String, Object> originFieldKeyWithValueMap, Class<?> beanClass) {
        /**如果不存在超类，那么跳出循环*/
        if (beanClass.getSuperclass() == null) {
            return;
        }
        Field[] targetFieldList = beanClass.getDeclaredFields();
        for (Field field : targetFieldList) {
            try {
                /*获取属性上的注解。如果不存在，使用属性名，如果存在使用注解名*/
                CopyField annotation = field.getAnnotation(CopyField.class);
                String originName;
                if (annotation != null) {
                    originName = annotation.originName();
                } else {
                    originName = field.getName();
                }
                //初始化当前属性的描述器
                propertyDescriptor = new PropertyDescriptor(field.getName(), beanClass);
                //获取当前属性的set方法
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(targetBean, originFieldKeyWithValueMap.get(originName));
            } catch (IntrospectionException e) {
                log.warn("【目标对象】<==异常:" + field.getName() + "不存在对应的set方法，无法参与拷贝！");
            } catch (IllegalAccessException e) {
                log.warn("【目标对象】<==异常:" + field.getName() + "的set方法执行失败！");
            } catch (InvocationTargetException e) {
                log.warn("【目标对象】<==异常:" + field.getName() + "的set方法执行失败！");
            }
        }
        //设置超类属性
        settingTargetFieldWithValue(propertyDescriptor, targetBean, originFieldKeyWithValueMap, beanClass.getSuperclass());
    }
}
