package ru.javabegin.training.vkt7.auxiliary_programs;

import ru.javabegin.training.vkt7.entities.Customer;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.sql.Timestamp.valueOf;

/**
 * Created by Николай on 14.11.2017.
 */
public class AuxiliaryServiceImpl implements AuxiliaryService {



    @Override
    public List<Date> from_the_beginning_of_month(Date date){
        LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
        System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<Date>  dateList=new ArrayList<>();
        while(day.isBefore(ldt)){
            dateList.add(localDateTime_to_date(day));
            day=day.plusDays(1);
        }
        return dateList;
    }


    @Override
    public List<String> from_the_beginning_of_month_str(Date date){
        LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
        System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<String>  dateListStr=new ArrayList<>();
        while(day.isBefore(ldt)){
          //  System.out.println("Рассматриваемая дата "+ day+ " находится перед "+ ldt );
            dateListStr.add(date_to_vktString(day));
            day=day.plusDays(1);
        }
        return dateListStr;
    }


    @Override
    public LocalDate date_to_localDate(Date data){
        LocalDate dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("Перевели в  LocalDate " + dateLocalDate);

        return dateLocalDate;
    }

    @Override
    public LocalDateTime date_to_localDateTime(Date data){
        LocalDateTime dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("Перевели в  LocalDate " + dateLocalDate);

        return dateLocalDate;
    }



    @Override
    public Date localDateTime_to_date(LocalDateTime ldt){
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        System.out.println("value of LocalDateTime: " + ldt);
        System.out.println("value of Date: " + output);

        return output;
    }


    @Override
    public LocalDateTime addTime (LocalDateTime data, String hour){
    LocalDate  dateLocalDate =data.toLocalDate();
    LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(23, 0, 0));
    return ldt;

    }

    @Override
    public Date addTime(Date data, String hour){
      LocalDateTime dataldt=date_to_localDateTime(data);
        LocalDate  dateLocalDate =dataldt.toLocalDate();
        LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(23, 0, 0));
        Date dateDate=localDateTime_to_date(ldt);
        return dateDate;

    }


    @Override
    public Timestamp date_TimeStamp (Date data){
        LocalDateTime dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Timestamp timestamp = valueOf(dateLocalDate);

        return timestamp;
    }

    @Override
    public Timestamp localDateTime_TimeStamp (LocalDateTime data){
        Timestamp timestamp = valueOf(data);
        return timestamp;

    }

    @Override
    public LocalDateTime timestamp_to_localDateTime(Timestamp ts){
        LocalDateTime ldt = ts.toLocalDateTime();
        return ldt;
    }

    @Override
    public String date_to_vktString(Date date){
        LocalDateTime dateLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String dataStr =dateLocalDate.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));

        return dataStr;

    }

    @Override
    public String date_to_vktString(LocalDateTime date){

        String dataStr =date.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));

        return dataStr;

    }
    public int rrr(int a,int b){
        return a+b;
    }


}
