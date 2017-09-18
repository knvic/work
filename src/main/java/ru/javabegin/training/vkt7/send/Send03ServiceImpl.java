package ru.javabegin.training.vkt7.send;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Николай on 20.08.2017.
 */
public class Send03ServiceImpl implements Send03Service {
    @Override
    public List<String> s_3FFE(String number){
        String command= "FF FF 03 3F FE 00 00";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        //list.forEach(System.out::print);
        list.add(2,number);
       // list.forEach(System.out::print);

        return list;
    }

    @Override
    public List<String> s_3FF9(String number){
        String command= "FF FF 03 3F F9 00 00";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        //list.forEach(System.out::print);
        list.add(2,number);
        list.forEach(System.out::print);

        return list;
    }

    @Override
    public List<String> s_3FF6(String number){
        String command= "FF FF 03 3F F6 00 00";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        list.forEach(System.out::print);
        list.add(2,number);
        list.forEach(System.out::print);

        return list;
    }

    @Override
    public List<String> s_3FFC(String number){
        String command= "FF FF 03 3F FC 00 00";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        list.forEach(System.out::print);
        list.add(2,number);
        list.forEach(System.out::print);

        return list;
    }

    @Override
    public List<String> s_3ECD(String number){
        String command= "FF FF 03 3E CD 00 01";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        list.forEach(System.out::print);
        list.add(2,number);
        list.forEach(System.out::print);

        return list;
    }

    @Override
    public List<String> s_3F5B(String number){
        //String command= "FF FF 03 3F FC 00 00";
        String command= "FF FF 03 3F 5B 00 01";//0x00 0x03 0x3e 0xcd 0x00 0x01

        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        list.forEach(System.out::print);
        list.add(2,number);
        list.forEach(System.out::print);

        return list;
    }

    @Override
    public List<String> s_3FE9(String number){
        //String command= "FF FF 03 3F FC 00 00";
        String command= "FF FF 03 3F E9 00 01";//0x00 0x03 0x3e 0xcd 0x00 0x01

        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 03 3F FE 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        list.forEach(System.out::print);
        list.add(2,number);
        list.forEach(System.out::print);

        return list;
    }



}
