package ru.javabegin.training.vkt7.reports;

import ru.javabegin.training.vkt7.entities.Operation;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.List;

/**
 * Created by Николай on 29.10.2017.
 */
public interface ReportService {



    //void createReport(DefaultTableModel tableModel, String file);
    void createReport1(List<ReportCustomers>  list);
    void createReport();


    DefaultTableModel prepare_data();
    List<Object> getObject_fullround (List<Operation> operationList);

    List<Object> getObject (List<Operation> operationList);

    /////основное

    /**
     * Получает список Operation. ПО каждому Operation берутся измерения Measurements
     * Формируется массив из объектов. Hashmap<Название колонки, <название колонки, значение в BigDecimal>>
     *     Возвращает список из двух объектов
     *     1. List<DataObject>
     *     2. List<String> список колонок
     *
     *
     * @param operationList
     * @return
     * Список из
     *
     */
    List<Object> getObject_ns (List<Operation> operationList);  ///695


    List<DataObject_str> getObject_ns_to_Str(List<DataObject> dataObjectList, List<String> id_coil);  ///1428


    /**
     * Входные данные сформированный масссив List<DataObject> и набор колонок List<String>
     * Вoзвращает массив объектов: List<Object>
     *
     *     DataObject sum объект с суммами
     *     DataObject average объект сo средними значениями
     *     DataObject_str sum_str объект с суммами в формате  String
     *     DataObject_str average_str объект сo средними значениями String
     *
     *
     * @param dataObjectList
     * @param id_col
     * @return
     */
    List<Object> getCalculations(List<DataObject> dataObjectList,List<String> id_col);  ///



    /**
     * Входные данные сформированный масссив List<Operation> с единственным значением TOTAL_CURRENT  за предыдущий месяц
     * и обзъект  DataObject с семмированными значениями измерений
     *
     * Вoзвращает массив объектов: List<Object>
     *
     *     List<DataObject> total_current //лист из трех объектов в формате DataObject значение total_moth, сумма всех значений, итоговое значение
     *     List<DataObject_str> total_current_str //лист из трех объектов в формате DataObject_str значение total_moth, сумма всех значений, итоговое значение
     *     List <String> list_calc_total/// Переlеланный список колонок под измерения итоговые текущие
     *     *
     * @param operationList
     * @param sum
     * @return
     */
    List<Object> getCalculations_total(List<Operation> operationList,DataObject sum);///




    //// конец основное

    List<DataObject> getDataObject (List<Operation> operationList);




    /// Вспомогательные сортировка списка ...
    List<String> sort (List<String> list);

}
