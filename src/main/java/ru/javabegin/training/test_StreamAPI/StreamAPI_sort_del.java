package ru.javabegin.training.test_StreamAPI;

import ru.javabegin.training.tv7.entity.Operationtv7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI_sort_del {

    public void sort(List<Operationtv7> list){

        System.out.println("До сортировки");
        list.forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));
                        /*.stream()
                        .sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()));
*/
        //.sorted((o1,o2) -> o1.getId().compareTo(o2.getId())).collect(Collectors.toList())
        //.forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" : "+p.getIdCustomer()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));
        System.out.println("После сортировки");
        list
                .stream()
                .sorted((f1, f2) ->Integer.compare(f1.getId(), f2.getId()))
                .forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));

        System.out.println("Добавляем в новый лист");

        List<Operationtv7> newList=list
                .stream()
                .sorted((f1, f2) ->Integer.compare(f1.getId(), f2.getId()))
                .collect(Collectors.toList());
        //.forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()))
        System.out.println("Выводим новый лист");

        newList.forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));



    }

    public void del(List<Operationtv7> list){

        System.out.println("До сортировки");
        list.forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));
                        /*.stream()
                        .sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()));
*/
        //.sorted((o1,o2) -> o1.getId().compareTo(o2.getId())).collect(Collectors.toList())
        //.forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" : "+p.getIdCustomer()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));
        System.out.println("После сортировки");
        list
                .stream()
                .sorted((f1, f2) ->Integer.compare(f1.getId(), f2.getId()))
                .forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));

        System.out.println("Добавляем в новый лист");

        List<Operationtv7> newList=list
                .stream()
                .sorted((f1, f2) ->Integer.compare(f1.getId(), f2.getId()))
                .collect(Collectors.toList());
        //.forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()))
        System.out.println("Выводим новый лист");

        newList.forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));


        for (int i=0; i<newList.size(); i++){
            if (i!=0&&i!=newList.size()-1){
                newList.remove(i);
            }
        }
        List<Operationtv7> out=new ArrayList<>();
                out.add(0,newList.get(0));
        out.add(1,newList.get(newList.size()-1));
        System.out.println("После удаления");

        out
                .stream()
                .sorted((f1, f2) ->Integer.compare(f1.getId(), f2.getId()))
                .forEach(p->System.out.println(p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));


    }

    public void max_min(List<Operationtv7> list){

        System.out.println("До сортировки");
        list.forEach(p->System.out.println(p.getChronoligical()+" : "+ p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));
                        /*.stream()
                        .sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()));
*/
        //.sorted((o1,o2) -> o1.getId().compareTo(o2.getId())).collect(Collectors.toList())
        //.forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" : "+p.getIdCustomer()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));

       ////Сортировка по Integer
        System.out.println("После сортировки по ID");
        list
                .stream()
                .sorted((f1, f2) ->Integer.compare(f1.getId(), f2.getId()))
                .forEach(p->System.out.println(p.getChronoligical()+" : "+ p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));


        ////Сортировка по TimeStamp
        System.out.println("После сортировки по ВРЕМНИ");
        list
                .stream()
                .sorted((o1,o2) -> o1.getChronoligical().compareTo(o2.getChronoligical()))
                .forEach(p->System.out.println(p.getChronoligical()+" : "+ p.getId()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));

/// НАХОЖДЕНИЕ МАКСИМАЛЬНОГО И МИНИМАЛЬНОГО ЗНАЧЕНИЯ

        Operationtv7 max=list.stream().max((p1, p2) -> p1.getChronoligical().compareTo(p2.getChronoligical())).get();

        System.out.println("максимальное"+max.getChronoligical()+" : "+ max.getId()+" : "+max.getV1Tv1()+" : "+max.getM1Tv1());

        Operationtv7 min=list.stream().max((p2, p1) -> p1.getChronoligical().compareTo(p2.getChronoligical())).get();

        System.out.println("минимальное"+min.getChronoligical()+" : "+ min.getId()+" : "+min.getV1Tv1()+" : "+min.getM1Tv1());


    }



}
