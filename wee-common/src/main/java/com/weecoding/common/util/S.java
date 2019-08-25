package com.weecoding.common.util;

import com.weecoding.common.constant.BaseConstants;
import com.weecoding.common.enumerate.SecurityCodeEnum;
import com.weecoding.common.wrapper.DataWrappers;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串常用操作：继承{@link StringUtils}
 *
 * @author : wee
 * @version : v 1.0
 * @Date 2019-07-04  23:25
 */
@Slf4j
public class S extends StringUtils {
    /***
     * 将list<string></>拼接成string，默认分隔符 ','
     * @param stringList
     * @return
     */
    public static String join(List<String> stringList) {
        return StringUtils.join(stringList, BaseConstants.EN_COMMA);
    }

    /***
     * 将string[]拼接成string，默认分隔符 ','
     * @param stringArray
     * @return
     */
    public static String join(String[] stringArray) {
        return StringUtils.join(stringArray, BaseConstants.EN_COMMA);
    }

    /***
     * 按@{@link BaseConstants#EN_COMMA}拆分字符串
     * @param string
     * @return
     */
    public static String[] split(String string) {
        return V.isEmpty(string) ? null : string.split(BaseConstants.EN_COMMA);
    }

    /**
     * 获得随机串
     *
     * @return
     */
    public static String newUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /***
     * 将首字母转为小写
     * @return
     */
    public static String lowerFirst(String string) {
        return V.isEmpty(string) ? null : (String.valueOf(string.charAt(0)).toLowerCase() + string.substring(1));

    }

    /***
     * 将首字母转为大写
     * @return
     */
    public static String upperFirst(String input) {
        if (input != null) {
            return String.valueOf(input.charAt(0)).toUpperCase() + input.substring(1);
        }
        return null;
    }

    /***
     * 生成指定位数的数字/验证码
     *
     * @param length 生成数据的长度
     * @param customCodeType {@link SecurityCodeEnum}
     * @return
     */
    public static String newRandom(int length, SecurityCodeEnum customCodeType) {
        String originData;
        switch (customCodeType) {
            //大写字母
            case UPPER_LETTER:
                originData = join(BaseConstants.LOWER_LETTER_ARR);
                break;
            //小写字母
            case LOWER_LETTER:
                originData = join(BaseConstants.LOWER_LETTER_ARR);
                break;
            //大小写混合
            case MIX_LETTER:
                originData = join(
                        DataWrappers.createArrayList(Arrays.asList(BaseConstants.UPPER_LETTER_ARR))
                                .addAll(Arrays.asList(BaseConstants.LOWER_LETTER_ARR))
                                .build()
                                .toArray()
                );
                break;
            //小写和数字
            case MIX_LOWER_LETTER_AND_NUMBER:
                originData = join(
                        DataWrappers.createArrayList()
                                .addAll(Arrays.asList(BaseConstants.LOWER_LETTER_ARR))
                                .addAll(Arrays.asList(BaseConstants.NUMBER_ARR))
                                .build()
                                .toArray()
                );
                break;
            //大写和数字
            case MIX_UPPER_LETTER_AND_NUMBER:
                originData = join(
                        DataWrappers.createArrayList()
                                .addAll(Arrays.asList(BaseConstants.UPPER_LETTER_ARR))
                                .addAll(Arrays.asList(BaseConstants.NUMBER_ARR))
                                .build()
                                .toArray()
                );
                break;
            //大小写和字母混合
            case MIX_ALL_LETTER_AND_NUMBER:
                originData = join(
                        DataWrappers.createArrayList()
                                .addAll(Arrays.asList(BaseConstants.UPPER_LETTER_ARR))
                                .addAll(Arrays.asList(BaseConstants.LOWER_LETTER_ARR))
                                .addAll(Arrays.asList(BaseConstants.NUMBER_ARR))
                                .build()
                                .toArray()
                );
                break;
            //默认是数字
            default:
                originData = join(BaseConstants.NUMBER_ARR);
                break;
        }
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            //随机获取字符串的下表 random.netInt(length) : [0, length - 1]
            int index = random.nextInt(originData.length());
            result.append(originData.charAt(index));
        }
        return result.toString();
    }

    /**
     * 去除重复的分隔符
     * 如：/a///b//c///d/f///
     * 如果{@code needLastSeparator} == false
     * => /a/b/c/d/f/
     * 如果{@code needLastSeparator} == true
     * => /a/b/c/d/f
     *
     * @param array
     * @param separator
     * @param needLastSeparator 默认为true
     * @return
     */
    public static String duplicateJoin(Object[] array, String separator, boolean... needLastSeparator) {
        boolean flag = V.notEmpty(needLastSeparator) && needLastSeparator.length > 0 ? needLastSeparator[0] : true;
        String duplicateJoin = S.join(array, separator).replaceAll(S.join("\\", separator, "{2,}"), "\\" + separator);
        if (flag && duplicateJoin.lastIndexOf(separator) == duplicateJoin.length() - 1) {
            return duplicateJoin.substring(0, duplicateJoin.length() - 1);
        }
        return duplicateJoin;
    }


    /**
     * {@link S#duplicateJoin(Object[], String, boolean...)}示例代码
     *
     * @param args
     */
    public static void main(String[] args) {
//        String join = S.duplicateJoin(new String[]{"1", "", null, "2", "", "", "d", "f", "", ""}, "，");
//        System.out.println(join);
//        String join2 = S.duplicateJoin(new String[]{"1", "", null, "2", "", "", "d", "f", "", ""}, "/", false);
//        System.out.println(join2);

        String s = newRandom(32, SecurityCodeEnum.MIX_ALL_LETTER_AND_NUMBER);
        System.out.println(s);
    }
}
