package com.independent.independent.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zou on 2018/5/9.
 */
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    /**
     * 常用变量
     */
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 获取系统前时间.
     *
     *
     * @return t time[yyyy-MM-dd HH:mm:ss]
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd HH:mm:ss");
        String time =  sdf.format(new Date());
        return time;
    }

    /**
     * 获取当前日期
     *
     * @return
     *
     */
    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     *
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     *
     */
    public static String getCurrentDateTime(String Dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(Dateformat);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String dateToDateTime(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_TIME_FORMAT);
        datestr = df.format(date);
        return datestr;
    }
    /**
     * 将字符串日期转换为日期格式
     *
     * @param datestr
     * @return
     *
     */
    public static Date stringToDate(String datestr) {

        if(datestr ==null ||datestr.equals("")){
            return null;
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            date=DateUtil.stringToDate(datestr,"yyyyMMdd");
        }
        return date;
    }

    /**
     * 将字符串日期转换为日期格式
     * 自定義格式
     *
     * @param datestr
     * @return
     *
     */
    public static Date stringToDate(String datestr, String dateformat) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        try {
            date = df.parse(datestr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    /**
     * 将日期格式日期转换为字符串格式
     *
     * @param date
     * @return
     *
     */
    public static String dateToString(Date date) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 将日期格式日期转换为字符串格式 自定義格式
     *
     * @param date
     * @param dateformat
     * @return
     */
    public static String dateToString(Date date, String dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        datestr = df.format(date);
        return datestr;
    }

    /**
     * 获取日期的HOUR值
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getHourOfDate(Date date) {
        int h = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        h = cd.get(Calendar.HOUR_OF_DAY);
        return h;
    }

    /**
     * 获取日期的DAY值
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getDayOfDate(Date date) {
        int d = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        d = cd.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    /**
     * 获取日期的MONTH值
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getMonthOfDate(Date date) {
        int m = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        m = cd.get(Calendar.MONTH) + 1;
        return m;
    }

    /**
     * 获取日期的YEAR值
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getYearOfDate(Date date) {
        int y = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        y = cd.get(Calendar.YEAR);
        return y;
    }

    /**
     * 获取星期几
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static int getWeekOfDate(Date date) {
        int wd = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        wd = cd.get(Calendar.DAY_OF_WEEK) - 1;
        return wd;
    }

    /**
     * 获取输入日期的当月第一天
     *
     * @param date
     *      输入日期
     * @return
     *
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 获得输入日期的当月最后一天
     *
     * @param date
     */
    public static Date getLastDayOfMonth(Date date) {
        return DateUtil.addDay(DateUtil.getFirstDayOfMonth(DateUtil.addMonth(date, 1)), -1);
    }


    /**
     * 两个日期相减得到的天数
     *
     * @param beginDate
     *
     * @param endDate
     */

    public static int getDiffDays(Date beginDate, Date endDate) {

        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();

        return days;
    }

    /**
     * 判断是否是闰年
     *
     * @param date
     *      输入日期
     * @return 是true 否false
     *
     */
    public static boolean isLeapYEAR(Date date) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int year = cd.get(Calendar.YEAR);

        if (year % 4 == 0 && year % 100 != 0 | year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据整型数表示的年月日，生成日期类型格式
     *
     * @param year
     *      年
     * @param month
     *      月
     * @param day
     *      日
     * @return
     *
     */
    public static Date getDateByYMD(int year, int month, int day) {
        Calendar cd = Calendar.getInstance();
        cd.set(year, month-1, day);
        return cd.getTime();
    }

    /**
     * 获取年周期对应日
     *
     * @param date
     *      输入日期
     * @param iyear
     *      年数  負數表示之前
     * @return
     *
     */
    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 获取月周期对应日
     *
     * @param date
     *      输入日期
     * @param i
     * @return
     *
     */
    public static Date getMonthCycleOfDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.MONTH, i);

        return cd.getTime();
    }

    /**
     * 计算 fromDate 到 toDate 相差多少年
     *
     * @param fromDate
     * @param toDate
     * @return 年数
     *
     */
    public static int getYearByMinusDate(Date fromDate, Date toDate) {
        Calendar df=Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt=Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR)-df.get(Calendar.YEAR);
    }

    /**
     * 计算 fromDate 到 toDate 相差多少个月
     *
     * @param fromDate
     * @param toDate
     * @return 月数
     *
     */
    public static int getMonthByMinusDate(Date fromDate, Date toDate) {
        Calendar df=Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt=Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR)*12+dt.get(Calendar.MONTH)-
                (df.get(Calendar.YEAR)*12+df.get(Calendar.MONTH));
    }

    /**
     * 计算 fromDate 到 toDate 相差多少天
     *
     * @param fromDate
     * @param toDate
     * @return 天数
     *
     */
    public static long getDayByMinusDate(Object fromDate, Object toDate) {

        Date f=DateUtil.chgObject(fromDate);

        Date t=DateUtil.chgObject(toDate);

        long fd=f.getTime();
        long td=t.getTime();

        return (td-fd)/(24L * 60L * 60L * 1000L);
    }

    /**
     * 计算年龄
     *
     * @param birthday
     *      生日日期
     * @param calcDate
     *      要计算的日期点
     * @return
     *
     */
    public static int calcAge(Date birthday, Date calcDate) {

        int cYear=DateUtil.getYearOfDate(calcDate);
        int cMonth=DateUtil.getMonthOfDate(calcDate);
        int cDay=DateUtil.getDayOfDate(calcDate);
        int bYear=DateUtil.getYearOfDate(birthday);
        int bMonth=DateUtil.getMonthOfDate(birthday);
        int bDay=DateUtil.getDayOfDate(birthday);

        if(cMonth>bMonth||(cMonth==bMonth&&cDay>bDay)){
            return cYear-bYear;
        }else{
            return cYear-1-bYear;
        }
    }

    /**
     * 从身份证中获取出生日期
     *
     * @param idno
     *      身份证号码
     * @return
     *
     */
    public static String getBirthDayFromIDCard(String idno) {
        Calendar cd = Calendar.getInstance();
        if (idno.length() == 15) {
            cd.set(Calendar.YEAR, Integer.valueOf("19" + idno.substring(6, 8))
                    .intValue());
            cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(8, 10))
                    .intValue() - 1);
            cd.set(Calendar.DAY_OF_MONTH,
                    Integer.valueOf(idno.substring(10, 12)).intValue());
        } else if (idno.length() == 18) {
            cd.set(Calendar.YEAR, Integer.valueOf(idno.substring(6, 10))
                    .intValue());
            cd.set(Calendar.MONTH, Integer.valueOf(idno.substring(10, 12))
                    .intValue() - 1);
            cd.set(Calendar.DAY_OF_MONTH,
                    Integer.valueOf(idno.substring(12, 14)).intValue());
        }
        return DateUtil.dateToString(cd.getTime());
    }

    /**
     * 在输入日期上增加（+）或减去（-）小时
     *
     * @param date
     *      输入日期
     * @param hour
     *      要增加或减少的小时
     */
    public static Date addHour(Date date, int hour) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.HOUR_OF_DAY, hour);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date
     *      输入日期
     * @param iday
     *      要增加或减少的天数
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, iday);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date
     *      输入日期
     * @param imonth
     *      要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.MONTH, imonth);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）年份
     *
     * @param date
     *      输入日期
     * @param iyear
     *      要增加或减少的年数
     */
    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 將OBJECT類型轉換為Date
     * @param date
     * @return
     */
    public static Date chgObject(Object date){

        if(date!=null&&date instanceof Date){
            return (Date)date;
        }

        if(date!=null&&date instanceof String){
            return DateUtil.stringToDate((String)date);
        }

        return null;

    }

    public static long getAgeByBirthday(String date){

        Date birthday = stringToDate(date, "yyyy-MM-dd");
        long sec = new Date().getTime() - birthday.getTime();

        long age = sec/(1000*60*60*24)/365;

        return age;
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        //String temp = DateUtil.dateToString(getLastDayOfMonth(new Date()),
        ///   DateUtil.DATE_FORMAT_CHINESE);
        //String s=DateUtil.dateToString(DateUtil.addDay(DateUtil.addYear(new Date(),1),-1));


        long s=DateUtil.getDayByMinusDate("2012-01-01", "2012-12-31");
        System.err.println(s);


    }
    //JAVA获取某段时间内的所有日期
    public static List< Date > findDates(Date DStart, Date DEnd){
        List Date1 = new ArrayList();
        Date1.add(DStart);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(DStart);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(DEnd);
        // 测试此日期是否在指定日期之后
        while (DEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Date1.add(calBegin.getTime());
        }
        return Date1;
    }


    //  String pTime = "2012-03-12";
    //传入"2012-03-12"或者”20120302都可以
    public static String getWeekday(String dateString) {
        SimpleDateFormat dateFormater1=new SimpleDateFormat("yyyyMMdd");
        Date date= null;
        try {
            date = dateFormater1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String Week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "周日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "周六";
        }
        return Week;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
