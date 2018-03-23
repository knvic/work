package ru.javabegin.training.recoveryData.auxrecovery;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class AuxRecoveryImplVkt implements AuxRecovery  {

    @Override
    public String forUpdateMoth() {

        String str="";
        LocalDateTime ldt=LocalDateTime.now();

        ldt=ldt.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        //str= ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        //ldt=addTime(ldt,"24");
        str= ldt.format(DateTimeFormatter.ofPattern("dd/MM/uu 24:00"));


        return str;

    }

    @Override
    public String forDay(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();

        String  str= ldt.format(DateTimeFormatter.ofPattern("dd/MM/uu"));


        return str;

    }

    @Override
    public Timestamp forDay(String date) {




        return null;
    }


    @Override
    public Timestamp stringDate_to_TimeStamp_forDay(String stringDate){

/**
 * Дата Дата приходит в формате 30/11/1724:00
 * Поэтому используем substring(0, 2) при получении года
 */
        List<String> d=new ArrayList<String>(Arrays.asList(stringDate.split("/")));

        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), 23, 0, 0);
        Timestamp timestamp_day = Timestamp.valueOf(ldt2);
        //System.out.println(" timestamp для суточного измерения = " + timestamp_moth);




        return timestamp_day;
    }


    @Override
    public Timestamp stringDate_to_TimeStamp_forMonth(String stringDate){

/**
 * Дата Дата приходит в формате 30/11/1724:00
 * Поэтому используем substring(0, 2) при получении года
 */
        List<String> d=new ArrayList<String>(Arrays.asList(stringDate.split("/")));

        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2).substring(0, 2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), 23, 0, 0);
        Timestamp timestamp_moth = Timestamp.valueOf(ldt2);
        //System.out.println(" timestamp для суточного измерения = " + timestamp_moth);




        return timestamp_moth;
    }

    @Override
    public boolean checkMonthDay(Timestamp date) {

        LocalDateTime ldt =date.toLocalDateTime();
        System.out.println("LocalDateTime = "+ldt);
        LocalDateTime lastDayOfMonth= ldt.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("LocalDateTime = "+lastDayOfMonth);
        boolean r=ldt.isEqual(lastDayOfMonth);
        System.out.println(r);


        return r;
    }


    ////Вспомогательные

}

