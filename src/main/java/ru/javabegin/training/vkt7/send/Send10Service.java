package ru.javabegin.training.vkt7.send;

/**
 * Created by Николай on 20.08.2017.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Запросы с байтом записи 0х10
 */
public interface Send10Service {

    /**
     * 4.7 Запрос «Начало сеанса связи»
     * @param number номер узла
     */
    List<String> s_3FFF_n(String number);

    /**
     *
     * @param number номер узла
     * @return Массив из двух объектов {LinkedList<Properts> data2, List<String> command}
     * 1 объект
     */
    ArrayList<Object> s_3FFF(String number);


    /**
     * Запрос на запись переменных для чтения
     * @param number номер узла
     * @param   command масив hex команды, сформированной для записи
     * @return Массив из двух объектов {LinkedList<Properts> data2, List<String> command}
     * 1 объект
     */
    List<String> s_3FFF_end(int number, List<String> command);


    /**
     * 4.3 Запрос на запись типа значений
      * @param number номер узла
     * @param type тип измерений (напр 4- текущие измерения)
     * @return массив строка команды
     */
    List<String> s_3FFD(int number, int type);

}
