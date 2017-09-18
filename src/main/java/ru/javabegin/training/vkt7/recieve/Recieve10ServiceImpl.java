package ru.javabegin.training.vkt7.recieve;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Николай on 20.08.2017.
 */
public class Recieve10ServiceImpl implements Recieve10Service {

    @Override
    public boolean r_3FFF_n(String recieve){
        //String command= "01 10 3F FF 00 00 FC 2D ";
        /**
         * Преобразовываем строку в массив <String>
         *
         */
        boolean a =false;


        List<String> list = new LinkedList<String>(Arrays.asList(recieve));
        List<String> filtered =
                list
                        .stream()
                        .filter(p -> p.contains("3F FF"))
                        .collect(Collectors.toList());
       // list.forEach(System.out::print);
       // System.out.println();
        //filtered.forEach(System.out::print);
        if (!filtered.isEmpty())a=true;
        return a;
    }


    @Override
    public boolean r_3FFF(String recieve){
        //String command= "01 10 3F FF 00 00 FC 2D ";
        /**
         * Преобразовываем строку в массив <String>
         *
         */
        boolean a =false;
        List<String> list = new LinkedList<String>(Arrays.asList(recieve));
        List<String> filtered =
                list
                        .stream()
                        .filter(p -> p.contains("3F FF"))
                        .collect(Collectors.toList());
        list.forEach(System.out::print);
        System.out.println();
        filtered.forEach(System.out::print);
        if (!filtered.isEmpty())a=true;
        return a;
    }


    @Override
    public boolean r_3FFD(String recieve){
        //String command= "01 10 3F FF 00 00 FC 2D ";
        /**
         * Преобразовываем строку в массив <String>
         *
         */
        boolean a =false;
        List<String> list = new LinkedList<String>(Arrays.asList(recieve));
        List<String> filtered =
                list
                        .stream()
                        .filter(p -> p.contains("3F FD"))
                        .collect(Collectors.toList());
        list.forEach(System.out::print);
        System.out.println();
        filtered.forEach(System.out::print);
        if (!filtered.isEmpty())a=true;
        return a;
    }

}




        /*
        System.out.print("filtered1 = ");
        filtered1.forEach(System.out::print);
        System.out.println();


        List<String> filtered =
                list
                        .stream()
                        .filter(p -> p.equals("3F")||  p.equals("FF"))
                        .collect(Collectors.toList());

        list
                .stream()
                .filter(p -> p.equals("3F"))
                .anyMatch(p -> {
                    System.out.println("anyMatch: " + p);
                    return p.contains("3F");
                });



      */
/*  Stream.of("dd2", "aa2", "bb1", "bb3", "cc4")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
*//*

        //boolean a = (p)->command.contains("3F FF"));
        System.out.println();
        filtered.forEach(System.out::print);
        System.out.println();


        return true;
*/

