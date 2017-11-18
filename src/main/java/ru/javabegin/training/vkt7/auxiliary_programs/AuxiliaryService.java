package ru.javabegin.training.vkt7.auxiliary_programs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Николай on 14.11.2017.
 */
public interface AuxiliaryService {

    List<Date> from_the_beginning_of_month(Date date);

    LocalDate date_to_localDate(Date data);

    LocalDateTime date_to_localDateTime(Date data);

    Date localDateTime_to_date(LocalDateTime ldt);


    LocalDateTime addTime (LocalDateTime data, String hour);


}
