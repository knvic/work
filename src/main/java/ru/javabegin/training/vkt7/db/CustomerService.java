package ru.javabegin.training.vkt7.db;

import ru.javabegin.training.vkt7.entities.Customer;

import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */
public interface CustomerService {

    List<Customer> findAll();
}
