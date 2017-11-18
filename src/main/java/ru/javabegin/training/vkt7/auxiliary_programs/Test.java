package ru.javabegin.training.vkt7.auxiliary_programs;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

/**
 * Created by Николай on 14.11.2017.
 */
public class Test {
    public static void main (String[] args) {
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        Date input = new Date();

        List<Date> dateList=auxiliaryService.from_the_beginning_of_month(input);

        for(Date data:dateList){
            System.out.println(data);
        }

        LocalDateTime ldt = auxiliaryService.date_to_localDateTime(input);
        ldt=auxiliaryService.addTime(ldt,"23");
        Date date=auxiliaryService.localDateTime_to_date(ldt);
        String dataStr= auxiliaryService.date_to_vktString(date);
        System.out.println("localDateTime Str = "+dataStr);

        dataStr= auxiliaryService.date_to_vktString(ldt);
        System.out.println("localDateTime Str = "+dataStr);



    }


}
