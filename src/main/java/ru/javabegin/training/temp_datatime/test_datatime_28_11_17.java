package ru.javabegin.training.temp_datatime;

import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class test_datatime_28_11_17 {


    public static void main(String[] args) throws ParseException {

        AuxiliaryServiceImpl auxiliaryService =new AuxiliaryServiceImpl();
       Timestamp barhive= auxiliaryService.localDateTime_TimeStamp(LocalDateTime.of(2017,11,17,23,0));

        List<String> strDataList=auxiliaryService.from_the_beginning_of_month_str(new Date());


        LocalDateTime begin_arhive = auxiliaryService.timestamp_to_localDateTime(barhive);
        System.out.println("Переводим в LocalData Time --- " + begin_arhive);

        for(String strData:strDataList){



       // System.out.println("Сегодня — это до ? : " + LocalDate.now().isBefore(LocalDate.of(2017,11,27)));




        LocalDateTime target_data = auxiliaryService.stringDate_to_LocalDateTime(strData);
        int date_before = 0;
        if (begin_arhive.isBefore(target_data)||begin_arhive.isEqual(target_data)) {

            System.out.println("Запрашиваемая дата " + strData + " после даты начала работы устройства " + begin_arhive + ". Запрос  СУТОЧНЫЕ возможен");
            date_before = 1;
        } else {
            System.out.println("Запрос СУТОЧНЫЕ НЕ ВОЗМОЖЕН!.  Дата " + strData + " до начала работы устройства " + begin_arhive);
           // auxiliaryService.saveMessage(log_cron, "Customer " + customer + " Запрос СУТОЧНЫЕ НЕ ВОЗМОЖЕН!.  Дата " + strData + " до начала работы устройства " + begin_arhive);

        }

        }
    }

}
