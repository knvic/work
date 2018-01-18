package ru.javabegin.training.tv7.auxillary;

import java.math.BigInteger;
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

    public List<String> parse_3FF9(String s, String f){
        /**
         *
         * @param s  целое 16-ти разрядное число из запроса 0х3FF9;
         * @return массив [{схема подключения},{Tp3},{t5}]
         */
        String digits = "0123456789ABCDEF";
        LinkedList<String> list= new LinkedList<>();
        int binnum[] = new int[100];
        int i, j;
        s = s.toUpperCase();
        int decnum = 0;
        for ( i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            decnum = 16*decnum + d;
        }
        // System.out.println("decnum= "+decnum);
        String temp = Integer.toBinaryString((decnum | 0x80000000));
        temp = temp.substring(Integer.SIZE - Short.SIZE);
          System.out.println("temp2 = "+temp);

        String preBin = new BigInteger(s, 16).toString(2);
        Integer length = preBin.length();
        if (length < 8) {
            for (i = 0; i < 8 - length; i++) {
                preBin = "0" + preBin;
            }
        }
        System.out.println(" preBin = "+ preBin);


       /* if (f.equals("1")) {
            //String s1 = temp.substring(8) + temp.substring(0, 8);
            int s1=Integer.parseInt(temp.substring(8,12),2);
            //  System.out.println("s1 = "+s1);
            int s2=Integer.parseInt(temp.substring(12,16),2);

            //  System.out.println("s1 = "+s2);
            String version =Integer.toString(s1)+"."+Integer.toString(s2);
            list.add(version);
            *//*list.add(Integer.parseInt(temp.substring(3, 7), 2));
            list.add(Integer.parseInt(s1.substring(7, 9), 2));
            list.add(Integer.parseInt(s1.substring(9, 11), 2));
            System.out.println(list.get(0) + ":: " + list.get(1) + ":: " + list.get(2));*//*
        }

        if (f.equals("2")||f.equals("3")) {
            String s1 = temp.substring(8) + temp.substring(0, 8);
            list.add(Integer.toString(Integer.parseInt(s1.substring(3, 7), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(7, 9), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(9, 11), 2)));
            // System.out.println(list.get(0) + ":: " + list.get(1) + ":: " + list.get(2));
        }

        if (f.equals("4")) {
            String s1 = temp.substring(8) + temp.substring(0, 8);
            list.add(Integer.toString(Integer.parseInt(s1.substring(3, 7), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(7, 9), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(9, 11), 2)));
            //  System.out.println(list.get(0) + ":: " + list.get(1) + ":: " + list.get(2));
        }*/

        return list;
    }


    @Override
    public String hexToBinary(String hex){
        String preBin = new BigInteger(hex, 16).toString(2);
        Integer length = preBin.length();
        if (length < 8) {
            for (int i = 0; i < 8 - length; i++) {
                preBin = "0" + preBin;
            }
        }
        System.out.println(" preBin = "+ preBin);
        return preBin;

    }



}
