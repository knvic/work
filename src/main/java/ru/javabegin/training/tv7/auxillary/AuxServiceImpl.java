package ru.javabegin.training.tv7.auxillary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.String.format;

public class AuxServiceImpl implements AuxService {

    @Override
    public  StringBuilder localDate_to_tv7(LocalDateTime ldt){
        StringBuilder datetv7=new StringBuilder();
        LocalDate ld=ldt.toLocalDate();
        ldt =LocalDateTime.of(ld, LocalTime.of(23, 0, 0));
        String dataStr =ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH:mm:ss"));
        System.out.println("строка = "+dataStr);

        //StringBuilder datetv7= new StringBuilder(ldt.format(DateTimeFormatter.ofPattern(format("%02X",ldt.getMonth()).toString()+" " +format("%02X",ldt.getDayOfMonth()).toString())));
        //StringBuilder datetv= new StringBuilder(ldt.format(DateTimeFormatter.ofPattern("MM dd HH uu ss mm")));

        datetv7.append(format("%02X",ldt.getMonthValue()));
        datetv7.append(format("%02X",ldt.getDayOfMonth()));
        datetv7.append(format("%02X",ldt.getHour()));
        datetv7.append(format("%02X",ldt.getYear()-2000));
        datetv7.append(format("%02X",ldt.getSecond()));
        datetv7.append(format("%02X",ldt.getMinute()));

        System.out.println("строка = "+datetv7.toString() );



        return  datetv7;
    }

    @Override
    public StringBuilder Date_to_tv7(Date date){
        LocalDateTime ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        StringBuilder datetv7=new StringBuilder();
        LocalDate ld=ldt.toLocalDate();
        ldt =LocalDateTime.of(ld, LocalTime.of(23, 0, 0));
        String dataStr =ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH:mm:ss"));
        System.out.println("строка = "+dataStr);

        //StringBuilder datetv7= new StringBuilder(ldt.format(DateTimeFormatter.ofPattern(format("%02X",ldt.getMonth()).toString()+" " +format("%02X",ldt.getDayOfMonth()).toString())));
        //StringBuilder datetv= new StringBuilder(ldt.format(DateTimeFormatter.ofPattern("MM dd HH uu ss mm")));

        datetv7.append(format("%02X",ldt.getMonthValue()));
        datetv7.append(format("%02X",ldt.getDayOfMonth()));
        datetv7.append(format("%02X",ldt.getHour()));
        datetv7.append(format("%02X",ldt.getYear()-2000));
        datetv7.append(format("%02X",ldt.getSecond()));
        datetv7.append(format("%02X",ldt.getMinute()));

        System.out.println("строка = "+datetv7.toString() );

        return  datetv7;
    }


    @Override
    public String l2b (String str) {
        StringBuilder str_out = new StringBuilder();
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ", "").split("(?<=\\G.{4})")));
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++){
            str_out.append(list.get(i));
        }
        return str_out.toString();
    }
}
