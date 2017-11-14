package ru.javabegin.training.vkt7.auxiliary_programs;

import ru.javabegin.training.vkt7.entities.Customer;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Николай on 14.11.2017.
 */
public class AuxiliaryServiceImpl implements AuxiliaryService {



    @Override
    public List<Date> from_the_beginning_of_month(Date date){



        LocalDate dateLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("Перевели в  LocalDate " + dateLocalDate);

        LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(23, 0, 0));

        System.out.println("Добавили время LocalDateTime(23-00) -  " + ldt);

        System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));

        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());

        List<Date>  dateList=new ArrayList<>();



        while(day.isBefore(ldt)){

            System.out.println("Рассматриваемая дата "+ day+ " находится перед "+ ldt );


            day=day.plusDays(1);
        }



        return dateList;
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



}
