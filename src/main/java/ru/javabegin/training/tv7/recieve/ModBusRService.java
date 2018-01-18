package ru.javabegin.training.tv7.recieve;

import ru.javabegin.training.tv7.initDataClass.Parametr;

import java.util.List;

public interface ModBusRService {
    /**
     *
     * @param list пришедшая строка СУТОЧНОГО архива
     * @param sys Единицы измерения счетчика 0-СИ 1-МКС
     */

    void total(List<String> list, List<Parametr> parametrList, int sys);
    void day(List<String> list, List<Parametr> parametrList, int sys);
    void day_old(List<String> list, List<Parametr> parametrList, int sys);

}
