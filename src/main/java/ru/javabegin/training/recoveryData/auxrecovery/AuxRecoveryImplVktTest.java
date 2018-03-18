package ru.javabegin.training.recoveryData.auxrecovery;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AuxRecoveryImplVktTest {

    @Test
    public void stringDate_to_TimeStamp_forDay() {
        AuxRecoveryImplVkt auxRecoveryImplVkt=new AuxRecoveryImplVkt();

      Timestamp a=  auxRecoveryImplVkt.stringDate_to_TimeStamp_forDay("01/03/18");

        System.out.println(a);
    }

    @Test
    public void checkMonthDay() {

        AuxRecoveryImplVkt auxRecoveryImplVkt=new AuxRecoveryImplVkt();

        LocalDateTime ldt2 = LocalDateTime.of(2018 , 2, 27, 23, 0);
        System.out.println("LocalDateTime ldt2 = " + ldt2);
        Timestamp timestamp = Timestamp.valueOf(ldt2);
        System.out.println(" timestamp = " + timestamp);


        auxRecoveryImplVkt.checkMonthDay(timestamp);
    }
}