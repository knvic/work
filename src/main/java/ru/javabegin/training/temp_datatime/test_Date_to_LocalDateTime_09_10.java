package ru.javabegin.training.temp_datatime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by Николай on 09.10.2017.
 */
public class test_Date_to_LocalDateTime_09_10 {

    public static void main(String args[]) {

        Date now = new Date();
        Instant current = now.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(current, ZoneId.systemDefault());
        System.out.println("value of Date: " + now);
        System.out.println("value of LocalDateTime: " + ldt);
        // converting java 8 LocalDateTime to java.util.Date

        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        System.out.println("value of LocalDateTime: " + ldt);
        System.out.println("value of Date: " + output);
    }

}
