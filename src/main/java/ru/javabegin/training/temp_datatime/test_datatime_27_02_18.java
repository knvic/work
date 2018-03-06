package ru.javabegin.training.temp_datatime;

import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class test_datatime_27_02_18 {


    public static void main(String[] args) throws ParseException {





        Date day_of = new Date();
        System.out.println(" Date day_of = " + day_of);


        Timestamp ts_day_of = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()));
        System.out.println(" Timestamp day_of = " + ts_day_of);

        Timestamp ts_day_of_minus_1 = Timestamp.valueOf(LocalDateTime.ofInstant(day_of.toInstant(), ZoneId.systemDefault()).minusDays(1));
        System.out.println(" Timestamp day_of = " + ts_day_of_minus_1);
    }

}
