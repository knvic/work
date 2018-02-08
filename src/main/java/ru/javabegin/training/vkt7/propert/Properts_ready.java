package ru.javabegin.training.vkt7.propert;

import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.util.List;

/**
 * Created by Николай on 05.09.2017.
 */
public interface Properts_ready {
    /**
     * Формируем из общего массива свойств и массива единиц измерения и количества знаков
     * конечный масиив свойств с указанием единиц измерения и количества знаков
     * @param prop_common - общий список свойств
     * @param prop_specificationt массив с единицами измерения и количеством знаков
     * @return  массив свойств с единицами измерения и количеством знаков (prop_completed)
     */
    List<Properts> prop_ready(List<Properts> prop_common, List<Properts> prop_specificationt);
}
