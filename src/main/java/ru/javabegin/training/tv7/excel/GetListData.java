package ru.javabegin.training.tv7.excel;

import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;

import java.util.List;

public interface GetListData {


    /**
     *
     * @param operationtv7List Список с измерениями АРХИВНЫЕ (суточные, месячные) для которых делается подготовка к Excel
     * @param type - TV1/TV2/ALL
     * @return
     */
    List<DataObjectTv7> getTv7List(List<Operationtv7> operationtv7List, String type);

    /**
     *
     * @param operationtv7TList Список с измерениями ИТОГОВЫЕ для которых делается подготовка к Excel
     * @param type - TV1/TV2/ALL
     * @return
     */
    List<DataObjectTv7> getTv7TList(List<Operationtv7T> operationtv7TList, String type);
}
