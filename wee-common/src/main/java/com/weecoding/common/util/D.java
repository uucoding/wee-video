package com.weecoding.common.util;


import com.weecoding.common.enumerate.DateFormatEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * 更多时间格式参考{@link DateFormatEnum}
 * @author : wee
 * @version : v1.0
 * @Date 2019-07-05  00:27
 */
@Slf4j
public class D extends DateUtils {

    /***
     * 当前的日期时间
     * @return format {@link DateFormatEnum}
     */
    public static String now(DateFormatEnum format) {
        return getDateString(new Date(), format);
    }

    /**
     * 获取指定时间格式的字符串
     *
     * @param date   待转化的时间
     * @param format {@link DateFormatEnum} 转化格式
     * @return
     */
    public static String getDateString(Date date, DateFormatEnum format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormat());
        return sdf.format(date);
    }

    /**
     * 毫秒数转date
     *
     * @param timeMillis
     * @return
     */
    public static Date timeMillis2Date(Long timeMillis) {
        return new Date(timeMillis);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss时间戳字符串转日期
     *
     * @param value
     * @return
     */
    public static Date datetimeString2Date(String value) {
        return convert2FormatDate(value, DateFormatEnum.yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 将yyyy-MM-dd字符串转化成日期
     *
     * @return
     * @throws ParseException
     */
    public static Date dateString2Date(String date) {
        return convert2FormatDate(date,DateFormatEnum.yyyy_MM_dd);
    }

    /**
     * 字符串转日期
     *
     * @param date  时间字符串
     * @param dateFormat 日期格式{@link DateFormatEnum}
     * @return
     * @throws ParseException
     */
    public static Date convert2FormatDate(String date, DateFormatEnum dateFormat) {
        try {
            return DateUtils.parseDate(date, dateFormat.getFormat());
        } catch (ParseException e) {
            log.error("[时间转化]<==异常：", e);
        }
        return null;
    }

    /**
     * 模糊匹配转化时间
     * @param dateString
     * @return
     */
    public static Date fuzzyConvert(String dateString){
        if(V.isEmpty(dateString)){
            return null;
        }
        if(dateString.contains("-")){
        }
        else if(dateString.contains("月")){
            dateString = dateString.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("号", "");
        }
        else{
            dateString = dateString.replaceAll("\\/", "-").replaceAll("\\.", "-");
        }
        String[] parts = dateString.split(" ");
        String[] ymd = parts[0].split("-");
        if(ymd.length >= 3){
            if(ymd[0].length() == 2){
                ymd[0] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(0, 2) + ymd[0];
            }
            if(ymd[1].length() == 1){
                ymd[1] = "0" + ymd[1];
            }
            if(ymd[2].length() == 1){
                ymd[2] = "0" + ymd[2];
            }
        }
        parts[0] = S.join(ymd, "-");
        if(parts.length == 1){
            return D.convert2FormatDate(parts[0], DateFormatEnum.yyyy_MM_dd);
        }
        // 18:20:30:103
        String[] hmsArray = new String[3];
        String[] hms = parts[1].split(":");
        if(hms[0].length() == 1){
            hms[0] = "0" + hms[0];
        }
        hmsArray[0] = hms[0];
        if(hms.length >= 2){
            if(hms[1].length() == 1){
                hms[1] = "0" + hms[1];
            }
            hmsArray[1] = hms[1];
        }
        else{
            hmsArray[1] = "00";
        }
        if(hms.length >= 3){
            if(hms[2].length() == 1){
                hms[2] = "0" + hms[2];
            }
            hmsArray[2] = hms[2];
        }
        else{
            hmsArray[2] = "00";
        }
        parts[1] = S.join(hmsArray, ":");
        return D.convert2FormatDate(S.join(parts, " "), DateFormatEnum.yyyy_MM_dd_HH_mm_ss);
    }
}
