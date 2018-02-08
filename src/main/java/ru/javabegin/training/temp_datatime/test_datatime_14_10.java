package ru.javabegin.training.temp_datatime;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;

/**
 * Created by Николай on 10.10.2017.
 */
public class test_datatime_14_10 {

    public static void main(String args[]) throws ParseException {

/**
 * Получаем дату по дню недели
 */
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime nextMonday = dateTime.with(TemporalAdjusters.next(MONDAY));
        System.out.println(nextMonday);

        LocalDateTime ddd = dateTime.with(TemporalAdjusters.dayOfWeekInMonth(2,THURSDAY));

        System.out.println("требуемая дата = "+ ddd);

        LocalDateTime today = LocalDateTime.now();
        System.out.println("Получаем текущее время : " + today);

        /**
         * Делаем дату по Date + Time
         */

        int h=23;
        int m=0;
        today = LocalDateTime.of(LocalDate.now(), LocalTime.of(h, m, 0));
        System.out.println("DateTime сделанная   : " + today);
        System.out.println("DateTime вчера  : " + today.minusDays(1));
        System.out.println("Первый день этого месяца : " + today.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("Первый день предыдущего месяца : " + today.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()));


        System.out.println("Последний день этого месяца : " + today.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("Последний день предыдущего месяца : " + today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("//////////////////////////////////");

        //System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));

        /**
         * Работаем для Mothly
         */

        System.out.println("LocalDateTime -->>  Date: " );
        ZonedDateTime zdt = today.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        //////////// получили дату в Date в начале программы
        System.out.println("Перевели обратно в  LocalDateTime =");
        Instant date_time = output.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(date_time, ZoneId.systemDefault());

        //// отсекаем время, если оно введено не правильно и делаем свое
        LocalDate dateFromDateTime = ldt.toLocalDate();

        ///// добавляем свое время и формируем LocalDataTime
        int hour=23;
        int mimut=0;
        today = LocalDateTime.of( dateFromDateTime, LocalTime.of(hour, mimut, 0));
        ///получили дату , которая приходит в программу вместе с типом измерения

        /// последний день предыдущего месяца
        LocalDateTime lastMoth_1 = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        /// последний день (пред)предыдущего месяца (local-2)
        LocalDateTime lastMoth_2 = today.minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("lastMoth_1 ="+lastMoth_1);
        System.out.println("lastMoth_2 ="+lastMoth_2);

        String lastMoth_1_str = lastMoth_1.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        System.out.println("lastMoth_1_str =" + lastMoth_1_str);
        String lastMoth_2_str = lastMoth_2.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        System.out.println("lastMoth_2_str =" + lastMoth_2_str);



        List<String> date_dd_MM_UU_HH = new ArrayList<String>();

        date_dd_MM_UU_HH.add(lastMoth_2_str);
        date_dd_MM_UU_HH.add(lastMoth_1_str);

        System.out.println("date_dd_MM_UU_HH 0 =" + date_dd_MM_UU_HH.get(0));
        System.out.println("date_dd_MM_UU_HH 1 =" + date_dd_MM_UU_HH.get(1));



       /* LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);

        System.out.println(dateTimeFromDateAndTime);

        LocalDate dateFromDateTime = LocalDateTime.now().toLocalDate();
        LocalTime timeFromDateTime = LocalDateTime.now().toLocalTime();
*/





    }
}
