package ru.javabegin.training.vkt7.db;

import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.entities.Result;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */
public interface CustomerService {

    List<Customer> findAll();
    List<Customer> findAllWithDetail();
    Customer save(Customer customer);
    void delete (Customer customer);
    Customer findById(Long id);

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




}
