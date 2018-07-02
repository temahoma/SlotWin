package com.cage.library.utils.time;

import android.text.TextUtils;

import com.cage.library.infrastructure.log.Log;
import com.cage.library.infrastructure.text.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luyunfeng on 16/6/8.
 */
public class DateUtils {

    public final static String DATE_FORMAT_PLAN = "yyyy-MM-dd hh:mm:ss";
    private final static String DATE_FORMAT_PARSE = "yyyy-MM-dd";
    private final static String DATE_FORMAT_PLACE = "%04d-%02d-%02d";
    private final static String TIME_FORMAT_PLACE = "%02d:%02d:%02d";
    public final static String TIME_FORMAT = "%02d:%02d";

    // 解析日期
    public static Calendar parseDate(String date) {
        Calendar temp = Calendar.getInstance();
        if (TextUtils.isEmpty(date)) {
            return temp;
        }
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_PARSE);
        try {
            temp.setTime(sf.parse(date));
        } catch (ParseException e) {
            Log.printStackTrace(e);
            return temp;
        }
        return temp;
    }


    // 解析日期
    public static Calendar parseDateNullable(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_PARSE);
        Calendar temp = Calendar.getInstance();
        try {
            temp.setTime(sf.parse(date));
        } catch (ParseException e) {
            Log.printStackTrace(e);
            return null;
        }
        return temp;
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */

    public static Date parse(String strDate, String pattern) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(strDate);
        } catch (Exception e) {
            Log.printStackTrace(e);
            return null;
        }
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    // 判断是否在日期区间
    public static boolean isDateBetween(String start, String end, String date) {
        return isDateBetween(start,end,parseDate(date));
    }

    // 判断是否在日期区间
    public static boolean isDateBetween(String start, String end, Calendar cal_date) {

        if (cal_date == null) {
            return false;
        }

        Calendar cal_start = parseDateNullable(start);
        Calendar cal_end = parseDateNullable(end);

        if (cal_start == null && cal_end == null ){
            return false;
        }

        if (cal_start != null && cal_end == null) {
            return cal_date.compareTo(cal_start) >= 0;
        }

        if (cal_start == null && cal_end != null) {
            return cal_date.compareTo(cal_end) <= 0;
        }

        return  cal_date.compareTo(cal_start) >= 0 && cal_date.compareTo(cal_end) <= 0;
    }

    public static int getDayCount(Calendar begin, Calendar end) {
        long l = end.getTimeInMillis() - begin.getTimeInMillis();
        if (l < 0){
            return 0;
        }
        int days = Long.valueOf(l / (1000 * 60 * 60 * 24)).intValue();
        if (days == 0)
            return 1;
        if (days < 0) {
            return 0;
        }
        return days;
    }

    public static int getDayCount(String begin, String end) {
        return getDayCount(parseDate(begin), parseDate(end));
    }

    public static int getDayGap(Calendar begin, Calendar end) {
        long l = end.getTimeInMillis() - begin.getTimeInMillis();
        if (l < 0){
            return 0;
        }
        return Long.valueOf(l / (1000 * 60 * 60 * 24)).intValue();
    }


    public static int getMonthGap(Calendar begin, Calendar end) {
        return (end.get(Calendar.YEAR) - begin.get(Calendar.YEAR)) * 12 + end.get(Calendar.MONTH) - begin.get(Calendar.MONTH);
    }

    public static void copyDate(Calendar target, Calendar original){
        target.set(original.get(Calendar.YEAR), original.get(Calendar.MONTH), original.get(Calendar.DAY_OF_MONTH));
    }

    public static String getDateString(int year, int month, int day) {
        return StringUtils.format(DATE_FORMAT_PLACE, year, month + 1, day);
    }

    private static String getTimeStringFromCalendar(Calendar cal) {
        return StringUtils.format(TIME_FORMAT_PLACE, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE) + 1, cal.get(Calendar.SECOND));
    }

    public static String getDateString(Calendar cal) {
        return StringUtils.format(DATE_FORMAT_PLACE, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }

    public static String getMonthShortDesc(int month) {
        switch (month) {
            case 0:
                return "JAN";
            case 1:
                return "FEB";
            case 2:
                return "MAR";
            case 3:
                return "APR";
            case 4:
                return "MAY";
            case 5:
                return "JUN";
            case 6:
                return "JUL";
            case 7:
                return "AUG";
            case 8:
                return "SEP";
            case 9:
                return "OCT";
            case 10:
                return "NOV";
            case 11:
                return "DEC";
        }
        return null;
    }

    // 纯日期比较
    public static int compare(Calendar calendar1, Calendar calendar2) {
        int gap = calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);

        if (gap != 0) return gap;

        gap = calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);

        if (gap != 0) return gap;

        gap = calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);

        if (gap != 0) return gap;

        return 0;
    }
}
