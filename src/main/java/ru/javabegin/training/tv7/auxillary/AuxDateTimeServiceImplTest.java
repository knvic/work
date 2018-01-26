package ru.javabegin.training.tv7.auxillary;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class AuxDateTimeServiceImplTest {
    @Test
    public void from_the_beginning_of_month() throws Exception {
        AuxDateTimeServiceImpl dateTimeService=new AuxDateTimeServiceImpl();

        LocalDateTime ldt = LocalDateTime.now();

        List<LocalDateTime> list=dateTimeService.from_the_beginning_of_month(ldt);
        list.forEach(p-> System.out.println(p));

    }

}