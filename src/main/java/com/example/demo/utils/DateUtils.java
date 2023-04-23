package com.example.demo.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.ChronoZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tangxiangan
 * 时间工具类
 */
public class DateUtils {
    public static final String COMMON_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String COMMON_FORMAT_DATE = "yyyy-MM-dd";

    private static final DateTimeFormatter DTF_FOR_ID = DateTimeFormatter.ofPattern("yyMMddHHmmss");


    /**
     * 时间戳转化时间
     *
     * @param timestamp 已处理过的时间戳
     * @return
     */
    public static Date longToDate(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 时间戳转化时间
     *
     * @param timestamp 已处理过的时间戳
     * @param pattern
     * @return
     */
    public static String longToDateStr(long timestamp, String pattern) {
        DateFormat dt = new SimpleDateFormat(pattern);
        return dt.format(new Date(timestamp));
    }

    /**
     * 字符串转化时间
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date parseStringToDate(String str, String pattern) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Date -> String
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date,String pattern){
        DateFormat dt = new SimpleDateFormat(pattern);
        return dt.format(date);
    }

    /**
     * 增减分钟
     * @param minutes 分钟
     * @param sign 增减标识 正数加 复数减
     * @return
     */
    public static Date addMinutes(Date date,int minutes,int sign){
        LocalDateTime localDateTime = getLocalDateTimeFormDate(date);
        if(sign > 0){
            localDateTime = localDateTime.plusMinutes(minutes);
        }else{
            localDateTime = localDateTime.minusHours(minutes);
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date handleHours(Date date,int hours,int sign){
        LocalDateTime localDateTime = getLocalDateTimeFormDate(date);
        if(sign > 0){
            localDateTime = localDateTime.plusHours(hours);
        }else{
            localDateTime = localDateTime.minusHours(hours);
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date localDateTimeToDate(LocalDateTime localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDate.atZone(zoneId);
       return Date.from(zonedDateTime.toInstant());
    }

    public static Date localDateToDate(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ChronoZonedDateTime<LocalDate> zonedDateTime = localDate.atStartOfDay(zoneId);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * @Description: Date -> LocalDateTime
     */
    public static LocalDateTime getLocalDateTimeFormDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);

    }
    public static LocalDate getLocalDateFormDate(Date date){
        LocalDateTime localDateTimeFormDate = getLocalDateTimeFormDate(date);
        return localDateTimeFormDate.toLocalDate();
    }

    /**
     * 获取最近一个月第一天的日期
     * @return
     */
    public static String lastMothFirstDay(String pattern){
        Date now = new Date();
        Calendar lastMonth = Calendar.getInstance();
        lastMonth.setTime(now);
        lastMonth.add(Calendar.MONTH, -1);
        String time = new SimpleDateFormat(pattern).format( lastMonth.getTime());
        return time;
    }

    /**
     * 时间比较 格式 yyyy-MM-dd
     * @param dateStr
     * @param targetDateStr
     * @return
     */
    public static int compareDate(String dateStr,String targetDateStr){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr + " 00:00:00",df);
        LocalDateTime targetLocalDateTime = LocalDateTime.parse(targetDateStr + " 00:00:00",df);
        return localDateTime.compareTo(targetLocalDateTime);
    }



    /**
     * 时间改为最小(00:00:00)
     *
     * @param date date
     */
    public static Date timeMin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        return cal.getTime();
    }

    /**
     * 时间改为最大(23:59:59)
     *
     * @param date date
     */
    public static Date timeMax(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        return cal.getTime();
    }


    /**
     * 日期相减，获取天数
     * @param currentDate
     * @param targetDate
     * @return
     */
    public static long minusDate(Date currentDate,Date targetDate){
        LocalDate currentLocalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate targetLocalDate = targetDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return targetLocalDate.until(currentLocalDate, ChronoUnit.DAYS);
    }

    /**
     * 计算date时间距离当前时间多少分钟
     * @param date 输入待比较日期
     * @return 分钟
     */
    public static long calMinutesToNow(Date date){
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        TimeUnit time = TimeUnit.MINUTES;
        return time.convert(l, TimeUnit.MILLISECONDS);
    }


    /**
     * 日期加减天数，day是正数为加法，是负数为减法
     * @param date 被操作的日期
     * @param day day是正数为加法，是负数为减法
     * @return 操作后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
//      rightNow.add(Calendar.YEAR,-1);//日期减1年
//      rightNow.add(Calendar.MONTH,3);//日期加3个月
//      rightNow.add(Calendar.DAY_OF_YEAR,5);//日期加5天
        rightNow.add(Calendar.DAY_OF_YEAR,day);
        return rightNow.getTime();
    }

}
