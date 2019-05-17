package com.jonas;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 【 时间工具类 】
 *
 * @author shenjy 2018/08/25
 */
public class DateUtils {

    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_ZERO_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final Integer MINUTE_SECOND = 60;

    public static final Integer HOUR_SECOND = 60 * 60;

    public static final Integer DAY_SECOND = HOUR_SECOND * 24;

    public static final Integer MONTH_SECOND = DAY_SECOND * 30;

    public static final Integer YEAR_SECOND = MONTH_SECOND * 12;

    /**
     * 获取当前日期字符串
     * @return
     */
    public static String getCurrentDate() {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD));
    }

    /**
     * 获取某一个时间段之后的毫秒时间戳
     * @param stamp
     * @param amount
     * @param unit
     * @return
     */
    public static Long getNextStamp(Long stamp, Integer amount, ChronoUnit unit) {
        LocalDateTime now = Instant.ofEpochMilli(stamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime next =  now.plus(amount, unit);
        return next.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 根据毫秒时间戳比较日期大小
     * @param stamp1
     * @param stamp2
     * @return
     */
    public static Integer compareDate(Long stamp1, Long stamp2) {
        LocalDate localDate1 = Instant.ofEpochMilli(stamp1).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = Instant.ofEpochMilli(stamp2).atZone(ZoneId.systemDefault()).toLocalDate();

        Integer result = localDate1.compareTo(localDate2);
        if (0 == result) {
            return 0;
        } else if (0 > result) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * "yyyy-MM-dd"格式 转化为 毫秒时间戳
     * @param date
     * @return
     */
    public static Long getStampFromDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD));
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"格式 转化为 毫秒时间戳
     * @param dateTime
     * @return
     */
    public static Long getStampFromTime(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD_HH_MM_SS));
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 处理0时区时间为本地时间
     * @param time
     * @return
     */
    public static Long parseZeroZoneTime(String time) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(FORMAT_ZERO_ZONE));
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    /**
     * 毫秒时间戳 转化为 "yyyy-MM-dd"
     * @param stamp
     * @return
     */
    public static String getDate(Long stamp) {
        LocalDate localDate = Instant.ofEpochMilli(stamp).atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD));
    }

    /**
     * 毫秒时间戳 转化为 "yyyy-MM-dd HH:mm:ss"
     * @param stamp
     * @return
     */
    public static String getDateTime(Long stamp) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(stamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD_HH_MM_SS));
    }

    /**
     * 秒时间戳
     * @return
     */
    public static int currentSecond() {
        return Long.valueOf(System.currentTimeMillis() / 1000).intValue();
    }

    /**
     * 秒时间戳
     * @return
     */
    public static int parseToSecond(Long millisecond) {
        return Long.valueOf(millisecond / 1000).intValue();
    }

    public static void main(String[] args) {
        String dateStr = "2018-08-30T08:38:35Z";
        Long stamp = parseZeroZoneTime(dateStr);
        System.out.println(stamp);
        System.out.println(getDateTime(stamp));
    }
}