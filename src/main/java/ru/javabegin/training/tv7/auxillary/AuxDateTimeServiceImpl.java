package ru.javabegin.training.tv7.auxillary;

import ru.javabegin.training.tv7.recieve.Tupel_date;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AuxDateTimeServiceImpl implements AuxDateTimeService {

    @Override
    public List<Date> from_the_beginning_of_month(Date date){
        LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
        //System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<Date>  dateList=new ArrayList<>();
        while(day.isBefore(ldt.plusDays(1))){
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
        while(day.isBefore(ldt.plusDays(1))){
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

    @Override
    public boolean checkBeginArchive(Map<String,Tupel_date> infOfDate, LocalDateTime targetDate, String type){
        // "begin_hour", "begin_day", "begin_month", "begin_total", "end_hour", "end_day", "end_month", "end_total", "reboot"
        boolean result=false;
        if (type.equals("day")){
            System.out.println("begin_day"+" : "+infOfDate.get("begin_day").getLocalDateTime());
            targetDate=addTime(targetDate,"23");
            if(targetDate.isAfter(infOfDate.get("begin_day").getLocalDateTime())||targetDate.equals(infOfDate.get("begin_day").getLocalDateTime())){result=true;}

        }
        if (type.equals("month")){
            System.out.println("begin_month"+" : "+infOfDate.get("begin_month").getLocalDateTime());
            System.out.println("Последний день этого месяца : " + targetDate.with(TemporalAdjusters.lastDayOfMonth()));
            targetDate=addTime(targetDate.with(TemporalAdjusters.lastDayOfMonth()),"23");
            System.out.println("Получившаяся targetDate : "+targetDate);
            if(targetDate.isAfter(infOfDate.get("begin_day").getLocalDateTime())||targetDate.equals(infOfDate.get("begin_month").getLocalDateTime())){result=true;}

        }
        if (type.equals("total")){
            System.out.println("begin_total"+" : "+infOfDate.get("begin_total").getLocalDateTime());
            targetDate=addTime(targetDate,"23");
            if(targetDate.isAfter(infOfDate.get("begin_total").getLocalDateTime())||targetDate.equals(infOfDate.get("begin_total").getLocalDateTime())){result=true;}

        }

        return result;
    }


    @Override
    public String timeStamp_to_stringData(Timestamp tsData){

        LocalDateTime ldt = tsData.toLocalDateTime();

        String data=ldt.format(DateTimeFormatter.ofPattern("dd.MM.uu HH"));

      return data;
    }
}
