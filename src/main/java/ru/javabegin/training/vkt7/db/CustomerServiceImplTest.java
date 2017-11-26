package ru.javabegin.training.vkt7.db;

import org.junit.Test;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {
    @Test
    public void customerOperationStatus() throws Exception {
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        Date date = new Date();
        date=auxiliaryService.addTime(date,"23");

        Timestamp date_ts=auxiliaryService.date_TimeStamp(date);

        System.out.println("date= "+date);
        System.out.println("date_ts= "+date_ts);

        LocalDateTime date_moth=auxiliaryService.timestamp_to_localDateTime(date_ts);
        ///Дата предыдущего месяца
        Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(date_ts);

        System.out.println(" date_moth= "+date_moth);
        System.out.println(" date_prevision_moth= "+ date_prevision_moth);

    }

}