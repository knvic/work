package ru.javabegin.training.tv7.recieve;

import ru.javabegin.training.tv7.initDataClass.Parametr;

import java.util.List;
import java.util.Map;

public interface ModBusRService {



    Map<String,Tupel_date> infOfDate(List<String> list);
    /**
     *
     * @param list пришедшая строка СУТОЧНОГО архива
     * @param sys Единицы измерения счетчика 0-СИ 1-МКС
     */
    List<Parametr> total(List<String> list, List<Parametr> parametrList, int sys);
    List<Parametr> day(List<String> list, List<Parametr> parametrList, int sys);
    void day_old(List<String> list, List<Parametr> parametrList, int sys);

}
