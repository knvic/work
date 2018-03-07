package ru.javabegin.training.tv7.auxillary;

import ru.javabegin.training.tv7.recieve.Tupel_date;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AuxDateTimeService {

    /**
     * Формирует массив дат от начала месяца до текущей-1
     * со временем 23.00
     * @param date дата до которой будет формаироваться массив
     * @return массив дат с 1 числа до даты date
     */
    List<Date> from_the_beginning_of_month(Date date);
    List<LocalDateTime> from_the_beginning_of_month(LocalDateTime dateLocalDateTime);


    LocalDateTime date_to_localDateTime(Date data);

    Date addTime(Date data, String hour);
    LocalDateTime addTime (LocalDateTime data, String hour);

    Date localDateTime_to_date(LocalDateTime ldt);

    boolean checkBeginArchive(Map<String,Tupel_date> infOfDate, LocalDateTime targetDate, String type);

    String timeStamp_to_stringData(Timestamp tsData);
}
