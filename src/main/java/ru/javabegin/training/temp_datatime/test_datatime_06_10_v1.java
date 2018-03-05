package ru.javabegin.training.temp_datatime;


import ru.javabegin.training.tv7.auxillary.AuxDateTimeService;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static java.sql.Timestamp.valueOf;

/**
 * Created by Николай on 28.09.2017.
 */
public class test_datatime_06_10_v1 {



    public static void main(String[] args) throws ParseException {


        System.out.println("localDateTime = "+LocalDateTime.now());

        LocalDateTime ttt = LocalDateTime.of(17, 10, 6, 17,0 ,0);
        System.out.println("localDateTime Str = "+ttt);
        String ddd=ttt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        System.out.println("localDateTime Str = "+ddd);


        AuxDateTimeService dateTimeService=new AuxDateTimeServiceImpl();
        Timestamp ts_day_of_minus_1 = Timestamp.valueOf(dateTimeService.addTime((LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())).minusDays(1),"00"));
        System.out.println(" Timestamp day_of = " + ts_day_of_minus_1);



    }
}
