package ru.javabegin.training.vkt7.reports;

import ru.javabegin.training.vkt7.entities.Operation;

import java.util.List;

/**
 * Created by Николай on 29.10.2017.
 */
public interface ReportService {


    List<DataObject> getDataObject (List<Operation> operationList);
    List<Object> getObject (List<Operation> operationList);

    /// Вспомогательные сортировка списка ...
    List<String> sort (List<String> list);

}
