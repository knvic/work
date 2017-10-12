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

        int day = 5;
        LocalDateTime ldt_d1 = LocalDateTime.of(2017, 10, day, 0, 0, 0);
        System.out.println("Создали дату  LocalDateTime = " + ldt_d1);

        ZonedDateTime zdt = ldt_d1.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        System.out.println("Перевели в  Date: " + output);


        Instant date_time = output.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(date_time, ZoneId.systemDefault());

        System.out.println("Перевели обратно в  LocalDateTime =" + ldt);


        // String date_str=ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        String date_str = ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu"));
        System.out.println("ddd = " + date_str);
        //List<String> date_dd_MM_UU_HH = new ArrayList<String>(Arrays.asList(date_str.split(":")));
        List<String> date_dd_MM_UU_HH_temp = new ArrayList<String>();
        for (int i = 0; i < 24; i++) {
            date_dd_MM_UU_HH_temp.add(date_str + ":" + String.valueOf(i));
        }
        date_dd_MM_UU_HH_temp.forEach(p -> System.out.println("date= " + p));
        System.out.println("строка для 23 часов" + date_dd_MM_UU_HH_temp.get(23));

/*for (String str_date: date_dd_MM_UU_HH_temp){
    System.out.println("str_date= "+str_date);

    //List<String> date_dd_MM_UU_HH = new ArrayList<String>(Arrays.asList(str_date.split(":")));

   }*/

        String test = date_dd_MM_UU_HH_temp.get(16);
        System.out.println("test= " + test);
        List<String> d = new ArrayList<String>(Arrays.asList(test.split(":")));

        //test= 05:10:17:16
        // LocalDateTime ldt = LocalDateTime.of(2017, 10, day, 0, 0, 0);
        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), Integer.parseInt(d.get(3)), 0, 0);
        System.out.println("LocalDateTime ldt2 = " + ldt2);
        Timestamp timestamp = Timestamp.valueOf(ldt2);
        System.out.println(" timestamp = " + timestamp);


        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("5", "ssdfgsdfg");
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }

        /*Set<String> rr= new HashSet<String>() {};
        }
        Map<String, List<String> > hashMap1 = new HashMap<String, List<String>>();
        hashMap.put("5",  d);
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
        */


        Map<String, List<String>> personMap = new HashMap<>();

        personMap.put("Иван", Arrays.asList("q","w","e","r"));
        personMap.put("Маша", Arrays.asList("4","5","6","7"));
        personMap.put("Ирина", Arrays.asList("Z","X","C","V"));

        System.out.println("personMap: " + personMap);
        System.out.println("personMap.keySet(): " + personMap.keySet());

        for(String person : personMap.keySet()){
            System.out.println(person + " имеет");
            for (String pet : personMap.get(person)){
                System.out.println("  " + pet);
            }
        }



    }
}
