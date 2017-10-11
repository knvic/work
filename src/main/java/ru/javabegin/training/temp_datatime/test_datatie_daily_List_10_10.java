package ru.javabegin.training.temp_datatime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Николай on 10.10.2017.
 */
public class test_datatie_daily_List_10_10 {

    public static void main(String args[]) throws ParseException {

       /* String data_day = "03:10:17:16";

        Date d=new Date();
        //System.out.println("data(Data) ="+d);*/

int day=5;
        LocalDateTime ldt_d1 = LocalDateTime.of(2017, 10, day, 0, 0, 0);
        System.out.println("Создали дату  LocalDateTime = "+ldt_d1);

        ZonedDateTime zdt = ldt_d1.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        System.out.println("Перевели в  Date: " + output);




        Instant date_time = output.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(date_time, ZoneId.systemDefault());

        System.out.println("Перевели обратно в  LocalDateTime =" + ldt);



       // String date_str=ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        String date_str=ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu"));
        System.out.println("ddd = "+date_str);
        //List<String> date_dd_MM_UU_HH = new ArrayList<String>(Arrays.asList(date_str.split(":")));
        List<String> date_dd_MM_UU_HH_temp = new ArrayList<String>();
        for (int i=0;i<24; i++) {
           date_dd_MM_UU_HH_temp.add(date_str + ":" + String.valueOf(i));
        }
        date_dd_MM_UU_HH_temp.forEach(p->System.out.println("date= "+p));


for (String str_date: date_dd_MM_UU_HH_temp){
    System.out.println("str_date= "+str_date);

    List<String> date_dd_MM_UU_HH = new ArrayList<String>(Arrays.asList(str_date.split(":")));




}


    }
}
