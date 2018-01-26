package ru.javabegin.training.test_StreamAPI;

import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StreemApi_Test {

    public static void main(String args[]){
        List<Operationtv7> list=new ArrayList<>();
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();

        LocalDateTime l=LocalDateTime.now();

        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(l.minusDays(4));

        list.add(new Operationtv7(2, "45","234",auxiliaryService.localDateTime_TimeStamp(l.minusDays(14))) );
        list.add(new Operationtv7(30, "ert","вап",auxiliaryService.localDateTime_TimeStamp(l.minusDays(75))) );
        list.add(new Operationtv7(6, "ее","ее",auxiliaryService.localDateTime_TimeStamp(l.minusDays(3))) );
        list.add(new Operationtv7(88, "pp","hg",auxiliaryService.localDateTime_TimeStamp(l.minusDays(7))) );
        list.add(new Operationtv7(9, "4h","gh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(9))) );
        list.add(new Operationtv7(50, "fgntht","2ghgh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(4))) );

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


        System.out.println("Сортируем по дате");
        list
                .stream()
                .sorted(Comparator.comparing(Operationtv7::getChronoligical))
                .forEach(p->System.out.println(p.getId()+" : "+p.getChronoligical()+" : "+p.getV1Tv1()+" : "+p.getM1Tv1()));


/*
        List<Operationtv7> l=
                list
                        .stream()
                        .sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()));

        //.sorted((o1,o2) -> o1.getId().compareTo(o2.getId())).collect(Collectors.toList())
        //.forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" : "+p.getIdCustomer()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));
        l.forEach(p->System.out.println(p.getId()+" : "+p.getCustomerName()+" : "+p.getIdCustomer()+" Дата  "+ p.getChronoligical()+" Qtv1 = "+ p.getQtvTv1()));*/

    }

}
