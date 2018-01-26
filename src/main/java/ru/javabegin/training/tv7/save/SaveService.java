package ru.javabegin.training.tv7.save;

import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.initDataClass.Parametr;

import java.util.List;

public interface SaveService {
    Operationtv7 saveDay(List<Parametr> parametrList);
    Operationtv7T saveTotal(List<Parametr> parametrList);
    String find(List<Parametr> parametrList, String parametr);

}
