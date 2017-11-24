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
     *
     * @param operationList
     * @return
     * Список из
     *
     */
    List<Object> getObject_ns (List<Operation> operationList);  ///695


    List<DataObject_str> getObject_ns_to_Str(List<DataObject> dataObjectList, List<String> id_coil);  ///1428

      List<Object> getCalculations(List<DataObject> dataObjectList,List<String> id_col);  ///

    void getCalculations_total(List<Operation> operationList,DataObject sum);///

    //// конец основное

    List<DataObject> getDataObject (List<Operation> operationList);




    /// Вспомогательные сортировка списка ...
    List<String> sort (List<String> list);

}
