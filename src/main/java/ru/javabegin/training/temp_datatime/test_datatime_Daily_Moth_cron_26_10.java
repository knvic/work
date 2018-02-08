package ru.javabegin.training.temp_datatime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;

/**
 * Created by Николай on 10.10.2017.
 */
public class test_datatime_Daily_Moth_cron_26_10 {
/*


    @Autowired
    @Qualifier("jpaCustomerService")
private CustomerService customerService;
*/

    public static void main(String[] args) throws ParseException {


        LocalDate a1=LocalDate.of(2017,11,30);
        LocalDate a2=LocalDate.of(2017,10,25);
        LocalTime t=LocalTime.of(23,00);

        Timestamp tstamp1 = Timestamp.valueOf(LocalDateTime.of(a1,t));
        Timestamp tstamp2 = Timestamp.valueOf(LocalDateTime.of(a2,t));

        LocalDateTime ldt1 =  tstamp1.toLocalDateTime();
        LocalDateTime ldt2 =  tstamp2.toLocalDateTime();

        System.out.println("isAfter #1: " +  a1.isAfter(a2));
        System.out.println("isAfter #1: " +  ldt2.isAfter(ldt1));



        System.out.println();

        LocalDateTime ldt55=(ldt1.minusMonths(1)).with(TemporalAdjusters.lastDayOfMonth());

        System.out.println("ldt =  " + ldt1);
        System.out.println("ldt55 =  " + ldt55);





    }
}
