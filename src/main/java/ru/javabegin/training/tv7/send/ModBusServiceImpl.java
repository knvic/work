package ru.javabegin.training.tv7.send;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public class ModBusServiceImpl implements ModBusService {

    @Override
    public List<String> typeUnit(int number){
        String command ="01 48 00 00 00 06 00 00 00 00 00 00 00 01";

        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        //list.forEach(System.out::print);
        list.add(0,format("%02X", number));
        // list.forEach(System.out::print);

        return list;
    }
}
