package ru.javabegin.training.recoveryData.auxrecovery;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public interface AuxRecovery {

    String forUpdateMoth();
    String forDay(Timestamp date);
    Timestamp forDay(String date);
    Timestamp stringDate_to_TimeStamp_forDay(String stringDate);
    Timestamp stringDate_to_TimeStamp_forMonth(String stringDate);
    boolean checkMonthDay(Timestamp date);




}
