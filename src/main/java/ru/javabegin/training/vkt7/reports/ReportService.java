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
    List<Object> getObject_ns (List<Operation> operationList);

    List<DataObject> getDataObject (List<Operation> operationList);



    /// Вспомогательные сортировка списка ...
    List<String> sort (List<String> list);

}
