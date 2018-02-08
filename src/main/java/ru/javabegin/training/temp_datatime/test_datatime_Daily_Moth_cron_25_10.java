package ru.javabegin.training.temp_datatime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;

/**
 * Created by Николай on 10.10.2017.
 */
public class test_datatime_Daily_Moth_cron_25_10 {
/*


    @Autowired
    @Qualifier("jpaCustomerService")
private CustomerService customerService;
*/

    public static void main(String[] args) throws ParseException {


        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime nextMonday = dateTime.with(TemporalAdjusters.next(MONDAY));
        System.out.println(nextMonday);

        LocalDateTime ddd = dateTime.with(TemporalAdjusters.dayOfWeekInMonth(2,THURSDAY));

        System.out.println("требуемая дата = "+ ddd);
        System.out.println("///////////////////////////////////////////////////////////////////////");
        System.out.println("///////////////////////////////////////////////////////////////////////");
/*

       // LocalDateTime ttt = LocalDateTime.of(17, 10, 6, 17,0 ,0);
        //LocalDate localDate=LocalDate.now();
        LocalDate localDate=LocalDate.of(2017,6,30);
        System.out.println("текущая дата = "+ localDate);
        LocalDate before_date=localDate.minusMonths(1);
        System.out.println("текущая дата в предыдущем месяце= "+ before_date);
        LocalDate before_date_last_day= before_date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("крайний день предыдущего месца = "+ before_date_last_day);
        LocalTime time = LocalTime.of(23,00);
        System.out.println("создаем тестовое время= "+ time);
        LocalDateTime last_day_before_moth = LocalDateTime.of(before_date_last_day,time);
        System.out.println("последний день предыдущего месяца 23-00= "+ last_day_before_moth);
        Timestamp timestamp = Timestamp.valueOf(last_day_before_moth);
        System.out.println("перевели в TimeStamp= "+  timestamp);

*/

        /**
         * поиск даты последнего дня предыдущего месяца 23-00
         */
        Timestamp tstamp = Timestamp.valueOf(LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00)));
        System.out.println("Последний день предыдущего месяца 23-00 переведенный в ");
        System.out.println("TimeStamp для запроса базы данных= "+  tstamp);

        System.out.println("дата для записи type=3 "+ LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00)));
        LocalDateTime data_temp =LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00));

        System.out.println(data_temp.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH:")));
       // System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));

        /**
         *
         */


        System.out.println("Сегодня — это до ? : " + LocalDate.now().isBefore(LocalDate.of(2017,10,25)));
        System.out.println("Сегодня — это до 25.10.2017? : " + LocalDate.now().equals(LocalDate.of(2017,10,25)));

        //System.out.println("что то про пнд : " +LocalDate.of(2017,10,24).isBefore(TemporalAdjusters.previous(MONDAY)));
        LocalDate previousMonday = LocalDate.now().with( TemporalAdjusters.previous( DayOfWeek.MONDAY ));
        System.out.println("предыдущий пнд"+previousMonday);


        if (LocalDate.now().minusDays(1).equals(LocalDate.now().with(TemporalAdjusters.previous( DayOfWeek.TUESDAY)))){
            System.out.println("Вчера был вторник. Запускаем программу");
        }
        else {
            System.out.println("программа не запускается");
        }

        /// Начинаем запрос к базе
/*


        List<Operation> operationList=customerService.findOperation_total_moth(38L,tstamp,"total_moth", "OK");



        System.out.println("размер ="+operationList);

        List<Operation> operationList1= customerService.findOperationByModemTimeCustomer("+79064426645", tstamp);
        System.out.println("размер1 ="+operationList1);

*/




        //

        //SUNDAY


        //dateTime.with(TemporalAdjusters.next(MONDAY));


       // today.with(TemporalAdjusters.firstDayOfMonth()));
       // LocalDateTime ldt = timestamp.toLocalDateTime();




  /* LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);

        System.out.println(dateTimeFromDateAndTime);

        LocalDate dateFromDateTime = LocalDateTime.now().toLocalDate();
        LocalTime timeFromDateTime = LocalDateTime.now().toLocalTime();
*/





    }
}
