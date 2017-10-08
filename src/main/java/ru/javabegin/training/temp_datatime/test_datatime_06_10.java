package ru.javabegin.training.temp_datatime;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static java.sql.Timestamp.valueOf;

/**
 * Created by Николай on 28.09.2017.
 */
public class test_datatime_06_10 {



    public static void main(String[] args) throws ParseException {

        LocalDateTime today1 = LocalDateTime.now();
        System.out.println("Получаем текущее время : " + today1);

        Date date = new Date();
        Object param = new Timestamp(date.getTime());
        System.out.println("Получаем текущее время : " + param);


        Timestamp ts = valueOf(today1);
        System.out.println("ts : " + ts);

        ////

        String str1="11:8:17:14";
        System.out.println("Str=" + str1);

        //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        SimpleDateFormat format = new SimpleDateFormat("dd:MM:yy:HH");
        Date date_test = format.parse(str1);
        System.out.println("Date : " + date_test);
////////////////////////////////////////

        LocalDateTime dateTime = LocalDateTime.now();
        //стандартный формат даты
        System.out.println("стандартный формат даты LocalDateTime : " + dateTime);
        //приименяем свой формат даты
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));
        System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

        System.out.println("Проверяем");
        System.out.println("TimeStamp= "+ts);
       // System.out.println(ts.format(DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss")));

        String S = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(ts);

        System.out.println("S= "+S);
////// Вывод Timestamp
        S = new SimpleDateFormat("MM/dd/yyyy HH").format(ts);
        System.out.println("S= "+S);
////////////////      Новое  Java 8 /////////////

        System.out.println("Новое Java 8");
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("localDateTime = "+localDateTime);
        System.out.println("Переводим в Timestamp");
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println("timestamp =" +timestamp );
        System.out.println("Переводим Timestamp в localDateTime");
        LocalDateTime ldt = timestamp.toLocalDateTime();
        System.out.println("localDateTime = "+ldt);
        System.out.println("Формируем localDateTime из строки");
        //LocalDateTime randDate = LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22);
        LocalDateTime randDate = LocalDateTime.of(17, 8, 11, 14, 0, 0);
        System.out.println("localDateTime Str = "+randDate);
        System.out.println(randDate.format(DateTimeFormatter.ofPattern("d::MMM::20uu")));
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////");
        System.out.println("/////////////////////////////////////////////////");

        LocalDateTime today =localDateTime.plusDays(9);
        System.out.println("localDateTime today  = "+localDateTime);
        System.out.println("plus 9 day = "+today);

        System.out.println(today.format(DateTimeFormatter.ofPattern("d::MMM::20uu")));

String ddd=today.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        System.out.println("ddd = "+ddd);
        List<String> list = new ArrayList<String>(Arrays.asList(ddd.split(":")));
        System.out.println(list.get(0));

        int to_int = Integer.parseInt(list.get(0));
        System.out.println("int = "+to_int );


        System.out.println(format("%02X", to_int));

        String hexx=format("%02X", to_int);
        System.out.println("hex = "+hexx);

        System.out.println("localDateTime = "+LocalDateTime.now());

        LocalDateTime ttt = LocalDateTime.of(17, 10, 6, 17,0 ,0);
        System.out.println("localDateTime Str = "+ttt);
        ddd=ttt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        System.out.println("localDateTime Str = "+ddd);



    }
}
