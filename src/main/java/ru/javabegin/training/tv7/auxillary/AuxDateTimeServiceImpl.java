package ru.javabegin.training.tv7.auxillary;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuxDateTimeServiceImpl implements AuxDateTimeService {

    @Override
    public List<Date> from_the_beginning_of_month(Date date){
        LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
        //System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<Date>  dateList=new ArrayList<>();
        while(day.isBefore(ldt)){
            dateList.add(localDateTime_to_date(day));
            day=day.plusDays(1);
        }
        return dateList;
    }



    @Override
    public  List<LocalDateTime> from_the_beginning_of_month(LocalDateTime dateLocalDateTime){
       // LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
        System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<LocalDateTime>  dateList=new ArrayList<>();
        while(day.isBefore(ldt)){
            dateList.add(day);
            day=day.plusDays(1);
        }
        return dateList;
    }

    @Override
    public LocalDateTime date_to_localDateTime(Date data){
        LocalDateTime dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //    System.out.println("Перевели в  LocalDate " + dateLocalDate);

        return dateLocalDate;
    }


    @Override
    public Date addTime(Date data, String hour){
        LocalDateTime dataldt=date_to_localDateTime(data);
        LocalDate dateLocalDate =dataldt.toLocalDate();
        int h=Integer.parseInt(hour);
        LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(h, 0, 0));
        Date dateDate=localDateTime_to_date(ldt);
        return dateDate;

    }

    @Override
    public LocalDateTime addTime (LocalDateTime data, String hour){
        LocalDate  dateLocalDate =data.toLocalDate();
        LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(23, 0, 0));
        return ldt;

    }

    @Override
    public Date localDateTime_to_date(LocalDateTime ldt){
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        //   System.out.println("value of LocalDateTime: " + ldt);
        //  System.out.println("value of Date: " + output);

        return output;
    }
}
