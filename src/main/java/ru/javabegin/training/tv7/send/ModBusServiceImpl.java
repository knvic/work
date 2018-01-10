package ru.javabegin.training.tv7.send;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public class ModBusServiceImpl implements ModBusService {

    @Override
    public List<String> typeUnit(int number){
        //String command ="48 00 00 00 06 00 00 00 00 00 00 00 01";
        String command ="48 00 00 00 13 00 00 00 00 00 00 00 00";


        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        //list.forEach(System.out::print);
        list.add(0,format("%02X", number));
        // list.forEach(System.out::print);

       String s1="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 41 35 0D 0A";
       String s2="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 0D 0A";
       String s3="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 41 35 0D 0A";
       String s4="3A 30 30 34 38 30 30 36 39 30 30 39 32 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 32 42 42 0D 0A";
        list = new LinkedList<String>(Arrays.asList(s4.split(" ")));
        return list;
    }
}
