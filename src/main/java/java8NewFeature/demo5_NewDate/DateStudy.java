package java8NewFeature.demo5_NewDate;

/**
 * @author by xinjian.yao
 * @date 2019/11/11 20:23
 */

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * LocalDate、LocalTime、LocalDateTime
 *
 * 第一个只负责日期相关的处理，第二个负责时间相关的处理，第三个日期时间一起处理，其本质是前两者操作的再封装。
 */
public class DateStudy {


    public static void main(String[] args) {

        // 以前的获取时间
        long l = System.currentTimeMillis();
        System.out.println(l);

        // java8获取
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        // 指定字符串获取时间
        LocalDateTime localDateTime = LocalDateTime.of(2019, Month.NOVEMBER, 11, 11, 11);
        System.out.println(localDateTime);

        // 指定数字获取时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String text = "2018-11-11 11:11:11";
        LocalDateTime someDay = LocalDateTime.parse(text, formatter);
        System.out.println(someDay);

        // 时间格式化
        String nowStr = now.format(formatter);
        System.out.println(nowStr);

        // 日期比较大小
        boolean nowIsBefore = LocalDateTime.now().isBefore(someDay);
        boolean nowIsAfter = LocalDateTime.now().isAfter(someDay);
        System.out.println(nowIsBefore+"---"+nowIsAfter);

        //日期相差多少年 | 月 | 天 | 小时 | 分钟 | 秒
        long diffYears = ChronoUnit.YEARS.between(LocalDateTime.now(), someDay);
        System.out.println(diffYears);
        long diffMonths = ChronoUnit.MONTHS.between(LocalDateTime.now(), someDay);
        System.out.println(diffMonths);
        long diffDays = ChronoUnit.DAYS.between(LocalDateTime.now(), someDay);
        System.out.println(diffDays);
        long diffHours = ChronoUnit.HOURS.between(LocalDateTime.now(), someDay);
        System.out.println(diffHours);
        long diffMins = ChronoUnit.MINUTES.between(LocalDateTime.now(), someDay);
        System.out.println(diffMins);
        long diffSecs = ChronoUnit.SECONDS.between(LocalDateTime.now(), someDay);
        System.out.println(diffSecs);


        // 获取分钟
        int mins2 = LocalDateTime.now().getMinute();
        long mins3 = LocalDateTime.now().getLong(ChronoField.MINUTE_OF_HOUR);
        long mins4 = LocalDateTime.now().getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(mins2+"----"+mins3+"------"+mins4);


    }
}
