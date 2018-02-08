package ru.javabegin.training.temp_datatime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.TemporalAdjusters.dayOfWeekInMonth;

/**
 * Created by Николай on 10.10.2017.
 */
public class test_datatime_DayOfWeek_12_10 {

    public static void main(String args[]) throws ParseException {


        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime nextMonday = dateTime.with(TemporalAdjusters.next(MONDAY));
        System.out.println(nextMonday);

        LocalDateTime ddd = dateTime.with(TemporalAdjusters.dayOfWeekInMonth(2,THURSDAY));

        System.out.println("требуемая дата = "+ ddd);






    }
}
