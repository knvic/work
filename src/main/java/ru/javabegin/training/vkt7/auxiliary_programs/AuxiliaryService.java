package ru.javabegin.training.vkt7.auxiliary_programs;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Николай on 14.11.2017.
 */
public interface AuxiliaryService {

    /**
     * Формирует массив дат от начала месяца до текущей-1
     * со временем 23.00
     * @param date дата до которой будет формаироваться массив
     * @return массив дат с 1 числа до даты date
     */
    List<Date> from_the_beginning_of_month(Date date);


    LocalDate date_to_localDate(Date data);

    LocalDateTime date_to_localDateTime(Date data);

    Date localDateTime_to_date(LocalDateTime ldt);

    /**
     * добавляет в дату время в часах
     * @param data дата в которую надо добавить
     * @param hour часы в формате hh
     * @return дата со временем
     */
    LocalDateTime addTime (LocalDateTime data, String hour);

    Timestamp date_TimeStamp (Date data);

    Timestamp localDateTime_TimeStamp (LocalDateTime data);

    LocalDateTime timestamp_to_localDateTime(Timestamp ts);

    String date_to_vktString(Date date);
    String date_to_vktString(LocalDateTime date);
   /* String date_to_vktString(Timestamp date);
    List<String> date_to_vktString(List<Date> dateList);*/


}
