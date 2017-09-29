package ru.javabegin.training.vkt7.db;

import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Result;

import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */
public interface CustomerService {

    List<Customer> findAll();
    Customer save(Customer customer);
    List<Result> r_findAll();
     Result save(Result result);
}
