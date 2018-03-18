package ru.javabegin.training.recoveryData.auxrecovery;

import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class AuxRecoveryImplVktTest {

    @Test
    public void stringDate_to_TimeStamp_forDay() {
        AuxRecoveryImplVkt auxRecoveryImplVkt=new AuxRecoveryImplVkt();

      Timestamp a=  auxRecoveryImplVkt.stringDate_to_TimeStamp_forDay("01/03/18");

        System.out.println(a);
    }
}