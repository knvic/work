package ru.javabegin.training.vkt7.db;

import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.entities.Result;
import ru.javabegin.training.vkt7.reports.DataCustomer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */
public interface CustomerService {

    List<Customer> findAll();
    List<Customer> findAllWithDetail();
    List<Customer> findAllWithDetailTv7();
    Customer save(Customer customer);
    void update(Customer customer);
    void delete (Customer customer);
    Customer findById(Long id);
    Customer findByIdTv7(Long id);
    Customer findByIdTv7T(Long id);

    ///////////// API Criteria ///////////////
   // List<Customer> findByCriteriaQuery_total_moth(Long id); /// Временный для разработки


    List<Customer> findCustomerLikeFirstName(String name);


    List<Operation> findOperationByModemCustomer(String modem);
    List<Operation> findOperation_total_moth(Long id, Timestamp ts, String type, String status);
    List<Operation> findOperation_daily(Long id, Timestamp ts, String type, String status);
    List<Operation> findOperation_betwen_data(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status);
    void delete_clone_Operation(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status);
    void update_del_clone(Long id_customer, Long id_opertion);


    List<Operation> findOperationByModemTimeCustomer(String modem, Timestamp date);
    List<Operation> findOperationByIdCustomer(Long id);


    List<Operation> getOperationsByCustomerId(Long id);

    /**
     * Удаляет измерение по ID Customer и ID Operation
     * @param customerID
     * @param operationID
     */
    void deleteOperation(Long customerID, Long operationID);

    List<DataCustomer> customerOperationStatus(); //599

    void test_customerOperationStatus();//687

    void deleteOperationQualityErr();


    List<Operationtv7> findOperationtv7ByDate(String type, Long idCustomer, LocalDateTime ldt);

    List<Operationtv7T> findOperationtv7TByDate(String type, Long idCustomer, LocalDateTime ldt);

    List<Customer> findTv7Customers();

    //List<Operationtv7> findOperationtv7All();




}
